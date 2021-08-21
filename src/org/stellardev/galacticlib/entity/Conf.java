package org.stellardev.galacticlib.entity;

import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.Material;

import java.util.List;

public class Conf extends Entity<Conf> {

    private static Conf i;
    public static Conf get() { return i; }
    public static void set(Conf conf) { i = conf; }

    public String msgInventoryFull = "&7&oYour inventory was full so the item was dropped at your feet.";

    public String msgInvalidBoolean = "&e&lGalacticLib &8» &cThe boolean you have entered is invalid. Please use either &ftrue&c or &ffalse&c.";
    public String msgEnabledDisplay = "&aenabled";
    public String msgDisabledDisplay = "&cdisabled";
    public String msgTrueDisplay = "&atrue";
    public String msgFalseDisplay = "&cfalse";

    public String msgInvalidTime = "&e&lGalacticLib &8» &cThe time format you have provided is invalid. Please use &f1&c, &f1s&c, &f1m&c or &f1h&c.";
    public String msgInvalidMaterial = "&e&lGalacticLib &8» &cThe inputted material is invalid. Please try something like &fDirt&c, &f3&c, &f3:0&c, or &fDirt:0&c.";

    public String msgGuiNameNotSet = "&e&lGalacticLib &8» &cThe gui name is not setup for that gui.";
    public String msgGuiFormatNotSet = "&e&lGalacticLib &8» &cThe gui format is not setup for that gui.";
    public String msgGuiRowLengthNotSame = "&e&lGalacticLib &8» &cThe gui is not setup correctly. Row lengths do not match.";
    public String msgGuiTooManyRows = "&e&lGalacticLib &8» &cThere is too many rows configured for this gui type.";
    public String msgGuiDesignNotSet = "&e&lGalacticLib &8» &cThe gui design is not set! Please contact the developer.";
    public String msgGuiPlayerNotSet = "&e&lGalacticLib &8» &cThe player is not set for this gui.";
    public String msgGuiInventoryNotSet = "&e&lGalacticLib &8» &cThe inventory has not been initialized! Please show a developer this.";
    public String msgGuiLeaveWindowToEdit = "&e&lGalacticLib &8» &cYou cannot do that while you're within a gui window.";

    public String msgTeleportingCancelled = "&e&lGalacticLib &8» &cYour teleportation was cancelled.";
    public String msgTeleportingDelayObject = "&e&lGalacticLib &8» &aTeleporting to &f%s&a in &f%ss&a unless you move.";
    public String msgTeleportingDelayNoObject = "&e&lGalacticLib &8» &aTeleporting in &f%s&a unless you move.";
    public String msgTeleportingNow = "&e&lGalacticLib &8» &aYou have just teleported to &f%s&a.";
    public String msgTeleporting = "&e&lGalacticLib &8» &aYou have just teleported.";

    public String msgCooldown = "&e&lGalacticLib &8» &cYou cannot use that for another &f%s&c.";

    public String msgDataHandlerNotFound = "&e&lGalacticLib &8» &cNo data could be found with that id.";

    public String failedGuiDisplay = "&c&lERROR 404";

    public long guiClickThrottleDelayMs = 200L;

    //--------------------------//
    // SQL SETTINGS
    //--------------------------//

    public String sqlDatabaseIp = "172.18.0.1:3306";

    public boolean sqlLogPool = true;
    public long sqlLogPoolDelayTick = 200L;

    //--------------------------//
    // FORTUNE SETTINGS
    //--------------------------//

    public List<Material> fortuneMaterials = MUtil.list(
            Material.COAL, Material.REDSTONE,
            Material.IRON_INGOT, Material.GOLD_INGOT,
            Material.DIAMOND, Material.EMERALD,
            Material.REDSTONE, Material.QUARTZ
    );

    public boolean randomFortuneDropAmounts = true;
    public double fortuneLevelMultiplier = 1.0D;
    public int fortuneMinDrops = 1;
    public int fortuneMaxDrops = 60;
    public double fortuneModifier = 0.005;

    public boolean sendInventoryFullInActionBar = true;

    public String spawnCommand = "spawn %s";

}
