package org.aren.particleanimationapi.test;

import org.aren.particleanimationapi.animation.Animation;
import org.aren.particleanimationapi.animation.animate.AnimateFactory;
import org.aren.particleanimationapi.particle.ParticleWrapper;
import org.aren.particleanimationapi.pattern.Pattern;
import org.aren.particleanimationapi.util.Range;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
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

        Player player = (Player) commandSender;
        player.sendMessage("실행되었습니다.");

        Pattern pattern = Pattern.builder()
                .range(0, 0)
                .nextPatterns(
                        Pattern.builder()
                                .particle(
                                        ParticleWrapper.builder()
                                                .particle(Particle.REDSTONE)
                                                .data(
                                                        new Particle.DustOptions(
                                                                Color.fromRGB(0x22092C),
                                                                0.5f
                                                        )
                                                )
                                                .build()
                                )
                                .range(new Range(0, 180, 5))
                                .offset(0, 0,0)
                                .xyz(d -> {
                                    double r = 1;
                                    double height = d / 90;
                                    double x = r * Math.cos(Math.toRadians(d));
                                    double y = r * Math.sin(Math.toRadians(d));
                                    return new Vector(x, height, y);
                                })
                                .build(),
                        Pattern.builder()
                                .particle(
                                        ParticleWrapper.builder()
                                                .particle(Particle.REDSTONE)
                                                .data(
                                                        new Particle.DustOptions(
                                                                Color.fromRGB(0xBE3144),
                                                                0.5f
                                                        )
                                                )
                                                .build()
                                )
                                .range(new Range(0, 180, 5))
                                .xyz(d -> {
                                    double r = 1;
                                    double height = 2 - (d / 90);
                                    double x = r * Math.cos(Math.toRadians(d));
                                    double y = r * Math.sin(Math.toRadians(d));
                                    return new Vector(x, height, y);
                                })
                                .build()
                )
                .build();

        Animation animation = Animation.create(
                pattern,
                player.getLocation()
        );
        animation.addAnimate(AnimateFactory.createIdle(20));
        animation.play(plugin, 0, 1);

        return true;
    }
}
