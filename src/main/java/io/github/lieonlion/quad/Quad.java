package io.github.lieonlion.quad;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quad implements ModInitializer {
	public static final TagKey<Item> WOOD_FUEL = TagKey.of(RegistryKeys.ITEM, new Identifier("quad", "wood_fuel"));
	public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

	@Override
	public void onInitialize() {
		LOGGER.info("[Quad] Initialising Quad Recipes!");

		CommonLifecycleEvents.TAGS_LOADED.register((identifier, tag) -> {
			LOGGER.info("[Quad] Adding fuel items from " + WOOD_FUEL.toString());
			FuelRegistry.INSTANCE.add(WOOD_FUEL, 300);
		});
	}
}