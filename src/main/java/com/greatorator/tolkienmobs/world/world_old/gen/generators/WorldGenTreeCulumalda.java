//package com.greatorator.tolkienmobs.world.world_old.gen.generators;
//
//import com.greatorator.tolkienmobs.block.BlockLeaf;
//import com.greatorator.tolkienmobs.block.BlockLogs;
//import com.greatorator.tolkienmobs.init.TTMFeatures;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.WorldGenAbstractTree;
//
//import java.util.Random;
//
//public class WorldGenTreeCulumalda extends WorldGenAbstractTree {
//    public static final IBlockState LOG = TTMFeatures.LOGS.getDefaultState().withProperty(BlockLogs.VARIANT, BlockLogs.EnumType.CULUMALDA);
//    public static final IBlockState LEAF = TTMFeatures.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, BlockLogs.EnumType.CULUMALDA).withProperty(BlockLeaf.CHECK_DECAY, false);
//
//    public WorldGenTreeCulumalda(boolean notify)
//    {
//        super(notify);
//    }
//
//    @Override
//    public boolean generate(World world, Random random, BlockPos pos)
//    {
//        int l = random.nextInt(3) + 4;
//
//        IBlockState j1 = world.getBlockState(pos.down());
//
//        if(j1.getBlock() != Blocks.GRASS && j1.getBlock() != Blocks.DIRT)
//        {
//            return false;
//        }
//
//        this.setBlockAndNotifyAdequately(world, pos.down(), Blocks.GRASS.getDefaultState());
//
//        for(int k1 = (pos.getY() - 3) + l; k1 <= pos.getY() + l; k1++)
//        {
//            int j2 = k1 - (pos.getY() + l);
//            int i3 = 1 - j2 / 2;
//
//            for(int k3 = pos.getX() - i3; k3 <= pos.getX() + i3; k3++)
//            {
//                int l3 = k3 - pos.getX();
//
//                for(int i4 = pos.getZ() - i3; i4 <= pos.getZ() + i3; i4++)
//                {
//                    int j4 = i4 - pos.getZ();
//
//                    BlockPos newPos = new BlockPos(k3, k1, i4);
//
//                    if((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && j2 != 0) && !world.getBlockState(newPos).isOpaqueCube())
//                    {
//                        this.setBlockAndNotifyAdequately(world, newPos, LEAF);
//                    }
//                }
//
//            }
//        }
//
//        for(int l1 = 0; l1 < l; l1++)
//        {
//            BlockPos newPos = new BlockPos(pos.up(l1));
//            IBlockState k2 = world.getBlockState(newPos);
//
//            if(k2.getBlock() == Blocks.AIR || k2 == LEAF)
//            {
//                this.setBlockAndNotifyAdequately(world, newPos, LOG);
//            }
//        }
//
//        return true;
//    }
//}