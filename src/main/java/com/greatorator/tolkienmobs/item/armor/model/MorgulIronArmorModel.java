package com.greatorator.tolkienmobs.item.armor.model;//package com.greatorator.tolkienmobs.client.render.model.items;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 *CustomArmor - GreatOrator
 */
public class MorgulIronArmorModel extends BipedModel {
    public ModelRenderer HelmMorgulIron;
    public ModelRenderer ChestMorgulIron;
    public ModelRenderer MorgulIronLA;
    public ModelRenderer MorgulIronRA;
    public ModelRenderer HelmMorgulIronPart;
    public ModelRenderer HelmMorgulIronPart_1;
    public ModelRenderer HelmMorgulIronPart_2;
    public ModelRenderer HelmMorgulIronPart_3;
    public ModelRenderer ChestMorgulIronPart;
    public ModelRenderer MorgulIronLAPart;
    public ModelRenderer MorgulIronRAPart;
    public ModelRenderer MorgulIronLAPart_1;
    public ModelRenderer MorgulIronRAPart_1;
    public ModelRenderer MorgulIronRL;
    public ModelRenderer MorgulIronLL;
    public ModelRenderer MorgulIronBody;
    public ModelRenderer MorgulIronRLPart;
    public ModelRenderer MorgulIronLLPart;
    public ModelRenderer MorgulIronRF;
    public ModelRenderer MorgulIronLF;
    public ModelRenderer MorgulIronRFPart;
    public ModelRenderer MorgulIronLFPart;

