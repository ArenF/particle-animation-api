package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.Animation;
import org.bukkit.entity.Entity;

public class FollowAnimate implements Animate {

    private Entity entity;

    public FollowAnimate(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void play(Animation animation) {
        animation.setLocation(entity.getLocation());
    }

    @Override
    public String getType() {
        return "FOLLOW";
    }
}
