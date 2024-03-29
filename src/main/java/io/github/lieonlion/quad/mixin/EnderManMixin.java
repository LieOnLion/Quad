package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnderMan.class)
public abstract class EnderManMixin {
    @WrapOperation(method = "isLookingAtMe", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeHooks;shouldSuppressEnderManAnger(Lnet/minecraft/world/entity/monster/EnderMan;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean applyTagEnderMask(EnderMan enderMan, Player player, ItemStack mask, Operation<Boolean> original) {
        return (original.call(enderMan, player, mask) && mask.is(QuadItemTags.ENDER_MASK))
                || player.getInventory().armor.get(3).is(QuadItemTags.ENDER_MASK);
    }
}