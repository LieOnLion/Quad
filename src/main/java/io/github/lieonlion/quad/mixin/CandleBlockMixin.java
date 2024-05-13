package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CandleBlock.class, priority = 1004)
public abstract class CandleBlockMixin {
    @ModifyReturnValue(method = "onUseWithItem", at = @At(value = "RETURN"))
    private ItemActionResult applyTagFireLighters(ItemActionResult original, ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (QuadUtil.isFireLighter(stack) && CandleBlock.canBeLit(state)) {
            QuadUtil.usedFireLighter(player.getWorld(), state, pos, player, hand, stack);
            return ItemActionResult.success(player.getWorld().isClient);
        } return original;
    }
}