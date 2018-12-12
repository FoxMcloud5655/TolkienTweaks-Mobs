package com.greatorator.tolkienmobs.entity.passive;

import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.entity.monster.*;
import com.greatorator.tolkienmobs.init.ProfessionInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import javax.annotation.Nullable;

import static net.minecraftforge.fml.common.registry.VillagerRegistry.FARMER;

public class EntityHuman extends EntityVillager implements IEntityAdditionalSpawnData {
    private int texture_index;
    public static final ResourceLocation LOOT = new ResourceLocation(TolkienMobs.MODID, "entities/human");

    public EntityHuman(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 2.0F);
        ((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
        this.texture_index = rand.nextInt(16);
    }

    public int getTextureIndex() {
        return this.texture_index;
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityTroll.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityMordorOrc.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityUrukHai.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityGoblin.class, 12.0F, 0.8D, 0.8D));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityWarg.class, 8.0F, 0.8D, 0.8D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    /** Let's try to decide which entity will do what work */
    public void setProfession(VillagerRegistry.VillagerProfession profession) {
        switch (texture_index) {
            case 0:
                profession = FARMER;
                break;

            case 1:
                profession = ProfessionInit.coin_trader;
                break;

            case 2:
                profession = FARMER;
                break;

            case 3:
                profession = ProfessionInit.grocery_store;

            case 4:
                profession = FARMER;
                break;

            case 5:
                profession = FARMER;
                break;

            case 6:
                profession = FARMER;
                break;

            case 7:
                profession = FARMER;
                break;

            case 8:
                profession = FARMER;
                break;

            case 9:
                profession = FARMER;
                break;

            case 10:
                profession = FARMER;
                break;

            case 11:
                profession = FARMER;
                break;

            case 12:
                profession = FARMER;
                break;

            case 13:
                profession = FARMER;
                break;

            case 14:
                profession = FARMER;
                break;

            case 15:
                profession = FARMER;

        }
        super.setProfession(profession);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 2;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.texture_index);
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        this.texture_index = buffer.readInt();
    }

    public EntityHuman createChild(EntityAgeable ageable)
    {
        EntityHuman entityhuman = new EntityHuman(this.world);
        entityhuman.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityhuman)), (IEntityLivingData)null);
        return entityhuman;
    }
}