package io.github.lieonlion.quad.mixin;

import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TntBlock.class)
public interface TntBlockInvoker {
    @Invoker
    static void callPrimeTnt(World world, BlockPos pos, @Nullable LivingEntity living) {}
}