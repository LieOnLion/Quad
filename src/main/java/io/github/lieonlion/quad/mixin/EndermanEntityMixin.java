package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = EnderMan.class, priority = 1004)
public abstract class EndermanEntityMixin {
    @ModifyReturnValue(method = "isLookingAtMe", at = @At(value = "RETURN"))
    private boolean applyTagEndermanPacifier(boolean original, Player player) {
        return !QuadUtil.hasEndermanPacifier(player) && original;
    }
}