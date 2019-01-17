package com.greatorator.tolkienmobs.entity.boss;

import com.greatorator.tolkienmobs.entity.entityai.FellBeastPhaseManager;
import com.greatorator.tolkienmobs.init.LootInit;
import com.greatorator.tolkienmobs.utils.LogHelperTTM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.dragon.phase.IPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathHeap;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EntityFellBeast extends EntityDragon {
	public MultiPartEntityPart[] fellbeastPartArray;
    /** The head bounding box of a fell beast */
    public MultiPartEntityPart fellbeastPartHead = new MultiPartEntityPart(this, "head", 6.0F, 6.0F);
    public MultiPartEntityPart fellbeastPartNeck = new MultiPartEntityPart(this, "neck", 6.0F, 6.0F);
    /** The body bounding box of a fell beast */
    public MultiPartEntityPart fellbeastPartBody = new MultiPartEntityPart(this, "body", 8.0F, 8.0F);
    public MultiPartEntityPart fellbeastPartTail1 = new MultiPartEntityPart(this, "tail", 4.0F, 4.0F);
    public MultiPartEntityPart fellbeastPartTail2 = new MultiPartEntityPart(this, "tail", 4.0F, 4.0F);
    public MultiPartEntityPart fellbeastPartTail3 = new MultiPartEntityPart(this, "tail", 4.0F, 4.0F);
    public MultiPartEntityPart fellbeastPartWing1 = new MultiPartEntityPart(this, "wing", 4.0F, 4.0F);
    public MultiPartEntityPart fellbeastPartWing2 = new MultiPartEntityPart(this, "wing", 4.0F, 4.0F);

    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    private final FellBeastPhaseManager fellbeastphaseManager;
    private int growlTime = 200;
    private int sittingDamageReceived;

    private final PathPoint[] pathPoints = new PathPoint[24];

    private final int[] neighbors = new int[24];
    private final PathHeap pathFindQueue = new PathHeap();

    public EntityFellBeast(World worldIn) {
        super(worldIn);
        this.fellbeastPartArray = new MultiPartEntityPart[] {this.fellbeastPartHead, this.fellbeastPartNeck, this.fellbeastPartBody, this.fellbeastPartTail1, this.fellbeastPartTail2, this.fellbeastPartTail3, this.fellbeastPartWing1, this.fellbeastPartWing2};
        this.setHealth(this.getMaxHealth());
        this.setSize(16.0F, 8.0F);
        this.noClip = true;
        this.isImmuneToFire = true;
        this.growlTime = 100;
        this.ignoreFrustumCheck = true;

        this.fellbeastphaseManager = new FellBeastPhaseManager(this);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootInit.FELLBEAST;
    }

    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    public void onLivingUpdate()
    {
        if (this.world.isRemote)
        {
            this.setHealth(this.getHealth());

            if (!this.isSilent())
            {
                float f = MathHelper.cos(this.animTime * ((float)Math.PI * 2F));
                float f1 = MathHelper.cos(this.prevAnimTime * ((float)Math.PI * 2F));

                if (f1 <= -0.3F && f >= -0.3F)
                {
                    this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ENDERDRAGON_FLAP, this.getSoundCategory(), 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
                }

                if (!this.fellbeastphaseManager.getCurrentPhase().getIsStationary() && --this.growlTime < 0)
                {
                    this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_ENDERDRAGON_GROWL, this.getSoundCategory(), 2.5F, 0.8F + this.rand.nextFloat() * 0.3F, false);
                    this.growlTime = 200 + this.rand.nextInt(200);
                }
            }
        }

        this.prevAnimTime = this.animTime;

        if (this.getHealth() <= 0.0F)
        {
            float f12 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f13 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f15 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + (double)f12, this.posY + 2.0D + (double)f13, this.posZ + (double)f15, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            this.updateFellBeastEnderCrystal();
            float f11 = 0.2F / (MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
            f11 = f11 * (float)Math.pow(2.0D, this.motionY);

            if (this.fellbeastphaseManager.getCurrentPhase().getIsStationary())
            {
                this.animTime += 0.1F;
            }
            else if (this.slowed)
            {
                this.animTime += f11 * 0.5F;
            }
            else
            {
                this.animTime += f11;
            }

            this.rotationYaw = MathHelper.wrapDegrees(this.rotationYaw);

            if (this.isAIDisabled())
            {
                this.animTime = 0.5F;
            }
            else
            {
                if (this.ringBufferIndex < 0)
                {
                    for (int i = 0; i < this.ringBuffer.length; ++i)
                    {
                        this.ringBuffer[i][0] = (double)this.rotationYaw;
                        this.ringBuffer[i][1] = this.posY;
                    }
                }

                if (++this.ringBufferIndex == this.ringBuffer.length)
                {
                    this.ringBufferIndex = 0;
                }

                this.ringBuffer[this.ringBufferIndex][0] = (double)this.rotationYaw;
                this.ringBuffer[this.ringBufferIndex][1] = this.posY;

                if (this.world.isRemote)
                {
                    if (this.newPosRotationIncrements > 0)
                    {
                        double d5 = this.posX + (this.interpTargetX - this.posX) / (double)this.newPosRotationIncrements;
                        double d0 = this.posY + (this.interpTargetY - this.posY) / (double)this.newPosRotationIncrements;
                        double d1 = this.posZ + (this.interpTargetZ - this.posZ) / (double)this.newPosRotationIncrements;
                        double d2 = MathHelper.wrapDegrees(this.interpTargetYaw - (double)this.rotationYaw);
                        this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
                        this.rotationPitch = (float)((double)this.rotationPitch + (this.interpTargetPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
                        --this.newPosRotationIncrements;
                        this.setPosition(d5, d0, d1);
                        this.setRotation(this.rotationYaw, this.rotationPitch);
                    }

                    this.fellbeastphaseManager.getCurrentPhase().doClientRenderEffects();
                }
                else
                {
                    IPhase iphase = this.fellbeastphaseManager.getCurrentPhase();
                    iphase.doLocalUpdate();

                    if (this.fellbeastphaseManager.getCurrentPhase() != iphase)
                    {
                        iphase = this.fellbeastphaseManager.getCurrentPhase();
                        iphase.doLocalUpdate();
                    }

                    Vec3d vec3d = iphase.getTargetLocation();

                    if (vec3d != null)
                    {
                        double d6 = vec3d.x - this.posX;
                        double d7 = vec3d.y - this.posY;
                        double d8 = vec3d.z - this.posZ;
                        double d3 = d6 * d6 + d7 * d7 + d8 * d8;
                        float f5 = iphase.getMaxRiseOrFall();
                        d7 = MathHelper.clamp(d7 / (double)MathHelper.sqrt(d6 * d6 + d8 * d8), (double)(-f5), (double)f5);
                        this.motionY += d7 * 0.10000000149011612D;
                        this.rotationYaw = MathHelper.wrapDegrees(this.rotationYaw);
                        double d4 = MathHelper.clamp(MathHelper.wrapDegrees(180.0D - MathHelper.atan2(d6, d8) * (180D / Math.PI) - (double)this.rotationYaw), -50.0D, 50.0D);
                        Vec3d vec3d1 = (new Vec3d(vec3d.x - this.posX, vec3d.y - this.posY, vec3d.z - this.posZ)).normalize();
                        Vec3d vec3d2 = (new Vec3d((double)MathHelper.sin(this.rotationYaw * 0.017453292F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * 0.017453292F)))).normalize();
                        float f7 = Math.max(((float)vec3d2.dotProduct(vec3d1) + 0.5F) / 1.5F, 0.0F);
                        this.randomYawVelocity *= 0.8F;
                        this.randomYawVelocity = (float)((double)this.randomYawVelocity + d4 * (double)iphase.getYawFactor());
                        this.rotationYaw += this.randomYawVelocity * 0.1F;
                        float f8 = (float)(2.0D / (d3 + 1.0D));
                        float f9 = 0.06F;
                        this.moveRelative(0.0F, 0.0F, -1.0F, 0.06F * (f7 * f8 + (1.0F - f8)));

                        if (this.slowed)
                        {
                            this.move(MoverType.SELF, this.motionX * 0.800000011920929D, this.motionY * 0.800000011920929D, this.motionZ * 0.800000011920929D);
                        }
                        else
                        {
                            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                        }

                        Vec3d vec3d3 = (new Vec3d(this.motionX, this.motionY, this.motionZ)).normalize();
                        float f10 = ((float)vec3d3.dotProduct(vec3d2) + 1.0F) / 2.0F;
                        f10 = 0.8F + 0.15F * f10;
                        this.motionX *= (double)f10;
                        this.motionZ *= (double)f10;
                        this.motionY *= 0.9100000262260437D;
                    }
                }

                this.renderYawOffset = this.rotationYaw;
                this.fellbeastPartHead.width = 1.0F;
                this.fellbeastPartHead.height = 1.0F;
                this.fellbeastPartNeck.width = 3.0F;
                this.fellbeastPartNeck.height = 3.0F;
                this.fellbeastPartTail1.width = 2.0F;
                this.fellbeastPartTail1.height = 2.0F;
                this.fellbeastPartTail2.width = 2.0F;
                this.fellbeastPartTail2.height = 2.0F;
                this.fellbeastPartTail3.width = 2.0F;
                this.fellbeastPartTail3.height = 2.0F;
                this.fellbeastPartBody.height = 3.0F;
                this.fellbeastPartBody.width = 5.0F;
                this.fellbeastPartWing1.height = 2.0F;
                this.fellbeastPartWing1.width = 4.0F;
                this.fellbeastPartWing2.height = 3.0F;
                this.fellbeastPartWing2.width = 4.0F;
                Vec3d[] avec3d = new Vec3d[this.fellbeastPartArray.length];

                for (int j = 0; j < this.fellbeastPartArray.length; ++j)
                {
                    avec3d[j] = new Vec3d(this.fellbeastPartArray[j].posX, this.fellbeastPartArray[j].posY, this.fellbeastPartArray[j].posZ);
                }

                float f14 = (float)(this.getMovementOffsets(5, 1.0F)[1] - this.getMovementOffsets(10, 1.0F)[1]) * 10.0F * 0.017453292F;
                float f16 = MathHelper.cos(f14);
                float f2 = MathHelper.sin(f14);
                float f17 = this.rotationYaw * 0.017453292F;
                float f3 = MathHelper.sin(f17);
                float f18 = MathHelper.cos(f17);
                this.fellbeastPartBody.onUpdate();
                this.fellbeastPartBody.setLocationAndAngles(this.posX + (double)(f3 * 0.5F), this.posY, this.posZ - (double)(f18 * 0.5F), 0.0F, 0.0F);
                this.fellbeastPartWing1.onUpdate();
                this.fellbeastPartWing1.setLocationAndAngles(this.posX + (double)(f18 * 4.5F), this.posY + 2.0D, this.posZ + (double)(f3 * 4.5F), 0.0F, 0.0F);
                this.fellbeastPartWing2.onUpdate();
                this.fellbeastPartWing2.setLocationAndAngles(this.posX - (double)(f18 * 4.5F), this.posY + 2.0D, this.posZ - (double)(f3 * 4.5F), 0.0F, 0.0F);

                if (!this.world.isRemote && this.hurtTime == 0)
                {
                    this.collideWithEntities(this.world.getEntitiesWithinAABBExcludingEntity(this, this.fellbeastPartWing1.getEntityBoundingBox().grow(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                    this.collideWithEntities(this.world.getEntitiesWithinAABBExcludingEntity(this, this.fellbeastPartWing2.getEntityBoundingBox().grow(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
                    this.attackEntitiesInList(this.world.getEntitiesWithinAABBExcludingEntity(this, this.fellbeastPartHead.getEntityBoundingBox().grow(1.0D)));
                    this.attackEntitiesInList(this.world.getEntitiesWithinAABBExcludingEntity(this, this.fellbeastPartNeck.getEntityBoundingBox().grow(1.0D)));
                }

                double[] adouble = this.getMovementOffsets(5, 1.0F);
                float f19 = MathHelper.sin(this.rotationYaw * 0.017453292F - this.randomYawVelocity * 0.01F);
                float f4 = MathHelper.cos(this.rotationYaw * 0.017453292F - this.randomYawVelocity * 0.01F);
                this.fellbeastPartHead.onUpdate();
                this.fellbeastPartNeck.onUpdate();
                float f20 = this.getHeadYOffset(1.0F);
                this.fellbeastPartHead.setLocationAndAngles(this.posX + (double)(f19 * 6.5F * f16), this.posY + (double)f20 + (double)(f2 * 6.5F), this.posZ - (double)(f4 * 6.5F * f16), 0.0F, 0.0F);
                this.fellbeastPartNeck.setLocationAndAngles(this.posX + (double)(f19 * 5.5F * f16), this.posY + (double)f20 + (double)(f2 * 5.5F), this.posZ - (double)(f4 * 5.5F * f16), 0.0F, 0.0F);

                for (int k = 0; k < 3; ++k)
                {
                    MultiPartEntityPart multipartentitypart = null;

                    if (k == 0)
                    {
                        multipartentitypart = this.fellbeastPartTail1;
                    }

                    if (k == 1)
                    {
                        multipartentitypart = this.fellbeastPartTail2;
                    }

                    if (k == 2)
                    {
                        multipartentitypart = this.fellbeastPartTail3;
                    }

                    double[] adouble1 = this.getMovementOffsets(12 + k * 2, 1.0F);
                    float f21 = this.rotationYaw * 0.017453292F + this.simplifyAngle(adouble1[0] - adouble[0]) * 0.017453292F;
                    float f6 = MathHelper.sin(f21);
                    float f22 = MathHelper.cos(f21);
                    float f23 = 1.5F;
                    float f24 = (float)(k + 1) * 2.0F;
                    multipartentitypart.onUpdate();
                    multipartentitypart.setLocationAndAngles(this.posX - (double)((f3 * 1.5F + f6 * f24) * f16), this.posY + (adouble1[1] - adouble[1]) - (double)((f24 + 1.5F) * f2) + 1.5D, this.posZ + (double)((f18 * 1.5F + f22 * f24) * f16), 0.0F, 0.0F);
                }

                for (int l = 0; l < this.fellbeastPartArray.length; ++l)
                {
                    this.fellbeastPartArray[l].prevPosX = avec3d[l].x;
                    this.fellbeastPartArray[l].prevPosY = avec3d[l].y;
                    this.fellbeastPartArray[l].prevPosZ = avec3d[l].z;
                }
            }
        }
    }

    private void updateFellBeastEnderCrystal()
    {
        if (this.healingEnderCrystal != null)
        {
            if (this.healingEnderCrystal.isDead)
            {
                this.healingEnderCrystal = null;
            }
            else if (this.ticksExisted % 10 == 0 && this.getHealth() < this.getMaxHealth())
            {
                this.setHealth(this.getHealth() + 1.0F);
            }
        }

        if (this.rand.nextInt(10) == 0)
        {
            List<EntityEnderCrystal> list = this.world.<EntityEnderCrystal>getEntitiesWithinAABB(EntityEnderCrystal.class, this.getEntityBoundingBox().grow(32.0D));
            EntityEnderCrystal entityendercrystal = null;
            double d0 = Double.MAX_VALUE;

            for (EntityEnderCrystal entityendercrystal1 : list)
            {
                double d1 = entityendercrystal1.getDistanceSq(this);

                if (d1 < d0)
                {
                    d0 = d1;
                    entityendercrystal = entityendercrystal1;
                }
            }

            this.healingEnderCrystal = entityendercrystal;
        }
    }

    private void collideWithEntities(List<Entity> p_70970_1_)
    {
        double d0 = (this.fellbeastPartBody.getEntityBoundingBox().minX + this.fellbeastPartBody.getEntityBoundingBox().maxX) / 2.0D;
        double d1 = (this.fellbeastPartBody.getEntityBoundingBox().minZ + this.fellbeastPartBody.getEntityBoundingBox().maxZ) / 2.0D;

        for (Entity entity : p_70970_1_)
        {
            if (entity instanceof EntityLivingBase)
            {
                double d2 = entity.posX - d0;
                double d3 = entity.posZ - d1;
                double d4 = d2 * d2 + d3 * d3;
                entity.addVelocity(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);

                if (!this.fellbeastphaseManager.getCurrentPhase().getIsStationary() && ((EntityLivingBase)entity).getRevengeTimer() < entity.ticksExisted - 2)
                {
                    entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5.0F);
                    this.applyEnchantments(this, entity);
                }
            }
        }
    }

    public boolean attackEntityFromPart(MultiPartEntityPart fellbeastPart, DamageSource source, float damage)
    {
        damage = this.fellbeastphaseManager.getCurrentPhase().getAdjustedDamage(fellbeastPart, source, damage);

        if (fellbeastPart != this.fellbeastPartHead)
        {
            damage = damage / 4.0F + Math.min(damage, 1.0F);
        }

        if (damage < 0.01F)
        {
            return false;
        }
        else
        {
            if (source.getTrueSource() instanceof EntityPlayer || source.isExplosion())
            {
                float f = this.getHealth();
                this.attackFellBeastFrom(source, damage);

                if (this.getHealth() <= 0.0F && !this.fellbeastphaseManager.getCurrentPhase().getIsStationary())
                {
                    this.setHealth(1.0F);
                    this.fellbeastphaseManager.setPhase(PhaseList.DYING);
                }

                if (this.fellbeastphaseManager.getCurrentPhase().getIsStationary())
                {
                    this.sittingDamageReceived = (int)((float)this.sittingDamageReceived + (f - this.getHealth()));

                    if ((float)this.sittingDamageReceived > 0.25F * this.getMaxHealth())
                    {
                        this.sittingDamageReceived = 0;
                        this.fellbeastphaseManager.setPhase(PhaseList.TAKEOFF);
                    }
                }
            }

            return true;
        }
    }

    private void attackEntitiesInList(List<Entity> p_70971_1_)
    {
        for (int i = 0; i < p_70971_1_.size(); ++i)
        {
            Entity entity = p_70971_1_.get(i);

            if (entity instanceof EntityLivingBase)
            {
                entity.attackEntityFrom(DamageSource.causeMobDamage(this), 10.0F);
                this.applyEnchantments(this, entity);
            }
        }
    }

    private float getHeadYOffset(float p_184662_1_)
    {
        double d0;

        if (this.fellbeastphaseManager.getCurrentPhase().getIsStationary())
        {
            d0 = -1.0D;
        }
        else
        {
            double[] adouble = this.getMovementOffsets(5, 1.0F);
            double[] adouble1 = this.getMovementOffsets(0, 1.0F);
            d0 = adouble[1] - adouble1[1];
        }

        return (float)d0;
    }

    private float simplifyAngle(double p_70973_1_)
    {
        return (float)MathHelper.wrapDegrees(p_70973_1_);
    }

    private boolean destroyBlocksInAABB(AxisAlignedBB p_70972_1_)
    {
        int i = MathHelper.floor(p_70972_1_.minX);
        int j = MathHelper.floor(p_70972_1_.minY);
        int k = MathHelper.floor(p_70972_1_.minZ);
        int l = MathHelper.floor(p_70972_1_.maxX);
        int i1 = MathHelper.floor(p_70972_1_.maxY);
        int j1 = MathHelper.floor(p_70972_1_.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for (int k1 = i; k1 <= l; ++k1)
        {
            for (int l1 = j; l1 <= i1; ++l1)
            {
                for (int i2 = k; i2 <= j1; ++i2)
                {
                    BlockPos blockpos = new BlockPos(k1, l1, i2);
                    IBlockState iblockstate = this.world.getBlockState(blockpos);
                    Block block = iblockstate.getBlock();

                    if (!block.isAir(iblockstate, this.world, blockpos) && iblockstate.getMaterial() != Material.FIRE)
                    {
                        if (!this.world.getGameRules().getBoolean("mobGriefing"))
                        {
                            flag = true;
                        }
                        else if (block.canEntityDestroy(iblockstate, this.world, blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, iblockstate))
                        {
                            if (block != Blocks.COMMAND_BLOCK && block != Blocks.REPEATING_COMMAND_BLOCK && block != Blocks.CHAIN_COMMAND_BLOCK && block != Blocks.IRON_BARS && block != Blocks.END_GATEWAY)
                            {
                                flag1 = this.world.setBlockToAir(blockpos) || flag1;
                            }
                            else
                            {
                                flag = true;
                            }
                        }
                        else
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1)
        {
            double d0 = p_70972_1_.minX + (p_70972_1_.maxX - p_70972_1_.minX) * (double)this.rand.nextFloat();
            double d1 = p_70972_1_.minY + (p_70972_1_.maxY - p_70972_1_.minY) * (double)this.rand.nextFloat();
            double d2 = p_70972_1_.minZ + (p_70972_1_.maxZ - p_70972_1_.minZ) * (double)this.rand.nextFloat();
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        return flag;
    }
    public int initPathPoints()
    {
        if (this.pathPoints[0] == null)
        {
            for (int i = 0; i < 24; ++i)
            {
                int j = 5;
                int l;
                int i1;

                if (i < 12)
                {
                    l = (int)(60.0F * MathHelper.cos(2.0F * (-(float)Math.PI + 0.2617994F * (float)i)));
                    i1 = (int)(60.0F * MathHelper.sin(2.0F * (-(float)Math.PI + 0.2617994F * (float)i)));
                }
                else if (i < 20)
                {
                    int lvt_3_1_ = i - 12;
                    l = (int)(40.0F * MathHelper.cos(2.0F * (-(float)Math.PI + 0.3926991F * (float)lvt_3_1_)));
                    i1 = (int)(40.0F * MathHelper.sin(2.0F * (-(float)Math.PI + 0.3926991F * (float)lvt_3_1_)));
                    j += 10;
                }
                else
                {
                    int k1 = i - 20;
                    l = (int)(20.0F * MathHelper.cos(2.0F * (-(float)Math.PI + ((float)Math.PI / 4F) * (float)k1)));
                    i1 = (int)(20.0F * MathHelper.sin(2.0F * (-(float)Math.PI + ((float)Math.PI / 4F) * (float)k1)));
                }

                int j1 = Math.max(this.world.getSeaLevel() + 10, this.world.getTopSolidOrLiquidBlock(new BlockPos(l, 0, i1)).getY() + j);
                this.pathPoints[i] = new PathPoint(l, j1, i1);
            }

            this.neighbors[0] = 6146;
            this.neighbors[1] = 8197;
            this.neighbors[2] = 8202;
            this.neighbors[3] = 16404;
            this.neighbors[4] = 32808;
            this.neighbors[5] = 32848;
            this.neighbors[6] = 65696;
            this.neighbors[7] = 131392;
            this.neighbors[8] = 131712;
            this.neighbors[9] = 263424;
            this.neighbors[10] = 526848;
            this.neighbors[11] = 525313;
            this.neighbors[12] = 1581057;
            this.neighbors[13] = 3166214;
            this.neighbors[14] = 2138120;
            this.neighbors[15] = 6373424;
            this.neighbors[16] = 4358208;
            this.neighbors[17] = 12910976;
            this.neighbors[18] = 9044480;
            this.neighbors[19] = 9706496;
            this.neighbors[20] = 15216640;
            this.neighbors[21] = 13688832;
            this.neighbors[22] = 11763712;
            this.neighbors[23] = 8257536;
        }

        return this.getNearestPpIdx(this.posX, this.posY, this.posZ);
    }

    public int getNearestPpIdx(double x, double y, double z)
    {
        float f = 10000.0F;
        int i = 0;
        PathPoint pathpoint = new PathPoint(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
        int j = 0;

        for (int k = j; k < 24; ++k)
        {
            if (this.pathPoints[k] != null)
            {
                float f1 = this.pathPoints[k].distanceToSquared(pathpoint);

                if (f1 < f)
                {
                    f = f1;
                    i = k;
                }
            }
        }

        return i;
    }

    @Nullable
    public Path findPath(int startIdx, int finishIdx, @Nullable PathPoint andThen)
    {
        for (int i = 0; i < 24; ++i)
        {
            PathPoint pathpoint = this.pathPoints[i];
            pathpoint.visited = false;
            pathpoint.distanceToTarget = 0.0F;
            pathpoint.totalPathDistance = 0.0F;
            pathpoint.distanceToNext = 0.0F;
            pathpoint.previous = null;
            pathpoint.index = -1;
        }

        PathPoint pathpoint4 = this.pathPoints[startIdx];
        PathPoint pathpoint5 = this.pathPoints[finishIdx];
        pathpoint4.totalPathDistance = 0.0F;
        pathpoint4.distanceToNext = pathpoint4.distanceTo(pathpoint5);
        pathpoint4.distanceToTarget = pathpoint4.distanceToNext;
        this.pathFindQueue.clearPath();
        this.pathFindQueue.addPoint(pathpoint4);
        PathPoint pathpoint1 = pathpoint4;
        int j = 0;

        while (!this.pathFindQueue.isPathEmpty())
        {
            PathPoint pathpoint2 = this.pathFindQueue.dequeue();

            if (pathpoint2.equals(pathpoint5))
            {
                if (andThen != null)
                {
                    andThen.previous = pathpoint5;
                    pathpoint5 = andThen;
                }

                return this.makePath(pathpoint4, pathpoint5);
            }

            if (pathpoint2.distanceTo(pathpoint5) < pathpoint1.distanceTo(pathpoint5))
            {
                pathpoint1 = pathpoint2;
            }

            pathpoint2.visited = true;
            int k = 0;

            for (int l = 0; l < 24; ++l)
            {
                if (this.pathPoints[l] == pathpoint2)
                {
                    k = l;
                    break;
                }
            }

            for (int i1 = j; i1 < 24; ++i1)
            {
                if ((this.neighbors[k] & 1 << i1) > 0)
                {
                    PathPoint pathpoint3 = this.pathPoints[i1];

                    if (!pathpoint3.visited)
                    {
                        float f = pathpoint2.totalPathDistance + pathpoint2.distanceTo(pathpoint3);

                        if (!pathpoint3.isAssigned() || f < pathpoint3.totalPathDistance)
                        {
                            pathpoint3.previous = pathpoint2;
                            pathpoint3.totalPathDistance = f;
                            pathpoint3.distanceToNext = pathpoint3.distanceTo(pathpoint5);

                            if (pathpoint3.isAssigned())
                            {
                                this.pathFindQueue.changeDistance(pathpoint3, pathpoint3.totalPathDistance + pathpoint3.distanceToNext);
                            }
                            else
                            {
                                pathpoint3.distanceToTarget = pathpoint3.totalPathDistance + pathpoint3.distanceToNext;
                                this.pathFindQueue.addPoint(pathpoint3);
                            }
                        }
                    }
                }
            }
        }

        if (pathpoint1 == pathpoint4)
        {
            return null;
        }
        else
        {
            LogHelperTTM.debug("Failed to find path from {} to {}", Integer.valueOf(startIdx), Integer.valueOf(finishIdx));

            if (andThen != null)
            {
                andThen.previous = pathpoint1;
                pathpoint1 = andThen;
            }

            return this.makePath(pathpoint4, pathpoint1);
        }
    }

    private Path makePath(PathPoint start, PathPoint finish)
    {
        int i = 1;

        for (PathPoint pathpoint = finish; pathpoint.previous != null; pathpoint = pathpoint.previous)
        {
            ++i;
        }

        PathPoint[] apathpoint = new PathPoint[i];
        PathPoint pathpoint1 = finish;
        --i;

        for (apathpoint[i] = finish; pathpoint1.previous != null; apathpoint[i] = pathpoint1)
        {
            pathpoint1 = pathpoint1.previous;
            --i;
        }

        return new Path(apathpoint);
    }

    protected boolean attackFellBeastFrom(DamageSource source, float amount)
    {
        return super.attackEntityFrom(source, amount);
    }

    public void onKillCommand()
    {
        this.setDead();
    }

    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
        int i = 500;

        if (!this.world.isRemote)
        {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0 && flag)
            {
                this.dropExperience(MathHelper.floor((float)i * 0.08F));
            }

            if (this.deathTicks == 1)
            {
                this.world.playBroadcastSound(1028, new BlockPos(this), 0);
            }
        }

        this.move(MoverType.SELF, 0.0D, 0.10000000149011612D, 0.0D);
        this.rotationYaw += 20.0F;
        this.renderYawOffset = this.rotationYaw;

        if (this.deathTicks == 200 && !this.world.isRemote)
        {
            if (flag)
            {
                this.dropExperience(MathHelper.floor((float)i * 0.2F));
            }

            this.setDead();
        }
    }

    private void dropExperience(int p_184668_1_)
    {
        while (p_184668_1_ > 0)
        {
            int i = EntityXPOrb.getXPSplit(p_184668_1_);
            p_184668_1_ -= i;
            this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, i));
        }
    }

    protected boolean canBeRidden(Entity entityIn)
    {
        return true;
    }

    public boolean isNonBoss()
    {
        return false;
    }
}
