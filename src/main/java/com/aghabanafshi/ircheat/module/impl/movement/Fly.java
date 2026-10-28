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
        // جلوگیری از آسیب سقوط در حین پرواز
        mc.player.fallDistance = 0f;
    }

    @Override
    protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            // اول سقوط را صفر کن، بعد پرواز را قطع کن
            mc.player.fallDistance = 0f;
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().allowFlying = false;
            // اگر در هوا بود، به آرامی پایین بیاید (اجباری نیست)
            mc.player.setVelocity(mc.player.getVelocity().x, -0.5, mc.player.getVelocity().z);
        }
    }
}
