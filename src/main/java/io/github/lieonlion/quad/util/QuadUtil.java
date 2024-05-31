package io.github.lieonlion.quad.util;

import io.github.lieonlion.quad.enchantment.QuadEnchantmentHelper;
import io.github.lieonlion.quad.tags.QuadEnchantmentTags;
import io.github.lieonlion.quad.tags.QuadItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class QuadUtil {
    public static void usedFireLighter(Level level, BlockState state, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        if (CampfireBlock.canLight(state) || CandleBlock.canLight(state) || CandleCakeBlock.canLight(state)) {
            level.setBlock(pos, state.setValue(BlockStateProperties.LIT, true), 11);
        } usedFireLighter(level, pos, player, hand, stack);
    }

    public static void usedFireLighter(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack) {
        level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0f, level.getRandom().nextFloat() * 0.4f + 0.8f);
        if (!stack.is(Items.ENCHANTED_BOOK) && !player.isCreative()) {
            if (stack.isDamageableItem()) {
                stack.hurtAndBreak(1, player, playerX -> playerX.broadcastBreakEvent(hand));
            } else if (!QuadEnchantmentHelper.hasFireLighter(stack)) {
                stack.shrink(1);
            }
        }
    }

    public static boolean hasEquipmentFromTag(TagKey<Item> itemTagKey, LivingEntity living) {
        for (ItemStack stack : living.getArmorSlots()) {
            if (stack.is(itemTagKey)) {
                return true;
            }
        } return false;
    }

    public static boolean hasItemOrEnchantmentEquipmentFromTag(TagKey<Item> itemTagKey, TagKey<Enchantment> enchantmentTagKey, LivingEntity living) {
        return QuadEnchantmentHelper.hasEnchantmentEquipmentFromTag(enchantmentTagKey, living) || hasEquipmentFromTag(itemTagKey, living);
    }

    public static boolean isFireLighter(ItemStack stack) {
        return stack.is(QuadItemTags.FIRE_LIGHTER) || QuadEnchantmentHelper.hasFireLighter(stack);
    }

    public static boolean isSnowActsSolid(ItemStack stack) {
        return stack.is(QuadItemTags.SNOW_ACTS_SOLID) || QuadEnchantmentHelper.hasSnowBoots(stack);
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