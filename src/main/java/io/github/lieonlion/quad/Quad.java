package io.github.lieonlion.quad;

import io.github.lieonlion.quad.registry.QuadFuelRegistry;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quad implements ModInitializer {
	public static final String MODID = "quad";
	public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

	@Override
	public void onInitialize() {
		LOGGER.info("[Quad] Initialising the Quad mod power! >:P");

		QuadFuelRegistry.registerFuel();

		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if(entity instanceof ItemEntity itemEntity && !world.isClient) {
				if (itemEntity.getStack().isIn(QuadItemTags.NEVER_DESPAWN)) {
					itemEntity.setNeverDespawn();
				} if (itemEntity.getStack().isIn(QuadItemTags.NO_GRAVITY)) {
					itemEntity.setNoGravity(true);
				} else if (itemEntity.hasNoGravity()) {
					itemEntity.setNoGravity(false);
				}
			}
		});
	}

	public static Identifier asId(String path) {
		return new Identifier(MODID, path);
	}
}