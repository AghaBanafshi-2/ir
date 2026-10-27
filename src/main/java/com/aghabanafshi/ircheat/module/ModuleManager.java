package com.aghabanafshi.ircheat.module;

import com.aghabanafshi.ircheat.module.impl.movement.Fly;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<Module> MODULES = new ArrayList<>();

    public static void init() {
        MODULES.add(new Fly());
    }

    public static void onTick() {
        for (Module module : MODULES) {
            if (module.isEnabled()) module.onTick();
        }
    }

    public static <T extends Module> T get(Class<T> type) {
        return MODULES.stream().filter(type::isInstance).map(type::cast).findFirst().orElse(null);
    }
}
