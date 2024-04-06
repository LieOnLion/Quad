package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin {
    @WrapOperation(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean applyParrotPoisonTag(ItemStack instance, Item item, Operation<Boolean> original) {
        return (original.call(instance, item) && item.getRegistryEntry().isIn(QuadItemTags.PARROT_POISON))
                || instance.isIn(QuadItemTags.PARROT_POISON);
    }
}