package com.aghabanafshi.ircheat.module.impl.movement;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

public class BoatFly extends Module {
    public BoatFly() { super("BoatFly"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || !(mc.player.getVehicle() instanceof BoatEntity boat)) return;

        Vec3d velocity = boat.getVelocity();

        if (mc.options.jumpKey.isPressed()) {
            // پرواز به بالا
            boat.setVelocity(velocity.x, 0.5, velocity.z);
        } else {
            // شناور ماندن (بدون سقوط)
            boat.setVelocity(velocity.x, 0.0, velocity.z);
        }
    }
}
