package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
    @ModifyReturnValue(method = "isWearingGold", at = @At(value = "RETURN"))
    private static boolean applyTagPiglinPacifier(boolean original, LivingEntity living) {
        return (QuadUtil.hasPiglinPacifier(living) && original) || QuadUtil.hasPiglinPacifier(living);
    }
}