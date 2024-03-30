package io.github.lieonlion.quad.registry;

import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

public class QuadFuelRegistry {
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
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
}
