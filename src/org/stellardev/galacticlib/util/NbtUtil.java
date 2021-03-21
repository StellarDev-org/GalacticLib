package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.util.InventoryUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@UtilityClass
public class NbtUtil {

    // -------------------------------------------- //
    // SET NBT
    // -------------------------------------------- //

    public ItemStack setSerializedNbt(ItemStack itemStack, Map<String, String> serializedNbt) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        for(Map.Entry<String, String> entry : serializedNbt.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String[] split = value.split(":");
            Object object = split[1];

            switch (split[0]) {
                case "D":
                    itemStack = setKey(itemStack, key, (double) object);
                    break;
                case "S":
                    itemStack = setKey(itemStack, key, (String) object);
                    break;
                case "L":
                    itemStack = setKey(itemStack, key, (long) object);
                    break;
                case "I":
                    itemStack = setKey(itemStack, key, (int) object);
                    break;
            }
        }

        return itemStack;
    }

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

    // -------------------------------------------- //
    // HAS NBT
    // -------------------------------------------- //

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

    // -------------------------------------------- //
    // REMOVE NBT
    // -------------------------------------------- //

    public ItemStack removeKey(ItemStack itemStack, String... keys) {
        if(InventoryUtil.isNothing(itemStack)) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);

        Arrays.stream(keys).forEach(nbtItem::removeKey);

        return nbtItem.getItem();
    }

    // -------------------------------------------- //
    // GET NBT
    // -------------------------------------------- //

    public Set<String> getKeySet(ItemStack itemStack) {
        if(InventoryUtil.isNothing(itemStack)) return new HashSet<>();

        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getKeys();
    }

    public Map<String, Object> getNbtValues(ItemStack itemStack) {
        if(InventoryUtil.isNothing(itemStack)) return new HashMap<>();

        NBTItem nbtItem = new NBTItem(itemStack);
        Map<String, Object> objectMap = new HashMap<>();

        nbtItem.getKeys().forEach(key -> {
            Object object = getValue(nbtItem, key);

            if(object == null) return;

            objectMap.put(key, object);
        });

        return objectMap;
    }

    public Map<String, String> getSerializedNbtValues(ItemStack itemStack) {
        if(InventoryUtil.isNothing(itemStack)) return null;

        Map<String, Object> nbtData = getNbtValues(itemStack);

        if(nbtData.isEmpty()) return null;

        Map<String, String> map = new HashMap<>();

        nbtData.forEach((key, data) -> {
            if(data instanceof Double) map.put(key, "D:" + data);
            else if(data instanceof String) map.put(key, "S:" + data);
            else if(data instanceof Long) map.put(key, "L:" + data);
            else if(data instanceof Integer) map.put(key, "I:" + data);
        });

        return map;
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

    private Object getValue(NBTItem nbtItem, String key) {
        Double doublee = nbtItem.getDouble(key);
        String stringg = nbtItem.getString(key);
        Long longg = nbtItem.getLong(key);
        Integer integer = nbtItem.getInteger(key);

        if(doublee != null) return doublee;
        if(stringg != null) return stringg;
        if(longg != null) return longg;

        return integer;
    }
}
