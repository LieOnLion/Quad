package io.github.lieonlion.quad;

import io.github.lieonlion.quad.registry.QuadFuelRegistry;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quad implements ModInitializer {
	public static final String MODID = "quad";
	public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

	@Override
	public void onInitialize() {
		LOGGER.info("[Quad] Initialising the Quad mod power! >:P");

		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof ItemEntity itemEntity && !world.isClientSide) {
				if (itemEntity.getItem().is(QuadItemTags.NEVER_DESPAWN)) {
					itemEntity.setUnlimitedLifetime();
				} if (itemEntity.getItem().is(QuadItemTags.NO_GRAVITY)) {
					itemEntity.setNoGravity(true);
				} else if (itemEntity.isNoGravity()) {
					itemEntity.setNoGravity(false);
				}
			}
		});

		//caching original fuel map when server started
		ServerLifecycleEvents.SERVER_STARTED.register(server -> QuadFuelRegistry.makeMap());
		//reloading fuel items after tags have been loaded
		CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> QuadFuelRegistry.registerFuel());
	}

	public static ResourceLocation asId(String id) {
		return new ResourceLocation(MODID, id);
	}
}