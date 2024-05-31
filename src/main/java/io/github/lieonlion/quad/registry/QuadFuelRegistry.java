package io.github.lieonlion.quad.registry;

import io.github.lieonlion.quad.Quad;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.HashMap;
import java.util.Map;

public class QuadFuelRegistry {
    public static Map<Item, Integer> default_map = new HashMap<>();

    public static void makeMap() {
        //cache original fuel map
        if (!default_map.isEmpty()) return;
        BuiltInRegistries.ITEM.forEach(item -> {
            int burnTime = item.getDefaultInstance().getBurnTime(null);
            if (burnTime > 0) {
                default_map.put(item, burnTime);
            }
        });
        Quad.LOGGER.info("[Quad] cached original fuel map with count of: {}", default_map.size());
    }

    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();
        //removing any overridden fuel times from quad tags
        event.setBurnTime(default_map.getOrDefault(stack.getItem(), 0));
        //overriding items from quad tags
        if (stack.is(QuadItemTags.FUEL_BAMBOO)) event.setBurnTime(50);
        if (stack.is(QuadItemTags.FUEL_CARPET)) event.setBurnTime(67);
        if (stack.is(QuadItemTags.FUEL_WOOL)) event.setBurnTime(100);
        if (stack.is(QuadItemTags.FUEL_WOOD_SLAB)) event.setBurnTime(150);
        if (stack.is(QuadItemTags.FUEL_WOOD_TOOL)) event.setBurnTime(200);
        if (stack.is(QuadItemTags.FUEL_WOOD)) event.setBurnTime(300);
        if (stack.is(QuadItemTags.FUEL_HANGING_SIGN)) event.setBurnTime(800);
        if (stack.is(QuadItemTags.FUEL_BOAT)) event.setBurnTime(1200);
        if (stack.is(QuadItemTags.FUEL_COAL)) event.setBurnTime(1600);
        if (stack.is(QuadItemTags.FUEL_BLAZE_ROD)) event.setBurnTime(2400);
        if (stack.is(QuadItemTags.FUEL_DRIED_KELP_BLOCK)) event.setBurnTime(4001);
        if (stack.is(QuadItemTags.FUEL_COAL_BLOCK)) event.setBurnTime(16000);
        if (stack.is(QuadItemTags.FUEL_LAVA)) event.setBurnTime(20000);
    }
}
