package org.aren.particleanimationapi.animation;

import lombok.Getter;
import lombok.Setter;
import org.aren.particleanimationapi.animation.animate.Animate;
import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AnimationImpl implements Animation {

    private Pattern pattern;
    private Location location;
    private int delay = 0;
    private int frame = 0;
    private List<Animate> animates = new ArrayList<>();
    private Animator animator;

    public AnimationImpl(Pattern defaultPattern, Location location) {
        this.pattern = defaultPattern;
        this.location = location;
    }

    @Override
    public void addAnimate(Animate animate) {
        this.animates.add(animate);
    }

    @Override
    public void removeAnimate(int index) {
        this.animates.remove(index);
    }

    public void show() {
        pattern.drawParticle(location);
    }

    public void animate() {
        this.show();
        if (delay > 0) {
            setDelay(delay - 1);
        } else {
            if (frame >= animates.size()) {
                return;
            }
            animates.get(frame).play(this);
            frame++;
        }
    }

    @Override
    public void play(JavaPlugin plugin) {
        animator = Animator.builder()
                .plugin(plugin)
                .animation(this)
                .delay(0)
                .period(1)
                .build();
        animator.run();
    }

    @Override
    public void play(JavaPlugin plugin, long delay, long period) {
        animator = Animator.builder()
                .plugin(plugin)
                .animation(this)
                .delay(delay)
                .period(period)
                .build();
        animator.run();
    }

    @Override
    public void stop() {
        if (animator == null)
            return;
        animator.cancel();
    }
}
