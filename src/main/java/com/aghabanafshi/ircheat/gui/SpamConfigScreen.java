package com.aghabanafshi.ircheat.gui;

import com.aghabanafshi.ircheat.module.ModuleManager;
import com.aghabanafshi.ircheat.module.impl.misc.Spam;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SpamConfigScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget messageField;

    public SpamConfigScreen(Screen parent) {
        super(Text.literal("Spam Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        Spam spam = ModuleManager.get(Spam.class);
        if (spam == null) return;

        int centerX = this.width / 2;

        messageField = new TextFieldWidget(
                this.textRenderer,
                centerX - 100,
                this.height / 2 - 10,
                200, 20,
                Text.literal("Message"));
        messageField.setText(spam.getMessage());
        messageField.setMaxLength(256);
        this.addDrawableChild(messageField);

        ButtonWidget saveButton = ButtonWidget.builder(
            Text.literal("Save").formatted(Formatting.GREEN),
            button -> {
                spam.setMessage(messageField.getText());
                this.close();
            }
        ).dimensions(centerX - 100, this.height / 2 + 20, 95, 20).build();
        this.addDrawableChild(saveButton);

        ButtonWidget backButton = ButtonWidget.builder(
            Text.literal("Back").formatted(Formatting.RED),
            button -> this.close()
        ).dimensions(centerX + 5, this.height / 2 + 20, 95, 20).build();
        this.addDrawableChild(backButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("Spam Message:"),
            this.width / 2,
            this.height / 2 - 30,
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
