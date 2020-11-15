package org.stellardev.galacticlib.coll;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.ReflectionUtil;

import java.lang.reflect.Method;

public class ConfigurationColl<T extends Entity<T>> extends Coll<T> {

    private final Class<T> clazz;

    public ConfigurationColl(String folderName, Class<T> clazz) {
        super(folderName);

        this.clazz = clazz;
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);

        if(!active) return;

        Method method = ReflectionUtil.getMethod(this.clazz, "set", this.clazz);

        if(method == null) return;

        ReflectionUtil.invokeMethod(method, null, this.get(MassiveCore.INSTANCE, true));
    }
}
