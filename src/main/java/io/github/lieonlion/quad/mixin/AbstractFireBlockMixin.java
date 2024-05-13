package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = AbstractFireBlock.class, priority = 1004)
public abstract class AbstractFireBlockMixin {
    @WrapOperation(method = "shouldLightPortalAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean applyTagNetherPortalFrame(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && instance.isIn(QuadBlockTags.NETHER_PORTAL_FRAME))
                || instance.isIn(QuadBlockTags.NETHER_PORTAL_FRAME);
    }
}