package net.mine_diver.manymoreblocks.mixin;

import net.mine_diver.manymoreblocks.impl.util.math.Sizes;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemBase.class)
public class MixinItemBase {

    @SuppressWarnings("UnresolvedMixinReference")
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 32000))
    private static int getItemsSize(int itemsSize) {
        return Sizes.ITEMS;
    }

    @ModifyConstant(method = "<init>(I)V", constant = @Constant(intValue = 256))
    private int getBlocksSize(int blocksSize) {
        return BlockBase.BY_ID.length;
    }
}
