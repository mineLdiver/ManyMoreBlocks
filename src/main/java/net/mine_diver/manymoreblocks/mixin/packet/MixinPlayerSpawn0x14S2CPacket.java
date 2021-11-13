package net.mine_diver.manymoreblocks.mixin.packet;

import net.minecraft.packet.play.PlayerSpawn0x14S2CPacket;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;

@Mixin(PlayerSpawn0x14S2CPacket.class)
public class MixinPlayerSpawn0x14S2CPacket {

    @Shadow public int heldItemId;

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/packet/play/PlayerSpawn0x14S2CPacket;heldItemId:I",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void readId(DataInputStream in, CallbackInfo ci) {
        heldItemId = Short.toUnsignedInt((short) heldItemId);
    }
}
