package org.stellardev.galacticlib.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Base64SerializationUtil {

    private static final Base64SerializationUtil i = new Base64SerializationUtil();
    public static Base64SerializationUtil get() { return i; }

    public ItemStack base64ToItemStack(String serialization) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(serialization));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            return (ItemStack) dataInput.readObject();
        } catch (Exception ignored) {}

        return null;
    }

    public String itemStackToBase64(ItemStack itemStack) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(itemStack);

            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception ignored) {}

        return null;
    }

}
