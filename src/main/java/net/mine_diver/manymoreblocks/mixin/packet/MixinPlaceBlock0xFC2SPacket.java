package net.mine_diver.manymoreblocks.mixin.packet;

import net.minecraft.packet.play.PlaceBlock0xFC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.*;

@Mixin(PlaceBlock0xFC2SPacket.class)
public class MixinPlaceBlock0xFC2SPacket {

    @ModifyVariable(
            method = "read(Ljava/io/DataInputStream;)V",
            index = 2,
            at = @At("STORE")
    )
    private int readId(int id, DataInputStream in) throws IOException {
        return in.readBoolean() ? -1 : Short.toUnsignedInt((short) id);
    }

    @Inject(
            method = "write(Ljava/io/DataOutputStream;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/DataOutputStream;writeShort(I)V",
                    ordinal = 0,
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
                    ordinal = 1,
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
