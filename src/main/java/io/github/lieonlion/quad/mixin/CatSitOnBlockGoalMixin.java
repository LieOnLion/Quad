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

@Mixin(CatSitOnBlockGoal.class)
public abstract class CatSitOnBlockGoalMixin {
    @WrapOperation(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
    private boolean applyCatSitOnChestTag(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.getRegistryEntry().isIn(QuadBlockTags.CAT_SIT_ON_BLOCK))
                || (instance.getBlock() instanceof ChestBlock && instance.isIn(QuadBlockTags.CAT_SIT_ON_BLOCK));
    }

    @WrapOperation(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1))
    private boolean applyCatSitOnFurnaceTag(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.getRegistryEntry().isIn(QuadBlockTags.CAT_SIT_ON_BLOCK))
                || (instance.getBlock() instanceof AbstractFurnaceBlock && instance.isIn(QuadBlockTags.CAT_SIT_ON_BLOCK));
    }

    @ModifyReturnValue(method = "isTargetPos", at = @At(value = "RETURN"))
    private boolean applyCatSitOnBlockTag(boolean original, WorldView world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos);
        return original || (world.isAir(pos.up()) && blockstate.isIn(QuadBlockTags.CAT_SIT_ON_BLOCK)
                && !(blockstate.getBlock() instanceof AbstractFurnaceBlock) && !(blockstate.getBlock() instanceof ChestBlock));
    }
}