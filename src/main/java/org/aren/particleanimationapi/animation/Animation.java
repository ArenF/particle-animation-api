package org.aren.particleanimationapi.animation;

import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Function;

public interface Animation {
    void play(JavaPlugin plugin);
    void play(JavaPlugin plugin, long delay, long period);


    static Animation create(Pattern pattern, Location location, ParticleWrapper wrapper) {
        return new AnimationImpl(pattern, location, i -> wrapper);
    }

    static Animation create(Pattern pattern, Location location, Function<Integer, ParticleWrapper> function) {
        return new AnimationImpl(pattern, location, function);
    }
}
