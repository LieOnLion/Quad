package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
    @ModifyReturnValue(method = "wearsGoldArmor", at = @At(value = "RETURN"))
    private static boolean wearsGoldArmor(boolean original, LivingEntity entity) {
        for (ItemStack stack: entity.getArmorItems()) {
            if (stack.isIn(QuadItemTags.PIGLIN_ARMOUR)) {
                return true;
            }
        } return original;
    }
}