package org.aren.particleanimationapi.animation;

import lombok.Getter;
import lombok.Setter;
import org.aren.particleanimationapi.animation.animate.Animate;
import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class AnimationImpl implements Animation {

    private Function<Integer, ParticleWrapper> particleFunction;
    private Pattern pattern;
    private Location location;
    private int delay = 0;
    private int frame = 0;
    private List<Animate> animates = new ArrayList<>();

    public AnimationImpl(Pattern defaultPattern, Location location, Function<Integer, ParticleWrapper> function) {
        this.pattern = defaultPattern;
        this.location = location;
        this.particleFunction = function;
    }

    public void addAnimate(Animate animate) {
        this.animates.add(animate);
    }

    public void removeAnimate(int index) {
        this.animates.remove(index);
    }

    public void show() {
        int patternCount = 0;

        for (Location loc : pattern.draw(location)) {
            particleFunction.apply(patternCount).showParticle(loc);
            patternCount++;
        }

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
        Animator animator = Animator.builder()
                .plugin(plugin)
                .animation(this)
                .delay(0)
                .period(1)
                .build();
        animator.run();
    }

    @Override
    public void play(JavaPlugin plugin, long delay, long period) {
        Animator animator = Animator.builder()
                .plugin(plugin)
                .animation(this)
                .delay(delay)
                .period(period)
                .build();
        animator.run();
    }
}
