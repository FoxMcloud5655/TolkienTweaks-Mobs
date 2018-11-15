package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Mordor Orc - GreatOrator
 */
public class ModelMordorOrc extends ModelBiped {
    public ModelRenderer OrcArmL;
    public ModelRenderer OrcLegL;
    public ModelRenderer OrcHead;
    public ModelRenderer OrcBody;
    public ModelRenderer OrcArmR;
    public ModelRenderer OrcLegR;
    public ModelRenderer OrcArmLUpper;
    public ModelRenderer OrcNose;
    public ModelRenderer OrcBrowL;
    public ModelRenderer OrcBrowR;
    public ModelRenderer OrcJaw;
    public ModelRenderer Tooth1;
    public ModelRenderer Tooth2;
    public ModelRenderer Tooth3;
    public ModelRenderer Tooth4;
    public ModelRenderer OrcHairTop;
    public ModelRenderer OrcHairBack;
    public ModelRenderer OrcEar1;
    public ModelRenderer OrcEar2;
    public ModelRenderer OrcEar3;
    public ModelRenderer OrcEar4;
    public ModelRenderer OrcBodyUpper;
    public ModelRenderer OrcArmRUpper;

    public ModelMordorOrc() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Tooth1 = new ModelRenderer(this, 0, 0);
        this.Tooth1.setRotationPoint(3.7F, -2.0F, -3.7F);
        this.Tooth1.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcHead = new ModelRenderer(this, 0, 0);
        this.OrcHead.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.OrcHead.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8, 0.0F);
        this.OrcArmLUpper = new ModelRenderer(this, 32, 37);
        this.OrcArmLUpper.setRotationPoint(-1.0F, -0.5F, -1.0F);
        this.OrcArmLUpper.addBox(-3.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.OrcArmRUpper = new ModelRenderer(this, 56, 37);
        this.OrcArmRUpper.setRotationPoint(-1.0F, -0.5F, -1.0F);
        this.OrcArmRUpper.addBox(-1.0F, -2.0F, -2.0F, 6, 5, 6, 0.0F);
        this.OrcBody = new ModelRenderer(this, 0, 48);
        this.OrcBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.OrcBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.OrcBrowR = new ModelRenderer(this, 45, 0);
        this.OrcBrowR.setRotationPoint(0.9F, -6.0F, -3.8F);
        this.OrcBrowR.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.OrcHairBack = new ModelRenderer(this, 52, 10);
        this.OrcHairBack.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.OrcHairBack.addBox(-1.5F, -0.5F, -0.5F, 3, 10, 1, 0.0F);
        this.Tooth2 = new ModelRenderer(this, 0, 4);
        this.Tooth2.setRotationPoint(-3.7F, -2.0F, -3.7F);
        this.Tooth2.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcArmL = new ModelRenderer(this, 40, 48);
        this.OrcArmL.setRotationPoint(-7.0F, 2.5F, 0.0F);
        this.OrcArmL.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcBrowL = new ModelRenderer(this, 32, 0);
        this.OrcBrowL.setRotationPoint(-2.8F, -6.0F, -3.8F);
        this.OrcBrowL.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.Tooth3 = new ModelRenderer(this, 4, 0);
        this.Tooth3.setRotationPoint(2.2F, -1.0F, -3.7F);
        this.Tooth3.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcHairTop = new ModelRenderer(this, 32, 10);
        this.OrcHairTop.setRotationPoint(0.0F, -8.0F, -2.5F);
        this.OrcHairTop.addBox(-1.5F, -0.5F, -0.5F, 3, 1, 7, 0.0F);
        this.OrcLegR = new ModelRenderer(this, 72, 48);
        this.OrcLegR.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.OrcLegR.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcArmR = new ModelRenderer(this, 24, 48);
        this.OrcArmR.setRotationPoint(7.0F, 2.5F, 0.0F);
        this.OrcArmR.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcNose = new ModelRenderer(this, 25, 0);
        this.OrcNose.setRotationPoint(0.0F, -3.5F, -3.7F);
        this.OrcNose.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(OrcNose, 0.7853981633974483F, -0.6684611035138281F, -3.6651914291880923F);
        this.OrcBodyUpper = new ModelRenderer(this, 0, 36);
        this.OrcBodyUpper.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.OrcBodyUpper.addBox(-4.0F, 0.0F, -2.0F, 10, 6, 6, 0.0F);
        this.OrcLegL = new ModelRenderer(this, 56, 48);
        this.OrcLegL.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.OrcLegL.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.OrcJaw = new ModelRenderer(this, 32, 3);
        this.OrcJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.OrcJaw.addBox(-4.5F, -1.0F, -4.5F, 9, 3, 4, 0.0F);
        this.Tooth4 = new ModelRenderer(this, 4, 4);
        this.Tooth4.setRotationPoint(-2.2F, -1.0F, -3.7F);
        this.Tooth4.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
        this.OrcEar1 = new ModelRenderer(this, 0, 12);
        this.OrcEar1.setRotationPoint(4.0F, -4.5F, 0.0F);
        this.OrcEar1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(OrcEar1, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcEar2 = new ModelRenderer(this, 0, 12);
        this.OrcEar2.setRotationPoint(4.0F, -3.7F, 0.6F);
        this.OrcEar2.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(OrcEar2, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcEar3 = new ModelRenderer(this, 0, 12);
        this.OrcEar3.setRotationPoint(-4.0F, -4.5F, 0.0F);
        this.OrcEar3.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(OrcEar3, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcEar4 = new ModelRenderer(this, 0, 12);
        this.OrcEar4.setRotationPoint(-4.0F, -3.7F, 0.6F);
        this.OrcEar4.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(OrcEar4, 0.6108652381980153F, 0.0F, 0.0F);
        this.OrcHead.addChild(this.OrcBrowL);
        this.OrcHead.addChild(this.OrcEar4);
        this.OrcHead.addChild(this.Tooth4);
        this.OrcHead.addChild(this.OrcBrowR);
        this.OrcHead.addChild(this.OrcEar1);
        this.OrcHead.addChild(this.OrcNose);
        this.OrcHead.addChild(this.OrcEar2);
        this.OrcArmL.addChild(this.OrcArmLUpper);
        this.OrcHead.addChild(this.OrcJaw);
        this.OrcHead.addChild(this.OrcHairTop);
        this.OrcHead.addChild(this.OrcHairBack);
        this.OrcHead.addChild(this.Tooth1);
        this.OrcHead.addChild(this.Tooth2);
        this.OrcBody.addChild(this.OrcBodyUpper);
        this.OrcHead.addChild(this.OrcEar3);
        this.OrcHead.addChild(this.Tooth3);
        this.OrcArmR.addChild(this.OrcArmRUpper);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.OrcHead.render(f5);
        this.OrcBody.render(f5);
        this.OrcArmL.render(f5);
        this.OrcLegR.render(f5);
        this.OrcArmR.render(f5);
        this.OrcLegL.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.OrcLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.OrcLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.OrcArmR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.OrcArmL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        this.OrcHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.OrcHead.rotateAngleX = headPitch * 0.017453292F;
    }
}