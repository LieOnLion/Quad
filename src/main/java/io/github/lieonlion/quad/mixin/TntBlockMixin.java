package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TntBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TntBlock.class)
public abstract class TntBlockMixin {
    @WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 0))
    private boolean applyTagTntIgniters(ItemStack instance, Item item, Operation<Boolean> original) {
        return (item.builtInRegistryHolder().is(QuadItemTags.TNT_IGNITERS) && original.call(instance, item))
                || instance.is(QuadItemTags.TNT_IGNITERS);
    }

    @WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z", ordinal = 2))
    private boolean applyIsDamageableItem(ItemStack instance, Item item, Operation<Boolean> original) {
        return (instance.isDamageableItem() && original.call(instance, item)) || instance.isDamageableItem();
    }
}