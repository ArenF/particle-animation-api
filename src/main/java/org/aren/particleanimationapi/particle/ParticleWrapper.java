package org.aren.particleanimationapi.particle;

import lombok.Builder;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

@Getter
@Builder
public class ParticleWrapper {

    @Builder.Default
    private Particle particle = Particle.FLAME;
    @Builder.Default
    private int count = 1;
    @Builder.Default
    private double offsetX = 0;
    @Builder.Default
    private double offsetY = 0;
    @Builder.Default
    private double offsetZ = 0;
    @Builder.Default
    private double extra = 0;
    @Builder.Default
    private Object data = null;
    @Builder.Default
    private boolean force = true;

    public void showParticle(Location location) {
        World world = location.getWorld();

        assert world != null;

        world.spawnParticle(this.particle, location, this.count, this.offsetX, this.offsetY, this.offsetZ, this.extra, this.data, this.force);
    }

    public static ParticleWrapper create(Particle particle) {
        return builder().particle(particle).build();
    }

    public static ParticleWrapper create(Particle particle, int count) {
        return builder().particle(particle).count(count).build();
    }

    public static ParticleWrapper create(Particle particle, int count, double offsetX, double offsetY, double offsetZ) {
        return builder().particle(particle).count(count).offsetX(offsetX).offsetY(offsetY).offsetZ(offsetZ).build();
    }
}
