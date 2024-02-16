package org.aren.particleanimationapi.pattern;

import org.aren.particleanimationapi.util.Range;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public interface Pattern {
    static PatternBuilder builder() {
        return new PatternBuilder();
    }

    Collection<Location> draw(Location location);

    class PatternBuilder {

        protected Range range = new Range(0.0, 360.0);
        protected double offsetX = 0.0;
        protected double offsetY = 0.0;
        protected double offsetZ = 0.0;
        protected Function<Double, Vector> xyz = i -> new Vector(i, i, i);
        protected String patternName = "null";
        protected Collection<Pattern> patterns = new ArrayList<>();

        public PatternBuilder() {
        }

        public PatternBuilder name(String patternName) {
            this.patternName = patternName;
            return this;
        }

        public PatternBuilder range(Range range) {
            this.range = range;
            return this;
        }

        public PatternBuilder range(double min, double max) {
            this.range = new Range(min, max);
            return this;
        }

        public PatternBuilder xyz(Function<Double, Vector> xyz) {
            this.xyz = xyz;
            return this;
        }

        public PatternBuilder offset(double offsetX, double offsetY, double offsetZ) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.offsetZ = offsetZ;
            return this;
        }

        public PatternBuilder nextPatterns(Collection<Pattern> patterns) {
            this.patterns = patterns;
            return this;
        }

        public PatternBuilder nextPatterns(Pattern... patterns) {
            this.patterns = Arrays.asList(patterns);
            return this;
        }

        public CustomPattern build() {
            return new CustomPattern(this);
        }
    }
}
