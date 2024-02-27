package org.aren.particleanimationapi.pattern;

import lombok.Getter;
import lombok.Setter;
import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.util.Range;
import org.aren.particleanimationapi.util.RotationMatrixFormula;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

@Getter
@Setter
public class CustomPattern implements Pattern {

    private Range range;
    private Function<Double, Vector> xyz;
    private Function<Integer, ParticleWrapper> function;
    private double offsetX;
    private double offsetY;
    private double offsetZ;
    private Collection<Pattern> nextPatterns;

    public CustomPattern(Pattern.PatternBuilder builder) {
        this.range = builder.range;
        this.offsetX = builder.offsetX;
        this.offsetY = builder.offsetY;
        this.offsetZ = builder.offsetZ;
        this.xyz = builder.xyz;
        this.function = builder.function;
        this.nextPatterns = builder.patterns == null ? new ArrayList<>() : builder.patterns;
    }

    private Collection<Location> currentDraw(Location location) {
        Collection<Location> result = new ArrayList<>();
        if (this.range.getMin() != this.range.getMax()) {
            for (double i = this.range.getMin(); i <= this.range.getMax(); i += this.range.getIncrease()) {
                Location clonedLocation = location.clone();
                Vector vector = RotationMatrixFormula.rotateAboutVector(
                        this.xyz.apply(i).add(new Vector(offsetX, offsetY, offsetZ)),
                        clonedLocation
                );
                result.add(clonedLocation.add(vector));
            }
        }

        return result;
    }

    @Override
    public Collection<Location> draw(Location location) {
        Collection<Location> result = currentDraw(location);

        if (nextPatterns == null) {
            return result;
        } else {
            for (Pattern pattern : nextPatterns) {
                result.addAll(pattern.draw(location));
            }
            return result;
        }
    }

    @Override
    public void drawParticle(Location location) {
        int i = 0;
        for (Location loc : currentDraw(location)) {
            function.apply(i).showParticle(loc);
            i++;
        }

        if (!nextPatterns.isEmpty()) {
            for (Pattern pattern : nextPatterns) {
                pattern.drawParticle(location);
            }
        }
    }
}
