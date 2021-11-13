package net.mine_diver.manymoreblocks.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.manymoreblocks.api.chunk.ChunkMMB;
import net.mine_diver.manymoreblocks.api.util.MathHelper;
import net.minecraft.class_257;
import net.minecraft.level.Level;
import net.minecraft.level.chunk.Chunk;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Chunk.class)
public class MixinChunk implements ChunkMMB {

    @Unique
    private class_257 mmbBlocks;

    @Unique
    private int
            capturedXOnMethod_892,
            capturedYOnMethod_892,
            capturedZOnMethod_892,
            capturedXOnGenerateHeight0,
            capturedYOnGenerateHeight0,
            capturedZOnGenerateHeight0,
            capturedYOnGenerateHeight1,
            capturedYOnMethod_889;

    @Unique
    private boolean firstCopy;

    @Inject(
            method = "<init>(Lnet/minecraft/level/Level;[BII)V",
            at = @At("RETURN")
    )
    private void onCor(Level arg, byte[] bs, int i, int j, CallbackInfo ci) {
        mmbBlocks = new class_257(bs.length);
    }

    @Inject(
            method = "method_892()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    args = "array=get",
                    shift = At.Shift.BY,
                    by = -2
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    @Environment(EnvType.CLIENT)
    private void captureBlockCoordsOnMethod_892(CallbackInfo ci, int var1, int var2, int var3, int var4) {
        capturedXOnMethod_892 = var2;
        capturedYOnMethod_892 = var4;
        capturedZOnMethod_892 = var3;
    }

    @Redirect(
            method = "method_892()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/BlockBase;LIGHT_OPACITY:[I",
                    args = {
                            "array=get",
                            "fuzz=12"
                    }
            )
    )
    @Environment(EnvType.CLIENT)
    private int getLightOpacityOnClientGenerateHeight(int[] lightOpacity, int access) {
        return getLightOpacity(lightOpacity, access, mmbBlocks, capturedXOnMethod_892, capturedYOnMethod_892, capturedZOnMethod_892);
    }

    @Inject(
            method = "generateHeightmap()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    args = "array=get",
                    shift = At.Shift.BY,
                    by = -2,
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void captureBlockCoordsOnGenerateHeight0(CallbackInfo ci, int var1, int var2, int var3, int var4) {
        capturedXOnGenerateHeight0 = var2;
        capturedYOnGenerateHeight0 = var4;
        capturedZOnGenerateHeight0 = var3;
    }

    @Redirect(
            method = "generateHeightmap()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/BlockBase;LIGHT_OPACITY:[I",
                    args = {
                            "array=get",
                            "fuzz=12"
                    },
                    ordinal = 0
            )
    )
    private int getLightOpacityOnGenerateHeight0(int[] lightOpacity, int access) {
        return getLightOpacity(lightOpacity, access, mmbBlocks, capturedXOnGenerateHeight0, capturedYOnGenerateHeight0 - 1, capturedZOnGenerateHeight0);
    }

    @Inject(
            method = "generateHeightmap()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    args = "array=get",
                    shift = At.Shift.BY,
                    by = -2,
                    ordinal = 1
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void captureBlockCoordsOnGenerateHeight1(CallbackInfo ci, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        capturedYOnGenerateHeight1 = var7;
    }

    @Redirect(
            method = "generateHeightmap()V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/BlockBase;LIGHT_OPACITY:[I",
                    args = {
                            "array=get",
                            "fuzz=12"
                    },
                    ordinal = 1
            )
    )
    private int getLightOpacityOnGenerateHeight1(int[] lightOpacity, int access) {
        return getLightOpacity(lightOpacity, access, mmbBlocks, capturedXOnGenerateHeight0, capturedYOnGenerateHeight1, capturedZOnGenerateHeight0);
    }

    @Inject(
            method = "method_889(III)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    args = "array=get",
                    shift = At.Shift.BY,
                    by = -2,
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void captureBlockCoordsOnMethod_889(int i, int j, int k, CallbackInfo ci, int var4, int var5) {
        capturedYOnMethod_889 = var5;
    }

    @Redirect(
            method = "method_889(III)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/BlockBase;LIGHT_OPACITY:[I",
                    args = {
                            "array=get",
                            "fuzz=12"
                    },
                    ordinal = 0
            )
    )
    private int getLightOpacityOnMethod_889(int[] lightOpacity, int access, int i, int j, int k) {
        return getLightOpacity(lightOpacity, access, mmbBlocks, i, capturedYOnMethod_889 - 1, k);
    }

