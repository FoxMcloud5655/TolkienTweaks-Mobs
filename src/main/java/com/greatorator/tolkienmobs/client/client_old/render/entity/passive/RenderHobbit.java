//package com.greatorator.tolkienmobs.client.render.entity.passive;
//
//import com.greatorator.tolkienmobs.TolkienMobs;
//import com.greatorator.tolkienmobs.client.render.model.passive.ModelHobbit;
//import com.greatorator.tolkienmobs.entity.passive.EntityTMHobbit;
//import net.minecraft.client.renderer.entity.Render;
//import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.renderer.entity.RenderManager;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.client.registry.IRenderFactory;
//
//import javax.annotation.Nullable;
//
//public class RenderHobbit extends RenderLiving<EntityTMHobbit> {
//    private static final ResourceLocation[] mobTexture = new ResourceLocation[5];
//    static {
//        for (int i = 0; i < 5; i++) {
//            mobTexture[ i ] = new ResourceLocation(TolkienMobs.MODID + ":textures/entity/hobbit/hobbit" + i + ".png");
//        }
//    }
//
//    public static final RenderHobbit.Factory FACTORY = new RenderHobbit.Factory();
//
//    public RenderHobbit(RenderManager rendermanagerIn) {
//        super(rendermanagerIn, new ModelHobbit(), 0.5F);
//    }
//
//    @Nullable
//    @Override
//    protected ResourceLocation getEntityTexture(EntityTMHobbit entity) {
//        int index = entity.getTextureIndex();
//        return mobTexture[index];
//    }
//
//    public static class Factory implements IRenderFactory<EntityTMHobbit> {
//
//        @Override
//        public Render<? super EntityTMHobbit> createRenderFor(RenderManager manager) {
//            return new RenderHobbit(manager);
//        }
//
//    }
//}