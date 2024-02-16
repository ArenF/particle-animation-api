package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.Animation;
import org.aren.particleanimationapi.pattern.Pattern;

public class ChangeAnimate implements Animate {

    private final Pattern pattern;

    public ChangeAnimate(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void play(Animation animation) {
        animation.setPattern(pattern);
    }

    @Override
    public String getType() {
        return "CHANGE";
    }
}
