package org.aren.particleanimationapi.test;

import org.aren.particleanimationapi.animation.Animation;
import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.pattern.Pattern;
import org.aren.particleanimationapi.util.Range;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class TestExecutor implements CommandExecutor {

    private JavaPlugin plugin;

    public TestExecutor(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player))
            return false;

        Player player = (Player) commandSender;

        Animation animation = Animation.create(
                Pattern.builder()
                        .name("attacklines")
                        .nextPatterns(
                                Pattern.builder()
                                        .name("first line")
                                        .range(0, 180)
                                        .offset(0, 0,0)
                                        .xyz(d -> {
                                            double r = 1;
                                            double height = d / 90;
                                            double x = r * Math.cos(d);
                                            double y = r * Math.sin(d);
                                            return new Vector(x, height, y);
                                        })
                                        .build(),
                                Pattern.builder()
                                        .name("second line")
                                        .range(0, 180)
                                        .xyz(d -> {
                                            double r = 1;
                                            double height = 2 - (d / 90);
                                            double x = r * Math.cos(d);
                                            double y = r * Math.sin(d);
                                            return new Vector(x, height, y);
                                        })
                                        .build()
                        )
                        .build(),
                player.getLocation(),
                i -> {
                    if (i % 2 == 0)
                        return ParticleWrapper.builder()
                                .particle(Particle.REDSTONE)
                                .data(new Particle.DustOptions(Color.fromRGB(0, 0, 0), 0.5f))
                                .build();
                    else
                        return ParticleWrapper.builder()
                                .particle(Particle.REDSTONE)
                                .data(new Particle.DustOptions(Color.fromRGB(0x872341), 0.5f))
                                .build();
                }
        );
        animation.play(plugin, 0, 1);

        return false;
    }
}
