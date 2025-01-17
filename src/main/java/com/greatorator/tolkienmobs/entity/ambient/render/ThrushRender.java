package com.greatorator.tolkienmobs.entity.ambient.render;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.ambient.ThrushEntity;
import com.greatorator.tolkienmobs.entity.ambient.model.ThrushModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrushRender extends MobRenderer<ThrushEntity, ThrushModel> {
    public static final ResourceLocation[] THRUSH_TEXTURES = new ResourceLocation[] {new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png"), new ResourceLocation(TolkienMobs.MODID+":textures/entity/birds/thrush.png")};

    public ThrushRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ThrushModel(), 0.3F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getTextureLocation(ThrushEntity entity) {
        return THRUSH_TEXTURES[entity.getVariant()];
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    @Override
    public float getBob(ThrushEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}