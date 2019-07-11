package com.greatorator.tolkienmobs.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentElvenLongevity extends Enchantment {
    public EnchantmentElvenLongevity(Rarity rarity, EnumEnchantmentType location, EntityEquipmentSlot...slot) {
        super(rarity, location, slot);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment enchantment) {
        return super.canApplyTogether(enchantment) && enchantment != Enchantments.THORNS && enchantment != Enchantments.PROJECTILE_PROTECTION;
    }
}