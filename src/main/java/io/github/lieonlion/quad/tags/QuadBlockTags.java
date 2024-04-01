package io.github.lieonlion.quad.tags;

import io.github.lieonlion.quad.Quad;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class QuadBlockTags {
    public static final TagKey<Block> CAT_SIT_ON_BLOCK = createTag("cat_sit_on_block");
    public static final TagKey<Block> CAT_LIE_ON_BLOCK = createTag("cat_lie_on_block");

    private static TagKey<Block> createTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(Quad.MODID, name));
    }
}