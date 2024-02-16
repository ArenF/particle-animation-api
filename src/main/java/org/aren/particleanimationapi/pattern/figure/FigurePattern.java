package org.aren.particleanimationapi.pattern.figure;

import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Location;

import java.util.Collection;

public abstract class FigurePattern implements Pattern {

    protected Pattern pattern;

    public FigurePattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Collection<Location> draw(Location location) {
        return this.pattern.draw(location);
    }
}
