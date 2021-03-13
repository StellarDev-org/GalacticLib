package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.nms.NmsSkullMeta;
import com.massivecraft.massivecore.util.MUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.stellardev.galacticlib.nms.NmsSkullTexture;

import java.util.Map;
import java.util.UUID;

@UtilityClass
public class SkullUtil {

    private final Map<EntityType, String> MOB_SKULL_TEXTURES = MUtil.map(
            EntityType.ENDERMITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWExYTA4MzFhYTAzYWZiNDIxMmFkY2JiMjRlNWRmYWE3ZjQ3NmExMTczZmNlMjU5ZWY3NWE4NTg1NSJ9fX0=",
            EntityType.SILVERFISH, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTI4ZTEwNjA5Yzc0NDlmYzdhYmM4MzJiMzBjYWE0YTBjZDlkZTZmMjU4NjA5N2M5NmMxYzhjNmU1Yzk1MDhmNyJ9fX0=",
            EntityType.CREEPER, "eyJ0aW1lc3RhbXAiOjE1NTM1NjcyMjcwMjksInByb2ZpbGVJZCI6IjA1N2IxYzQ3MTMyMTQ4NjNhNmZlODg4N2Y5ZWMyNjVmIiwicHJvZmlsZU5hbWUiOiJNSEZfQ3JlZXBlciIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iYTVlOTU3MzVhM2YzNzcyYjFiNDg1ZTE1MDI4MDdhZTM5NmE3MmM2MWJmZDM2YWI0MWZhNzFiZWMyZjY0YWEyIn19fQ==",
            EntityType.BLAZE, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgxNjM5NjcsInByb2ZpbGVJZCI6IjRjMzhlZDExNTk2YTRmZDRhYjFkMjZmMzg2YzFjYmFjIiwicHJvZmlsZU5hbWUiOiJNSEZfQmxhemUiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA2ZTM0MmY5MGVjNTM4YWFhMTU1MmIyMjRmMjY0YTA0MDg0MDkwMmUxMjZkOTFlY2U2MTM5YWE1YjNjN2NjMyJ9fX0=",
            EntityType.CAVE_SPIDER, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgxODIxMzcsInByb2ZpbGVJZCI6ImNhYjI4NzcxZjBjZDRmZTdiMTI5MDJjNjllYmE3OWE1IiwicHJvZmlsZU5hbWUiOiJNSEZfQ2F2ZVNwaWRlciIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83N2IwNzA2M2E2ODc0ZmEzZTIyNTQ4ZTAyMDYyYmQ3MzNjMjU4ODU5Mjk4MDk2MjQxODBhZWJiODUxNTU3ZjZhIn19fQ==",
            EntityType.CHICKEN, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgyMDExODUsInByb2ZpbGVJZCI6IjkyZGVhZmE5NDMwNzQyZDliMDAzODg2MDE1OThkNmMwIiwicHJvZmlsZU5hbWUiOiJNSEZfQ2hpY2tlbiIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85MTZiOGU5ODM4OWM1NDFiYjM2NDUzODUwYmNiZDFmN2JjNWE1N2RhNjJkY2M1MDUwNjA0MDk3MzdlYzViNzJhIn19fQ==",
            EntityType.COW, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgyMTgwNzcsInByb2ZpbGVJZCI6ImYxNTliMjc0YzIyZTQzNDBiN2MxNTJhYmRlMTQ3NzEzIiwicHJvZmlsZU5hbWUiOiJNSEZfQ293IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2QwZTRlNmZiZjVmM2RjZjk0NDIyYTFmMzE5NDQ4ZjE1MjM2OWQxNzlkYmZiY2RmMDBlNWJmZTg0OTVmYTk3NyJ9fX0=",
            EntityType.ENDERMAN, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgyMzU4MjgsInByb2ZpbGVJZCI6IjQwZmZiMzcyMTJmNjQ2NzhiM2YyMjE3NmJmNTZkZDRiIiwicHJvZmlsZU5hbWUiOiJNSEZfRW5kZXJtYW4iLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIwOWEzNzUyNTEwZTkxNGIwYmRjOTA5NmIzOTJiYjM1OWY3YThlOGE5NTY2YTAyZTdmNjZmYWZmOGQ2Zjg5ZSJ9fX0=",
            EntityType.GHAST, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgyNjE2NzcsInByb2ZpbGVJZCI6IjA2MzA4NWE2Nzk3ZjQ3ODViZTFhMjFjZDc1ODBmNzUyIiwicHJvZmlsZU5hbWUiOiJNSEZfR2hhc3QiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGE0ZTQyZWIxNWEwODgxM2E2YTZmNjFmMTBhYTI4ODAxOWZhMGZhZTEwNmEyOTUzZGRiNDZmNzdlZTJkNzdmIn19fQ==",
            EntityType.IRON_GOLEM, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgyNzkwMzIsInByb2ZpbGVJZCI6Ijc1N2Y5MGIyMjM0NDRiOGQ4ZGFjODI0MjMyZTJjZWNlIiwicHJvZmlsZU5hbWUiOiJNSEZfR29sZW0iLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM2Y2Q3MjAyYzM0ZTc4ZjMwNzMwOTAzNDlmN2Q5NzNiMjg4YWY1ZTViNzMzNGRkMjQ5MDEwYjNmMjcwNzhmOSJ9fX0=",
            EntityType.SLIME, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgzMDUxNTgsInByb2ZpbGVJZCI6Ijg3MGFiYTkzNDBlODQ4YjM4OWM1MzJlY2UwMGQ2NjMwIiwicHJvZmlsZU5hbWUiOiJNSEZfU2xpbWUiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjMjdiMDEzZjFiZjMzNDQ4NjllODFlNWM2MTAwMjdiYzQ1ZWM1Yjc5NTE0ZmRjOTZlMDFkZjFiN2UzYTM4NyJ9fX0=",
            EntityType.MUSHROOM_COW, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgzMjY4NDMsInByb2ZpbGVJZCI6ImE0NjgxN2Q2NzNjNTRmM2ZiNzEyYWY2YjNmZjQ3Yjk2IiwicHJvZmlsZU5hbWUiOiJNSEZfTXVzaHJvb21Db3ciLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTIzY2ZjNTU4MjQ1NGZjZjk5MDZmODQxZmRhMmNjNmFlODk2Y2Y0NTU4MjFjNGFkYTE5OThkZTcwODc3Y2M4NiJ9fX0=",
            EntityType.OCELOT, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgzNDMyNjEsInByb2ZpbGVJZCI6IjFiZWU5ZGY1NGY3MTQyYTJiZjUyZDk3OTcwZDNmZWEzIiwicHJvZmlsZU5hbWUiOiJNSEZfT2NlbG90IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzExOGI2Yjc5NzgzMzY4ZGZlMDA0Mjk4NTExMGRhMzY2ZjljNzg4YjQ1MDk3YTNlYTZkMGQ5YTc1M2U5ZjQyYzYifX19",
            EntityType.PIG, "eyJ0aW1lc3RhbXAiOjE1NTM1NjgzNTc5NTcsInByb2ZpbGVJZCI6IjhiNTcwNzhiZjFiZDQ1ZGY4M2M0ZDg4ZDE2NzY4ZmJlIiwicHJvZmlsZU5hbWUiOiJNSEZfUGlnIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E1NjJhMzdiODcxZjk2NGJmYzNlMTMxMWVhNjcyYWFhMDM5ODRhNWRjNDcyMTU0YTM0ZGMyNWFmMTU3ZTM4MmIifX19",
            EntityType.PIG_ZOMBIE, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg0MDE0OTksInByb2ZpbGVJZCI6IjE4YTJiYjUwMzM0YTQwODQ5MTg0MmMzODAyNTFhMjRiIiwicHJvZmlsZU5hbWUiOiJNSEZfUGlnWm9tYmllIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkxNmQxNjdjNTc0NGVkMTRlYmMwMmY0NDdmMzI2MTQwNTkzNjJiN2QyZWNiODA4ZmYwNjE2NWQyYzM0M2JlZjIifX19",
            EntityType.SHEEP, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg0MTc2NjcsInByb2ZpbGVJZCI6ImRmYWFkNTUxNGU3ZTQ1YTFhNmY3YzZmYzVlYzgyM2FjIiwicHJvZmlsZU5hbWUiOiJNSEZfU2hlZXAiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NhMzhjY2Y0MTdlOTljYTlkNDdlZWIxNWE4YTMwZWRiMTUwN2FhNTJiNjc4YzIyMGM3MTdjNDc0YWE2ZmUzZSJ9fX0=",
            EntityType.SKELETON, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg0MzU3NDUsInByb2ZpbGVJZCI6ImEzZjQyN2E4MThjNTQ5YzVhNGZiNjRjNmUwZTFlMGE4IiwicHJvZmlsZU5hbWUiOiJNSEZfU2tlbGV0b24iLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI3OTVjM2M2ZjM2ZDY3ZGVjZjlhMzE5NWUxMjgwNDBiZWM1MjI2YjA1NWYyYjE2ZDQ2ZmExOWE5MTgwZTAyMyJ9fX0=",
            EntityType.WITHER_SKULL, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg0NTUyMDAsInByb2ZpbGVJZCI6IjdlZDU3MWE1OWZiODQxNmM4YjlkZmIyZjQ0NmFiNWIyIiwicHJvZmlsZU5hbWUiOiJNSEZfV1NrZWxldG9uIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2JhOTZlOWQ3NmJlZDMwMDkwY2U2ZTJkODQyNTk5NjU5NGVlYzZkNjhhYzg4Y2YwNzM1NmU5ODE0ODM0MjQzZWMifX19",
            EntityType.SPIDER, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg0ODM0NjYsInByb2ZpbGVJZCI6IjVhZDU1ZjM0NDFiNjRiZDI5YzMyMTg5ODNjNjM1OTM2IiwicHJvZmlsZU5hbWUiOiJNSEZfU3BpZGVyIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y2MWE0OTU0MWE4MzZhYThmNGY3NmUwZDRjYjJmZjA0ODg4YzYyZjk0MTFlYTEwY2JhY2YxZjJhNTQ0MjQyNDAifX19",
            EntityType.SQUID, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg1MDM4MTQsInByb2ZpbGVJZCI6IjcyZTY0NjgzZTMxMzRjMzZhNDA4YzY2YjY0ZTk0YWY1IiwicHJvZmlsZU5hbWUiOiJNSEZfU3F1aWQiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU4OTEwMWQ1Y2M3NGFhNDU4MDIxYTA2MGY2Mjg5YTUxYTM1YTdkMzRkOGNhZGRmYzNjZGYzYjJjOWEwNzFhIn19fQ==",
            EntityType.VILLAGER, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg1MTk0MjksInByb2ZpbGVJZCI6ImJkNDgyNzM5NzY3YzQ1ZGNhMWY4YzMzYzQwNTMwOTUyIiwicHJvZmlsZU5hbWUiOiJNSEZfVmlsbGFnZXIiLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjRiZDgzMjgxM2FjMzhlNjg2NDg5MzhkN2EzMmY2YmEyOTgwMWFhZjMxNzQwNDM2N2YyMTRiNzhiNGQ0NzU0YyJ9fX0=",
            EntityType.ZOMBIE, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg1Mzc2NjUsInByb2ZpbGVJZCI6ImRhY2EyYzNkNzE5YjQxZjViNjI0ZTQwMzllNmMwNGJkIiwicHJvZmlsZU5hbWUiOiJNSEZfWm9tYmllIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q5N2U0MjU5Mzc5YTA2ZjI0ODQzYzFiYjQyZjJkZjM1YzEzZjgwMWFkMDc5ZjcxNWJkZWQ0ODhkYjhmNTdjMyJ9fX0=",
            EntityType.MAGMA_CUBE, "eyJ0aW1lc3RhbXAiOjE1NTM1Njg1NjEzMzYsInByb2ZpbGVJZCI6IjA5NzJiZGQxNGI4NjQ5ZmI5ZWNjYTM1M2Y4NDkxYTUxIiwicHJvZmlsZU5hbWUiOiJNSEZfTGF2YVNsaW1lIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q5MGQ2MWU4Y2U5NTExYTBhMmI1ZWEyNzQyY2IxZWYzNjEzMTM4MGVkNDEyOWUxYjE2M2NlOGZmMDAwZGU4ZWEifX19",
            EntityType.WITCH, "eyJ0aW1lc3RhbXAiOjE1NTM1NjkzMDk3MzQsInByb2ZpbGVJZCI6ImZlZjg1YzQ5MmZkZjQ3Zjg5MTMyNTUyMDQ2MjQzMjIzIiwicHJvZmlsZU5hbWUiOiJNSEZfV2l0Y2giLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBhNDY3OTliNmNlNGI3ZTI5YThkZWY5ZjU0ZjMwY2M3MDI1ZTk2MzIxNjI1ZjJhYjQwYTlkNzBiODQzNmIyMSJ9fX0=",
            EntityType.GUARDIAN, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBiZjM0YTcxZTc3MTViNmJhNTJkNWRkMWJhZTVjYjg1Zjc3M2RjOWIwZDQ1N2I0YmZjNWY5ZGQzY2M3Yzk0In19fQ=="
    );

    public ItemStack getSkullItem(String texture, String name) {
        return getSkullItem(texture, UUID.randomUUID(), name);
    }

    public ItemStack getSkullItem(String texture, UUID uuid, String name) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        NmsSkullTexture.get().set(skullMeta, name, uuid, texture);
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public ItemStack getSkullItem(OfflinePlayer offlinePlayer) {
        return getSkullItem(offlinePlayer.getUniqueId(), offlinePlayer.getName());
    }

    public ItemStack getSkullItem(UUID uuid, String name) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        NmsSkullTexture.get().set(skullMeta, name, uuid);
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public ItemStack getMobSkull(EntityType entityType) {
        if(!MOB_SKULL_TEXTURES.containsKey(entityType)) return null;

        return getSkullItem(MOB_SKULL_TEXTURES.get(entityType), entityType.name());
    }

    public static String getSkullTexture(ItemStack itemStack) {
        if(!(itemStack.getItemMeta() instanceof SkullMeta)) return null;

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        String owner = NmsSkullMeta.get().getName(skullMeta);

        if(owner != null) return owner;

        return NmsSkullTexture.get().getTexture(skullMeta);
    }
}
