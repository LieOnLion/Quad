package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = EndermanEntity.class, priority = 1004)
public abstract class EndermanEntityMixin {
    @ModifyReturnValue(method = "isPlayerStaring", at = @At(value = "RETURN"))
    private boolean applyTagEndermanPacifier(boolean original, PlayerEntity player) {
        return !QuadUtil.hasEndermanPacifier(player) && original;
    }
}