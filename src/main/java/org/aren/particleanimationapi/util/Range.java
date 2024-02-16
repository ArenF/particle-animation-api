package org.aren.particleanimationapi.util;

public class Range {
    private double min;
    private double max;
    private double increase;

    public Range(double min, double max, double increase) {
        this.min = min;
        this.max = max;
        this.increase = increase;
    }

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
        this.increase = 1.0;
    }

    public double getIncrease() {
        return this.increase;
    }

    public double getMax() {
        return this.max;
    }

    public double getMin() {
        return this.min;
    }
}
