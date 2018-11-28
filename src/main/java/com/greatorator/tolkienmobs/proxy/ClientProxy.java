package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.client.render.entity.ammo.RenderBoulder;
import com.greatorator.tolkienmobs.client.render.entity.monster.*;
import com.greatorator.tolkienmobs.client.render.entity.passive.RenderDwarf;
import com.greatorator.tolkienmobs.client.render.entity.passive.RenderHobbit;
import com.greatorator.tolkienmobs.entity.ammo.EntityAmmo;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.entity.passive.EntityDwarf;
import com.greatorator.tolkienmobs.entity.passive.EntityHobbit;
import com.greatorator.tolkienmobs.handler.FogHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        /** Monsters */
        RenderingRegistry.registerEntityRenderingHandler(EntityHuron.class, RenderHuron.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTreeEnt.class, RenderTreeEnt.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMirkwoodSpider.class, RenderMirkwoodSpider.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, RenderGoblin.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMordorOrc.class, RenderMordorOrc.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityWarg.class, RenderWarg.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityMumakil.class, RenderMumakil.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrebain.class, RenderCrebain.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityTroll.class, RenderTroll.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityUrukHai.class, RenderUrukHai.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityBarrowWight.class, RenderBarrowWight.FACTORY);

        /** Passive */
        RenderingRegistry.registerEntityRenderingHandler(EntityHobbit.class, RenderHobbit.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityDwarf.class, RenderDwarf.FACTORY);

        /** Miscellaneous */
        RenderingRegistry.registerEntityRenderingHandler(EntityAmmo.class, RenderBoulder.FACTORY);
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
    }
}
