package net.mine_diver.manymoreblocks.mixin.packet;

import net.mine_diver.manymoreblocks.api.util.MathHelper;
import net.minecraft.packet.play.BlockChange0x35S2CPacket;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.*;

@Mixin(BlockChange0x35S2CPacket.class)
public class MixinBlockChange0x35S2CPacket {

    @Shadow public int blockId;

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/packet/play/BlockChange0x35S2CPacket;blockId:I",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void readMMB(DataInputStream in, CallbackInfo ci) throws IOException {
        blockId = MathHelper.getBlockID(blockId, in.read());
    }

    @Inject(
            method = "write(Ljava/io/DataOutputStream;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/io/DataOutputStream;write(I)V",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )
    private void writeMMB(DataOutputStream out, CallbackInfo ci) throws IOException {
        out.write(MathHelper.getMMBID((short) blockId));
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
