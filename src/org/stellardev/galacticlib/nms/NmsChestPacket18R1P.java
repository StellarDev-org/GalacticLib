package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.nms.NmsBasics;
import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class NmsChestPacket18R1P extends NmsChestPacket {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsChestPacket18R1P i = new NmsChestPacket18R1P();
    public static NmsChestPacket18R1P get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    // net.minecraft.server.BlockPosition
    protected Class<?> classBlockPosition;
    // ...(int, int, int)
    protected Constructor<?> constructorBlockPosition;

    // net.minecraft.server.Block
    protected Class<?> classBlock;

    // net.minecraft.server.Blocks
    protected Class<?> classBlocks;
    // ...#CHEST
    protected Field fieldBlocksChest;
    // ...#ENDER_CHEST
    protected Field fieldBlocksEnderChest;
    // ...#TRAPPED_CHEST
    protected Field fieldBlocksTrappedChest;

    // net.minecraft.server.PacketPlayOutBlockAction
    protected Class<?> classPacketPlayOutBlockAction;
    // ...#(BlockPosition, Blocks, int, int)
    protected Constructor<?> constructorPacketPlayOutBlockAction;


    @Override
    public void setup() throws Throwable {
        this.classBlockPosition = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("BlockPosition");
        this.constructorBlockPosition = ReflectionUtil.getConstructor(this.classBlockPosition, int.class, int.class, int.class);

        this.classBlock = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Block");

        this.classBlocks = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Blocks");
        this.fieldBlocksChest = ReflectionUtil.getField(this.classBlocks, "CHEST");
        this.fieldBlocksEnderChest = ReflectionUtil.getField(this.classBlocks, "ENDER_CHEST");
        this.fieldBlocksTrappedChest = ReflectionUtil.getField(this.classBlocks, "TRAPPED_CHEST");

        this.classPacketPlayOutBlockAction = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutBlockAction");
        this.constructorPacketPlayOutBlockAction = ReflectionUtil.getConstructor(this.classPacketPlayOutBlockAction, this.classBlockPosition, this.classBlock, int.class, int.class);
    }

    @Override
    public void playOpenChest(Player player, Block block) {
        playPacket(block, player, 1);
    }

    @Override
    public void playCloseChest(Player player, Block block) {
        playPacket(block, player, 0);
    }

    private void playPacket(Block block, Player player, int action) {
        Object blockPosition = ReflectionUtil.invokeConstructor(this.constructorBlockPosition, block.getX(), block.getY(), block.getZ());
        Object blocks;

        switch (block.getType()) {
            case CHEST:
                blocks = ReflectionUtil.getField(this.fieldBlocksChest, null);
                break;
            case ENDER_CHEST:
                blocks = ReflectionUtil.getField(this.fieldBlocksEnderChest, null);
                break;
            case TRAPPED_CHEST:
                blocks = ReflectionUtil.getField(this.fieldBlocksTrappedChest, null);
                break;
            default:
                return;
        }

        Object packet = ReflectionUtil.invokeConstructor(this.constructorPacketPlayOutBlockAction, blockPosition, blocks, 1, action);
        NmsBasics.get().sendPacket(player, packet);
    }
}
