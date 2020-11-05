package org.stellardev.galacticlib.gui.configuration;

import org.stellardev.galacticlib.item.ItemStackWrapper;

public class Clickable {

    private final ItemStackWrapper displayItem;
    private final String action;
    private final int slot;

    public Clickable(int slot, ItemStackWrapper itemStackWrapper) {
        this(slot, itemStackWrapper, null);
    }

    public Clickable(int slot, ItemStackWrapper itemStackWrapper, String actionLabel) {
        this.slot = slot;
        this.displayItem = itemStackWrapper;
        this.action = actionLabel;
    }

    public int getSlot() {
        return this.slot;
    }

    public ItemStackWrapper getDisplayItem() {
        return this.displayItem;
    }

    public String getAction() {
        return this.action;
    }
}
