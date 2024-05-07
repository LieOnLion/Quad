package io.github.lieonlion.quad.enchantment;

import io.github.lieonlion.quad.tags.QuadEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

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
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : EnchantmentHelper.getEnchantments(stack).getEnchantmentsMap()) {
            Enchantment enchantment = entry.getKey().value();
            if (enchantment != null && enchantment.getRegistryEntry().isIn(enchantmentTagKey)) {
                return enchantment;
            }
        } return null;
    }

    public static ItemStack getEquipmentFromTag(TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        for (ItemStack stack : living.getAllArmorItems()) {
            if (hasEnchantmentFromTag(enchantmentTagKey, stack)) {
                return stack;
            }
        } return null;
    }

    public static int getLevel(Enchantment enchantment, ItemStack stack) {
        DataComponentType<ItemEnchantmentsComponent> dataComponentType = getEnchantmentsComponentType(stack);
        ItemEnchantmentsComponent itemEnchantmentsComponent = stack.getOrDefault(dataComponentType, ItemEnchantmentsComponent.DEFAULT);
        return enchantment != null ? itemEnchantmentsComponent.getLevel(enchantment) : 0;
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

    public static DataComponentType<ItemEnchantmentsComponent> getEnchantmentsComponentType(ItemStack stack) {
        return stack != null && stack.isOf(Items.ENCHANTED_BOOK) ? DataComponentTypes.STORED_ENCHANTMENTS : DataComponentTypes.ENCHANTMENTS;
    }
}