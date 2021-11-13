package net.mine_diver.manymoreblocks.mixin.packet;

import net.minecraft.packet.play.EntityEquipment0x5S2CPacket;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.*;

@Mixin(EntityEquipment0x5S2CPacket.class)
public class MixinEntityEquipment0x5S2CPacket {

    @Shadow public int itemId;

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/packet/play/EntityEquipment0x5S2CPacket;itemId:I",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void readId(DataInputStream in, CallbackInfo ci) {
        itemId = Short.toUnsignedInt((short) itemId);
    }

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At("RETURN")
    )
    private void readIfItemEmpty(DataInputStream in, CallbackInfo ci) throws IOException {
        if (in.readBoolean())
            itemId = -1;
    }

    @Inject(
            method = "write(Ljava/io/DataOutputStream;)V",
            at = @At("RETURN")
    )
    private void writeIfItemEmpty(DataOutputStream out, CallbackInfo ci) throws IOException {
        out.writeBoolean(itemId < 0);
    }

    @Inject(
            method = "length()I",
            at = @At("RETURN"),
            cancellable = true
    )
    private void addByteLength(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValueI() + 1);
    }
}
