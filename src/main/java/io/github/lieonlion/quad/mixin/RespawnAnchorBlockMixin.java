package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorBlockMixin {
    @WrapOperation(method = "isRespawnFuel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private static boolean applyRespawnAnchorCharger(ItemStack instance, Item item, Operation<Boolean> original) {
        return (original.call(instance, item) && item.builtInRegistryHolder().is(QuadItemTags.RESPAWN_ANCHOR_CHARGER))
                || instance.is(QuadItemTags.RESPAWN_ANCHOR_CHARGER);
    }
}