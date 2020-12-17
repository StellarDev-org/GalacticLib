package org.stellardev.galacticlib.coll;

import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.Entity;
import org.bukkit.Location;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IDataHandler;

public class DataHandlerColl<T extends Entity<T>> extends Coll<T> {

    public DataHandlerColl(String id) {
        super(id);
    }

    public T getByLocation(Location location) {
        IDataHandler dataHandler = GalacticLib.get().getDataHandler();

        if(dataHandler == null) return null;
        if(!dataHandler.isInValidWorld(location)) return null;

        String id = dataHandler.getEntityHandler(location);

        if(id == null) return null;

        return get(id, true);
    }

}
