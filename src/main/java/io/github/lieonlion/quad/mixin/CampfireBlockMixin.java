package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CampfireBlock.class, priority = 1004)
public abstract class CampfireBlockMixin {
    @ModifyReturnValue(method = "useItemOn", at = @At(value = "RETURN"))
    private ItemInteractionResult applyTagFireLighters(ItemInteractionResult original, ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (QuadUtil.isFireLighter(stack) && CampfireBlock.canLight(state)) {
            QuadUtil.usedFireLighter(level, state, pos, player, hand, stack);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } return original;
    }

    @WrapOperation(method = "entityInside", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;hasFrostWalker(Lnet/minecraft/world/entity/LivingEntity;)Z"))
    private boolean applyTagProtectsFromBurns(LivingEntity living, Operation<Boolean> original) {
        return ((original.call(living) && QuadUtil.hasBurnsProtector(living)))
                || QuadUtil.hasBurnsProtector(living);
    }
}