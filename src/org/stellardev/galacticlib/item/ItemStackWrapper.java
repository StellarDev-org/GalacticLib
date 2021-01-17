package org.stellardev.galacticlib.item;

import com.massivecraft.massivecore.MassiveCore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.stellardev.galacticlib.util.NbtUtil;
import org.stellardev.galacticlib.util.SkullUtil;
import org.stellardev.galacticlib.util.TxtUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
public class ItemStackWrapper {

    private final Material material;
    private final short damage;
    private final int amount;

    @Getter @Setter private SkullWrapper skull;
    @Getter @Setter private MetaWrapper meta;

    public ItemStackWrapper(Material type) {
        this(type, 1);
    }

    public ItemStackWrapper(Material type, int amount) {
        this(type, amount, (short) 0);
    }

    public ItemStackWrapper(Material type, short damage) {
        this(type, 1, damage);
    }

    public ItemStackWrapper(Material type, int amount, short damage) {
        this.material = type;
        this.amount = amount;
        this.damage = damage;
    }

    public ItemStackWrapper(Material type, String displayName) {
        this(type, 1, (short) 0, displayName);
    }

    public ItemStackWrapper(Material type, short damage, String displayName) {
        this(type, 1, damage, displayName);
    }

    public ItemStackWrapper(Material type, int amount, short damage, String displayName) {
        this(type, amount, damage, displayName, null);
    }

    public ItemStackWrapper(Material type, int amount, short damage, List<String> lore) {
        this(type, amount, damage, null, lore);
    }

    public ItemStackWrapper(Material type, int amount, short damage, String displayName, List<String> lore) {
        this(type, amount, damage);

        if(displayName != null || lore != null) {
            MetaWrapper metaWrapper = new MetaWrapper();

            if(displayName != null) metaWrapper.setDisplayName(displayName);
            if(lore != null) metaWrapper.setLore(lore);

            this.meta = metaWrapper;
        }
    }

    public ItemStackWrapper(Material material, int amount, short damage, MetaWrapper metaWrapper) {
        this(material, amount, damage, metaWrapper, null);
    }

    public ItemStackWrapper(Material material, int amount, short damage, SkullWrapper skullWrapper) {
        this(material, amount, damage, null, skullWrapper);
    }

    public ItemStackWrapper(Material material, int amount, short damage, MetaWrapper metaWrapper, SkullWrapper skullWrapper) {
        this(material, amount, damage);

        this.meta = metaWrapper;
        this.skull = skullWrapper;
    }

