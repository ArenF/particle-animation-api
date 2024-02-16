package org.aren.particleanimationapi.animation;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Animator {

    @Getter
    @Setter
    private Animation animation;
    private BukkitTask task;
    @Getter
    private boolean cancelled;
    private JavaPlugin plugin;
    private long delay;
    private long period;

    public Animator(JavaPlugin plugin, long delay, long period) {
        this.plugin = plugin;
        this.delay = delay;
        this.period = period;
        this.cancelled = true;
    }

    public void run() {
        if (this.cancelled) {
            this.cancelled = false;
            this.task = Bukkit.getScheduler().runTaskTimer(this.plugin, new Runnable() {
                public void run() {
                    if (Animator.this.animation.getFrame() >= Animator.this.animation.getAnimates().size() && Animator.this.animation.getDelay() <= 0) {
                        Animator.this.cancel();
                    } else {
                        Animator.this.animation.animate();
                    }
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
