package io.github.mkaksoy.elementmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabled Element Manager successfully!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disabled Element Manager successfully!");
    }
}