    public ItemStackWrapper(Material material, short damage, int amount, SkullWrapper skullWrapper, MetaWrapper metaWrapper) {
        this(material, amount, damage);

        this.meta = metaWrapper;
        this.skull = skullWrapper;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        if(this.material != null) stringBuilder.append("material=").append(this.material.name()).append(" ");
        if(this.meta != null) {
            if(this.meta.getDisplayName() != null) stringBuilder.append("displayName=").append(this.meta.getDisplayName()).append(" ");
            if(this.meta.getLore() != null) stringBuilder.append("lore=").append(this.meta.getLore().toString()).append(" ");
            if(this.meta.getEnchants() != null) stringBuilder.append("enchants=").append(this.meta.getEnchants().toString()).append(" ");
            if(this.meta.getItemFlags() != null) stringBuilder.append("itemFlags=").append(this.meta.getItemFlags().toString()).append(" ");
            if(this.meta.getNbtMap() != null) stringBuilder.append("nbtMap=").append(this.meta.getNbtMap().toString()).append(" ");
        }
        if(this.skull != null) {
            if(this.skull.getOwner() != null) stringBuilder.append("skullOwner=").append(this.skull.getOwner()).append(" ");
            if(this.skull.getTexture() != null) stringBuilder.append("skullTexture=").append(this.skull.getTexture()).append(" ");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public ItemStack getBaseItem() {
        try {
            Material material = this.material;

            if(material == null) throw new NullPointerException();
        } catch (NullPointerException ignore) {
            MassiveCore.get().log("material is missing from ItemStackWrapper!");
            MassiveCore.get().log(toString());
            return new ItemStack(Material.AIR);
        }

        try {
            int amount = this.amount;
        } catch (NullPointerException ignore) {
            MassiveCore.get().log("amount is missing from ItemStackWrapper.");
            MassiveCore.get().log(toString());
            return new ItemStack(Material.AIR);
        }

        try {
            short damage = this.damage;
        } catch (NullPointerException ignore) {
            MassiveCore.get().log("damage is missing from ItemStackWrapper.");
            MassiveCore.get().log(toString());
            return new ItemStack(Material.AIR);
        }

        return new ItemStack(this.material, this.amount, this.damage);
    }

    public boolean hasDisplayName() {
        return this.meta != null && this.meta.getDisplayName() != null && !this.meta.getDisplayName().isEmpty();
    }

    public boolean hasDisplayLore() {
        return this.meta != null && this.meta.getLore() != null && !this.meta.getLore().isEmpty();
    }

    public ItemStack applyMeta(ItemStack itemStack, String... replacements) {
        if(this.meta != null) {
            MetaWrapper metaWrapper = this.meta;
            ItemMeta itemMeta = itemStack.getItemMeta();
            String displayName = metaWrapper.getDisplayName();
            List<String> lore = metaWrapper.getLore();
            Map<String, Integer> enchants = metaWrapper.getEnchants();
            Set<ItemFlag> itemFlags = metaWrapper.getItemFlags();
            Map<String, Object> nbt = metaWrapper.getNbtMap();

            if(displayName != null) itemMeta.setDisplayName(TxtUtil.parseAndReplace(displayName, replacements));
            if(lore != null) itemMeta.setLore(TxtUtil.parseAndReplace(lore, replacements));
            if(enchants != null) {
                enchants.forEach((enchant, level) -> {
                    Enchantment enchantment = Enchantment.getByName(enchant);

                    if(enchant == null) return;

                    itemMeta.addEnchant(enchantment, level, true);
                });
            }
            if(itemFlags != null) {
                itemFlags.forEach(itemMeta::addItemFlags);
            }

            itemStack.setItemMeta(itemMeta);

            if(nbt != null) {
                itemStack = NbtUtil.setKeys(itemStack, nbt);
            }
        }

        return itemStack;
    }

    public ItemStack toItemStack(List<String> replacements) {
        return toItemStack(replacements.toArray(new String[0]));
    }

    public ItemStack toItemStack(String... replacements) {
        ItemStack clone = getBaseItem();

        if(clone == null) {
            throw new NullPointerException("clone itemStack is null!");
        }

        if(clone.getType() == Material.AIR) return clone;

        if(this.skull != null) {
            SkullWrapper skullWrapper = this.skull;
            ItemStack itemStack = null;

            if(skullWrapper.getTexture() != null) {
                itemStack = SkullUtil.getSkullItem(skullWrapper.getTexture(), null);
            } else if(skullWrapper.getOwner() != null) {
                itemStack = SkullUtil.getSkullItem(Bukkit.getOfflinePlayer(skullWrapper.getOwner()));
            }

            if(itemStack != null) {
                clone = itemStack;
            }
        }

        if(this.meta != null) {
            MetaWrapper metaWrapper = this.meta;
            ItemMeta itemMeta = clone.getItemMeta();
            String displayName = metaWrapper.getDisplayName();
            List<String> lore = metaWrapper.getLore();
            Map<String, Integer> enchants = metaWrapper.getEnchants();
            Set<ItemFlag> itemFlags = metaWrapper.getItemFlags();
            boolean unbreakable = metaWrapper.isUnbreakable();
            Map<String, Object> nbt = metaWrapper.getNbtMap();

            if(itemMeta == null) {
                throw new NullPointerException("itemMeta is null!");
            }
            if(replacements == null) {
                throw new NullPointerException("replacements is null!");
            }

            if(displayName != null) itemMeta.setDisplayName(TxtUtil.parseAndReplace(displayName, replacements));
            if(lore != null) itemMeta.setLore(TxtUtil.parseAndReplace(lore, replacements));
            if(enchants != null) {
                enchants.forEach((enchant, level) -> {
                    Enchantment enchantment = Enchantment.getByName(enchant);

                    if(enchant == null) return;

                    itemMeta.addEnchant(enchantment, level, true);
                });
            }
            if(itemFlags != null) {
                itemFlags.forEach(itemMeta::addItemFlags);
            }
            itemMeta.spigot().setUnbreakable(unbreakable);

            clone.setItemMeta(itemMeta);


            if(nbt != null) {
                clone = NbtUtil.setKeys(clone, nbt);
            }
        }

        return clone;
    }

    public static ItemStackWrapper toWrapper(ItemStack itemStack) {
        ItemStackWrapper.ItemStackWrapperBuilder itemStackWrapperBuilder = ItemStackWrapper.builder();

        itemStackWrapperBuilder
                .amount(itemStack.getAmount())
                .damage(itemStack.getDurability())
                .material(itemStack.getType());

        if(itemStack.hasItemMeta()) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            MetaWrapper metaWrapper = new MetaWrapper();

            if(itemMeta.hasDisplayName()) metaWrapper.setDisplayName(itemMeta.getDisplayName());
            if(itemMeta.hasLore()) metaWrapper.setLore(itemMeta.getLore());
            if(itemMeta.hasEnchants()) {
                itemMeta.getEnchants().forEach((enchant, level) -> metaWrapper.addEnchant(enchant.getName(), level));
            }
            if(!itemMeta.getItemFlags().isEmpty()) metaWrapper.setItemFlags(itemMeta.getItemFlags());
            if(itemMeta.spigot().isUnbreakable()) metaWrapper.setUnbreakable(true);

            if(itemMeta instanceof SkullMeta) {
                SkullWrapper skullWrapper = new SkullWrapper();
                SkullMeta skullMeta = (SkullMeta) itemMeta;

                if(skullMeta.hasOwner()) {
                    skullWrapper.setOwner(skullMeta.getOwner());
                } else {
                    skullWrapper.setTexture(SkullUtil.getSkullTexture(itemStack));
                }

                itemStackWrapperBuilder.skull(skullWrapper);
            }

            itemStackWrapperBuilder.meta(metaWrapper);
        }

        return itemStackWrapperBuilder.build();
    }

}
