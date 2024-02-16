package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.Animation;

public class IdleAnimate implements Animate {

    private final int delay;

    public IdleAnimate(int delay) {
        this.delay = delay;
    }

    @Override
    public void play(Animation animation) {
        animation.setDelay(delay);
    }

    @Override
    public String getType() {
        return "IDLE";
    }
}
