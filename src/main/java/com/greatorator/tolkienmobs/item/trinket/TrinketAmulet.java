package com.greatorator.tolkienmobs.item.trinket;

import com.brandon3055.brandonscore.items.ItemBCore;
import com.brandon3055.brandonscore.utils.ItemNBTHelper;
import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TrinketAmulet extends ItemBCore {
    private static final String TAG_POTION_EFFECT = "effect";

    public TrinketAmulet(Properties properties) {
        super(properties);
    }

    public void getSubItems(ItemGroup tab, NonNullList<ItemStack> subItems) {
        if(tab == getCreativeTabs()) {
            subItems.add(new ItemStack(this));
            for(Effect p : TTMConfig.effectArray)
                subItems.add(getTrinketForPotion(p));
        }
    }

    public static ItemStack getTrinketForPotion(Effect potion) {
        String id = potion.getRegistryName().toString();
        ItemStack stack = new ItemStack(TTMContent.TRINKET_AMULET.get());
        ItemNBTHelper.setString(stack, TAG_POTION_EFFECT, id);
        return stack;
    }

    public static Potion getPotion(ItemStack stack) {
        if(stack == null)
            return null;

        String effect = ItemNBTHelper.getString(stack, TAG_POTION_EFFECT, "");
        if(effect.isEmpty())
            return null;

        return Potion.getPotionTypeForName(effect);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking() && (getPotion(stack) != null)) {
            toggleEnabled(stack);
        }
        return super.onItemRightClick(world, player, hand);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return isEnabled(stack);
    }

    public static boolean isEnabled(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "IsActive", false);
    }

    public static void toggleEnabled(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "IsActive", !isEnabled(stack));
    }

//    @SuppressWarnings("unchecked")
//    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean hotbar) {
//        updateTrinket(stack, entity);
//    }

//    private void updateTrinket(ItemStack stack, Entity entity) {
//        PlayerEntity player = (PlayerEntity) entity;
//        Effects pe = new Effects(EffectInstance(stack));
//
//        if(isEnabled(stack)){
//            //World world = entity.getEntityWorld();
//            //boolean flag = false;
//            player.addPotionEffect(new Potion(new EffectInstance(pe,2400,3,false,false));
//            //LogHelperTTM.info("Ring has been enabled");
//        }else {
//            player.removePotionEffect(pe);
//        }
//    }

//    public String getItemStackDisplayName(ItemStack stack) {
//        String name = super.getItemStackDisplayName(stack);
//        Potion p = getPotion(stack);
//        String potionName = "Nothingness";
//        if(p != null)
//            potionName = I18n.translateToLocal(p.getName());
//
//        return String.format(name, potionName);
//    }
}