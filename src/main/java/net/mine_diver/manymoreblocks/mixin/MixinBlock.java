package net.mine_diver.manymoreblocks.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.item.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Block.class)
public class MixinBlock {

    @ModifyConstant(method = "<init>(I)V", constant = @Constant(intValue = 256))
    private int getBlocksSize(int blocksSize) {
        return BlockBase.BY_ID.length;
    }
}
