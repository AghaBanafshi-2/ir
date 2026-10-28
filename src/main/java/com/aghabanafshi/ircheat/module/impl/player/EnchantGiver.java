package com.aghabanafshi.ircheat.module.impl.player;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class EnchantGiver extends Module {

    // در 1.21.5، Enchantments.SHARPNESS از نوع RegistryKey است
    public static final Map<String, RegistryKey<Enchantment>> ENCHANTS = new LinkedHashMap<>();
    static {
        ENCHANTS.put("Sharpness", Enchantments.SHARPNESS);
        ENCHANTS.put("Smite", Enchantments.SMITE);
        ENCHANTS.put("Bane of Arthropods", Enchantments.BANE_OF_ARTHROPODS);
        ENCHANTS.put("Knockback", Enchantments.KNOCKBACK);
        ENCHANTS.put("Fire Aspect", Enchantments.FIRE_ASPECT);
        ENCHANTS.put("Looting", Enchantments.LOOTING);
        ENCHANTS.put("Efficiency", Enchantments.EFFICIENCY);
        ENCHANTS.put("Unbreaking", Enchantments.UNBREAKING);
        ENCHANTS.put("Fortune", Enchantments.FORTUNE);
        ENCHANTS.put("Protection", Enchantments.PROTECTION);
    }

    private String selectedEnchant = "Sharpness";
    private int level = 255;

    public EnchantGiver() { super("EnchantGiver"); }

    public String getSelectedEnchant() { return selectedEnchant; }
    public void setSelectedEnchant(String name) { this.selectedEnchant = name; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = Math.max(1, Math.min(255, level)); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        ItemStack stack = mc.player.getMainHandStack();
        if (stack.isEmpty()) return;

        RegistryKey<Enchantment> key = ENCHANTS.get(selectedEnchant);
        if (key == null) return;

        // تبدیل RegistryKey به RegistryEntry از طریق Registry بازی
        Optional<RegistryEntry.Reference<Enchantment>> entryOpt =
                mc.world.getRegistryManager()
                        .getWrapperOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOptional(key);

        if (entryOpt.isEmpty()) return;

        // اعمال enchantment
        stack.addEnchantment(entryOpt.get(), level);
    }
}
