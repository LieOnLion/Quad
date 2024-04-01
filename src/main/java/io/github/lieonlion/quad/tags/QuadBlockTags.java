package io.github.lieonlion.quad.tags;

import io.github.lieonlion.quad.Quad;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class QuadBlockTags {
    public static final TagKey<Block> CAT_SIT_ON_CHEST = createTag("cat_sit_on_chest");
    public static final TagKey<Block> CAT_SIT_ON_FURNACE = createTag("cat_sit_on_furnace");

    private static TagKey<Block> createTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Quad.MODID, name));
    }
}