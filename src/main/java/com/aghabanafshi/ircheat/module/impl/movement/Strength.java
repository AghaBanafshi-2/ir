package com.aghabanafshi.ircheat.module.impl.combat;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Strength extends Module {
    public Strength() { super("Strength255"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // اثر Strength سطح 255 (amplifier = 254)
        mc.player.addStatusEffect(new StatusEffectInstance(
            StatusEffects.STRENGTH,
            60,     // مدت: 3 ثانیه
            254,    // سطح 255
            false,  // ambient
            false,  // showParticles
            false   // showIcon
        ));
    }
}
