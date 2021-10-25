package com.greatorator.tolkienmobs.world.biome;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import static com.greatorator.tolkienmobs.TolkienMobs.LOGGER;

public class BiomeShire {
    private static int getSkyColorWithTemperatureModifier(float p_244206_0_) {
        float lvt_1_1_ = p_244206_0_ / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }

    public static Biome makeBiomeShire(float depth, float scale) {
        // Spawn Settings
        MobSpawnInfo.Builder spawnInf = new MobSpawnInfo.Builder();
        spawnInf.setPlayerCanSpawn();
        TTMDefaultBiomeFeatures.hobbitSpawns(spawnInf);
        TTMDefaultBiomeFeatures.passiveAnimals(spawnInf);
        TTMDefaultBiomeFeatures.ttmAmbientSpawns(spawnInf);

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder())
                .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        TTMDefaultBiomeFeatures.addWaterLakes(biomegenerationsettings$builder);
        TTMDefaultBiomeFeatures.addShireTrees(biomegenerationsettings$builder);
        TTMDefaultBiomeFeatures.addCulumaldaLeafPiles(biomegenerationsettings$builder);
        TTMDefaultBiomeFeatures.addLebethronLeafPiles(biomegenerationsettings$builder);

        DefaultBiomeFeatures.addFossilDecoration(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addForestGrass(biomegenerationsettings$builder);
        DefaultBiomeFeatures.addFerns(biomegenerationsettings$builder);

        LOGGER.info("And that means comfort...");
        return (new Biome.Builder())
                .precipitation(Biome.RainType.RAIN)
                .biomeCategory(Biome.Category.PLAINS)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects((new BiomeAmbience.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(10518688)
                        .grassColorOverride(2292007)
                        .skyColor(getSkyColorWithTemperatureModifier(0.8F))
                        .foliageColorOverride(2292007)
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(spawnInf.build())
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }
}