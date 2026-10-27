package com.aghabanafshi.ircheat.gui;

import com.aghabanafshi.ircheat.module.ModuleManager;
import com.aghabanafshi.ircheat.module.impl.movement.Fly;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ClickGUI extends Screen {

    public ClickGUI() {
        super(Text.literal("IR Cheat -> Telegram : @AghaBanafshi"));
    }

    @Override
    protected void init() {
        super.init();

        int centerX = this.width / 2;
        int startY = this.height / 2 - 40;

        // --- دکمه Fly ---
        Fly fly = ModuleManager.get(Fly.class);
        if (fly != null) {
            ButtonWidget flyButton = ButtonWidget.builder(
                getFlyText(fly),
                button -> {
                    fly.toggle();
                    button.setMessage(getFlyText(fly));
                }
            ).dimensions(centerX - 100, startY, 200, 20).build();
            this.addDrawableChild(flyButton);
        }

        // --- دکمه Close ---
        ButtonWidget closeButton = ButtonWidget.builder(
            Text.literal("Close").formatted(Formatting.RED),
            button -> this.close()
        ).dimensions(centerX - 100, startY + 60, 200, 20).build();
        this.addDrawableChild(closeButton);
    }

    private Text getFlyText(Fly fly) {
        String status = fly.isEnabled() ? "ON" : "OFF";
        Formatting color = fly.isEnabled() ? Formatting.GREEN : Formatting.GRAY;
        return Text.literal("Fly: " + status).formatted(color);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // پس‌زمینه تیره نیمه‌شفاف
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        // عنوان بالای صفحه
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            this.title,
            this.width / 2,
            20,
            0xFFFFFF
        );
    }

    @Override
    public boolean shouldPause() {
        return false; // بازی در پس‌زمینه متوقف نشود
    }
    }
