package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.block.TntBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin {
    @WrapOperation(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean applyTagTntIgniters(ItemStack instance, Item item, Operation<Boolean> original) {
        return (item.getRegistryEntry().isIn(QuadItemTags.TNT_IGNITERS) && original.call(instance, item))
                || instance.isIn(QuadItemTags.TNT_IGNITERS);
    }

    @WrapOperation(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 2))
    private boolean applyIsDamageable(ItemStack instance, Item item, Operation<Boolean> original) {
        return (instance.isDamageable() && original.call(instance, item)) || instance.isDamageable();
    }
}