package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = PiglinBrain.class, priority = 1004)
public abstract class PiglinBrainMixin {
    @ModifyReturnValue(method = "wearsGoldArmor", at = @At(value = "RETURN"))
    private static boolean applyTagPiglinPacifier(boolean original, LivingEntity living) {
        return (QuadUtil.hasPiglinPacifier(living) && original) || QuadUtil.hasPiglinPacifier(living);
    }
}