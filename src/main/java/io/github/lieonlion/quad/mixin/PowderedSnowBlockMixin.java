package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = PowderSnowBlock.class, priority = 1004)
public abstract class PowderedSnowBlockMixin {
    @ModifyReturnValue(method = "canWalkOnPowderSnow", at = @At(value = "RETURN"))
    private static boolean applyTagSnowActsSolid(boolean original, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && QuadUtil.hasSnowBoots(livingEntity)) {
            return true;
        } if (entity instanceof ItemEntity itemEntity) {
            return QuadUtil.isSnowActsSolid(itemEntity.getStack());
        } return original;
    }
}