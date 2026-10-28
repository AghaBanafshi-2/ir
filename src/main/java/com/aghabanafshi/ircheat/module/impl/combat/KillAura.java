package com.aghabanafshi.ircheat.module.impl.combat;

import com.aghabanafshi.ircheat.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class KillAura extends Module {
    private TargetMode targetMode = TargetMode.BOTH;
    private double range = 3.5;

    public KillAura() { super("KillAura"); }

    public TargetMode getTargetMode() { return targetMode; }
    public void setTargetMode(TargetMode mode) { this.targetMode = mode; }

    public double getRange() { return range; }
    public void setRange(double range) { this.range = Math.max(2.0, Math.min(6.0, range)); }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        Entity target = null;
        double closest = range;

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity living)) continue;
            if (living.isDead() || living.getHealth() <= 0) continue;

            // فیلتر بر اساس حالت هدف
            boolean isPlayer = entity instanceof PlayerEntity;
            boolean isMob = entity instanceof MobEntity && !isPlayer;

            if (targetMode == TargetMode.PLAYER && !isPlayer) continue;
            if (targetMode == TargetMode.MOB && !isMob) continue;

            double dist = mc.player.distanceTo(entity);
            if (dist < closest) {
                closest = dist;
                target = entity;
            }
        }

        if (target != null) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }

    public enum TargetMode {
        PLAYER("Players"),
        MOB("Mobs"),
        BOTH("Both");

        private final String label;
        TargetMode(String label) { this.label = label; }
        public String getLabel() { return label; }
        public TargetMode next() {
            return switch (this) {
                case PLAYER -> MOB;
                case MOB -> BOTH;
                case BOTH -> PLAYER;
            };
        }
    }
                }
