package com.aghabanafshi.ircheat.module.impl.misc;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;

public class Spam extends Module {
    private String message = "IR Cheat -> @AghaBanafshi";
    private int tickCounter = 0;
    private int intervalTicks = 40; // پیش‌فرض: هر 2 ثانیه

    public Spam() { super("Spam"); }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.getNetworkHandler() == null) return;

        tickCounter++;
        if (tickCounter >= intervalTicks) {
            tickCounter = 0;
            if (!message.isEmpty()) {
                mc.getNetworkHandler().sendChatMessage(message);
            }
        }
    }

    @Override
    protected void onDisable() {
        tickCounter = 0;
    }
}
