package org.aren.particleanimationapi.animation.animate;

import org.aren.particleanimationapi.animation.AnimationImpl;
import org.bukkit.Location;

public class RotateAnimate implements Animate {

    private final float pitch;
    private final float yaw;

    public RotateAnimate(float pitch, float yaw) {
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public void play(AnimationImpl animation) {
        Location location = animation.getLocation();
        location.setPitch(pitch);
        location.setYaw(yaw);
        animation.setLocation(location);
    }

    @Override
    public String getType() {
        return "ROTATE";
    }
}
