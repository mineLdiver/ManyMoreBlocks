package net.mine_diver.manymoreblocks.mixin.client;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {

    @ModifyConstant(
            method = "playLevelEvent(Lnet/minecraft/entity/player/PlayerBase;IIIII)V",
            constant = @Constant(intValue = 8)
    )
    private int getMetaOffset(int constant) {
        return 12;
    }

    @ModifyConstant(
            method = "playLevelEvent(Lnet/minecraft/entity/player/PlayerBase;IIIII)V",
            constant = @Constant(
                    intValue = 255,
                    ordinal = 0
            )
    )
    private int blockIdToUnsignedInt1(int constant) {
        return 0xFFF;
    }

    @ModifyConstant(
            method = "playLevelEvent(Lnet/minecraft/entity/player/PlayerBase;IIIII)V",
            constant = @Constant(
                    intValue = 255,
                    ordinal = 1
            )
    )
    private int blockIdToUnsignedInt2(int constant) {
        return 0xFFF;
    }
}
