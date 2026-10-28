package com.aghabanafshi.ircheat.module.impl.combat;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;

public class WTap extends Module {
    private int releaseTicks = 0;

    public WTap() { super("WTap"); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // اگر در حال رها کردن W هستیم
        if (releaseTicks > 0) {
            releaseTicks--;
            mc.options.forwardKey.setPressed(false);
            return;
        }

        // تشخیص حمله جدید (با استفاده از وضعیت swing)
        if (mc.player.handSwinging && mc.player.handSwingProgress < 0.1f) {
            releaseTicks = 2; // W را برای 2 تیک رها کن
        }
    }

    @Override
    protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options != null) {
            mc.options.forwardKey.setPressed(false);
        }
    }
}
