package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.ai.goal.CatSitOnBlockGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CatSitOnBlockGoal.class)
public abstract class CatSitOnBlockGoalMixin {
    @WrapOperation(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
    private boolean applyCatSitOnChestTag(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.getRegistryEntry().isIn(QuadBlockTags.CAT_SIT_ON_CHEST))
                || (instance.getBlock() instanceof ChestBlock && instance.isIn(QuadBlockTags.CAT_SIT_ON_CHEST));
    }

    @WrapOperation(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1))
    private boolean applyCatSitOnFurnaceTag(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.getRegistryEntry().isIn(QuadBlockTags.CAT_SIT_ON_FURNACE))
                || (instance.getBlock() instanceof FurnaceBlock && instance.isIn(QuadBlockTags.CAT_SIT_ON_FURNACE));
    }
}