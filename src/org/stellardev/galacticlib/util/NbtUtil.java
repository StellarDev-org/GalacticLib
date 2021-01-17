package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.util.InventoryUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@UtilityClass
public class NbtUtil {

    public ItemStack setKeys(ItemStack itemStack, Map<String, Object> nbt) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        nbt.forEach(nbtItem::setObject);

        return nbtItem.getItem();
    }

    public ItemStack setKey(ItemStack itemStack, String key, String value) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setString(key, value);

        return nbtItem.getItem();
    }
    public ItemStack setKey(ItemStack itemStack, String key, int value) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setInteger(key, value);

        return nbtItem.getItem();
    }
    public ItemStack setKey(ItemStack itemStack, String key, long value) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setLong(key, value);

        return nbtItem.getItem();
    }
    public ItemStack setKey(ItemStack itemStack, String key, double value) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        nbtItem.setDouble(key, value);

        return nbtItem.getItem();
    }

    public boolean hasKey(ItemStack itemStack, String... keys) {
        if(InventoryUtil.isNothing(itemStack)) return false;

        NBTItem nbtItem = new NBTItem(itemStack);
        boolean containsAll = true;

        for (String key : keys) {
            Boolean has = nbtItem.hasKey(key);

            if(has == null || !has) {
                containsAll = false;
                break;
            }
        }

        return containsAll;
    }
    public ItemStack removeKey(ItemStack itemStack, String... keys) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        Arrays.stream(keys).forEach(nbtItem::removeKey);

        return nbtItem.getItem();
    }

    public Set<String> getKeySet(ItemStack itemStack) {
        if(InventoryUtil.isNothing(itemStack)) return new HashSet<>();

        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getKeys();
    }

    public String getKeyStringValue(ItemStack itemStack, String key) {
        if(InventoryUtil.isNothing(itemStack)) return "";

        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getString(key);
    }
    public int getKeyIntValue(ItemStack itemStack, String key) {
        if(InventoryUtil.isNothing(itemStack)) return 0;

        NBTItem nbtItem = new NBTItem(itemStack);

        Integer integer = nbtItem.getInteger(key);

        return integer == null? 0 : integer;
    }
    public long getKeyLongValue(ItemStack itemStack, String key) {
        if(InventoryUtil.isNothing(itemStack)) return 0L;

        NBTItem nbtItem = new NBTItem(itemStack);

        Long longg = nbtItem.getLong(key);

        return longg == null? 0 : longg;
    }
    public double getKeyDoubleValue(ItemStack itemStack, String key) {
        if(InventoryUtil.isNothing(itemStack)) return 0D;

        NBTItem nbtItem = new NBTItem(itemStack);

        Double doublee = nbtItem.getDouble(key);

        return doublee == null? 0 : doublee;
    }
}
