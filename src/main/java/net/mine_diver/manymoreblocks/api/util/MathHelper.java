package net.mine_diver.manymoreblocks.api.util;

public class MathHelper {

    public static byte getVanillaByte(short blockID) {
        return (byte) blockID;
    }

    public static int getMMBID(short blockID) {
        return Byte.toUnsignedInt((byte) (blockID >> 8));
    }

    public static int getBlockID(int vanillaID, int mmbID) {
        return (mmbID << 8) + vanillaID;
    }
}
