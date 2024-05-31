package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BaseFireBlock.class, priority = 1004)
public abstract class BaseFireBlockMixin {
    @WrapOperation(method = "isPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
    private static boolean applyTagNetherPortalBuilt(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && instance.is(QuadBlockTags.NETHER_PORTAL_BUILT))
                || instance.is(QuadBlockTags.NETHER_PORTAL_BUILT);
    }
}