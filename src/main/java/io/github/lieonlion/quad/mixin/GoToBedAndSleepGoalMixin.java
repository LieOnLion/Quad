package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.entity.ai.goal.GoToBedAndSleepGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GoToBedAndSleepGoal.class)
public abstract class GoToBedAndSleepGoalMixin {
    @ModifyReturnValue(method = "isTargetPos", at = @At(value = "RETURN"))
    private boolean applyTagCatsOnBlocksLie(boolean original, WorldView world, BlockPos pos) {
        return original || (world.isAir(pos.up()) && world.getBlockState(pos).isIn(QuadBlockTags.CATS_ON_BLOCKS_LIE));
    }
}