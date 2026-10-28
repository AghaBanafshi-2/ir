package com.aghabanafshi.ircheat.gui;

import com.aghabanafshi.ircheat.module.Module;
import com.aghabanafshi.ircheat.module.ModuleManager;
import com.aghabanafshi.ircheat.module.impl.combat.KillAura;
import com.aghabanafshi.ircheat.module.impl.misc.Spam;
import com.aghabanafshi.ircheat.module.impl.player.EnchantGiver;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ClickGUI extends Screen {

    public ClickGUI() {
        super(Text.literal("IR Cheat -> Telegram : @AghaBanafshi"));
    }

    @Override
    protected void init() {
        super.init();

        List<Module> modules = ModuleManager.getAll();
        int centerX = this.width / 2;
        int startY = Math.max(30, this.height / 2 - (modules.size() * 25) / 2);

        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            int y = startY + (i * 25);

            ButtonWidget button = ButtonWidget.builder(
                getModuleText(module),
                b -> {
                    module.toggle();
                    b.setMessage(getModuleText(module));
                }
            ).dimensions(centerX - 100, y, 160, 20).build();
            this.addDrawableChild(button);

            // دکمه تنظیمات برای Spam
            if (module instanceof Spam) {
                ButtonWidget configButton = ButtonWidget.builder(
                    Text.literal("⚙").formatted(Formatting.YELLOW),
                    b -> this.client.setScreen(new SpamConfigScreen(this))
                ).dimensions(centerX + 65, y, 35, 20).build();
                this.addDrawableChild(configButton);
            }

            // دکمه تنظیمات برای KillAura
            if (module instanceof KillAura ka) {
                ButtonWidget configButton = ButtonWidget.builder(
                    Text.literal("⚙").formatted(Formatting.YELLOW),
                    b -> this.client.setScreen(new KillAuraConfigScreen(this, ka))
                ).dimensions(centerX + 65, y, 35, 20).build();
                this.addDrawableChild(configButton);
            }

            // دکمه تنظیمات برای EnchantGiver
            if (module instanceof EnchantGiver eg) {
                ButtonWidget configButton = ButtonWidget.builder(
                    Text.literal("⚙").formatted(Formatting.YELLOW),
                    b -> this.client.setScreen(new EnchantGiverConfigScreen(this, eg))
                ).dimensions(centerX + 65, y, 35, 20).build();
                this.addDrawableChild(configButton);
            }
        }

        ButtonWidget closeButton = ButtonWidget.builder(
            Text.literal("Close").formatted(Formatting.RED),
            button -> this.close()
        ).dimensions(centerX - 100, startY + (modules.size() * 25) + 10, 200, 20).build();
        this.addDrawableChild(closeButton);
    }

    private Text getModuleText(Module module) {
        String status = module.isEnabled() ? "ON" : "OFF";
        Formatting color = module.isEnabled() ? Formatting.GREEN : Formatting.GRAY;
        return Text.literal(module.getName() + ": " + status).formatted(color);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);

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
        return false;
    }
}
