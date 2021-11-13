package net.mine_diver.manymoreblocks.mixin.server;

import net.minecraft.block.BlockBase;
import net.minecraft.class_70;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(class_70.class)
public class Mixinclass_70 {

    @ModifyConstant(
            method = "method_1834(III)Z",
            constant = @Constant(intValue = 256)
    )
    private int getMetaOffset(int constant) {
        return BlockBase.BY_ID.length;
    }
}
