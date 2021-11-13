package net.mine_diver.manymoreblocks.mixin.client;

import net.minecraft.block.BlockBase;
import net.minecraft.client.render.entity.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {

    @ModifyConstant(
            method = "method_827(Lnet/minecraft/entity/player/PlayerBase;F)V",
            constant = @Constant(intValue = 256)
    )
    private int getBlockSize(int constant) {
        return BlockBase.BY_ID.length;
    }
}
