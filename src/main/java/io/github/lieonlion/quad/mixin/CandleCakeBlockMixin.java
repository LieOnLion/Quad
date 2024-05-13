package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CandleCakeBlock.class, priority = 1004)
public abstract class CandleCakeBlockMixin {
    @ModifyReturnValue(method = "onUse", at = @At(value = "RETURN"))
    private ActionResult applyTagFireLighters(ActionResult original, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (QuadUtil.isFireLighter(stack) && CandleCakeBlock.canBeLit(state)) {
            QuadUtil.usedFireLighter(world, state, pos, player, hand, stack);
            return ActionResult.success(world.isClient);
        } return original;
    }
}