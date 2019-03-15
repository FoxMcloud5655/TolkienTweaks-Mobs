package com.greatorator.tolkienmobs.world.biomes;

import com.greatorator.tolkienmobs.TTMConfig;
import com.greatorator.tolkienmobs.block.BlockFlowers;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMHuron;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMMidgeFly;
import com.greatorator.tolkienmobs.entity.hostile.EntityTMMirkwoodSpider;
import com.greatorator.tolkienmobs.entity.ambient.EntityTMToad;
import com.greatorator.tolkienmobs.handler.interfaces.IFogyBiome;
import com.greatorator.tolkienmobs.init.TTMFeatures;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import com.greatorator.tolkienmobs.world.gen.generators.WorldGenTreeMirkwood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BiomeMirkwood extends Biome implements IFogyBiome {
    public BiomeMirkwood()
    {

        super(new BiomeProperties("Mirkwood")
                .setBaseHeight(0.2F)
                .setHeightVariation(0.2F)
                .setTemperature(0.25F)
                .setRainfall(0.8F)
                .setWaterColor(3091811));

        LogHelperTTM.info("Preparing for the necromancer's return...");
        topBlock = Blocks.GRASS.getDefaultState();
        fillerBlock = Blocks.DIRT.getDefaultState();

        this.decorator = this.createBiomeDecorator();
        this.decorator.treesPerChunk = 8;
        this.decorator.deadBushPerChunk = 10;
        this.decorator.grassPerChunk = 5;
        this.decorator.mushroomsPerChunk = 24;
        this.decorator.bigMushroomsPerChunk = 1;
        this.decorator.generateFalls = false;
        this.decorator.flowersPerChunk = 6;

        addFlowers();
        setSpawnables();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand)
    {
        return new WorldGenTreeMirkwood(false);
    }

    public List<FlowerEntry> getFlowerList()
    {
        return flowers;
    }

    private void addFlowers()
    {
        flowers.clear();
        addFlower(TTMFeatures.FLOWERS.getDefaultState().withProperty(BlockFlowers.VARIANT, BlockFlowers.EnumType.MIRKWOOD), 10);
    }

    private void setSpawnables()
    {
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        if (TTMConfig.enableNaturalSpawn) {
            if (TTMConfig.enableMonster) {
                if (TTMConfig.enableMirkwoodSpiders) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMMirkwoodSpider.class, 10, 1, 1));
                }
                if (TTMConfig.enableHurons) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMHuron.class, 8, 1, 2));
                }
            }
            if (TTMConfig.enableAmbient) {
                if (TTMConfig.enableMidgeFlies) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMMidgeFly.class, 4, 1, 1));
                }
                if (TTMConfig.enableFrogs) {
                    this.spawnableMonsterList.add(new SpawnListEntry(EntityTMToad.class, 1, 1, 1));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return d0 < -0.1D ? 738353 : 738353;
    }

    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 738353;
    }

    @Override
    public int getFogColour(EntityPlayer player) {
        return 0x5b5f61;
    }

    @Override
    public float getFogDensity(EntityPlayer player) {
        return 0.2F;
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float currentTemperature)
    {
        currentTemperature = currentTemperature / 3.0F;
        currentTemperature = MathHelper.clamp(currentTemperature, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - currentTemperature * 0.05F, 0.5F + currentTemperature * 0.1F, 1.0F);
    }
}