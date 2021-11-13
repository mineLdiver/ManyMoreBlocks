package net.mine_diver.manymoreblocks.api.chunk;

import net.mine_diver.manymoreblocks.api.util.MathHelper;
import net.minecraft.class_257;

public interface ChunkMMB {

    class_257 getMMBBlocks();

    void setRawMMBBlocks(byte[] mmbTiles);

    void setMMBBlocks(class_257 mmbBlocks);

    default int getLightOpacity(int[] lightOpacity, int access, class_257 mmbTiles, int x, int y, int z) {
        return lightOpacity[MathHelper.getBlockID(access, mmbTiles.method_1703(x, y, z))];
    }
}
