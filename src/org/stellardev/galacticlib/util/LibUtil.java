package org.stellardev.galacticlib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

@UtilityClass
public class LibUtil {

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

}
