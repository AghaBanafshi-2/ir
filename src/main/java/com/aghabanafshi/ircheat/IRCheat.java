package com.aghabanafshi.ircheat;

import com.aghabanafshi.ircheat.event.EventBus;
import com.aghabanafshi.ircheat.gui.ClickGUI;
import com.aghabanafshi.ircheat.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCheat implements ClientModInitializer {
    public static final String MOD_ID = "ir-cheat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final EventBus EVENT_BUS = new EventBus();

    private static KeyBinding clickGuiKey;

    @Override
    public void onInitializeClient() {
        LOGGER.info("[IR CHEAT] Initializing for MC 1.21.5...");
        ModuleManager.init();

        // ثبت کلید Right Shift برای باز کردن GUI
        clickGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ir-cheat.clickgui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.ir-cheat"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ModuleManager.onTick();

            // باز کردن GUI با Right Shift
            while (clickGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGUI());
                }
            }
        });

        LOGGER.info("[IR CHEAT] Ready. Press Right Shift for GUI. Telegram: @AghaBanafshi");
    }
}
