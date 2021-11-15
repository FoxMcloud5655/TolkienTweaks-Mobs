package com.greatorator.tolkienmobs.entity.monster.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Mirkwood Spider - GreatOrator
 */
public class ModelTTMMirkwoodSpider<T extends Entity> extends SegmentedModel<T> {
    public ModelRenderer SpiderHead;
    public ModelRenderer SpiderMandibleA1;
    public ModelRenderer SpiderMandibleA2;
    public ModelRenderer SpiderMandibleB1;
    public ModelRenderer SpiderMandibleB2;
    public ModelRenderer SpiderThorax;
    public ModelRenderer SpiderAbdomen;
    public ModelRenderer SpiderLegA;
    public ModelRenderer SpiderLegA2;
    public ModelRenderer SpiderLegA3;
    public ModelRenderer SpiderLegB;
    public ModelRenderer SpiderLegB2;
    public ModelRenderer SpiderLegB3;
    public ModelRenderer SpiderLegC;
    public ModelRenderer SpiderLegC2;
    public ModelRenderer SpiderLegC3;
    public ModelRenderer SpiderLegD;
    public ModelRenderer SpiderLegD2;
    public ModelRenderer SpiderLegD3;
    public ModelRenderer SpiderLegE;
    public ModelRenderer SpiderLegE2;
    public ModelRenderer SpiderLegE3;
    public ModelRenderer SpiderLegF;
    public ModelRenderer SpiderLegF2;
    public ModelRenderer SpiderLegF3;
    public ModelRenderer SpiderLegG;
    public ModelRenderer SpiderLegG2;
    public ModelRenderer SpiderLegG3;
    public ModelRenderer SpiderLegH;
    public ModelRenderer SpiderLegH2;
    public ModelRenderer SpiderLegH3;

