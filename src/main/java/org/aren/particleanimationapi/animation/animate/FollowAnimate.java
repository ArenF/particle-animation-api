package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class FollowAnimate implements Animate {

    private Entity entity;
    private boolean onEyeLocation;

    public FollowAnimate(Entity entity, boolean isOnEyeLocation) {
        this.entity = entity;
        this.onEyeLocation = isOnEyeLocation;
    }

    public FollowAnimate(Entity entity) {
        this.entity = entity;
        this.onEyeLocation = false;
    }

    @Override
    public void play(AnimationImpl animation) {
        Location loc;

        if (onEyeLocation) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                loc = location(living.getEyeLocation());
            } else {
                loc = location(entity.getLocation());
            }
        } else {
            loc = location(entity.getLocation());
        }

        animation.setLocation(loc);
    }

    private Location location(Location location) {
        return new Location(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ()
        );
    }

    @Override
    public String getType() {
        return "FOLLOW";
    }
}
