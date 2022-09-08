package com.greatorator.tolkienmobs.proxy;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.common.MobModify;
import com.greatorator.tolkienmobs.datagen.EntityGenerator;
import com.greatorator.tolkienmobs.datagen.RecipeGenerator;
import com.greatorator.tolkienmobs.datagen.StructureGenerator;
import com.greatorator.tolkienmobs.event.entity.living.TTMSleepingEvent;
import com.greatorator.tolkienmobs.init.TTMTags;
import com.greatorator.tolkienmobs.integration.TTMHelper;
import com.greatorator.tolkienmobs.integration.curios.TTMEquipMgr;
import com.greatorator.tolkienmobs.network.TTMNetwork;
import com.greatorator.tolkienmobs.server.TTMServerEvents;
import com.greatorator.tolkienmobs.world.gen.TTMFeature;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMStructureConfig;
import com.greatorator.tolkienmobs.world.gen.feature.config.TTMTreeFeatureConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.ConcurrentHashMap;

public class CommonProxy {
    private Minecraft mc;
    private World lastWorld;
    private final ConcurrentHashMap<LivingEntity, MobModify> rareMobsClient = new ConcurrentHashMap<>();

    public void construct() {
        registerEventListeners();
        TTMConfig.load();
        TTMContent.init();
        TTMHelper.init();
        TTMEquipMgr.initialize();
        TTMTags.init();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::balrogMark);
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::hobbitPlow);
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::onPlayerUpdate);
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::addDimensionalSpacing);
        MinecraftForge.EVENT_BUS.addListener(TTMServerEvents::biomeModification);

        TTMTreeFeatureConfig.FOLIAGE_PLACER_REGISTER.register(modBus);

        MinecraftForge.EVENT_BUS.addListener(TTMFeature::biomeLoading);

        modBus.addListener(EntityGenerator::registerAttributes);

        TTMNetwork.init();
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        RecipeGenerator.potions();
        TTMConfig.loadPotionList();
        TTMConfig.loadItemList();
        TTMConfig.loadBlacklist();
        event.enqueueWork(() -> {
            StructureGenerator.setupStructures();
            TTMStructureConfig.registerConfiguredStructures();
        });
        EntityGenerator.registerSpawnPlacement();
    }

    public void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new TTMSleepingEvent());
    }

    public void serverSetup(FMLDedicatedServerSetupEvent event) {

    }

    public void onAirPacket(int air) {

    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent tick) {
        if (mc.level == null || (mc.screen != null && mc.screen.isPauseScreen()))
            return;

        if (mc.level != lastWorld) {
            boolean newGame = lastWorld == null;
            lastWorld = mc.level;

            if (!newGame) {
                TolkienMobs.proxy.getRareMobs().clear();
            }
        }
    }

    public ConcurrentHashMap<LivingEntity, MobModify> getRareMobs() {
        return rareMobsClient;
    }

    public void registerEventListeners() {

    }

    public PlayerEntity getPlayer() {
        return null;
    }
}
