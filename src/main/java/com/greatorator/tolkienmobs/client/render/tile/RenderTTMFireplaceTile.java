package com.greatorator.tolkienmobs.client.render.tile;

import com.brandon3055.brandonscore.client.render.TESRBase;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.block.BlockTTMFireplace;
import com.greatorator.tolkienmobs.tileentity.TTMFireplaceTile;
import com.greatorator.tolkienmobs.world.gen.TTMFeatures;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;

import static net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType.*;

public class RenderTTMFireplaceTile extends TileEntityRenderer<TTMFireplaceTile> {
    public RenderTTMFireplaceTile(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TTMFireplaceTile te, float partialTicks, MatrixStack mStack, IRenderTypeBuffer getter, int packedLight, int packedOverlay) {
        ItemStack stack1 = te.getItem(0);
        ItemStack stack2 = te.getItem(1);
        ItemStack stack3 = te.getItem(3);
        if (stack1.isEmpty() && stack2.isEmpty() && stack3.isEmpty()) return;
        BlockState state = te.getBlockState(TTMContent.TTMFIREPLACE.get());
        Direction facing = state.getValue(BlockTTMFireplace.FACING);
        ItemStack bars = new ItemStack(Blocks.IRON_BARS);
        Minecraft mc = Minecraft.getInstance();

        mStack.pushPose();
        mStack.translate(0.5, 0.5, 0.5);
        mStack.mulPose(new Quaternion(90, 0, 0, true));

        switch (facing) {
            case NORTH:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                break;
            case SOUTH:
                mStack.mulPose(new Quaternion(0, 0, -90, true));
                break;
            case WEST:
                mStack.mulPose(new Quaternion(0, 0, 90, true));
                break;
            case EAST:
                mStack.mulPose(new Quaternion(0, 0, 180, true));
                break;
        }

        mStack.scale(0.85F, 0.85F, 0.85F);
        mc.getItemRenderer().renderStatic(bars, NONE, packedLight, packedOverlay, mStack, getter);

        mStack.translate(0, 0, -0.04);
        mStack.translate(0.1, -0.3, 0);
        if (!stack1.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack1, GROUND, packedLight, packedOverlay, mStack, getter);
        }

        mStack.translate(-0.2, 0.4, 0);
        if (!stack2.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack2, GROUND, packedLight, packedOverlay, mStack, getter);
        }

        mStack.translate(-0.2, -0.4, 0.5);
        mStack.mulPose(new Quaternion(0, 0, 90, true));
        if (!stack3.isEmpty()) {
            mc.getItemRenderer().renderStatic(stack3, GROUND, packedLight, packedOverlay, mStack, getter);
        }

        mStack.popPose();
    }
}