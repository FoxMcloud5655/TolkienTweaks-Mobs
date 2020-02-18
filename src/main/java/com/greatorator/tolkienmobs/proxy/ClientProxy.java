package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.TTMClientEvents;
import com.greatorator.tolkienmobs.client.render.entity.RenderTMBirds;
import com.greatorator.tolkienmobs.client.render.entity.RenderTMGeneric;
import com.greatorator.tolkienmobs.client.render.entity.ambient.*;
import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderBoulder;
import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderFellBeastFireball;
import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderGaladhrimArrow;
import com.greatorator.tolkienmobs.client.render.entity.boss.*;
import com.greatorator.tolkienmobs.client.render.entity.monster.*;
import com.greatorator.tolkienmobs.client.render.entity.passive.*;
import com.greatorator.tolkienmobs.client.render.entity.special.RenderGollum;
import com.greatorator.tolkienmobs.client.render.entity.special.RenderMithrilGolem;
import com.greatorator.tolkienmobs.client.render.entity.special.RenderNazgul;
import com.greatorator.tolkienmobs.client.render.entity.special.RenderTMShadowfax;
import com.greatorator.tolkienmobs.client.render.model.ambient.ModelTMMidgeFly;
import com.greatorator.tolkienmobs.client.render.model.boss.ModelTMGwaihir;
import com.greatorator.tolkienmobs.entity.ambient.*;
import com.greatorator.tolkienmobs.entity.ammo.EntityBoulder;
import com.greatorator.tolkienmobs.entity.ammo.EntityFellBeastFireball;
import com.greatorator.tolkienmobs.entity.ammo.EntityGaladhrimArrow;
import com.greatorator.tolkienmobs.entity.boss.*;
import com.greatorator.tolkienmobs.entity.hostile.*;
import com.greatorator.tolkienmobs.entity.passive.*;
import com.greatorator.tolkienmobs.entity.special.*;
import com.greatorator.tolkienmobs.handler.FogHandler;
import com.greatorator.tolkienmobs.handler.TTMExtraHearts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    public void registerModel(Item item, int metadata)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new TTMClientEvents());

        OBJLoader.INSTANCE.addDomain(TolkienMobs.MODID);

        /* Fluids */

        /* Bosses */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMBalrog.class, RenderBalrog.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMFellBeast.class, RenderFellBeast::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMWitchKing.class, RenderWitchKing.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMorgulGolem.class, RenderMorgulGolem.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMWatcher.class, RenderWatcher.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGoblinKing.class, RenderGoblinKing.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMShelob.class, RenderTMShelob.FACTORY);

        /* Monsters */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHuron.class, RenderHuron.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMOathbreaker.class, RenderOathbreaker.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMTreeEnt.class, RenderTreeEnt.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMirkwoodSpider.class, RenderMirkwoodSpider.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGoblin.class, RenderGoblin.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMordorOrc.class, RenderMordorOrc.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMWarg.class, RenderWarg.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMTroll.class, RenderTroll.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMUrukHai.class, RenderUrukHai.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMBarrowWight.class, RenderBarrowWight.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMimicChest.class, RenderMimicChest.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMinotaur.class, RenderMinotaur.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMBrigand.class, RenderBrigand.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMFellSpirit.class, RenderFellSpirit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMElementalGolem.class, RenderElementalGolem.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMSwampHag.class, RenderSwampHag.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMDuergar.class, RenderTMDuergar.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHaradrim.class, RenderTMHaradrim.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMDeepClaw.class, RenderDeepClaw.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRomieWalker.class, RenderRomieWalker.FACTORY);

        /* Special */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGollum.class, RenderGollum.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMNazgul.class, RenderNazgul.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMithrilGolem.class, RenderMithrilGolem.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMShadowfax.class, RenderTMShadowfax.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMGreatEagle.class,
                RenderTMBirds.getRenderFactory(
                        new ModelTMGwaihir(),
                        new ModelTMGwaihir(),
                        1.2F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/birds/greateagle.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/birds/bird_legband.png")
                )
        );

        /* Passive */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHobbit.class, RenderHobbit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMDwarf.class, RenderDwarf.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMHuman.class, RenderHuman.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMElves.class, RenderElves.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMGoat.class, RenderGoat.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMumakil.class, RenderMumakil.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMAuroch.class, RenderAuroch.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(
                EntityTMGwaihir.class,
                RenderTMBirds.getRenderFactory(
                        new ModelTMGwaihir(),
                        new ModelTMGwaihir(),
                        1.2F,
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/birds/greateagle.png"),
                        new ResourceLocation(TolkienMobs.MODID + ":textures/entity/birds/bird_legband.png")
                )
        );

        /* Ambient */
        RenderingRegistry.registerEntityRenderingHandler(EntityTMSquirrel.class, RenderSOSquirrel.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMToad.class, RenderToaddle.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMRat.class, RenderRat.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMMidgeFly.class, m -> new RenderTMGeneric<>(m, new ModelTMMidgeFly(), 0.0F, "midgeflies.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTMCrebain.class, RenderTMCrebain::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTMThrush.class, RenderTMThrush::new);

        /* Miscellaneous */
        RenderingRegistry.registerEntityRenderingHandler(EntityBoulder.class, RenderBoulder.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityFellBeastFireball.class, RenderFellBeastFireball::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGaladhrimArrow.class, RenderGaladhrimArrow::new);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(new FogHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        if (!Loader.isModLoaded("mantle")) {
            MinecraftForge.EVENT_BUS.register(new TTMExtraHearts());
        }
    }

    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().player;
    }
}