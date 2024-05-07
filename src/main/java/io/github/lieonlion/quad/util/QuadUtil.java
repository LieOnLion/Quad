package io.github.lieonlion.quad.util;

import io.github.lieonlion.quad.enchantment.QuadEnchantmentHelper;
import io.github.lieonlion.quad.tags.QuadEnchantmentTags;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuadUtil {
    public static void usedFireLighter(World world, BlockState state, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        if (CampfireBlock.canBeLit(state) || CandleBlock.canBeLit(state) || CandleCakeBlock.isLitCandle(state)) {
            world.setBlockState(pos, state.with(Properties.LIT, true), Block.NOTIFY_ALL_AND_REDRAW);
        } usedFireLighter(world, pos, player, hand, stack);
    }

    public static void usedFireLighter(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.4f + 0.8f);
        if (!stack.isOf(Items.ENCHANTED_BOOK) && !player.isCreative()) {
            if (stack.isDamageable()) {
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            } else if (!QuadEnchantmentHelper.hasFireLighter(stack)) {
                stack.decrement(1);
            }
        }
    }

    public static boolean hasEquipmentFromTag(TagKey<Item> itemTagKey, LivingEntity living) {
        for (ItemStack stack : living.getAllArmorItems()) {
            if (stack.isIn(itemTagKey)) {
                return true;
            }
        } return false;
    }

    public static boolean hasItemOrEnchantmentEquipmentFromTag(TagKey<Item> itemTagKey, TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        return QuadEnchantmentHelper.hasEnchantmentEquipmentFromTag(enchantmentTagKey, living) || hasEquipmentFromTag(itemTagKey, living);
    }

    public static boolean isFireLighter(ItemStack stack) {
        return stack.isIn(QuadItemTags.FIRE_LIGHTER) || QuadEnchantmentHelper.hasFireLighter(stack);
    }

    public static boolean isSnowActsSolid(ItemStack stack) {
        return stack.isIn(QuadItemTags.SNOW_ACTS_SOLID) || QuadEnchantmentHelper.hasSnowBoots(stack);
    }

    public static boolean hasBurnsProtector(LivingEntity living) {
        return hasItemOrEnchantmentEquipmentFromTag(QuadItemTags.PROTECTS_FROM_BURNS, QuadEnchantmentTags.PROTECTS_FROM_BURNS, living);
    }

    public static boolean hasPiglinPacifier(LivingEntity living) {
        return hasItemOrEnchantmentEquipmentFromTag(QuadItemTags.PACIFIER_PIGLIN, QuadEnchantmentTags.PACIFIER_PIGLIN, living);
    }

    public static boolean hasEndermanPacifier(LivingEntity living) {
        return hasItemOrEnchantmentEquipmentFromTag(QuadItemTags.PACIFIER_ENDERMAN, QuadEnchantmentTags.PACIFIER_ENDERMAN, living);
    }

    public static boolean hasSnowBoots(LivingEntity living) {
        return hasItemOrEnchantmentEquipmentFromTag(QuadItemTags.SNOW_BOOTS, QuadEnchantmentTags.SNOW_BOOTS, living);
    }
}