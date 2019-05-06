package com.greatorator.tolkienmobs.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

/**
 * This class only needs exists because {@link BlockStairs} has a protected constructor making it impossible to instantiate from our TTMFeatures class.
 */
public class BlockTMStairs extends BlockStairs {

    public BlockTMStairs(IBlockState modelState)
    {
        super(modelState);
        //And i guess you need this for some reason?
        this.useNeighborBrightness = true;
    }
}
