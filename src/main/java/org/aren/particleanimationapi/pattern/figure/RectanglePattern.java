package org.aren.particleanimationapi.pattern.figure;

import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.Axis;
import org.bukkit.util.Vector;

public class RectanglePattern extends FigurePattern {
    public RectanglePattern(double rad, Axis axis) {
        super(Pattern.builder().range(0.0, 360.0).offset(0.0, 0.0, 0.0).xyz((i) -> {
            double radius = rad * Math.sqrt(2.0);
            int angle = 45;
            if (i < 90.0) {
                angle += 90;
            } else if (i < 180.0) {
                angle += 180;
            } else if (i < 270.0) {
                angle += 270;
            } else {
                angle += 360;
            }

            double length = i % 90.0;
            double x = radius * Math.cos(Math.toRadians(angle));
            double y = radius * Math.sin(Math.toRadians(angle));
            double nextX = radius * Math.cos(Math.toRadians(angle + 90));
            double nextY = radius * Math.sin(Math.toRadians(angle + 90));
            Vector from = new Vector(x, y, 0.0);
            Vector to = new Vector(nextX, nextY, 0.0);
            double distance = to.distance(from);
            Vector go = to.subtract(from).normalize();
            double resultX = x + go.getX() * distance * length / 90.0;
            double resultY = y + go.getY() * distance * length / 90.0;
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
