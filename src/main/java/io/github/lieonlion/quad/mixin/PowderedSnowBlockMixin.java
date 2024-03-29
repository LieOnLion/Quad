package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderedSnowBlockMixin {
    @Inject(method = "canWalkOnPowderSnow", at = @At(value = "HEAD"), cancellable = true)
    private static void applyTagSnowActsSolid(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ItemEntity iEntity) {
            cir.setReturnValue(iEntity.getStack().isIn(QuadItemTags.SNOW_ACTS_SOLID));
        }
    }

    @WrapOperation(method = "canWalkOnPowderSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean applyTagSnowBoots(ItemStack instance, Item item, Operation<Boolean> original) {
        return item.getRegistryEntry().isIn(QuadItemTags.SNOW_BOOTS) && original.call(instance, item)
                || instance.isIn(QuadItemTags.SNOW_BOOTS);
    }
}