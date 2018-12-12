package com.greatorator.tolkienmobs.client.render.entity.monster;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.client.render.model.monster.ModelBalrog;
import com.greatorator.tolkienmobs.entity.monster.EntityBalrog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nonnull;

public class RenderBalrog extends RenderLiving<EntityBalrog> {
    private ResourceLocation mobTexture = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/balrog.png");

    public static final RenderBalrog.Factory FACTORY = new RenderBalrog.Factory();

    public RenderBalrog(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelBalrog(), 2.5F);
        this.addLayer(new LayerHeldItem(this){
            @Override
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.LEFT;
                ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
                ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

                if (!itemstack.isEmpty() || !itemstack1.isEmpty())
                {
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(1.5F, 1.5F, 1.5F);

                    if (this.livingEntityRenderer.getMainModel().isChild)
                    {
                        float f = 0.5F;
                        GlStateManager.translate(0.0F, 0.75F, 0.0F);
                        GlStateManager.scale(0.5F, 0.5F, 0.5F);
                    }

                    renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
                    renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
                    GlStateManager.popMatrix();
                }
            }

            private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType transform, EnumHandSide handSide)
            {
                if (!stack.isEmpty())
                {
                    GlStateManager.pushMatrix();

                    if (entity.isSneaking())
                    {
                        GlStateManager.translate(0.0F, 0.2F, 0.0F);
                    }
                    // Forge: moved this call down, fixes incorrect offset while sneaking.
//                    GlStateManager.translate(0.0F, 0.625F, 0);
                    this.translateToHand(handSide);
                    GlStateManager.rotate(-100.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    boolean flag = handSide == EnumHandSide.LEFT;
                    GlStateManager.translate((float)(flag ? 1 : -1) / 8.0F, 1.2F, 0.75F);
                    Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, transform, flag);
                    GlStateManager.popMatrix();
                }
            }

            @Override
            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelBiped)this.livingEntityRenderer.getMainModel()).postRenderArm(-0.08F, p_191361_1_);
            }
        });
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityBalrog entity) {
        return mobTexture;
    }

    public void transformHeldFull3DItemLayer() {
        GlStateManager.translate(-2.0F, 0.1875F, 0.5F);
    }

    public static class Factory implements IRenderFactory<EntityBalrog> {
        @Override
        public Render<? super EntityBalrog> createRenderFor(RenderManager manager) {
            return new RenderBalrog(manager);
        }
    }
}