package com.greatorator.tolkienmobs.item.potion.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class FearTTMEffect extends TTMEffectBase {
    public static FearTTMEffect instance = null;
    public static int fearDuration = 10;

    public FearTTMEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.addEffect((new EffectInstance(Effects.WITHER, 40, amplifier, true, false, false, null)));
        entity.addEffect((new EffectInstance(Effects.BLINDNESS, 40, amplifier, true, false, false, null)));
        entity.addEffect((new EffectInstance(Effects.WEAKNESS, 40, amplifier, true, false, false, null)));
        entity.addEffect((new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, amplifier, true, false, false, null)));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % fearDuration == 0;
    }
}