package org.aren.particleanimationapi.animation;

import lombok.Builder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

@Builder
public class Animator {

    private AnimationImpl animation;
    @Builder.Default
    private BukkitTask task = null;
    @Builder.Default
    private boolean cancelled = true;
    private JavaPlugin plugin;
    @Builder.Default
    private long delay = 0L;
    @Builder.Default
    private long period = 1L;

    public void run() {
        if (this.cancelled) {
            this.cancelled = false;
            this.task = Bukkit.getScheduler().runTaskTimer(this.plugin, new Runnable() {
                public void run() {
                    if (animation.getFrame() >= animation.getAnimates().size() && animation.getDelay() <= 0) {
                        cancel();
                    }
                    animation.animate();
                }
            }, this.delay, this.period);
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            Bukkit.getScheduler().cancelTask(this.task.getTaskId());
        }

        this.cancelled = true;
    }
}
