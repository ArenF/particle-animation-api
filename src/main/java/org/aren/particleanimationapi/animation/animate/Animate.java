package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;

public interface Animate {

    void play(AnimationImpl animation);
    String getType();
}
