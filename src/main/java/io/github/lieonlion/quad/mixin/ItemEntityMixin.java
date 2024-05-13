package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ItemEntity.class, priority = 1004)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getStack();

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z"))
    private boolean applyTagsImmuneDamages(ItemEntity instance, DamageSource damageSource, Operation<Boolean> original) {
        return original.call(instance, damageSource) || !this.getStack().isEmpty() &&
                (this.getStack().isIn(QuadItemTags.IMMUNE_CACTUS) && damageSource.isOf(DamageTypes.CACTUS)) ||
                (this.getStack().isIn(QuadItemTags.IMMUNE_EXPLOSION) && damageSource.isIn(DamageTypeTags.IS_EXPLOSION)) ||
                (this.getStack().isIn(QuadItemTags.IMMUNE_LIGHTNING) && damageSource.isIn(DamageTypeTags.IS_LIGHTNING));
    }

    @ModifyReturnValue(method = "isFireImmune", at = @At(value = "RETURN"))
    private boolean applyTagImmuneFire(boolean original) {
        return original || this.getStack().isIn(QuadItemTags.IMMUNE_FIRE);
    }
}