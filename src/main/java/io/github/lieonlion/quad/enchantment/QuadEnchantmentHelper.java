package io.github.lieonlion.quad.enchantment;

import io.github.lieonlion.quad.tags.QuadEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

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
        for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.get(stack).entrySet()) {
            Enchantment enchantment = entry.getKey();
            if (getRegistryEntry(enchantment).isIn(enchantmentTagKey)) {
                return enchantment;
            }
        } return null;
    }

    public static ItemStack getEquipmentFromTag(TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        for (ItemStack stack : living.getArmorItems()) {
            if (hasEnchantmentFromTag(enchantmentTagKey, stack)) {
                return stack;
            }
        } return null;
    }

    public static int getLevel(Enchantment enchantment, ItemStack stack) {
        for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.get(stack).entrySet()) {
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

    public static RegistryEntry<Enchantment> getRegistryEntry(Enchantment enchantment) {
        return Registries.ENCHANTMENT.getEntry(enchantment);
    }
}