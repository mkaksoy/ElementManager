package io.github.mkaksoy.elementmanager;

import io.github.mkaksoy.elementmanager.management.Config;
import io.github.mkaksoy.elementmanager.management.Management;
import io.github.mkaksoy.elementmanager.tasks.AutoRestart;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabled Element Manager successfully!");

        try {
            Management.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disabled Element Manager successfully!");
        if (Config.config.getBoolean("options.auto-restart")) {
            AutoRestart.restart();
        }
    }
}
