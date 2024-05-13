package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.block.*;
import net.minecraft.entity.ai.goal.CatSitOnBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CatSitOnBlockGoal.class, priority = 1004)
public abstract class CatSitOnBlockGoalMixin {
    @WrapOperation(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
    private boolean applyTagCatsOnBlocksSit1(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.getRegistryEntry().isIn(QuadBlockTags.CATS_ON_BLOCKS_SIT))
                || (instance.getBlock() instanceof ChestBlock && instance.isIn(QuadBlockTags.CATS_ON_BLOCKS_SIT));
    }

    @WrapOperation(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1))
    private boolean applyTagCatsOnBlocksSit2(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.getRegistryEntry().isIn(QuadBlockTags.CATS_ON_BLOCKS_SIT))
                || (instance.getBlock() instanceof AbstractFurnaceBlock && instance.isIn(QuadBlockTags.CATS_ON_BLOCKS_SIT));
    }

    @ModifyReturnValue(method = "isTargetPos", at = @At(value = "RETURN"))
    private boolean applyTagCatsOnBlocksSit3(boolean original, WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return original || (world.isAir(pos.up()) && state.isIn(QuadBlockTags.CATS_ON_BLOCKS_SIT)
                && !(state.getBlock() instanceof AbstractFurnaceBlock) && !(state.getBlock() instanceof ChestBlock));
    }
}