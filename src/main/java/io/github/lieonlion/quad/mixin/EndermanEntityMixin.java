package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {
    @WrapOperation(method = "isPlayerStaring", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean applyTagEnderMask(ItemStack instance, Item item, Operation<Boolean> original) {
        return (item.getRegistryEntry().isIn(QuadItemTags.ENDER_MASK) && original.call(instance, item))
                || instance.isIn(QuadItemTags.ENDER_MASK);
    }
}