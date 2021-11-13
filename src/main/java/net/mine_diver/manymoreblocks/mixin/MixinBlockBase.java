package net.mine_diver.manymoreblocks.mixin;

import net.mine_diver.manymoreblocks.impl.util.math.Sizes;
import net.minecraft.block.BlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockBase.class)
public class MixinBlockBase {

    @SuppressWarnings("UnresolvedMixinReference")
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 256))
    private static int getBlocksSize(int blocksSize) {
        return Sizes.BLOCKS;
    }
}
