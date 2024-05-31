package io.github.lieonlion.quad;

import io.github.lieonlion.quad.registry.QuadFuelRegistry;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Quad.MODID)
public class Quad {
    public static final String MODID = "quad";
    public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

    public Quad() {
        LOGGER.info("[Quad] Initialising the Quad mod power! >:P");

        MinecraftForge.EVENT_BUS.addListener((EntityJoinLevelEvent event) -> {
            if (event.getEntity() instanceof ItemEntity itemEntity) {
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
        MinecraftForge.EVENT_BUS.addListener((ServerStartedEvent event) -> QuadFuelRegistry.makeMap());
        //forge event for items fuel times
        MinecraftForge.EVENT_BUS.addListener(QuadFuelRegistry::onFurnaceFuelBurnTime);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation asId(String id) {
        return new ResourceLocation(MODID, id);
    }
}