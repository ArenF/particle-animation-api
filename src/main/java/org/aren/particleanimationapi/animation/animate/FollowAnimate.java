package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class FollowAnimate implements Animate {

    private Entity entity;
    private boolean onEyeLocation;
//  해당 엔티티의 pitch와 yaw 까지 같이 적용된다.
    private boolean withSight;

    public FollowAnimate(Entity entity, boolean isOnEyeLocation, boolean withSight) {
        this.entity = entity;
        this.onEyeLocation = isOnEyeLocation;
        this.withSight = withSight;
    }

    public FollowAnimate(Entity entity, boolean withSight) {
        this.entity = entity;
        this.onEyeLocation = false;
        this.withSight = withSight;
    }

    @Override
    public void play(AnimationImpl animation) {
        Location loc;

        if (onEyeLocation) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                loc = location(living.getEyeLocation(), withSight);
            } else {
                loc = location(entity.getLocation(), withSight);
            }
        } else {
            loc = location(entity.getLocation(), withSight);
        }

        animation.setLocation(loc);
    }

    private Location location(Location location, boolean withSight) {
        return !withSight ? new Location(
                location.getWorld(),
                location.getX(),
                location.getY(),
                location.getZ()
        ) : location;
    }

    @Override
    public String getType() {
        return "FOLLOW";
    }
}
