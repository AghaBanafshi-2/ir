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

public class EnchantGiver extends Module {

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

        // دریافت رجیستری enchantment از world
        var registry = mc.world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);

        // در 1.21.5، getEntry فقط با int یا Identifier کار می‌کند
        // پس از key.getValue() (که Identifier است) استفاده می‌کنیم
        var entryOpt = registry.getEntry(key.getValue());

        if (entryOpt.isPresent()) {
            stack.addEnchantment(entryOpt.get(), level);
        }
    }
                                                            }
