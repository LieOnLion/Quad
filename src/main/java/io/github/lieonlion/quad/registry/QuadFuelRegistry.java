package io.github.lieonlion.quad.registry;

import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class QuadFuelRegistry {
    static FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;

    public static void registerFuel() {
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_LAVA.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_LAVA, 20000);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_COAL_BLOCK.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_COAL_BLOCK, 16000);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_DRIED_KELP_BLOCK.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_DRIED_KELP_BLOCK, 4001);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_BLAZE_ROD.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_BLAZE_ROD, 2400);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_COAL.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_COAL, 1600);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_BOAT.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_BOAT, 1200);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_HANGING_SIGN.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_HANGING_SIGN, 800);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_WOOD.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_WOOD, 300);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_WOOD_TOOL.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_WOOD_TOOL, 200);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_WOOD_SLAB.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_WOOD_SLAB, 150);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_WOOL.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_WOOL, 100);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_CARPET.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_CARPET, 67);
        Quad.LOGGER.info("[Quad] Adding fuel items from '" + QuadItemTags.FUEL_BAMBOO.id() + "'");
        fuelRegistry.add(QuadItemTags.FUEL_BAMBOO, 50);
    }
}