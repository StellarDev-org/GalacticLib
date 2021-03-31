package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.util.InventoryUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.entity.Conf;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class FortuneUtil {

    public void applyFortuneEffect(int fortuneLevel, ItemStack itemStack) {
        applyFortuneEffect(fortuneLevel, itemStack, false);
    }

    // Fortune system provided by clip
    public void applyFortuneEffect(int fortuneLevel, ItemStack itemStack, boolean bypassMaterialCheck) {
        if(InventoryUtil.isNothing(itemStack)) return;

        if(!Conf.get().fortuneMaterials.contains(itemStack.getType())) {
            if(!bypassMaterialCheck) return;
        }

        Random r = ThreadLocalRandom.current();
        boolean random = Conf.get().randomFortuneDropAmounts;
        double multiplier = Conf.get().fortuneLevelMultiplier;
        int min = Conf.get().fortuneMinDrops;
        int max = Conf.get().fortuneMaxDrops;
        int modifier = (int) Conf.get().fortuneModifier;
        int amount = (int) (Math.floor((double) fortuneLevel * multiplier) + 1.0D);

        if(amount > max) {
            if(random) {
                amount = r.nextInt(max) + min;
            } else {
                amount = max + min;
            }
        } else if(random) {
            amount = r.nextInt(amount) + min;
        } else {
            amount += min;
        }

        if(modifier > 0) {
            modifier = r.nextInt(modifier);
        }

        if(modifier <= 0) {
            itemStack.setAmount(amount);
        } else if(r.nextBoolean()) {
            itemStack.setAmount(amount + modifier);
        } else {
            itemStack.setAmount(amount);
        }

        itemStack.setAmount(ThreadLocalRandom.current().nextInt(fortuneLevel) + 1);
    }

}
