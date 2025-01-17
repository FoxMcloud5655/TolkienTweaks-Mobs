package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.CrebainEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.CrebainModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrebainRender extends MobRenderer<CrebainEntity, CrebainModel> {
    public static final ResourceLocation[] THRUSH_TEXTURES = new ResourceLocation[] {new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/crebain.png")};

    public CrebainRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CrebainModel(), 0.3F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getTextureLocation(CrebainEntity entity) {
        return THRUSH_TEXTURES[entity.getVariant()];
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    public float getBob(CrebainEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}