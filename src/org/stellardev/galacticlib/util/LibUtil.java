package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import com.massivecraft.massivecore.util.Txt;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class LibUtil {

    @SuppressWarnings({"SortedCollectionWithNonComparableKeys", "unchecked"})
    public <K, V> TreeMap<K, V> treeMap(K key1, V value1, Object... objects) {
        TreeMap<K, V> ret = new TreeMap<>();

        ret.put(key1, value1);

        Iterator<Object> iter = Arrays.asList(objects).iterator();
        while (iter.hasNext())
        {
            K key = (K) iter.next();
            V value = (V) iter.next();
            ret.put(key, value);
        }

        return ret;
    }

    @SuppressWarnings("unchecked")
    public <K, V> LinkedHashMap<K, V> linkedMap(K key1, V value1, Object... objects) {
        LinkedHashMap<K, V> ret = new LinkedHashMap<>();

        ret.put(key1, value1);

        Iterator<Object> iter = Arrays.asList(objects).iterator();
        while (iter.hasNext())
        {
            K key = (K) iter.next();
            V value = (V) iter.next();
            ret.put(key, value);
        }

        return ret;
    }

    public boolean callEvent(Event event) {
        Bukkit.getPluginManager().callEvent(event);

        if(event instanceof Cancellable) {
            return !((Cancellable) event).isCancelled();
        } else {
            return true;
        }
    }

    public String getTimeDescription(long millis) {
        String ret = "";

        List<String> unitCountParts = new ArrayList<>();
        double millisLeft = (double) Math.abs(millis);

        for (Map.Entry<String, Long> entry : Txt.unitMillis.entrySet())
        {
            if (unitCountParts.size() == 3 ) break;
            String unitName = entry.getKey();
            long unitSize = entry.getValue();
            long unitCount = (long) Math.floor(millisLeft / unitSize);
            if (unitCount < 1) continue;
            millisLeft -= unitSize*unitCount;
            unitCountParts.add(unitCount+" "+unitName);
        }

        if (unitCountParts.size() == 0) return "0 seconds";

        ret += Txt.implodeCommaAnd(unitCountParts);

        return ret;
    }

    public String getStackName(ItemStack itemStack) {
        String itemName = WordUtils.capitalizeFully(itemStack.getType().name().replace("_", " "));

        try {
            Method asCraftCopy = ReflectionUtil.getMethod(ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asCraftCopy", ItemStack.class);
            Method asNMSCopy = ReflectionUtil.getMethod(ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", ItemStack.class);
            Object craftCopy = ReflectionUtil.invokeMethod(asCraftCopy, null, itemStack);
            Object nmsStack = ReflectionUtil.invokeMethod(asNMSCopy, null, craftCopy);
            itemName = ReflectionUtil.invokeMethod(ReflectionUtil.getMethod(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ItemStack"), "getName"), nmsStack);
            return itemName;
        } catch (Exception var9) {
            return itemName;
        } finally {
            ;
        }
    }
}
