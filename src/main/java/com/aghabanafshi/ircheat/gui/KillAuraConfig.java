package com.aghabanafshi.ircheat.gui;

import com.aghabanafshi.ircheat.module.ModuleManager;
import com.aghabanafshi.ircheat.module.impl.combat.KillAura;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class KillAuraConfigScreen extends Screen {
    private final Screen parent;
    private final KillAura killAura;

    public KillAuraConfigScreen(Screen parent, KillAura killAura) {
        super(Text.literal("KillAura Config"));
        this.parent = parent;
        this.killAura = killAura;
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int y = this.height / 2 - 40;

        // دکمه انتخاب هدف
        ButtonWidget targetButton = ButtonWidget.builder(
            getTargetText(),
            button -> {
                killAura.setTargetMode(killAura.getTargetMode().next());
                button.setMessage(getTargetText());
            }
        ).dimensions(centerX - 100, y, 200, 20).build();
        this.addDrawableChild(targetButton);

        // دکمه افزایش Range
        ButtonWidget rangePlusButton = ButtonWidget.builder(
            Text.literal("Range: " + String.format("%.1f", killAura.getRange()) + "  [+0.5]"),
            button -> {
                killAura.setRange(killAura.getRange() + 0.5);
                button.setMessage(Text.literal("Range: " + String.format("%.1f", killAura.getRange()) + "  [+0.5]"));
            }
        ).dimensions(centerX - 100, y + 30, 200, 20).build();
        this.addDrawableChild(rangePlusButton);

        // دکمه کاهش Range
        ButtonWidget rangeMinusButton = ButtonWidget.builder(
            Text.literal("Range: " + String.format("%.1f", killAura.getRange()) + "  [-0.5]"),
            button -> {
                killAura.setRange(killAura.getRange() - 0.5);
                button.setMessage(Text.literal("Range: " + String.format("%.1f", killAura.getRange()) + "  [-0.5]"));
            }
        ).dimensions(centerX - 100, y + 60, 200, 20).build();
        this.addDrawableChild(rangeMinusButton);

        // دکمه بازگشت
        ButtonWidget backButton = ButtonWidget.builder(
            Text.literal("Back").formatted(Formatting.RED),
            button -> this.close()
        ).dimensions(centerX - 100, y + 100, 200, 20).build();
        this.addDrawableChild(backButton);
    }

    private Text getTargetText() {
        return Text.literal("Target: " + killAura.getTargetMode().getLabel()).formatted(Formatting.YELLOW);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("KillAura Settings"),
            this.width / 2,
            this.height / 2 - 70,
            0xFFFFFF
        );
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