    public MorgulIronArmorModel(float slot) {
        super(slot);
        this.texWidth = 128;
        this.texHeight = 128;
        this.MorgulIronRAPart_1 = new ModelRenderer(this, 102, 94);
        this.MorgulIronRAPart_1.mirror = true;
        this.MorgulIronRAPart_1.setPos(-1.0F, 8.5F, -1.0F);
        this.MorgulIronRAPart_1.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
        this.MorgulIronLAPart = new ModelRenderer(this, 102, 103);
        this.MorgulIronLAPart.mirror = true;
        this.MorgulIronLAPart.setPos(-0.5F, 2.0F, -0.5F);
        this.MorgulIronLAPart.addBox(0.0F, 0.0F, 0.0F, 5, 9, 5, 0.0F);
        this.HelmMorgulIronPart_1 = new ModelRenderer(this, 40, 118);
        this.HelmMorgulIronPart_1.setPos(0.0F, 0.0F, 0.0F);
        this.HelmMorgulIronPart_1.addBox(-1.0F, 5.0F, 4.0F, 10, 5, 5, 0.0F);
        this.MorgulIronLA = new ModelRenderer(this, 102, 117);
        this.MorgulIronLA.mirror = true;
        this.MorgulIronLA.setPos(-1.0F, -2.0F, -2.0F);
        this.MorgulIronLA.addBox(-1.0F, -0.3F, -1.0F, 6, 5, 6, 0.0F);
        this.HelmMorgulIronPart_3 = new ModelRenderer(this, 40, 99);
        this.HelmMorgulIronPart_3.setPos(0.0F, 0.0F, 0.0F);
        this.HelmMorgulIronPart_3.addBox(3.0F, -1.0F, -1.0F, 2, 3, 10, 0.0F);
        this.HelmMorgulIronPart_2 = new ModelRenderer(this, 40, 83);
        this.HelmMorgulIronPart_2.setPos(0.0F, 0.0F, 0.0F);
        this.HelmMorgulIronPart_2.addBox(-1.0F, 1.7F, 6.0F, 10, 3, 3, 0.0F);
        this.MorgulIronLAPart_1 = new ModelRenderer(this, 102, 94);
        this.MorgulIronLAPart_1.setPos(-1.0F, 8.5F, -1.0F);
        this.MorgulIronLAPart_1.addBox(0.0F, 0.0F, 0.0F, 6, 3, 6, 0.0F);
        this.HelmMorgulIronPart = new ModelRenderer(this, 0, 100);
        this.HelmMorgulIronPart.setPos(0.0F, 0.0F, 0.0F);
        this.HelmMorgulIronPart.addBox(-1.0F, 4.0F, -1.0F, 10, 4, 10, 0.0F);
        this.ChestMorgulIron = new ModelRenderer(this, 70, 117);
        this.ChestMorgulIron.setPos(-4.0F, 0.0F, -2.0F);
        this.ChestMorgulIron.addBox(-1.0F, -0.4F, -1.0F, 10, 5, 6, 0.0F);
        this.MorgulIronRA = new ModelRenderer(this, 102, 117);
        this.MorgulIronRA.setPos(-3.0F, -2.0F, -2.0F);
        this.MorgulIronRA.addBox(-1.0F, -0.3F, -1.0F, 6, 5, 6, 0.0F);
        this.MorgulIronRAPart = new ModelRenderer(this, 102, 103);
        this.MorgulIronRAPart.mirror = true;
        this.MorgulIronRAPart.setPos(-0.5F, 2.0F, -0.5F);
        this.MorgulIronRAPart.addBox(0.0F, 0.0F, 0.0F, 5, 9, 5, 0.0F);
        this.ChestMorgulIronPart = new ModelRenderer(this, 70, 100);
        this.ChestMorgulIronPart.setPos(0.0F, 0.0F, 0.0F);
        this.ChestMorgulIronPart.addBox(-0.5F, 0.6F, -0.5F, 9, 12, 5, 0.0F);
        this.HelmMorgulIron = new ModelRenderer(this, 0, 86);
        this.HelmMorgulIron.setPos(-4.0F, -8.0F, -4.0F);
        this.HelmMorgulIron.addBox(-0.5F, -0.5F, -0.5F, 9, 5, 9, 0.0F);
        this.MorgulIronLLPart = new ModelRenderer(this, 0, 97);
        this.MorgulIronLLPart.setPos(-0.5F, 3.0F, -0.5F);
        this.MorgulIronLLPart.addBox(-0.5F, 0.1F, -0.5F, 6, 8, 6, 0.0F);
        this.MorgulIronRLPart = new ModelRenderer(this, 0, 97);
        this.MorgulIronRLPart.setPos(-0.5F, 3.0F, -0.5F);
        this.MorgulIronRLPart.addBox(-0.5F, 0.1F, -0.5F, 6, 8, 6, 0.0F);
        this.MorgulIronLL = new ModelRenderer(this, 0, 112);
        this.MorgulIronLL.setPos(-2.0F, 0.0F, -2.0F);
        this.MorgulIronLL.addBox(-0.5F, 0.0F, -0.5F, 5, 11, 5, 0.0F);
        this.MorgulIronRL = new ModelRenderer(this, 0, 112);
        this.MorgulIronRL.setPos(-2.0F, 0.0F, -2.0F);
        this.MorgulIronRL.addBox(-0.5F, 0.0F, -0.5F, 5, 11, 5, 0.0F);
        this.MorgulIronBody = new ModelRenderer(this, 20, 118);
        this.MorgulIronBody.setPos(-4.0F, 0.0F, -2.0F);
        this.MorgulIronBody.addBox(-1.0F, 11.5F, -0.5F, 10, 5, 5, 0.0F);
        this.MorgulIronLF = new ModelRenderer(this, 108, 0);
        this.MorgulIronLF.setPos(-2.0F, 0.0F, -2.0F);
        this.MorgulIronLF.addBox(-0.5F, 8.0F, -0.5F, 5, 4, 5, 0.0F);
        this.MorgulIronRF = new ModelRenderer(this, 108, 0);
        this.MorgulIronRF.setPos(-2.0F, 0.0F, -2.0F);
        this.MorgulIronRF.addBox(-0.5F, 8.0F, -0.5F, 5, 4, 5, 0.0F);
        this.MorgulIronLFPart = new ModelRenderer(this, 104, 9);
        this.MorgulIronLFPart.setPos(0.0F, 0.0F, 0.0F);
        this.MorgulIronLFPart.addBox(-1.0F, 9.0F, -1.0F, 6, 3, 6, 0.0F);
        this.MorgulIronRFPart = new ModelRenderer(this, 104, 9);
        this.MorgulIronRFPart.setPos(0.0F, 0.0F, 0.0F);
        this.MorgulIronRFPart.addBox(-1.0F, 9.0F, -1.0F, 6, 3, 6, 0.0F);
        this.MorgulIronLF.addChild(this.MorgulIronLFPart);
        this.MorgulIronRF.addChild(this.MorgulIronRFPart);
        this.MorgulIronLL.addChild(this.MorgulIronLLPart);
        this.MorgulIronRL.addChild(this.MorgulIronRLPart);
        this.MorgulIronRA.addChild(this.MorgulIronRAPart_1);
        this.MorgulIronLA.addChild(this.MorgulIronLAPart);
        this.HelmMorgulIron.addChild(this.HelmMorgulIronPart_1);
        this.HelmMorgulIron.addChild(this.HelmMorgulIronPart_3);
        this.HelmMorgulIron.addChild(this.HelmMorgulIronPart_2);
        this.MorgulIronLA.addChild(this.MorgulIronLAPart_1);
        this.HelmMorgulIron.addChild(this.HelmMorgulIronPart);
        this.MorgulIronRA.addChild(this.MorgulIronRAPart);
        this.ChestMorgulIron.addChild(this.ChestMorgulIronPart);

        this.head.addChild(HelmMorgulIron);
        this.leftArm.addChild(MorgulIronLA);
        this.rightArm.addChild(MorgulIronRA);
        this.body.addChild(ChestMorgulIron);
        this.body.addChild(MorgulIronBody);
        this.rightLeg.addChild(MorgulIronRL);
        this.leftLeg.addChild(MorgulIronLL);
        this.rightLeg.addChild(MorgulIronRF);
        this.leftLeg.addChild(MorgulIronLF);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}