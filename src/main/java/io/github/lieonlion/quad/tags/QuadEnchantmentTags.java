package io.github.lieonlion.quad.tags;

import io.github.lieonlion.quad.Quad;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class QuadEnchantmentTags {
    public static final TagKey<Enchantment> PROTECTS_FROM_BURNS = createTag("protects_from/burns");

    public static final TagKey<Enchantment> SNOW_BOOTS = createTag("snow/boots");

    public static final TagKey<Enchantment> PACIFIER_PIGLIN = createTag("pacifier/piglin");
    public static final TagKey<Enchantment> PACIFIER_ENDERMAN = createTag("pacifier/enderman");

    public static final TagKey<Enchantment> FIRE_LIGHTER = createTag("fire_lighter");

    private static TagKey<Enchantment> createTag(String name) {
        return TagKey.create(Registries.ENCHANTMENT, Quad.asId(name));
    }
}