package net.mine_diver.manymoreblocks.mixin.client;

import net.minecraft.block.BlockBase;
import net.minecraft.client.render.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {

    @ModifyConstant(
            method = {
                    "render(Lnet/minecraft/entity/Item;DDDFF)V",
                    "renderItemOnGui(Lnet/minecraft/client/render/TextRenderer;Lnet/minecraft/client/texture/TextureManager;IIIII)V"
            },
            constant = @Constant(intValue = 256)
    )
    private int getBlockSize(int constant) {
        return BlockBase.BY_ID.length;
    }
}
