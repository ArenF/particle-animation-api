package org.aren.particleanimationapi.animation;

import org.aren.particleanimationapi.animation.animate.Animate;
import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public interface Animation {
    void play(JavaPlugin plugin);
    void play(JavaPlugin plugin, long delay, long period);

    void addAnimate(Animate animate);
    void removeAnimate(int index);

    static Animation create(Pattern pattern, Location location) {
        return new AnimationImpl(pattern, location);
    }
}
