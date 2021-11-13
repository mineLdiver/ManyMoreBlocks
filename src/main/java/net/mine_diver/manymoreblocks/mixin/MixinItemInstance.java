package net.mine_diver.manymoreblocks.mixin;

import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInstance.class)
public class MixinItemInstance {

    @Shadow public int itemId;

    @Inject(
            method = "fromTag(Lnet/minecraft/util/io/CompoundTag;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/ItemInstance;itemId:I",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            )
    )
    private void unsignId(CompoundTag tag, CallbackInfo ci) {
        itemId = Short.toUnsignedInt((short) itemId);
    }
}
