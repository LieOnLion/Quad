package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
    @ModifyReturnValue(method = "isWearingGold", at = @At(value = "RETURN"))
    private static boolean wearsGoldArmor(boolean original, LivingEntity entity) {
        for (ItemStack stack: entity.getArmorSlots()) {
            if (stack.is(QuadItemTags.PIGLIN_ARMOUR)) {
                return true;
            }
        } return original;
    }
}