package com.greatorator.tolkienmobs.entity.boss;

import com.google.common.collect.Maps;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.datagen.SoundGenerator;
import com.greatorator.tolkienmobs.entity.monster.EntityTTMGoblin;
import com.greatorator.tolkienmobs.utils.TTMRand;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Map;

public class EntityTTMGoblinKing extends EntityTTMGoblin implements IRangedAttackMob {
    private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    private static final DataParameter<Integer> GOBLINKING_TYPE = EntityDataManager.createKey(EntityTTMGoblinKing.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (option) -> {
        option.put(0, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblinking.png"));
        option.put(1, new ResourceLocation(TolkienMobs.MODID, "textures/entity/goblin/goblinking.png"));
    });

    public EntityTTMGoblinKing(EntityType<? extends EntityTTMGoblinKing> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.35D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0D);
    }

    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        SnowballEntity snowballentity = new SnowballEntity(this.world, this);
        double d0 = target.getPosYEye() - (double)1.1F;
        double d1 = target.getPosX() - this.getPosX();
        double d2 = d0 - snowballentity.getPosY();
        double d3 = target.getPosZ() - this.getPosZ();
        float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.addEntity(snowballentity);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundGenerator.soundIdleGoblin.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundGenerator.soundHurtGoblin.get();
    }

    protected SoundEvent getDeathSound()
    {
        return SoundGenerator.soundDeathGoblin.get();
    }

    /**
     * Region for determining random skin
     */
    public ResourceLocation getGoblinKingTypeName() {
        return TEXTURE_BY_ID.getOrDefault(this.getGoblinKingType(), TEXTURE_BY_ID.get(0));
    }

    public int getGoblinKingType() {
        return this.dataManager.get(GOBLINKING_TYPE);
    }

    public void setGoblinKingType(int type) {
        if (type < 0 || type >= 3) {
            type = this.rand.nextInt(2);
        }

        this.dataManager.set(GOBLINKING_TYPE, type);
    }

    @Nullable
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int job = TTMRand.getRandomInteger(1, 1);
        this.setGoblinKingType(job);

        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GOBLINKING_TYPE, 1);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("GoblinKingType", this.getGoblinKingType());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setGoblinKingType(compound.getInt("GoblinKingType"));
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    public void setCustomName(@Nullable ITextComponent name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    protected void updateAITasks() {
        if (this.ticksExisted % 20 == 0) {
            this.heal(1.0F);
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}