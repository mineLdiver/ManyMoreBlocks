package net.mine_diver.manymoreblocks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.packet.play.MapChunk0x33S2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MapChunk0x33S2CPacket.class)
public class MixinMapChunk0x33S2CPacket {

    @ModifyConstant(
            method = "<init>(IIIIIILnet/minecraft/level/Level;)V",
            constant = @Constant(intValue = 5)
    )
    @Environment(EnvType.SERVER)
    private int modifyServerSize(int constant) {
        return 6;
    }

    @ModifyConstant(
            method = "read(Ljava/io/DataInputStream;)V",
            constant = @Constant(intValue = 5)
    )
    @Environment(EnvType.CLIENT)
    private int modifyClientSize(int constant) {
        return 6;
    }
}
