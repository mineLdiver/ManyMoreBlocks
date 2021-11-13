package net.mine_diver.manymoreblocks.mixin.packet;

import net.minecraft.packet.play.InventoryUpdate0x68S2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;

@Mixin(InventoryUpdate0x68S2CPacket.class)
public class MixinInventoryUpdate0x68S2CPacket {

//    @Unique
//    private int realId;
//
//    @ModifyVariable(
//            method = "read(Ljava/io/DataInputStream;)V",
//            index = 4,
//            at = @At("STORE")
//    )
//    private short readId(short id, DataInputStream in) throws IOException {
//        realId = Short.toUnsignedInt(id);
//        return (short) (in.readBoolean() ? -1 : 1);
//    }

    @ModifyVariable(
            method = "read(Ljava/io/DataInputStream;)V",
            index = 4,
            at = @At("STORE")
    )
    private int readId(int id, DataInputStream in) throws IOException {
        return in.readBoolean() ? -1 : Short.toUnsignedInt((short) id);
    }

//    @ModifyArg(
//            method = "read(Ljava/io/DataInputStream;)V",
//            index = 0,
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/item/ItemInstance;<init>(III)V"
//            )
//    )
//    private int changeId(int original) {
//        return realId;
//    }

    @Inject(
            method = "write(Ljava/io/DataOutputStream;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/DataOutputStream;writeShort(I)V",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )
    private void writeEmpty(DataOutputStream out, CallbackInfo ci) throws IOException {
        out.writeBoolean(true);
    }

    @Inject(
            method = "write(Ljava/io/DataOutputStream;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/DataOutputStream;writeShort(I)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private void writeNotEmpty(DataOutputStream out, CallbackInfo ci) throws IOException {
        out.writeBoolean(false);
    }

    @ModifyConstant(
            method = "length()I",
            constant = @Constant(intValue = 5)
    )
    private int modifyLength(int constant) {
        return 6;
    }
}