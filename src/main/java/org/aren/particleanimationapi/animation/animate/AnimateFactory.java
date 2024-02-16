package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.pattern.Pattern;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class AnimateFactory {

    public static Animate createIdle(int delay) {
        return new IdleAnimate(delay);
    }

    public static Animate createMove(Vector vector) {
        return new MoveAnimate(vector);
    }

    public static Animate createRotate(float pitch, float yaw) {
        return new RotateAnimate(pitch, yaw);
    }

    public static Animate createChange(Pattern pattern) {
        return new ChangeAnimate(pattern);
    }

    public static Animate createFollow(Entity entity) {
        return new FollowAnimate(entity);
    }
}
