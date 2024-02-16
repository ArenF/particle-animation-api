package org.aren.particleanimationapi.pattern.figure;

import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Axis;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Collection;

public class CirclePattern extends FigurePattern {


    public CirclePattern(double rad, Axis axis) {
        super(Pattern.builder().name("circle").range(0.0, 360.0).offset(0.0, 0.0, 0.0).xyz((i) -> {
            double y = rad * Math.sin(Math.toRadians(i));
            double x = rad * Math.cos(Math.toRadians(i));
            switch (axis) {
                case X:
                    return new Vector(0.0, x, y);
                case Z:
                    return new Vector(x, y, 0.0);
                default:
                    return new Vector(x, 0.0, y);
            }
        }).build());
    }
}
