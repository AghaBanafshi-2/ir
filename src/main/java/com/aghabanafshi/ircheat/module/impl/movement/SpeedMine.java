package com.aghabanafshi.ircheat.module.impl.player;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class SpeedMine extends Module {
    public SpeedMine() { super("SpeedMine"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // اعمال Haste سطح 2 (amplifier = 1) برای سرعت تخریب بالا
        mc.player.addStatusEffect(new StatusEffectInstance(
            StatusEffects.HASTE, 40, 1, false, false, false
        ));
    }
}
