package io.github.lieonlion.quad;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quad implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

	@Override
	public void onInitialize() {
		LOGGER.info("[Quad] Initialising Quad Recipes!");
	}
}