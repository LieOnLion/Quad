package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemEntityMixin {
    @WrapOperation(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean applyTagIronGolemHeal(ItemStack instance, Item item, Operation<Boolean> original) {
        return (item.getRegistryEntry().isIn(QuadItemTags.IRON_GOLEM_HEAL) && original.call(instance, item))
                || instance.isIn(QuadItemTags.IRON_GOLEM_HEAL);
    }
}