package net.mine_diver.manymoreblocks.mixin;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.stat.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Stats.class)
public class MixinStats {

    @ModifyConstant(
            method = {
                    "setupItemStats()V",
                    "setupCrafting()V",
                    "setupUse([Lnet/minecraft/stat/Stat;Ljava/lang/String;III)[Lnet/minecraft/stat/Stat;",
                    "setupBreak([Lnet/minecraft/stat/Stat;Ljava/lang/String;III)[Lnet/minecraft/stat/Stat;"
            },
            constant = @Constant(intValue = 32000)
    )
    private static int getItemsSize(int itemsSize) {
        return ItemBase.byId.length;
    }

    @ModifyConstant(
            method = "setupMinedBlocks(Ljava/lang/String;I)[Lnet/minecraft/stat/Stat;",
            constant = @Constant(intValue = 256)
    )
    private static int getBlocksSize(int blocksSize) {
        return BlockBase.BY_ID.length;
    }
}
