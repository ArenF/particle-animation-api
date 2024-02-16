package org.aren.particleanimationapi.animation;

import lombok.Getter;
import lombok.Setter;
import org.aren.particleanimationapi.animation.animate.Animate;
import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class Animation implements AnimationImpl {

    private Function<Integer, ParticleWrapper> particleFunction;
    private Pattern pattern;
    private Location location;
    private int delay = 0;
    private int frame = 0;
    private List<Animate> animates = new ArrayList<>();

    public Animation(Pattern defaultPattern, Location location, Function<Integer, ParticleWrapper> function) {
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

            animates.get(frame).play(this);
            if (frame < animates.size()) {
                frame++;
            }
        }
    }
}
