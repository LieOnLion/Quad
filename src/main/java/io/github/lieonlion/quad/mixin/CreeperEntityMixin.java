package io.github.lieonlion.quad.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import io.github.lieonlion.quad.util.QuadUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = CreeperEntity.class, priority = 1004)
public abstract class CreeperEntityMixin extends Entity {
    public CreeperEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract void ignite();

    @Shadow public abstract boolean isIgnited();

    @ModifyReturnValue(method = "interactMob", at = @At(value = "RETURN"))
    private ActionResult applyTagFireLighters(ActionResult original, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (QuadUtil.isFireLighter(stack) && !this.isIgnited()) {
            this.ignite();
            QuadUtil.usedFireLighter(this.getWorld(), new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ()), player, hand, stack);
            return ActionResult.success(this.getWorld().isClient);
        } return original;
    }
}