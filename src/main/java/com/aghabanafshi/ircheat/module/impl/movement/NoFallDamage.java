package com.aghabanafshi.ircheat.module.impl.movement;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFallDamage extends Module {
    private int tickCounter = 0;

    public NoFallDamage() { super("NoFallDamage"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        // هر 10 تیک یک بار پکت OnGround ارسال کن
        tickCounter++;
        if (tickCounter >= 10) {
            tickCounter = 0;
            if (mc.player.fallDistance > 2.0f) {
                mc.getNetworkHandler().sendPacket(
                    new PlayerMoveC2SPacket.OnGroundOnly(true, mc.player.horizontalCollision)
                );
            }
        }
    }
}
