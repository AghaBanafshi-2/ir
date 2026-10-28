package com.aghabanafshi.ircheat.gui;

import com.aghabanafshi.ircheat.module.impl.player.EnchantGiver;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class EnchantGiverConfigScreen extends Screen {
    private final Screen parent;
    private final EnchantGiver module;
    private final List<String> enchNames;
    private int enchIndex;

    public EnchantGiverConfigScreen(Screen parent, EnchantGiver module) {
        super(Text.literal("EnchantGiver Config"));
        this.parent = parent;
        this.module = module;
        this.enchNames = new ArrayList<>(EnchantGiver.ENCHANTS.keySet());
        int idx = enchNames.indexOf(module.getSelectedEnchant());
        this.enchIndex = Math.max(0, idx);
    }

    @Override
    protected void init() {
        super.init();
        int cx = this.width / 2;
        int y = this.height / 2 - 40;

        // دکمه چرخش انتخاب enchantment
        ButtonWidget enchBtn = ButtonWidget.builder(
            Text.literal("Enchant: " + module.getSelectedEnchant()).formatted(Formatting.AQUA),
            b -> {
                enchIndex = (enchIndex + 1) % enchNames.size();
                module.setSelectedEnchant(enchNames.get(enchIndex));
                b.setMessage(Text.literal("Enchant: " + module.getSelectedEnchant()).formatted(Formatting.AQUA));
            }
        ).dimensions(cx - 120, y, 240, 20).build();
        this.addDrawableChild(enchBtn);

        // افزایش سطح
        ButtonWidget lvlPlus = ButtonWidget.builder(
            Text.literal("Level: " + module.getLevel() + "  [+1]"),
            b -> {
                module.setLevel(module.getLevel() + 1);
                b.setMessage(Text.literal("Level: " + module.getLevel() + "  [+1]"));
            }
        ).dimensions(cx - 120, y + 30, 115, 20).build();
        this.addDrawableChild(lvlPlus);

        // کاهش سطح
        ButtonWidget lvlMinus = ButtonWidget.builder(
            Text.literal("Level: " + module.getLevel() + "  [-1]"),
            b -> {
                module.setLevel(module.getLevel() - 1);
                b.setMessage(Text.literal("Level: " + module.getLevel() + "  [-1]"));
            }
        ).dimensions(cx + 5, y + 30, 115, 20).build();
        this.addDrawableChild(lvlMinus);

        // بازگشت
        ButtonWidget back = ButtonWidget.builder(
            Text.literal("Back").formatted(Formatting.RED),
            b -> this.close()
        ).dimensions(cx - 120, y + 70, 240, 20).build();
        this.addDrawableChild(back);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public void close() { this.client.setScreen(this.parent); }

    @Override
    public boolean shouldPause() { return false; }
                                                                                               }
