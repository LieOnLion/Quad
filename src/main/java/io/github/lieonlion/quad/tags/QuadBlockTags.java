package io.github.lieonlion.quad.tags;

import io.github.lieonlion.quad.Quad;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class QuadBlockTags {
    public static final TagKey<Block> CAT_SIT_ON_BLOCK = createTag("cat_sit_on_block");
    public static final TagKey<Block> CAT_LIE_ON_BLOCK = createTag("cat_lie_on_block");

    private static TagKey<Block> createTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Quad.MODID, name));
    }
}