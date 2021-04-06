package org.stellardev.galacticlib.integration.luckperms;

import com.massivecraft.massivecore.Engine;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedPermissionData;
import net.luckperms.api.context.ContextManager;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.model.data.DataType;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EngineLuckPerms extends Engine {

    private static final EngineLuckPerms instance = new EngineLuckPerms();
    public static EngineLuckPerms get() { return instance; }

    private final LuckPerms luckPermsApi;

    private EngineLuckPerms() {
        this.luckPermsApi = LuckPermsProvider.get();
    }

    public int getPermissionPoolTop(UUID uuid, String permissionBase, int cap) {
        User user = getUser(uuid);

        if(user == null) return -1;

        for(int i = cap; i >= 0; i--) {
            if(hasPermission(user, permissionBase + i)) return i;
        }

        return -1;
    }

    public boolean hasPermission(OfflinePlayer offlinePlayer, String permission) {
        User user = getUser(offlinePlayer.getUniqueId());

        if(user == null) return false;

        return hasPermission(user, permission);
    }

    public void givePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
        User user = getUser(offlinePlayer.getUniqueId());

        if(user == null) return;

        setPermission(user, permission, durationMs, true);
    }

    public void removePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
        User user = getUser(offlinePlayer.getUniqueId());

        if(user == null) return;

        setPermission(user, permission, durationMs, false);
    }

    public String getPrefix(Player player) {
        User user = getUser(player.getUniqueId());

        if(user == null) return null;

        ContextManager contextManager = this.luckPermsApi.getContextManager();
        ImmutableContextSet staticContexts = contextManager.getStaticContext();
        ImmutableContextSet contexts = contextManager.getContext(user).orElse(null);

        if(contexts == null) {
            contexts = staticContexts;
        }

        return user.getCachedData().getMetaData(QueryOptions.contextual(contexts)).getPrefix();
    }

    public String getSuffix(Player player) {
        User user = getUser(player.getUniqueId());

        if(user == null) return null;

        ContextManager contextManager = this.luckPermsApi.getContextManager();
        ImmutableContextSet staticContexts = contextManager.getStaticContext();
        ImmutableContextSet contexts = contextManager.getContext(user).orElse(null);

        if(contexts == null) {
            contexts = staticContexts;
        }

        return user.getCachedData().getMetaData(QueryOptions.contextual(contexts)).getSuffix();
    }

    public String getRankPrefix(Player player) {
        User user = getUser(player.getUniqueId());

        if(user == null) return null;

        String primaryGroup = user.getPrimaryGroup();
        Group group = this.luckPermsApi.getGroupManager().getGroup(primaryGroup);

        if(group == null) return null;

        return group.getDisplayName();
    }

    private User getUser(UUID uuid) {
        UserManager userManager = this.luckPermsApi.getUserManager();
        User user = userManager.getUser(uuid);

        if(user == null) {
            CompletableFuture<User> userFuture = userManager.loadUser(uuid);

            try {
                user = userFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        }

        return user;
    }

    private boolean hasPermission(User user, String permission) {
        ContextManager contextManager = this.luckPermsApi.getContextManager();

        ImmutableContextSet staticContexts = contextManager.getStaticContext();
        ImmutableContextSet contexts = contextManager.getContext(user).orElse(null);

        if(contexts == null) {
            contexts = staticContexts;
        }

        CachedPermissionData permissionData = user.getCachedData().getPermissionData(QueryOptions.contextual(contexts));

        return permissionData.checkPermission(permission).asBoolean();
    }

    private void setPermission(User user, String permission, Long durationMs, boolean add) {
        Node node;

        if(durationMs != null) {
            node = Node.builder(permission).expiry(durationMs, TimeUnit.MILLISECONDS).build();
        } else {
            node = Node.builder(permission).build();
        }

        if(add) {
            user.getData(DataType.NORMAL).add(node);
        } else {
            user.getData(DataType.NORMAL).remove(node);
        }

        this.luckPermsApi.getUserManager().saveUser(user);
    }

}
