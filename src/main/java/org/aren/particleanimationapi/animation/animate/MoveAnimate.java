package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;
import org.aren.particleanimationapi.util.RotationMatrixFormula;
import org.bukkit.util.Vector;

public class MoveAnimate implements Animate {

    private final Vector vector;

    public MoveAnimate(Vector vector) {
        this.vector = vector;
    }

    @Override
    public void play(AnimationImpl animation) {
        Vector adder = RotationMatrixFormula.rotateAboutVector(vector, animation.getLocation());
        animation.setLocation(animation.getLocation().add(adder));
    }

    @Override
    public String getType() {
        return "MOVE";
    }
}
