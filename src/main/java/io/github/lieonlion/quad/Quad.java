package io.github.lieonlion.quad;

import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Quad.MODID)
public class Quad {
    public static final String MODID = "quad";
    public static final Logger LOGGER = LoggerFactory.getLogger("Quad");

    public Quad() {
        LOGGER.info("[Quad] Initialising Quad Recipes!");

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ItemStack item = event.getItemStack();
        if (item.is(QuadItemTags.FUEL_LAVA)) {
            event.setBurnTime(20000);
        } if (item.is(QuadItemTags.FUEL_COAL_BLOCK)) {
            event.setBurnTime(16000);
        } if (item.is(QuadItemTags.FUEL_DRIED_KELP_BLOCK)) {
            event.setBurnTime(4001);
        } if (item.is(QuadItemTags.FUEL_BLAZE_ROD)) {
            event.setBurnTime(2400);
        } if (item.is(QuadItemTags.FUEL_COAL)) {
            event.setBurnTime(1600);
        } if (item.is(QuadItemTags.FUEL_BOAT)) {
            event.setBurnTime(1200);
        } if (item.is(QuadItemTags.FUEL_HANGING_SIGN)) {
            event.setBurnTime(800);
        } if (item.is(QuadItemTags.FUEL_WOOD)) {
            event.setBurnTime(300);
        } if (item.is(QuadItemTags.FUEL_WOOD_TOOL)) {
            event.setBurnTime(200);
        } if (item.is(QuadItemTags.FUEL_WOOD_SLAB)) {
            event.setBurnTime(150);
        } if (item.is(QuadItemTags.FUEL_WOOL)) {
            event.setBurnTime(100);
        } if (item.is(QuadItemTags.FUEL_CARPET)) {
            event.setBurnTime(67);
        } if (item.is(QuadItemTags.FUEL_BAMBOO)) {
            event.setBurnTime(50);
        }
    }

    @SubscribeEvent
    public void entityLoad(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ItemEntity iEntity) {
            if (iEntity.getItem().is(QuadItemTags.NEVER_DESPAWN)) {
                iEntity.setUnlimitedLifetime();
            } if (iEntity.getItem().is(QuadItemTags.NO_GRAVITY)) {
                iEntity.setNoGravity(true);
            }
        }
    }
}
