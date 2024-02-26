package org.aren.particleanimationapi.pattern.figure;

import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Axis;
import org.bukkit.util.Vector;

public class TrianglePattern extends FigurePattern {
    public TrianglePattern(double rad, Axis axis) {
        super(Pattern.builder().range(0.0, 360.0).offset(0.0, 0.0, 0.0).xyz((i) -> {
            short angle;
            if (i <= 120.0) {
                angle = 120;
            } else if (i <= 240.0) {
                angle = 240;
            } else {
                angle = 360;
            }

            double length = i % 120.0;
            double x = rad * Math.cos(Math.toRadians(angle));
            double y = rad * Math.sin(Math.toRadians(angle));
            double nextX = rad * Math.cos(Math.toRadians(angle + 120));
            double nextY = rad * Math.sin(Math.toRadians(angle + 120));
            Vector from = new Vector(x, 0.0, y);
            Vector to = new Vector(nextX, 0.0, nextY);
            double distance = to.distance(from);
            Vector go = to.subtract(from).normalize();
            double resultX = x + go.getX() * distance * (length / 120.0);
            double resultY = y + go.getZ() * distance * (length / 120.0);
            switch (axis) {
                case X:
                    return new Vector(0.0, resultX, resultY);
                case Z:
                    return new Vector(resultX, resultY, 0.0);
                default:
                    return new Vector(resultX, 0.0, resultY);
            }
        }).build());
    }
}
