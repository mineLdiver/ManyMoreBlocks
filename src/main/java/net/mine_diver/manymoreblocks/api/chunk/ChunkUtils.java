package net.mine_diver.manymoreblocks.api.chunk;

import net.mine_diver.manymoreblocks.api.util.MathHelper;
import net.minecraft.level.Level;
import net.minecraft.level.chunk.Chunk;

public class ChunkUtils {

    public static Chunk newChunk(Level level, short[] extendedBlocks, int x, int z) {
        Chunk chunk = new Chunk(level, getVanillaBlocks(extendedBlocks), x, z);
        //noinspection ConstantConditions
        ((ChunkMMB) chunk).setRawMMBBlocks(getMMBBlocks(extendedBlocks));
        return chunk;
    }

    public static byte[] getVanillaBlocks(short[] extendedBlocks) {
        int length = extendedBlocks.length;
        byte[] tiles = new byte[length];
        for (int i = 0; i < length; i++)
            tiles[i] = MathHelper.getVanillaByte(extendedBlocks[i]);
        return tiles;
    }

    public static byte[] getMMBBlocks(short[] extendedBlocks) {
        int length = extendedBlocks.length;
        byte[] mmbBlocks = new byte[length >> 1];
        for (int i = 0; i < length; i++) {
            int mmbID = MathHelper.getMMBID(extendedBlocks[i]);
            int var6 = i >> 1;
            mmbBlocks[var6] = (i & 1) == 0 ? (byte)(mmbBlocks[var6] & 240 | mmbID & 15) : (byte)(mmbBlocks[var6] & 15 | (mmbID & 15) << 4);
        }
        return mmbBlocks;
    }
}
