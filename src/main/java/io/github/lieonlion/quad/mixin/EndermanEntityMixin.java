package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.enchantment.QuadEnchantmentHelper;
import io.github.lieonlion.quad.tags.QuadEnchantmentTags;
import io.github.lieonlion.quad.tags.QuadItemTags;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {
    @ModifyReturnValue(method = "isPlayerStaring", at = @At(value = "RETURN"))
    private boolean applyTagEndermanPacifier(boolean original, PlayerEntity player) {
        return !QuadUtil.hasEndermanPacifier(player) && original;
    }
}