package net.mine_diver.manymoreblocks.mixin.packet;

import net.minecraft.packet.play.ItemEntitySpawn0x15S2CPacket;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;

@Mixin(ItemEntitySpawn0x15S2CPacket.class)
public class MixinItemEntitySpawn0x15S2CPacket {

    @Shadow public int itemid;

    @Inject(
            method = "read(Ljava/io/DataInputStream;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/packet/play/ItemEntitySpawn0x15S2CPacket;itemid:I",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void unsignId(DataInputStream in, CallbackInfo ci) {
        itemid = Short.toUnsignedInt((short) itemid);
    }
}
