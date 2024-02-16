package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.Animation;

public interface Animate {

    void play(Animation animation);
    String getType();
}
