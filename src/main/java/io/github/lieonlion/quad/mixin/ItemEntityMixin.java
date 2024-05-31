package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ItemEntity.class, priority = 1004)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getItem();

    @WrapOperation(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;isInvulnerableTo(Lnet/minecraft/world/damagesource/DamageSource;)Z"))
    private boolean applyTagsImmuneDamages(ItemEntity instance, DamageSource damageSource, Operation<Boolean> original) {
        return original.call(instance, damageSource) || !this.getItem().isEmpty() &&
                ((this.getItem().is(QuadItemTags.IMMUNE_CACTUS) && damageSource.is(DamageTypes.CACTUS)) ||
                        (this.getItem().is(QuadItemTags.IMMUNE_EXPLOSION) && damageSource.is(DamageTypeTags.IS_EXPLOSION)) ||
                        (this.getItem().is(QuadItemTags.IMMUNE_LIGHTNING) && damageSource.is(DamageTypeTags.IS_LIGHTNING)));
    }

    @ModifyReturnValue(method = "fireImmune", at = @At(value = "RETURN"))
    private boolean applyTagImmuneFire(boolean original) {
        return original || this.getItem().is(QuadItemTags.IMMUNE_FIRE);
    }
}