package io.github.lieonlion.quad.enchantment;

import io.github.lieonlion.quad.tags.QuadEnchantmentTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public class QuadEnchantmentHelper {
    public static int getLevelFromTag(TagKey<Enchantment> enchantmentTagKey, ItemStack stack) {
        Enchantment enchantment = getEnchantmentFromTag(enchantmentTagKey, stack);
        return getLevel(enchantment, stack);
    }

    public static int getEquipmentLevelFromTag(TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        ItemStack stack = getEquipmentFromTag(enchantmentTagKey, living);
        Enchantment enchantment = getEnchantmentFromTag(enchantmentTagKey, stack);

        return enchantment != null || stack != null ? getLevel(enchantment, stack) : 0;
    }

    public static Enchantment getEnchantmentFromTag(TagKey<Enchantment> enchantmentTagKey, ItemStack stack) {
        if (stack == null) return null;
        for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(stack).entrySet()) {
            Enchantment enchantment = entry.getKey();
            if (enchantment != null && getRegistryEntry(enchantment).is(enchantmentTagKey)) {
                return enchantment;
            }
        } return null;
    }

    public static ItemStack getEquipmentFromTag(TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        for (ItemStack stack : living.getArmorSlots()) {
            if (hasEnchantmentFromTag(enchantmentTagKey, stack)) {
                return stack;
            }
        } return null;
    }

    public static int getLevel(Enchantment enchantment, ItemStack stack) {
        for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(stack).entrySet()) {
            if (enchantment == entry.getKey()) {
                return entry.getValue();
            }
        } return 0;
    }

    public static boolean hasEnchantmentFromTag(TagKey<Enchantment> enchantmentTagKey, ItemStack stack) {
        return getLevelFromTag(enchantmentTagKey, stack) > 0;
    }

    public static boolean hasEnchantmentEquipmentFromTag(TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        return getEquipmentLevelFromTag(enchantmentTagKey, living) > 0;
    }

    public static boolean hasFireLighter(ItemStack stack) {
        return getLevelFromTag(QuadEnchantmentTags.FIRE_LIGHTER, stack) > 0;
    }

    public static boolean hasSnowBoots(ItemStack stack) {
        return getLevelFromTag(QuadEnchantmentTags.SNOW_BOOTS, stack) > 0;
    }

    public static Holder<Enchantment> getRegistryEntry(Enchantment enchantment) {
        return BuiltInRegistries.ENCHANTMENT.wrapAsHolder(enchantment);
    }
}