package net.mine_diver.manymoreblocks.mixin.client;

import net.minecraft.block.BlockBase;
import net.minecraft.client.BaseClientInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BaseClientInteractionManager.class)
public class MixinBaseClientInteractionManager {

    @ModifyConstant(
            method = "method_1716(IIII)Z",
            constant = @Constant(intValue = 256)
    )
    private int getMetaOffset(int constant) {
        return BlockBase.BY_ID.length;
    }
}
