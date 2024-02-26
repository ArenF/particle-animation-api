package org.aren.particleanimationapi;

import org.aren.particleanimationapi.test.TestExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ParticleAnimationAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("test").setExecutor(new TestExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
