package io.github.lieonlion.quad.registry;

import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class QuadFuelRegistry {
    static FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;

    public static void registerFuel() {
        fuelRegistry.add(QuadItemTags.FUEL_LAVA, 20000);
        fuelRegistry.add(QuadItemTags.FUEL_COAL_BLOCK, 16000);
        fuelRegistry.add(QuadItemTags.FUEL_DRIED_KELP_BLOCK, 4001);
        fuelRegistry.add(QuadItemTags.FUEL_BLAZE_ROD, 2400);
        fuelRegistry.add(QuadItemTags.FUEL_COAL, 1600);
        fuelRegistry.add(QuadItemTags.FUEL_BOAT, 1200);
        fuelRegistry.add(QuadItemTags.FUEL_HANGING_SIGN, 800);
        fuelRegistry.add(QuadItemTags.FUEL_WOOD, 300);
        fuelRegistry.add(QuadItemTags.FUEL_WOOD_TOOL, 200);
        fuelRegistry.add(QuadItemTags.FUEL_WOOD_SLAB, 150);
        fuelRegistry.add(QuadItemTags.FUEL_WOOL, 100);
        fuelRegistry.add(QuadItemTags.FUEL_CARPET, 67);
        fuelRegistry.add(QuadItemTags.FUEL_BAMBOO, 50);
    }
}