package org.stellardev.galacticlib.util;

import org.bukkit.block.BlockFace;

import java.util.Arrays;

public enum BlockFaceToYaw {

    NORTH(0, BlockFace.NORTH, 180),
    NORTH_NORTH_EAST(1, BlockFace.NORTH_NORTH_EAST, -157),
    NORTH_EAST(2, BlockFace.NORTH_EAST, -135),
    EAST_NORTH_EAST(3, BlockFace.EAST_NORTH_EAST, -112),
    EAST(4, BlockFace.EAST, -90),
    EAST_SOUTH_EAST(5, BlockFace.EAST_SOUTH_EAST, -67),
    SOUTH_EAST(6, BlockFace.SOUTH_EAST, -45),
    SOUTH_SOUTH_EAST(7, BlockFace.SOUTH_SOUTH_EAST, -22),
    SOUTH(8, BlockFace.SOUTH, 0),
    SOUTH_SOUTH_WEST(9, BlockFace.SOUTH_SOUTH_WEST, 22),
    SOUTH_WEST(10, BlockFace.SOUTH_WEST, 45),
    WEST_SOUTH_WEST(11, BlockFace.WEST_SOUTH_WEST, 67),
    WEST(12, BlockFace.WEST, 90),
    WEST_NORTH_WEST(13, BlockFace.WEST_NORTH_WEST, 112),
    NORTH_WEST(14, BlockFace.NORTH_WEST, 135),
    NORTH_NORTH_WEST(15, BlockFace.NORTH_NORTH_WEST, 157);

    private final BlockFace blockFace;
    private final int position;
    private final float yaw;

    BlockFaceToYaw(int position, BlockFace blockFace, float yaw) {
        this.blockFace = blockFace;
        this.position = position;
        this.yaw = yaw;
    }

    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    public float getYaw() {
        return this.yaw;
    }

    public BlockFaceToYaw getNext() {
        return this == NORTH_NORTH_WEST? NORTH : getYawFromPosition(this.position+1);
    }

    public static BlockFaceToYaw getYawFromBlockFace(BlockFace blockFace) {
        return Arrays.stream(values())
                .filter(bFTY -> bFTY.blockFace == blockFace)
                .findFirst()
                .orElse(null);
    }

    public static BlockFaceToYaw getYawFromPosition(int position) {
        return Arrays.stream(values())
                .filter(bFTY -> bFTY.position == position)
                .findFirst()
                .orElse(null);
    }
}
