package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.tags.QuadBlockTags;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Block.class, priority = 1004)
public abstract class BlockMixin {
    @Shadow public abstract BlockState getDefaultState();

    @Inject(method = "onSteppedOn", at = @At(value = "HEAD"))
    private void applyTagWhenSteppedOnBurns(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (!getDefaultState().isIn(QuadBlockTags.WHEN_STEPPED_ON_BURNS)) return;
        if (entity instanceof LivingEntity living && !entity.bypassesSteppingEffects() &&
                !QuadUtil.hasBurnsProtector(living)) {
            entity.damage(world.getDamageSources().hotFloor(), 1.0f);
        }
    }

    @ModifyReturnValue(method = "getVelocityMultiplier", at = @At(value = "RETURN"))
    private float applyTagWhenSteppedOnSlows(float original) {
        return getDefaultState().isIn(QuadBlockTags.WHEN_STEPPED_ON_SLOWS) ? 0.4f : original;
    }

    @ModifyReturnValue(method = "getJumpVelocityMultiplier", at = @At(value = "RETURN"))
    private float applyTagsWhenSteppedOnSticks(float original) {
        return getDefaultState().isIn(QuadBlockTags.WHEN_STEPPED_ON_STICKS) ? 0.5f : original;
    }
}