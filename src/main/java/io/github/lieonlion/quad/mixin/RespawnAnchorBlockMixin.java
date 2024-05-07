package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorBlockMixin {
    @ModifyReturnValue(method = "isChargeItem", at = @At(value = "RETURN"))
    private static boolean applyTagRespawnAnchorCharger(boolean original, ItemStack stack) {
        return (original && stack.isIn(QuadItemTags.RESPAWN_ANCHOR_CHARGER))
                || stack.isIn(QuadItemTags.RESPAWN_ANCHOR_CHARGER);
    }
}