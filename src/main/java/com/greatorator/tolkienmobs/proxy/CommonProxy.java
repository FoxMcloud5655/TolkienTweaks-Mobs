package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.handler.TerrainEventHandler;
import com.greatorator.tolkienmobs.init.BiomeInit;
import com.greatorator.tolkienmobs.init.CraftingInit;
import com.greatorator.tolkienmobs.init.EntityInit;
import com.greatorator.tolkienmobs.init.ProfessionInit;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomOres;
import com.greatorator.tolkienmobs.world.gen.WorldGenCustomTrees;
import com.greatorator.tolkienmobs.world.types.WorldTypeArda;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenCustomTrees(), 0);
        BiomeInit.registerBiomes();
        EntityInit.init();
        CraftingInit.init();

        MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainEventHandler());
    }

    public void init(FMLInitializationEvent event)
    {
        ProfessionInit.associateCareersAndTrades();
    }

    public void postInit(FMLPostInitializationEvent event) {
        WorldType ARDA = new WorldTypeArda("Arda");
    }

}
