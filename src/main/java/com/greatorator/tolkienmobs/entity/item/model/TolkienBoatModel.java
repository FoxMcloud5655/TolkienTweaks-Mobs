package com.greatorator.tolkienmobs.entity.item.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.greatorator.tolkienmobs.entity.item.TolkienBoatEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class TolkienBoatModel extends SegmentedModel<TolkienBoatEntity> {
   private final ModelRenderer[] paddles = new ModelRenderer[2];
   private final ModelRenderer waterPatch;
   private final ImmutableList<ModelRenderer> parts;

   public TolkienBoatModel() {
      ModelRenderer[] amodelrenderer = new ModelRenderer[]{(new ModelRenderer(this, 0, 0)).setTexSize(128, 64), (new ModelRenderer(this, 0, 19)).setTexSize(128, 64), (new ModelRenderer(this, 0, 27)).setTexSize(128, 64), (new ModelRenderer(this, 0, 35)).setTexSize(128, 64), (new ModelRenderer(this, 0, 43)).setTexSize(128, 64)};
      int i = 32;
      int j = 6;
      int k = 20;
      int l = 4;
      int i1 = 28;
      amodelrenderer[0].addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
      amodelrenderer[0].setPos(0.0F, 3.0F, 1.0F);
      amodelrenderer[1].addBox(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[1].setPos(-15.0F, 4.0F, 4.0F);
      amodelrenderer[2].addBox(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[2].setPos(15.0F, 4.0F, 0.0F);
      amodelrenderer[3].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[3].setPos(0.0F, 4.0F, -9.0F);
      amodelrenderer[4].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
      amodelrenderer[4].setPos(0.0F, 4.0F, 9.0F);
      amodelrenderer[0].xRot = ((float)Math.PI / 2F);
      amodelrenderer[1].yRot = ((float)Math.PI * 1.5F);
      amodelrenderer[2].yRot = ((float)Math.PI / 2F);
      amodelrenderer[3].yRot = (float)Math.PI;
      this.paddles[0] = this.makePaddle(true);
      this.paddles[0].setPos(3.0F, -5.0F, 9.0F);
      this.paddles[1] = this.makePaddle(false);
      this.paddles[1].setPos(3.0F, -5.0F, -9.0F);
      this.paddles[1].yRot = (float)Math.PI;
      this.paddles[0].zRot = 0.19634955F;
      this.paddles[1].zRot = 0.19634955F;
      this.waterPatch = (new ModelRenderer(this, 0, 0)).setTexSize(128, 64);
      this.waterPatch.addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
      this.waterPatch.setPos(0.0F, -3.0F, 1.0F);
      this.waterPatch.xRot = ((float)Math.PI / 2F);
      Builder<ModelRenderer> builder = ImmutableList.builder();
      builder.addAll(Arrays.asList(amodelrenderer));
      builder.addAll(Arrays.asList(this.paddles));
      this.parts = builder.build();
   }

   @Override
   public void setupAnim(TolkienBoatEntity tolkienBoatEntity, float x, float y, float z, float pitch, float yaw) {
      this.animatePaddle(tolkienBoatEntity, 0, x);
      this.animatePaddle(tolkienBoatEntity, 1, x);
   }

   @Override
   public ImmutableList<ModelRenderer> parts() {
      return this.parts;
   }

   public ModelRenderer waterPatch() {
      return this.waterPatch;
   }

   protected ModelRenderer makePaddle(boolean paddle) {
      ModelRenderer modelrenderer = (new ModelRenderer(this, 62, paddle ? 0 : 20)).setTexSize(128, 64);
      int i = 20;
      int j = 7;
      int k = 6;
      float f = -5.0F;
      modelrenderer.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
      modelrenderer.addBox(paddle ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
      return modelrenderer;
   }

   protected void animatePaddle(TolkienBoatEntity tolkienBoatEntity, int p_228244_2_, float p_228244_3_) {
      float f = tolkienBoatEntity.getRowingTime(p_228244_2_, p_228244_3_);
      ModelRenderer modelrenderer = this.paddles[p_228244_2_];
      modelrenderer.xRot = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 3F), (double)-0.2617994F, (double)((MathHelper.sin(-f) + 1.0F) / 2.0F));
      modelrenderer.yRot = (float)MathHelper.clampedLerp((double)(-(float)Math.PI / 4F), (double)((float)Math.PI / 4F), (double)((MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F));
      if (p_228244_2_ == 1) {
         modelrenderer.yRot = (float)Math.PI - modelrenderer.yRot;
      }
   }
}