    @Inject(
            method = "getTileId(III)I",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getTileId(int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(MathHelper.getBlockID(cir.getReturnValueI(), mmbBlocks.method_1703(x, y, z)));
    }

    @ModifyVariable(
            method = "setTileWithMetadata(IIIII)Z",
            index = 8,
            at = @At(value = "STORE")
    )
    private int setTileWithMetadata1(int vanillaID, int x, int y, int z, int i1, int j1) {
        return MathHelper.getBlockID(vanillaID, mmbBlocks.method_1703(x, y, z));
    }

    @Inject(
            method = "setTileWithMetadata(IIIII)Z",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    args = {
                            "array=set",
                            "fuzz=12"
                    }
            )
    )
    private void setTileWithMetadata2(int x, int y, int z, int i1, int j1, CallbackInfoReturnable<Boolean> cir) {
        mmbBlocks.method_1704(x, y, z, MathHelper.getMMBID((short) i1));
    }

    @Redirect(
            method = "setTileWithMetadata(IIIII)Z",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/BlockBase;LIGHT_OPACITY:[I",
                    args = {
                            "array=get",
                            "fuzz=12"
                    }
            )
    )
    private int getLightOpacityOnSetTileWithMetadata(int[] lightOpacity, int access, int x, int y, int z, int i1, int j1) {
        return getLightOpacity(lightOpacity, access, mmbBlocks, x, y, z);
    }

    @ModifyVariable(
            method = "method_860(IIII)Z",
            index = 7,
            at = @At(value = "STORE")
    )
    private int method_8601(int vanillaID, int i, int j, int k, int i1) {
        return MathHelper.getBlockID(vanillaID, mmbBlocks.method_1703(i, j, k));
    }

    @Inject(
            method = "method_860(IIII)Z",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;tiles:[B",
                    args = {
                            "array=set",
                            "fuzz=12"
                    }
            )
    )
    private void method_8602(int x, int y, int z, int i1, CallbackInfoReturnable<Boolean> cir) {
        mmbBlocks.method_1704(x, y, z, MathHelper.getMMBID((short) i1));
    }

    @Redirect(
            method = "method_860(IIII)Z",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/BlockBase;LIGHT_OPACITY:[I",
                    args = {
                            "array=get",
                            "fuzz=12"
                    }
            )
    )
    private int getLightOpacityOnMethod_860(int[] lightOpacity, int access, int x, int y, int z, int i1) {
        return getLightOpacity(lightOpacity, access, mmbBlocks, x, y, z);
    }

    @Inject(
            method = "method_872([BIIIIIII)I",
            at = @At("HEAD")
    )
    @Environment(EnvType.SERVER)
    private void setFirstCopy(byte[] bs, int i, int j, int k, int i1, int j1, int k1, int i12, CallbackInfoReturnable<Integer> cir) {
        firstCopy = true;
    }

    @ModifyVariable(
            method = "method_872([BIIIIIII)I",
            index = 8,
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;field_957:Lnet/minecraft/class_257;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0,
                    shift = At.Shift.BEFORE
            )
    )
    @Environment(EnvType.SERVER)
    private int copyMMBDataToArray0(int size, byte[] bs, int i, int j, int k, int i1, int j1, int k1, int i12) {
        System.arraycopy(mmbBlocks.field_2103, 0, bs, size, mmbBlocks.field_2103.length);
        return size + mmbBlocks.field_2103.length;
    }

    @ModifyVariable(
            method = "method_872([BIIIIIII)I",
            index = 8,
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/level/chunk/Chunk;field_957:Lnet/minecraft/class_257;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 3,
                    shift = At.Shift.BEFORE
            )
    )
    @Environment(EnvType.SERVER)
    private int copyMMBDataToArray1(int size, byte[] bs, int i, int j, int k, int i1, int j1, int k1, int i12) {
        if (firstCopy) {
            firstCopy = false;
            for (int var21 = i; var21 < i1; ++var21) {
                for (int var24 = k; var24 < k1; ++var24) {
                    int var27 = (var21 << 11 | var24 << 7 | j) >> 1;
                    int var30 = (j1 - j) / 2;
                    System.arraycopy(mmbBlocks.field_2103, var27, bs, size, var30);
                    size += var30;
                }
            }
        }
        return size;
    }

    @ModifyVariable(
            method = "method_891([BIIIIIII)I",
            index = 8,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/chunk/Chunk;method_892()V"
            )
    )
    @Environment(EnvType.CLIENT)
    private int readMMBDataFromArray(int size, byte[] bs, int i, int j, int k, int i1, int j1, int k1, int i12) {
        for(int var13 = i; var13 < i1; ++var13) {
            for(int var16 = k; var16 < k1; ++var16) {
                int var19 = (var13 << 11 | var16 << 7 | j) >> 1;
                int var22 = (j1 - j) / 2;
                System.arraycopy(bs, size, mmbBlocks.field_2103, var19, var22);
                size += var22;
            }
        }
        return size;
    }

    @Override
    public class_257 getMMBBlocks() {
        return this.mmbBlocks;
    }

    @Override
    public void setRawMMBBlocks(byte[] mmbBlocks) {
        System.arraycopy(mmbBlocks, 0, this.mmbBlocks.field_2103, 0, mmbBlocks.length);
    }

    @Override
    public void setMMBBlocks(class_257 mmbBlocks) {
        this.mmbBlocks = mmbBlocks;
    }
}
