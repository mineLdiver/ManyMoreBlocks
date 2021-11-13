package net.mine_diver.manymoreblocks.mixin.packet;

import net.minecraft.packet.play.SlotClicked0x66C2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.*;

@Mixin(SlotClicked0x66C2SPacket.class)
public class MixinSlotClicked0x66C2SPacket {

//    @Unique
//    private int realId;
//
//    @ModifyVariable(
//            method = "read(Ljava/io/DataInputStream;)V",
//            index = 2,
//            at = @At("STORE")
//    )
//    private short readId(short id, DataInputStream in) throws IOException {
//        realId = Short.toUnsignedInt(id);
//        return (short) (in.readBoolean() ? -1 : 1);
//    }

    @ModifyVariable(
            method = "read(Ljava/io/DataInputStream;)V",
            index = 2,
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
                    ordinal = 2,
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
                    ordinal = 3,
                    shift = At.Shift.AFTER
            )
    )
    private void writeNotEmpty(DataOutputStream out, CallbackInfo ci) throws IOException {
        out.writeBoolean(false);
    }

    @Inject(
            method = "length()I",
            at = @At("RETURN"),
            cancellable = true
    )
    private void modifyLength(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValueI() + 1);
    }
}
