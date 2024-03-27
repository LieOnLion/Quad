package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
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

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract ItemStack getStack();

    @Inject(method = "damage", at = @At(value = "HEAD"), cancellable = true)
    private void applyTagImmuneDamages(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!this.getStack().isEmpty() && this.getStack().isIn(QuadItemTags.IMMUNE_EXPLOSION) && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            cir.setReturnValue(false);
        } if (!this.getStack().isEmpty() && this.getStack().isIn(QuadItemTags.IMMUNE_CACTUS) && source.isOf(DamageTypes.CACTUS)) {
            cir.setReturnValue(false);
        } if (!this.getStack().isEmpty() && this.getStack().isIn(QuadItemTags.IMMUNE_LIGHTNING) && source.isIn(DamageTypeTags.IS_LIGHTNING)) {
            cir.setReturnValue(false);
        }
    }

    @ModifyReturnValue(method = "isFireImmune", at = @At(value = "RETURN"))
    private boolean applyTagImmuneFire(boolean original) {
        return original || this.getStack().isIn(QuadItemTags.IMMUNE_FIRE);
    }
}