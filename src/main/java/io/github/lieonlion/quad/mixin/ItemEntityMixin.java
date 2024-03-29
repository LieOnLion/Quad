package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Shadow public abstract ItemStack getItem();

    @Inject(method = "hurt", at = @At(value = "HEAD"), cancellable = true)
    private void applyTagImmuneDamages(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!this.getItem().isEmpty() && this.getItem().is(QuadItemTags.IMMUNE_EXPLOSION) && source.is(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(false);
        } if (!this.getItem().isEmpty() && this.getItem().is(QuadItemTags.IMMUNE_CACTUS) && source.is(DamageTypes.CACTUS)) {
            cir.setReturnValue(false);
        } if (!this.getItem().isEmpty() && this.getItem().is(QuadItemTags.IMMUNE_LIGHTNING) && source.is(DamageTypeTags.IS_LIGHTNING)) {
            cir.setReturnValue(false);
        }
    }

    @ModifyReturnValue(method = "fireImmune", at = @At(value = "RETURN"))
    private boolean applyTagImmuneFire(boolean original) {
        return original || this.getItem().is(QuadItemTags.IMMUNE_FIRE);
    }
}
