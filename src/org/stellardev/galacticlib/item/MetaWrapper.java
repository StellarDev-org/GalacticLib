package org.stellardev.galacticlib.item;

import lombok.*;
import org.bukkit.inventory.ItemFlag;

import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaWrapper {

    @Getter @Setter private Map<String, Integer> enchants;
    @Getter @Setter private Map<String, String> nbtMap;
    @Getter @Setter private Set<ItemFlag> itemFlags;
    @Getter @Setter private String displayName;
    @Getter @Setter private List<String> lore;
    private Boolean unbreakable;

    public void addEnchant(String enchant, int level) {
        if(this.enchants == null) this.enchants = new HashMap<>();

        this.enchants.put(enchant, level);
    }

    public void addItemFlags(ItemFlag... itemFlags) {
        if(this.itemFlags == null) this.itemFlags = new HashSet<>();

        this.itemFlags.addAll(Arrays.asList(itemFlags));
    }

    public boolean isUnbreakable() {
        return this.unbreakable != null && this.unbreakable;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }
}
