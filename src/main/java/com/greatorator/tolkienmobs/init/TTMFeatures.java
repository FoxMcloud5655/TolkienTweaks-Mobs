package com.greatorator.tolkienmobs.init;

import com.brandon3055.brandonscore.blocks.BlockBCore;
import com.brandon3055.brandonscore.blocks.ItemBlockBCore;
import com.brandon3055.brandonscore.blocks.NoItemBlock;
import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.registry.Feature;
import com.brandon3055.brandonscore.registry.IModFeatures;
import com.brandon3055.brandonscore.registry.ModFeature;
import com.brandon3055.brandonscore.registry.ModFeatures;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.*;
import com.greatorator.tolkienmobs.block.itemblock.ItemBlockSlabs;
import com.greatorator.tolkienmobs.handler.ItemTTMAmmo;
import com.greatorator.tolkienmobs.handler.ItemTTMFood;
import com.greatorator.tolkienmobs.item.tools.ToolAxe;
import com.greatorator.tolkienmobs.tile.TileSign;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

/**
 * Created by brandon3055 on 31/10/18.
 */
@GameRegistry.ObjectHolder(TolkienMobs.MODID)
@ModFeatures(modid = TolkienMobs.MODID) //This is what allows Brandon's Core to find this class and load all of its features
public class TTMFeatures implements IModFeatures {

    private CreativeTabs[] tabs = new CreativeTabs[]{TolkienMobs.tabToolsArmor, TolkienMobs.tabWorldMats, TolkienMobs.tabMobsSpawn, TolkienMobs.tabFoodItems, TolkienMobs.tabSignItems};

    @Nullable
    @Override
    public CreativeTabs getCreativeTab(Feature feature) {
        return feature.creativeTab() == -1 ? null : tabs[feature.creativeTab()];
    }

    //Material //I don't like the fact that this exists here but... for now this is fine.
    public static Item.ToolMaterial TOOL_MITHRIL = EnumHelper.addToolMaterial("tool_mithril", 2, 180, 5.0F, 1.5F, 5);
    public static ItemArmor.ArmorMaterial ARMOR_MITHRIL = EnumHelper.addArmorMaterial("armour_mithril", TolkienMobs.MODID + ":mithril", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
    public static Item.ToolMaterial TOOL_MORGULIRON = EnumHelper.addToolMaterial("tool_morguliron", 2, 180, 5.0F, 1.5F, 5);
    public static ItemArmor.ArmorMaterial ARMOR_MORGULIRON = EnumHelper.addArmorMaterial("armour_morguliron", TolkienMobs.MODID + ":morguliron", 20, new int[]{6, 7, 7, 6}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

    //region Simple Items
    @ModFeature(name = "ingot_mithril", stateOverride = "simple_items#type=ingot_mithril", cTab = 1)
    public static Item INGOT_MITHRIL = new ItemBCore();

    @ModFeature(name = "ingot_morguliron", stateOverride = "simple_items#type=ingot_morguliron", cTab = 1)
    public static Item INGOT_MORGULIRON = new ItemBCore();

    @ModFeature(name = "nugget_mithril", stateOverride = "simple_items#type=nugget_mithril", cTab = 1)
    public static Item NUGGET_MITHRIL = new ItemBCore();

    @ModFeature(name = "nugget_morguliron", stateOverride = "simple_items#type=nugget_morguliron", cTab = 1)
    public static Item NUGGET_MORGULIRON = new ItemBCore();

    @ModFeature(name = "dust_mithril", stateOverride = "simple_items#type=dust_mithril", cTab = 1)
    public static Item DUST_MITHRIL = new ItemBCore();

    @ModFeature(name = "dust_morguliron", stateOverride = "simple_items#type=dust_morguliron", cTab = 1)
    public static Item DUST_MORGULIRON = new ItemBCore();
    //endregion

    //region Tools
    @ModFeature(name = "axe_mithril", stateOverride = "tools#type=axe_mithril")
    public static Item AXE_MITHRIL = new ToolAxe(TOOL_MITHRIL, TOOL_MITHRIL.getAttackDamage(), -3.0F);

    @ModFeature(name = "hoe_mithril", stateOverride = "tools#type=hoe_mithril")
    public static Item HOE_MITHRIL = new ItemHoe(TOOL_MITHRIL);

    @ModFeature(name = "pickaxe_mithril", stateOverride = "tools#type=pickaxe_mithril")
    public static Item PICKAXE_MITHRIL = new ItemPickaxe(TOOL_MITHRIL);

    @ModFeature(name = "shovel_mithril", stateOverride = "tools#type=shovel_mithril")
    public static Item SHOVEL_MITHRIL = new ItemSpade(TOOL_MITHRIL);

    @ModFeature(name = "sword_mithril", stateOverride = "tools#type=sword_mithril")
    public static Item SWORD_MITHRIL = new ItemSword(TOOL_MITHRIL);

    @ModFeature(name = "axe_morguliron", stateOverride = "tools#type=axe_morguliron")
    public static Item AXE_MORGULIRON = new ToolAxe(TOOL_MORGULIRON, TOOL_MORGULIRON.getAttackDamage(), -3.0F);

    @ModFeature(name = "hoe_morguliron", stateOverride = "tools#type=hoe_morguliron")
    public static Item HOE_MORGULIRON = new ItemHoe(TOOL_MORGULIRON);

    @ModFeature(name = "pickaxe_morguliron", stateOverride = "tools#type=pickaxe_morguliron")
    public static Item PICKAXE_MORGULIRON = new ItemPickaxe(TOOL_MORGULIRON);

    @ModFeature(name = "shovel_morguliron", stateOverride = "tools#type=shovel_morguliron")
    public static Item SHOVEL_MORGULIRON = new ItemSpade(TOOL_MORGULIRON);

    @ModFeature(name = "sword_morguliron", stateOverride = "tools#type=sword_morguliron")
    public static Item SWORD_MORGULIRON = new ItemSword(TOOL_MORGULIRON);

    @ModFeature(name = "whip_fire", stateOverride = "tools#type=whip_fire")
    public static Item WHIP_FIRE = new ItemSword(TOOL_MORGULIRON);
    //endregion

    //region Armor
    @ModFeature(name = "helmet_mithril", stateOverride = "armor#type=helmet_mithril")
    public static Item HELMET_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 1, EntityEquipmentSlot.HEAD);

    @ModFeature(name = "chestplate_mithril", stateOverride = "armor#type=chestplate_mithril")
    public static Item CHESTPLATE_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 1, EntityEquipmentSlot.CHEST);

