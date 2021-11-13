package net.mine_diver.manymoreblocks.mixin;

import net.mine_diver.manymoreblocks.api.chunk.ChunkMMB;
import net.minecraft.class_257;
import net.minecraft.level.Level;
import net.minecraft.level.LevelManager;
import net.minecraft.level.chunk.Chunk;
import net.minecraft.util.io.CompoundTag;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LevelManager.class)
public class MixinLevelManager {

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
            method = "method_1480(Lnet/minecraft/level/chunk/Chunk;Lnet/minecraft/level/Level;Lnet/minecraft/util/io/CompoundTag;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/io/CompoundTag;put(Ljava/lang/String;[B)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private static void method_1480(Chunk chunk, Level level, CompoundTag tag, CallbackInfo ci) {
        tag.put("manymoreblocks:tiles", ((ChunkMMB) chunk).getMMBBlocks().field_2103);
    }

    @Inject(
            method = "method_1479(Lnet/minecraft/level/Level;Lnet/minecraft/util/io/CompoundTag;)Lnet/minecraft/level/chunk/Chunk;",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    opcode = Opcodes.PUTFIELD,
                    shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void method_1479(Level arg, CompoundTag arg1, CallbackInfoReturnable<Chunk> cir, int var2, int var3, Chunk var4) {
        byte[] mmbtiles = arg1.getByteArray("manymoreblocks:tiles");
        ((ChunkMMB) var4).setMMBBlocks(mmbtiles.length == 0 ? new class_257(var4.tiles.length) : new class_257(mmbtiles));
    }
}
