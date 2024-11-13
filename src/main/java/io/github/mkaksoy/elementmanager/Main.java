package io.github.mkaksoy.elementmanager;

import io.github.mkaksoy.elementmanager.management.Management;
import io.github.mkaksoy.elementmanager.tasks.AutoLogger;
import io.github.mkaksoy.elementmanager.tasks.AutoRestart;
import io.github.mkaksoy.elementmanager.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("Manager");

    @Override
    public void onEnable() {
        logger.info("Enabled Element Manager successfully!");

        try {
            Management.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getServer().getPluginManager().registerEvents(new AutoLogger(), this);
    }

    @Override
    public void onDisable() {
        logger.info("Disabled Element Manager successfully!");
        if (Boolean.TRUE.equals(Config.get("options.auto-restart"))) {
            AutoRestart.restart();
        }
    }
}
