package io.github.lieonlion.quad.mixin;

import io.github.lieonlion.quad.tags.QuadBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ConduitBlockEntity.class, priority = 1004)
public abstract class ConduitBlockEntityMixin {
    @Shadow @Mutable @Final private static Block[] VALID_BLOCKS;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void PortalShape(BlockPos blockPos, BlockState blockState, CallbackInfo ci) {
        VALID_BLOCKS = BuiltInRegistries.BLOCK.getOrCreateTag(QuadBlockTags.CONDUIT_BASE_BLOCKS).stream().map(Holder::value).toList().toArray(new Block[0]);
    }
}