package io.github.lieonlion.quad.tags;

import io.github.lieonlion.quad.Quad;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class QuadItemTags {
    public static final TagKey<Item> FUEL_LAVA = createTag("fuel_lava"); //20000
    public static final TagKey<Item> FUEL_COAL_BLOCK = createTag("fuel_coal_block"); //16000
    public static final TagKey<Item> FUEL_DRIED_KELP_BLOCK = createTag("fuel_dried_kelp_block"); //4001
    public static final TagKey<Item> FUEL_BLAZE_ROD = createTag("fuel_blaze_rod"); //2400
    public static final TagKey<Item> FUEL_COAL = createTag("fuel_coal"); //1600
    public static final TagKey<Item> FUEL_BOAT = createTag("fuel_boat"); //1200
    public static final TagKey<Item> FUEL_HANGING_SIGN = createTag("fuel_hanging_sign"); //800
    public static final TagKey<Item> FUEL_WOOD = createTag("fuel_wood"); //300
    public static final TagKey<Item> FUEL_WOOD_TOOL = createTag("fuel_wood_tool"); //200
    public static final TagKey<Item> FUEL_WOOD_SLAB = createTag("fuel_wood_slab"); //150
    public static final TagKey<Item> FUEL_WOOL = createTag("fuel_wool"); //100
    public static final TagKey<Item> FUEL_CARPET = createTag("fuel_carpet"); //67
    public static final TagKey<Item> FUEL_BAMBOO = createTag("fuel_bamboo"); //50

    public static final TagKey<Item> IMMUNE_CACTUS = createTag("immune_cactus");
    public static final TagKey<Item> IMMUNE_EXPLOSION = createTag("immune_explosion");
    public static final TagKey<Item> IMMUNE_FIRE = createTag("immune_fire");
    public static final TagKey<Item> IMMUNE_LIGHTNING = createTag("immune_lightning");

    public static final TagKey<Item> SNOW_ACTS_SOLID = createTag("snow_acts_solid");
    public static final TagKey<Item> SNOW_BOOTS = createTag("snow_boots");

    public static final TagKey<Item> NEVER_DESPAWN = createTag("never_despawn");
    public static final TagKey<Item> NO_GRAVITY = createTag("no_gravity");

    public static final TagKey<Item> IRON_GOLEM_HEAL = createTag("iron_golem_heal");
    public static final TagKey<Item> ENDER_MASK = createTag("ender_mask");
    public static final TagKey<Item> PIGLIN_ARMOUR = createTag("piglin_armour");
    public static final TagKey<Item> TNT_IGNITERS = createTag("tnt_igniters");
    public static final TagKey<Item> RESPAWN_ANCHOR_CHARGER = createTag("respawn_anchor_charger");

    private static TagKey<Item> createTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(Quad.MODID, name));
    }
}