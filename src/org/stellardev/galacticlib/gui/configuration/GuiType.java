package org.stellardev.galacticlib.gui.configuration;

import com.massivecraft.massivecore.MassiveException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.stellardev.galacticlib.entity.Conf;
import org.stellardev.galacticlib.gui.IGuiTypeBuilder;
import org.stellardev.galacticlib.util.TxtUtil;

import java.util.Arrays;
import java.util.List;

public enum GuiType implements IGuiTypeBuilder {

    INVENTORY(9, -1, InventoryType.CHEST),
    HOPPER(5, 1, InventoryType.HOPPER),
    DISPENSER(3, 3, InventoryType.DISPENSER)
    ;

    private final InventoryType inventoryType;
    private final int rowLength, rows;

    GuiType(int rowLength, int rows, InventoryType inventoryType) {
        this.rowLength = rowLength;
        this.rows = rows;

        this.inventoryType = inventoryType;
    }

    public int getRowLength() {
        return this.rowLength;
    }

    public int getRows() {
        return this.rows;
    }

    public InventoryType getInventoryType() {
        return this.inventoryType;
    }

    @Override
    public Inventory build(Player player, String guiName, List<String> guiFormat, String... replacements) throws MassiveException {
        if(guiName == null || guiName.isEmpty()) {
            throw new MassiveException().addMsg(Conf.get().msgGuiNameNotSet);
        }

        if(guiFormat.isEmpty()) {
            throw new MassiveException().addMsg(Conf.get().msgGuiFormatNotSet);
        }

        String firstRow = guiFormat.get(0);
        int rowLength = firstRow.length();
        int rows = guiFormat.size();

        if(rowLength != getRowLength()) {
            throw new MassiveException().addMsg(Conf.get().msgGuiRowLengthNotSame);
        }

        if(getRows() > 0 && rows != getRows()) {
            throw new MassiveException().addMsg(Conf.get().msgGuiTooManyRows);
        }

        if(getRows() > 0) {
            return Bukkit.createInventory(player, getInventoryType(), TxtUtil.parseAndReplace(guiName, replacements));
        } else {
            return Bukkit.createInventory(player, rows * 9, TxtUtil.parseAndReplace(guiName, replacements));
        }
    }

    public static GuiType getFromFormat(List<String> guiFormat) throws MassiveException {
        if(guiFormat.isEmpty()) {
            throw new MassiveException().addMsg(Conf.get().msgGuiFormatNotSet);
        }

        String firstRow = guiFormat.get(0);
        int rowLength = firstRow.length();
        int rows = guiFormat.size();

        return Arrays.stream(values())
                .filter(guiType -> guiType.getRows() == rows && guiType.getRowLength() == rowLength)
                .findFirst()
                .orElse(INVENTORY);
    }
}