    @ModFeature(name = "leggings_mithril", stateOverride = "armor#type=leggings_mithril")
    public static Item LEGGINGS_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 2, EntityEquipmentSlot.LEGS);

    @ModFeature(name = "boots_mithril", stateOverride = "armor#type=boots_mithril")
    public static Item BOOTS_MITHRIL = new ItemArmor(ARMOR_MITHRIL, 1, EntityEquipmentSlot.FEET);

    @ModFeature(name = "helmet_morguliron", stateOverride = "armor#type=helmet_morguliron")
    public static Item HELMET_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.HEAD);

    @ModFeature(name = "chestplate_morguliron", stateOverride = "armor#type=chestplate_morguliron")
    public static Item CHESTPLATE_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.CHEST);

    @ModFeature(name = "leggings_morguliron", stateOverride = "armor#type=leggings_morguliron")
    public static Item LEGGINGS_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 2, EntityEquipmentSlot.LEGS);

    @ModFeature(name = "boots_morguliron", stateOverride = "armor#type=boots_morguliron")
    public static Item BOOTS_MORGULIRON = new ItemArmor(ARMOR_MORGULIRON, 1, EntityEquipmentSlot.FEET);
    //endregion

    //region Simple Blocks
    @ModFeature(name = "block_mithril", cTab = 1)
    public static final Block BLOCK_MITHRIL = new BlockBCore(Material.IRON).setHardness(1.5F).setResistance(10F);

    @ModFeature(name = "block_morguliron", cTab = 1)
    public static final Block BLOCK_MORGULIRON = new BlockBCore(Material.IRON).setHardness(1.5F).setResistance(10F);

    @ModFeature(name = "ore", variantMap = {"0:type=mithril", "1:type=morguliron", "2:type=nether_mithril", "3:type=nether_morguliron", "4:type=ender_mithril", "5:type=ender_morguliron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block ORE = new BlockOres();

    @ModFeature(name = "planks", variantMap = {"0:variant=mallorn", "1:variant=mirkwood", "2:variant=culumalda", "3:variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block PLANKS = new BlockPlank();

    @ModFeature(name = "log", variantMap = {"0:axis=y,variant=mallorn", "1:axis=y,variant=mirkwood", "2:axis=y,variant=culumalda", "3:axis=y,variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block LOGS = new BlockLogs();

    @ModFeature(name = "slab", variantMap = {"0:half=bottom,variant=mallorn", "1:half=bottom,variant=mirkwood", "2:half=bottom,variant=culumalda", "3:half=bottom,variant=lebethron"}, itemBlock = ItemBlockSlabs.class, cTab = 1)
    public static final BlockSlabs HALF_SLAB = new BlockSlabs();

    @ModFeature(name = "double_slab", itemBlock = NoItemBlock.class)
    public static final BlockSlabs DOUBLE_SLAB = new BlockDoubleSlabs();

    //@ModFeature(name = "stairs", variantMap = {"0:variant=mallorn", "1:variant=mirkwood"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    //public static final Block STAIRS = new BlockStair(PLANKS.getDefaultState());

    //                                             //TODO add a way to avoid this mess in 1.13...
    @ModFeature(name = "leaves", variantMap = {"0:check_decay=false,decayable=false,variant=mallorn", "1:check_decay=false,decayable=false,variant=mirkwood", "2:check_decay=false,decayable=false,variant=culumalda", "3:check_decay=false,decayable=false,variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block LEAVES = new BlockLeaf();

    @ModFeature(name = "sapling", variantMap = {"0:stage=0,variant=mallorn", "1:stage=0,variant=mirkwood", "2:stage=0,variant=culumalda", "3:stage=0,variant=lebethron"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final Block SAPLINGS = new BlockSaplings();

    @ModFeature(name = "flower", variantMap = {"0:variant=simbelmyne", "1:variant=mirkwood", "2:variant=alfirin", "3:variant=athelas", "4:variant=niphredil"}, itemBlock = ItemBlockBCore.class, cTab = 1)
    public static final BlockFlowers FLOWERS = new BlockFlowers();
    //endregion

    //region Basic Items
    @ModFeature(name = "feather_crebain", stateOverride = "simple_items#type=feather_crebain", cTab = 1)
    public static Item CREBAIN_FEATHER = new ItemBCore();
    @ModFeature(name = "leather_mumakil", stateOverride = "simple_items#type=leather_mumakil", cTab = 1)
    public static Item MUMAKIL_LEATHER = new ItemBCore();
    //endregion

    //region Food
    @ModFeature(name = "food_lembas", stateOverride = "simple_items#type=food_lembas", cTab = 3)
    public static Item LEMBAS = new ItemTTMFood(20, 20, new PotionEffect(MobEffects.ABSORPTION,12000,5), new PotionEffect(MobEffects.REGENERATION,100,5));
    @ModFeature(name = "food_honeycake", stateOverride = "simple_items#type=food_honeycake", cTab = 3)
    public static Item HONEY_CAKE = new ItemTTMFood(15, 15, new PotionEffect(MobEffects.ABSORPTION,6000,3), new PotionEffect(MobEffects.REGENERATION,50,5));
    @ModFeature(name = "food_cram", stateOverride = "simple_items#type=food_cram", cTab = 3)
    public static Item CRAM = new ItemTTMFood(10, 10, new PotionEffect(MobEffects.ABSORPTION,3000,1), new PotionEffect(MobEffects.REGENERATION,25,5));
    @ModFeature(name = "potion_entdraught", stateOverride = "simple_items#type=potion_entdraught", cTab = 3)
    public static Item ENT_DRAUGHT = new ItemTTMFood(5, 5, new PotionEffect(PotionInit.ENT_STANCE,6000,2));
    @ModFeature(name = "potion_miruvor", stateOverride = "simple_items#type=potion_miruvor", cTab = 3)
    public static Item MIRUVOR = new ItemTTMFood(5, 5, new PotionEffect(MobEffects.SPEED,3000,3), new PotionEffect(MobEffects.REGENERATION, 600, 3), new PotionEffect(MobEffects.NAUSEA, 40, 3));
    @ModFeature(name = "potion_grog", stateOverride = "simple_items#type=potion_grog", cTab = 3)
    public static Item GROG = new ItemTTMFood(5, 5, new PotionEffect(MobEffects.SPEED,1500,3), new PotionEffect(MobEffects.REGENERATION, 300, 3), new PotionEffect(MobEffects.NAUSEA, 100, 3));
    @ModFeature(name = "monster_flesh", stateOverride = "simple_items#type=monster_flesh", cTab = 3)
    public static Item MONSTER_FLESH = new ItemTTMFood(5, 2, new PotionEffect(MobEffects.HUNGER,100,2));
    //endregion

    //region Custom Blocks
    @ModFeature(name = "sign", itemBlock = ItemBlockBCore.class, tileEntity = TileSign.class, cTab = 4)
    public static final BlockSigns SIGNS = new BlockSigns();
    //endregion

    //region Ammo
    @ModFeature(name = "ammo_boulder", stateOverride = "simple_items#type=ammo_boulder", cTab = 1)
    public static Item AMMO_BOULDER = new ItemTTMAmmo(16);
    //endregion
}
