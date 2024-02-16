package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.Animation;
import org.aren.particleanimationapi.util.RotationMatrixFormula;
import org.bukkit.util.Vector;

public class MoveAnimate implements Animate {

    private final Vector vector;

    public MoveAnimate(Vector vector) {
        this.vector = vector;
    }

    @Override
    public void play(Animation animation) {
        Vector adder = RotationMatrixFormula.rotateAboutVector(vector, animation.getLocation());
        animation.setLocation(animation.getLocation().add(adder));
    }

    @Override
    public String getType() {
        return "MOVE";
    }
}
