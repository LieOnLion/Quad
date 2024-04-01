package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorBlockMixin {
    @WrapOperation(method = "isChargeItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private static boolean applyRespawnAnchorCharger(ItemStack instance, Item item, Operation<Boolean> original) {
        return (original.call(instance, item) && item.getRegistryEntry().isIn(QuadItemTags.RESPAWN_ANCHOR_CHARGER))
                || instance.isIn(QuadItemTags.RESPAWN_ANCHOR_CHARGER);
    }
}