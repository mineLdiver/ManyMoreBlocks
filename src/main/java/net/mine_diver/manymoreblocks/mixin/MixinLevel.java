package net.mine_diver.manymoreblocks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Level.class)
public class MixinLevel {

    @ModifyConstant(
            method = "method_217(IIIIII)[B",
            constant = @Constant(intValue = 5)
    )
    @Environment(EnvType.SERVER)
    private int modifySize(int constant) {
        return 6;
    }
}
