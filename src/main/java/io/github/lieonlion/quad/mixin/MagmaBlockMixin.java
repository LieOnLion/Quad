package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.block.MagmaBlock;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MagmaBlock.class)
public abstract class MagmaBlockMixin {
    @WrapOperation(method = "onSteppedOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;hasFrostWalker(Lnet/minecraft/entity/LivingEntity;)Z"))
    private boolean applyTagProtectsFromBurns(LivingEntity living, Operation<Boolean> original) {
        return ((original.call(living) && QuadUtil.hasBurnsProtector(living)) || QuadUtil.hasBurnsProtector(living));
    }
}