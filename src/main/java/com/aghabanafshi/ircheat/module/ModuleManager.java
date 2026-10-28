package com.aghabanafshi.ircheat.module;

import com.aghabanafshi.ircheat.module.impl.combat.WTap;
import com.aghabanafshi.ircheat.module.impl.misc.Spam;
import com.aghabanafshi.ircheat.module.impl.movement.BoatFly;
import com.aghabanafshi.ircheat.module.impl.movement.Fly;
import com.aghabanafshi.ircheat.module.impl.movement.NoFallDamage;
import com.aghabanafshi.ircheat.module.impl.player.SpeedMine;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<Module> MODULES = new ArrayList<>();

    public static void init() {
        MODULES.add(new Fly());
        MODULES.add(new NoFallDamage());
        MODULES.add(new WTap());
        MODULES.add(new SpeedMine());
        MODULES.add(new BoatFly());
        MODULES.add(new Spam());
    }

    public static void onTick() {
        for (Module module : MODULES) {
            if (module.isEnabled()) module.onTick();
        }
    }

    public static <T extends Module> T get(Class<T> type) {
        return MODULES.stream().filter(type::isInstance).map(type::cast).findFirst().orElse(null);
    }

    public static List<Module> getAll() {
        return MODULES;
    }
}
