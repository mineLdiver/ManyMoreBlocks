package net.mine_diver.manymoreblocks.mixin.client;

import net.minecraft.block.BlockBase;
import net.minecraft.class_556;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(class_556.class)
public class Mixinclass_556 {

    @ModifyConstant(
            method = "method_1862(Lnet/minecraft/entity/Living;Lnet/minecraft/item/ItemInstance;)V",
            constant = @Constant(intValue = 256)
    )
    private int getBlockSize(int constant) {
        return BlockBase.BY_ID.length;
    }
}
