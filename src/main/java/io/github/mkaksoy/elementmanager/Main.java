package io.github.mkaksoy.elementmanager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Supplier;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabled Element Manager successfully!");
        getLogger().info(getConfig().getString("mesaj"));
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disabled Element Manager successfully!");
    }
}
