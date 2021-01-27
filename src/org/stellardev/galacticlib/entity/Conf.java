package org.stellardev.galacticlib.entity;

import com.massivecraft.massivecore.store.Entity;

public class Conf extends Entity<Conf> {

    private static Conf i;
    public static Conf get() { return i; }
    public static void set(Conf conf) { i = conf; }

    public String msgInventoryFull = "&7&oYour inventory was full so the item was dropped at your feet.";

    public String msgInvalidBoolean = "&e&lGalacticLib &8» &cThe boolean you have entered is invalid. Please use either &ftrue&c or &ffalse&c.";
    public String msgEnabledDisplay = "&aenabled";
    public String msgDisabledDisplay = "&cdisabled";

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

    public String msgTeleportingDelayObject = "&e&lGalacticLib &8» &aTeleporting to &f%s&a in &f%ss&a unless you move.";
    public String msgTeleportingDelayNoObject = "&e&lGalacticLib &8» &aTeleporting in &f%ss&a unless you move.";
    public String msgTeleportingNow = "&e&lGalacticLib &8» &aTeleported to &f%s&a.";

    public String msgCooldown = "&e&lGalacticLib &8» &cYou cannot use that for another &f%s&c.";

    public String failedGuiDisplay = "&c&lERROR 404";

    public long guiClickThrottleDelayMs = 200L;

    //--------------------------//
    // SQL SETTINGS
    //--------------------------//

    public String sqlDatabaseIp = "172.18.0.1:3306";

    public boolean sqlLogPool = true;
    public long sqlLogPoolDelayTick = 200L;

}