    public ModelTTMMirkwoodSpider() {
        this.texWidth = 128;
        this.texHeight = 64;
        this.SpiderLegH = new ModelRenderer(this, 80, 36);
        this.SpiderLegH.setPos(-4.0F, 16.8F, 2.0F);
        this.SpiderLegH.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH, 0.9162978572970231F, 0.7853981633974483F, 1.0786134777324956F);
        this.SpiderLegD = new ModelRenderer(this, 80, 12);
        this.SpiderLegD.setPos(-4.0F, 16.8F, 0.0F);
        this.SpiderLegD.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD, 0.0F, -0.39269908169872414F, 0.4118977034706618F);
        this.SpiderLegD3 = new ModelRenderer(this, 44, 28);
        this.SpiderLegD3.setPos(-22.1F, 2.4F, 0.0F);
        this.SpiderLegD3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD3, -0.1954768762233649F, 0.0F, -1.3962634015954636F);
        this.SpiderLegC3 = new ModelRenderer(this, 80, 28);
        this.SpiderLegC3.setPos(21.6F, 2.8F, -1.3F);
        this.SpiderLegC3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC3, -0.11519173063162574F, -0.17453292519943295F, 4.537856055185257F);
        this.SpiderLegF2 = new ModelRenderer(this, 80, 20);
        this.SpiderLegF2.setPos(-15.3F, -0.2F, 0.0F);
        this.SpiderLegF2.addBox(-7.4F, -0.7F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF2, 0.0F, 0.0F, -0.2530727415391778F);
        this.SpiderThorax = new ModelRenderer(this, 0, 52);
        this.SpiderThorax.setPos(0.0F, 16.8F, 0.3F);
        this.SpiderThorax.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.SpiderLegG3 = new ModelRenderer(this, 44, 40);
        this.SpiderLegG3.setPos(18.4F, 6.4F, 0.0F);
        this.SpiderLegG3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG3, -0.013962634015954637F, 0.0F, 1.7453292519943295F);
        this.SpiderLegA3 = new ModelRenderer(this, 44, 4);
        this.SpiderLegA3.setPos(19.7F, 1.2F, -4.8F);
        this.SpiderLegA3.addBox(0.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA3, 0.598647933434055F, 0.9337511498169663F, 1.1693705988362009F);
        this.SpiderLegH3 = new ModelRenderer(this, 80, 16);
        this.SpiderLegH3.setPos(-18.4F, 6.4F, 0.0F);
        this.SpiderLegH3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH3, 0.0F, 0.0F, -1.7453292519943295F);
        this.SpiderMandibleA2 = new ModelRenderer(this, 0, 48);
        this.SpiderMandibleA2.setPos(2.6F, 1.5F, -9.5F);
        this.SpiderMandibleA2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleA2, 0.0F, 2.0943951023931953F, 0.0F);
        this.SpiderMandibleB2 = new ModelRenderer(this, 11, 48);
        this.SpiderMandibleB2.setPos(-3.5F, 1.5F, -9.9F);
        this.SpiderMandibleB2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleB2, 0.0F, 1.0471975511965976F, 0.0F);
        this.SpiderAbdomen = new ModelRenderer(this, 0, 0);
        this.SpiderAbdomen.setPos(0.0F, 14.2F, 11.7F);
        this.SpiderAbdomen.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F);
        this.setRotateAngle(SpiderAbdomen, 0.10471975511965977F, 0.0F, 0.0F);
        this.SpiderLegE2 = new ModelRenderer(this, 80, 44);
        this.SpiderLegE2.setPos(14.9F, 0.1F, 0.0F);
        this.SpiderLegE2.addBox(-7.4F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE2, 0.0F, -3.2026791774095944F, 0.7295476273336298F);
        this.SpiderLegB = new ModelRenderer(this, 44, 12);
        this.SpiderLegB.setPos(-4.0F, 16.8F, -1.0F);
        this.SpiderLegB.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB, 0.0F, -0.7853981633974483F, 0.5567600313861911F);
        this.SpiderLegH2 = new ModelRenderer(this, 80, 8);
        this.SpiderLegH2.setPos(-14.5F, 0.0F, 0.0F);
        this.SpiderLegH2.addBox(-7.4F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegH2, 0.0F, 0.0F, -0.9599310885968813F);
        this.SpiderLegG2 = new ModelRenderer(this, 44, 44);
        this.SpiderLegG2.setPos(14.7F, 0.4F, 0.0F);
        this.SpiderLegG2.addBox(-1.0F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG2, 0.0F, 0.0F, 0.9599310885968813F);
        this.SpiderLegE = new ModelRenderer(this, 44, 24);
        this.SpiderLegE.setPos(4.0F, 16.8F, 1.0F);
        this.SpiderLegE.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE, 0.0F, -0.4223696789826278F, -0.5497787143782138F);
        this.SpiderLegF3 = new ModelRenderer(this, 80, 4);
        this.SpiderLegF3.setPos(-21.9F, 2.2F, 0.0F);
        this.SpiderLegF3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF3, 0.019198621771937624F, 0.0F, -1.3962634015954636F);
        this.SpiderMandibleB1 = new ModelRenderer(this, 11, 46);
        this.SpiderMandibleB1.setPos(-3.5F, 1.5F, -6.0F);
        this.SpiderMandibleB1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleB1, 0.0F, 1.5707963267948966F, 0.0F);
        this.SpiderLegG = new ModelRenderer(this, 44, 36);
        this.SpiderLegG.setPos(4.0F, 16.8F, 2.0F);
        this.SpiderLegG.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegG, 0.9162978572970231F, -0.7853981633974483F, -1.0786134777324956F);
        this.SpiderLegA = new ModelRenderer(this, 44, 0);
        this.SpiderLegA.setPos(4.0F, 16.8F, -1.0F);
        this.SpiderLegA.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA, 0.0F, 0.890117918517108F, -0.7853981633974483F);
        this.SpiderLegD2 = new ModelRenderer(this, 80, 32);
        this.SpiderLegD2.setPos(-15.6F, -0.8F, -1.5F);
        this.SpiderLegD2.addBox(-7.4F, -0.3F, 0.6F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegD2, 0.0F, 0.0F, -0.3141592653589793F);
        this.SpiderLegC = new ModelRenderer(this, 80, 0);
        this.SpiderLegC.setPos(4.0F, 16.8F, 0.0F);
        this.SpiderLegC.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC, 0.0F, 0.39269908169872414F, -0.45378560551852565F);
        this.SpiderMandibleA1 = new ModelRenderer(this, 0, 46);
        this.SpiderMandibleA1.setPos(2.5F, 1.5F, -6.0F);
        this.SpiderMandibleA1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(SpiderMandibleA1, 0.0F, 1.5707963267948966F, 0.0F);
        this.SpiderLegC2 = new ModelRenderer(this, 44, 32);
        this.SpiderLegC2.setPos(15.1F, 0.1F, 0.0F);
        this.SpiderLegC2.addBox(-7.4F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegC2, -0.017453292519943295F, 3.3161255787892263F, 0.3490658503988659F);
        this.SpiderLegF = new ModelRenderer(this, 80, 24);
        this.SpiderLegF.setPos(-4.0F, 16.8F, 1.0F);
        this.SpiderLegF.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegF, 0.0F, 0.39269908169872414F, 0.4084070449666731F);
        this.SpiderLegB2 = new ModelRenderer(this, 44, 20);
        this.SpiderLegB2.setPos(-15.0F, -0.6F, -0.2F);
        this.SpiderLegB2.addBox(-7.4F, -0.5F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB2, 0.0F, -0.7853981633974483F, -0.26354471705114374F);
        this.SpiderLegB3 = new ModelRenderer(this, 44, 16);
        this.SpiderLegB3.setPos(-20.0F, 1.4F, -5.4F);
        this.SpiderLegB3.addBox(-15.3F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegB3, 0.6719517620178168F, -0.7853981633974483F, -1.117010721276371F);
        this.SpiderHead = new ModelRenderer(this, 0, 20);
        this.SpiderHead.setPos(0.0F, 16.8F, -4.0F);
        this.SpiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
        this.SpiderLegA2 = new ModelRenderer(this, 44, 8);
        this.SpiderLegA2.setPos(14.8F, 0.0F, -0.1F);
        this.SpiderLegA2.addBox(-0.6F, -1.0F, -1.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegA2, 0.0F, 0.7853981633974483F, 0.26354471705114374F);
        this.SpiderLegE3 = new ModelRenderer(this, 80, 40);
        this.SpiderLegE3.setPos(20.3F, 5.4F, 0.4F);
        this.SpiderLegE3.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F);
        this.setRotateAngle(SpiderLegE3, 0.0F, 0.0F, 1.3962634015954636F);
        this.SpiderLegD.addChild(this.SpiderLegD3);
        this.SpiderLegC.addChild(this.SpiderLegC3);
        this.SpiderLegF.addChild(this.SpiderLegF2);
        this.SpiderLegG.addChild(this.SpiderLegG3);
        this.SpiderLegA.addChild(this.SpiderLegA3);
        this.SpiderLegH.addChild(this.SpiderLegH3);
        this.SpiderHead.addChild(this.SpiderMandibleA2);
        this.SpiderHead.addChild(this.SpiderMandibleB2);
        this.SpiderLegE.addChild(this.SpiderLegE2);
        this.SpiderLegH.addChild(this.SpiderLegH2);
        this.SpiderLegG.addChild(this.SpiderLegG2);
        this.SpiderLegF.addChild(this.SpiderLegF3);
        this.SpiderHead.addChild(this.SpiderMandibleB1);
        this.SpiderLegD.addChild(this.SpiderLegD2);
        this.SpiderHead.addChild(this.SpiderMandibleA1);
        this.SpiderLegC.addChild(this.SpiderLegC2);
        this.SpiderLegB.addChild(this.SpiderLegB2);
        this.SpiderLegB.addChild(this.SpiderLegB3);
        this.SpiderLegA.addChild(this.SpiderLegA2);
        this.SpiderLegE.addChild(this.SpiderLegE3);
   }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.SpiderHead, this.SpiderAbdomen, this.SpiderThorax, this.SpiderLegA, this.SpiderLegB, this.SpiderLegA, this.SpiderLegC, this.SpiderLegD, this.SpiderLegE, this.SpiderLegG, this.SpiderLegH);
    }

    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.SpiderHead.yRot = p_225597_5_ * 0.017453292F;
        this.SpiderHead.xRot = p_225597_6_ * 0.017453292F;
        float lvt_7_1_ = 0.7853982F;
        this.SpiderLegA.zRot = -0.7853982F;
        this.SpiderLegB.zRot = 0.7853982F;
        this.SpiderLegC.zRot = -0.58119464F;
        this.SpiderLegD.zRot = 0.58119464F;
        this.SpiderLegE.zRot = -0.58119464F;
        this.SpiderLegF.zRot = 0.58119464F;
        this.SpiderLegG.zRot = -0.7853982F;
        this.SpiderLegH.zRot = 0.7853982F;
        float lvt_8_1_ = -0.0F;
        float lvt_9_1_ = 0.3926991F;
        this.SpiderLegA.yRot = 0.7853982F;
        this.SpiderLegB.yRot = -0.7853982F;
        this.SpiderLegC.yRot = 0.3926991F;
        this.SpiderLegD.yRot = -0.3926991F;
        this.SpiderLegE.yRot = -0.3926991F;
        this.SpiderLegF.yRot = 0.3926991F;
        this.SpiderLegG.yRot = -0.7853982F;
        this.SpiderLegH.yRot = 0.7853982F;
        float lvt_10_1_ = -(MathHelper.cos(p_225597_2_ * 0.6662F * 2.0F + 0.0F) * 0.4F) * p_225597_3_;
        float lvt_11_1_ = -(MathHelper.cos(p_225597_2_ * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * p_225597_3_;
        float lvt_12_1_ = -(MathHelper.cos(p_225597_2_ * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * p_225597_3_;
        float lvt_13_1_ = -(MathHelper.cos(p_225597_2_ * 0.6662F * 2.0F + 4.712389F) * 0.4F) * p_225597_3_;
        float lvt_14_1_ = Math.abs(MathHelper.sin(p_225597_2_ * 0.6662F + 0.0F) * 0.4F) * p_225597_3_;
        float lvt_15_1_ = Math.abs(MathHelper.sin(p_225597_2_ * 0.6662F + 3.1415927F) * 0.4F) * p_225597_3_;
        float lvt_16_1_ = Math.abs(MathHelper.sin(p_225597_2_ * 0.6662F + 1.5707964F) * 0.4F) * p_225597_3_;
        float lvt_17_1_ = Math.abs(MathHelper.sin(p_225597_2_ * 0.6662F + 4.712389F) * 0.4F) * p_225597_3_;
        ModelRenderer var10000 = this.SpiderLegA;
        var10000.yRot += lvt_10_1_;
        var10000 = this.SpiderLegB;
        var10000.yRot += -lvt_10_1_;
        var10000 = this.SpiderLegC;
        var10000.yRot += lvt_11_1_;
        var10000 = this.SpiderLegD;
        var10000.yRot += -lvt_11_1_;
        var10000 = this.SpiderLegE;
        var10000.yRot += lvt_12_1_;
        var10000 = this.SpiderLegF;
        var10000.yRot += -lvt_12_1_;
        var10000 = this.SpiderLegG;
        var10000.yRot += lvt_13_1_;
        var10000 = this.SpiderLegH;
        var10000.yRot += -lvt_13_1_;
        var10000 = this.SpiderLegA;
        var10000.zRot += lvt_14_1_;
        var10000 = this.SpiderLegB;
        var10000.zRot += -lvt_14_1_;
        var10000 = this.SpiderLegC;
        var10000.zRot += lvt_15_1_;
        var10000 = this.SpiderLegD;
        var10000.zRot += -lvt_15_1_;
        var10000 = this.SpiderLegE;
        var10000.zRot += lvt_16_1_;
        var10000 = this.SpiderLegF;
        var10000.zRot += -lvt_16_1_;
        var10000 = this.SpiderLegG;
        var10000.zRot += lvt_17_1_;
        var10000 = this.SpiderLegH;
        var10000.zRot += -lvt_17_1_;
    }
}