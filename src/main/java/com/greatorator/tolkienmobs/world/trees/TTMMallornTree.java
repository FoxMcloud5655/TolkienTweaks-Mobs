package com.greatorator.tolkienmobs.world.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

import static com.greatorator.tolkienmobs.world.gen.TTMFeatures.MALLORN;

public class TTMMallornTree extends Tree {
    public TTMMallornTree() {
    }

    @Override
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
            return MALLORN;
    }

}
