package com.aghabanafshi.ircheat.module.impl.movement;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;

public class Fly extends Module {
    public Fly() { super("Fly"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(0.1f);
    }

    @Override
    protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) mc.player.getAbilities().flying = false;
    }
}
