package com.greatorator.tolkienmobs.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DwarfHeldItemLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
   public DwarfHeldItemLayer(IEntityRenderer<T, M> p_i50934_1_) {
      super(p_i50934_1_);
   }

   public void render(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
      boolean flag = p_225628_4_.getMainArm() == HandSide.RIGHT;
      ItemStack itemstack = flag ? p_225628_4_.getOffhandItem() : p_225628_4_.getMainHandItem();
      ItemStack itemstack1 = flag ? p_225628_4_.getMainHandItem() : p_225628_4_.getOffhandItem();
      if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
         p_225628_1_.pushPose();
         if (this.getParentModel().young) {
            float f = 0.5F;
            p_225628_1_.translate(0.0D, 0.75D, 0.0D);
            p_225628_1_.scale(0.5F, 0.5F, 0.5F);
         }

         this.renderArmWithItem(p_225628_4_, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT, p_225628_1_, p_225628_2_, p_225628_3_);
         this.renderArmWithItem(p_225628_4_, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT, p_225628_1_, p_225628_2_, p_225628_3_);
         p_225628_1_.popPose();
      }
   }

   private void renderArmWithItem(LivingEntity p_229135_1_, ItemStack p_229135_2_, ItemCameraTransforms.TransformType p_229135_3_, HandSide p_229135_4_, MatrixStack p_229135_5_, IRenderTypeBuffer p_229135_6_, int p_229135_7_) {
      if (!p_229135_2_.isEmpty()) {
         p_229135_5_.pushPose();
         this.getParentModel().translateToHand(p_229135_4_, p_229135_5_);
         p_229135_5_.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
         p_229135_5_.mulPose(Vector3f.YP.rotationDegrees(180.0F));
         boolean flag = p_229135_4_ == HandSide.LEFT;
         p_229135_5_.translate(-0.1D, 0.125D, -0.44D);
         p_229135_5_.scale(0.75F, 0.75F, 0.75F);
         Minecraft.getInstance().getItemInHandRenderer().renderItem(p_229135_1_, p_229135_2_, p_229135_3_, flag, p_229135_5_, p_229135_6_, p_229135_7_);
         p_229135_5_.popPose();
      }
   }
}