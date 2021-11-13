package net.mine_diver.manymoreblocks.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.block.Fire;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Fire.class)
public class MixinFire {

    @ModifyConstant(
            method = "<init>(II)V",
            constant = @Constant(intValue = 256)
    )
    private int getBlockSize(int constant) {
        return BlockBase.BY_ID.length;
    }
}
