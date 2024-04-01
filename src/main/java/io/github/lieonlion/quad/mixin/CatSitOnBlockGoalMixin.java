package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.world.entity.ai.goal.CatSitOnBlockGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CatSitOnBlockGoal.class)
public abstract class CatSitOnBlockGoalMixin {
    @WrapOperation(method = "isValidTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 0))
    private boolean applyCatSitOnChestTag(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.builtInRegistryHolder().is(QuadBlockTags.CAT_SIT_ON_CHEST))
                || (instance.getBlock() instanceof ChestBlock && instance.is(QuadBlockTags.CAT_SIT_ON_CHEST));
    }

    @WrapOperation(method = "isValidTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z", ordinal = 1))
    private boolean applyCatSitOnFurnaceTag(BlockState instance, Block block, Operation<Boolean> original) {
        return (original.call(instance, block) && block.builtInRegistryHolder().is(QuadBlockTags.CAT_SIT_ON_FURNACE))
                || (instance.getBlock() instanceof FurnaceBlock && instance.is(QuadBlockTags.CAT_SIT_ON_CHEST));
    }
}