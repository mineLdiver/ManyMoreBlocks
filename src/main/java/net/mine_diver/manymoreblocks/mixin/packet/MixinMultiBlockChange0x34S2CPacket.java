package net.mine_diver.manymoreblocks.mixin.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.manymoreblocks.api.packet.MultiBlockChange0x34S2CPacketMMB;
import net.mine_diver.manymoreblocks.api.util.MathHelper;
import net.minecraft.level.Level;
import net.minecraft.level.chunk.Chunk;
import net.minecraft.packet.play.MultiBlockChange0x34S2CPacket;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;

@Mixin(MultiBlockChange0x34S2CPacket.class)
public class MixinMultiBlockChange0x34S2CPacket implements MultiBlockChange0x34S2CPacketMMB {

    @Shadow public int arraySize;
    @Unique
    private byte[] mmbArray;

    @Inject(
            method = "<init>(II[SILnet/minecraft/level/Level;)V",
            at = @At("RETURN")
    )
    @Environment(EnvType.SERVER)
    private void addMMBBlocks(int i, int j, short[] ss, int k, Level arg, CallbackInfo ci) {
        this.mmbArray = new byte[k];
        Chunk var6 = arg.getChunkFromCache(i, j);
        for(int var7 = 0; var7 < k; ++var7) {
            int var8 = ss[var7] >> 12 & 15;
            int var9 = ss[var7] >> 8 & 15;
            int var10 = ss[var7] & 255;
            this.mmbArray[var7] = (byte) MathHelper.getMMBID((short) var6.getTileId(var8, var10, var9));
        }
    }

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/packet/play/MultiBlockChange0x34S2CPacket;typeArray:[B",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void initMMBBlocks(DataInputStream in, CallbackInfo ci) {
        mmbArray = new byte[arraySize];
    }

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/DataInputStream;readFully([B)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void readMMBBlocks(DataInputStream in, CallbackInfo ci) throws IOException {
        in.readFully(mmbArray);
    }

    @Inject(
            method = "write(Ljava/io/DataOutputStream;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/DataOutputStream;write([B)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void writeMMBBlocks(DataOutputStream out, CallbackInfo ci) throws IOException {
        out.write(mmbArray);
    }

    @ModifyConstant(
            method = "length()I",
            constant = @Constant(intValue = 4)
    )
    private int modifySize(int constant) {
        return 5;
    }

    @Override
    @Unique
    public byte[] getMMBArray() {
        return mmbArray;
    }
}
