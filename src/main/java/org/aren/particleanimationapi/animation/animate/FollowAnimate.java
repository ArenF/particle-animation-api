package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class FollowAnimate implements Animate {

    private Entity entity;
    private boolean onEyeLocation;
//  해당 엔티티의 pitch와 yaw 까지 같이 적용된다.
    private boolean force;

    public FollowAnimate(Entity entity, boolean isOnEyeLocation, boolean force) {
        this.entity = entity;
        this.onEyeLocation = isOnEyeLocation;
        this.force = force;
    }

    public FollowAnimate(Entity entity, boolean force) {
        this.entity = entity;
        this.onEyeLocation = false;
        this.force = force;
    }

    @Override
    public void play(AnimationImpl animation) {
        Location loc;

        if (onEyeLocation) {
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                loc = location(living.getEyeLocation(), force);
            } else {
                loc = location(entity.getLocation(), force);
            }
        } else {
            loc = location(entity.getLocation(), force);
        }

        animation.setLocation(loc);
    }

    private Location location(Location location, boolean force) {
        return force ? new Location(
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
