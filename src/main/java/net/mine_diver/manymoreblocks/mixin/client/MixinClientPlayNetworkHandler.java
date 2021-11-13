package net.mine_diver.manymoreblocks.mixin.client;

import net.mine_diver.manymoreblocks.api.packet.MultiBlockChange0x34S2CPacketMMB;
import net.mine_diver.manymoreblocks.api.util.MathHelper;
import net.minecraft.level.chunk.Chunk;
import net.minecraft.network.ClientPlayNetworkHandler;
import net.minecraft.packet.play.MultiBlockChange0x34S2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

    @Unique
    private int capturedIndex;

    @Inject(
            method = "onMultiBlockChange(Lnet/minecraft/packet/play/MultiBlockChange0x34S2CPacket;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/packet/play/MultiBlockChange0x34S2CPacket;typeArray:[B",
                    args = "array=get",
                    shift = At.Shift.BEFORE
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void captureIndex(MultiBlockChange0x34S2CPacket packet, CallbackInfo ci, Chunk var2, int var3, int var4, int var5) {
        capturedIndex = var5;
    }

    @ModifyVariable(
            method = "onMultiBlockChange(Lnet/minecraft/packet/play/MultiBlockChange0x34S2CPacket;)V",
            index = 7,
            at = @At("STORE")
    )
    private int modifyTypeId(int type, MultiBlockChange0x34S2CPacket packet) {
        return MathHelper.getBlockID(type, Byte.toUnsignedInt(((MultiBlockChange0x34S2CPacketMMB) packet).getMMBArray()[capturedIndex]));
    }
}
