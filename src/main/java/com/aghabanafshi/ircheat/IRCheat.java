package com.aghabanafshi.ircheat;

import com.aghabanafshi.ircheat.event.EventBus;
import com.aghabanafshi.ircheat.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCheat implements ClientModInitializer {
    public static final String MOD_ID = "ir-cheat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final EventBus EVENT_BUS = new EventBus();

    @Override
    public void onInitializeClient() {
        LOGGER.info("[IR CHEAT] Initializing for MC 1.21.5...");
        ModuleManager.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> ModuleManager.onTick());
        LOGGER.info("[IR CHEAT] Ready. Telegram: @AghaBanafshi");
    }
}
