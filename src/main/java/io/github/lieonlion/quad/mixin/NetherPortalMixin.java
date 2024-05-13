package io.github.lieonlion.quad.mixin;

import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NetherPortal.class, priority = 1004)
public abstract class NetherPortalMixin {
    @Shadow private static @Final @Mutable AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void PortalShape(WorldAccess world, BlockPos pos, Direction.Axis axis, CallbackInfo ci) {
        IS_VALID_FRAME_BLOCK = (BlockState blockState, BlockView blockView, BlockPos blockPos) ->
                blockState.isIn(QuadBlockTags.NETHER_PORTAL_FRAME);
    }
}