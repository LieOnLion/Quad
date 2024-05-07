package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderedSnowBlockMixin {
    @ModifyReturnValue(method = "canEntityWalkOnPowderSnow", at = @At(value = "RETURN"))
    private static boolean applyTagSnowActsSolid(boolean original, Entity entity) {
        if (entity instanceof LivingEntity livingEntity && QuadUtil.hasSnowBoots(livingEntity)) {
            return true;
        } if (entity instanceof ItemEntity itemEntity) {
            return QuadUtil.isSnowActsSolid(itemEntity.getItem());
        } return original;
    }
}