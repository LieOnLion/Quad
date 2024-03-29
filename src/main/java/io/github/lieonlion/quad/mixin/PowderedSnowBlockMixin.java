package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderedSnowBlockMixin {
    @Inject(method = "canEntityWalkOnPowderSnow", at = @At(value = "HEAD"), cancellable = true)
    private static void applyTagSnowActsSolid(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ItemEntity iEntity) {
            cir.setReturnValue(iEntity.getItem().is(QuadItemTags.SNOW_ACTS_SOLID));
        }
    }

    @WrapOperation(method = "canEntityWalkOnPowderSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canWalkOnPowderedSnow(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private static boolean applyTagSnowBoots(ItemStack instance, LivingEntity livingEntity, Operation<Boolean> original) {
        return (instance.is(QuadItemTags.SNOW_BOOTS) && original.call(instance, livingEntity))
                || instance.is(QuadItemTags.SNOW_BOOTS);
    }
}