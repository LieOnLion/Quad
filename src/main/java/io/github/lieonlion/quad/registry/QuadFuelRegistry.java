package io.github.lieonlion.quad.registry;

import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

import java.util.HashMap;
import java.util.Map;

public class QuadFuelRegistry {
    static FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
    public static Map<Item, Integer> default_map = new HashMap<>();

    public static void cacheMap() {
        //cache original fuel map
        if (!default_map.isEmpty()) return;
        default_map = AbstractFurnaceBlockEntity.getFuel();
        Quad.LOGGER.info("[Quad] cached original fuel map with count of: {}", default_map.size());
    }

    public static void registerFuel() {
        for (var entry : default_map.entrySet()) {
            //removing any overridden items from quad tags
            fuelRegistry.add(entry.getKey(), default_map.getOrDefault(entry.getKey(), 0));
        } Quad.LOGGER.info("[Quad] loaded default fuel values");
        //overriding items from quad tags
        fuelRegistry.add(QuadItemTags.FUEL_BAMBOO, 50);
        fuelRegistry.add(QuadItemTags.FUEL_CARPET, 67);
        fuelRegistry.add(QuadItemTags.FUEL_WOOL, 100);
        fuelRegistry.add(QuadItemTags.FUEL_WOOD_SLAB, 150);
        fuelRegistry.add(QuadItemTags.FUEL_WOOD_TOOL, 200);
        fuelRegistry.add(QuadItemTags.FUEL_WOOD, 300);
        fuelRegistry.add(QuadItemTags.FUEL_HANGING_SIGN, 800);
        fuelRegistry.add(QuadItemTags.FUEL_BOAT, 1200);
        fuelRegistry.add(QuadItemTags.FUEL_COAL, 1600);
        fuelRegistry.add(QuadItemTags.FUEL_BLAZE_ROD, 2400);
        fuelRegistry.add(QuadItemTags.FUEL_DRIED_KELP_BLOCK, 4001);
        fuelRegistry.add(QuadItemTags.FUEL_COAL_BLOCK, 16000);
        fuelRegistry.add(QuadItemTags.FUEL_LAVA, 20000);
        Quad.LOGGER.info("[Quad] loaded quad fuel tags");
    }
}