package io.github.lieonlion.quad;

import io.github.lieonlion.quad.registry.QuadFuelRegistry;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quad implements ModInitializer {
	public static final String MODID = "quad";

	public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

	@Override
	public void onInitialize() {
		LOGGER.info("[Quad] Initialising Quad Recipes!");

		QuadFuelRegistry.registerFuel();

		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if(entity instanceof ItemEntity iEntity && !world.isClient) {
				if (iEntity.getStack().isIn(QuadItemTags.NEVER_DESPAWN)) {
					iEntity.setNeverDespawn();
				} if (iEntity.getStack().isIn(QuadItemTags.NO_GRAVITY)) {
					iEntity.setNoGravity(true);
				}
			}
		});
	}
}