package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;
import org.bukkit.entity.Entity;

public class FollowAnimate implements Animate {

    private Entity entity;

    public FollowAnimate(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void play(AnimationImpl animation) {
        animation.setLocation(entity.getLocation());
    }

    @Override
    public String getType() {
        return "FOLLOW";
    }
}
