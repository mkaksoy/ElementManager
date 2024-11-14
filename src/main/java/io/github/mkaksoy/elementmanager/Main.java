package io.github.mkaksoy.elementmanager;

import io.github.mkaksoy.elementmanager.management.Management;
import io.github.mkaksoy.elementmanager.management.TaskManager;
import io.github.mkaksoy.elementmanager.tasks.AutoLogger;
import io.github.mkaksoy.elementmanager.tasks.AutoRestart;
import io.github.mkaksoy.elementmanager.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private final BukkitScheduler scheduler = this.getServer().getScheduler();
    public static final Logger logger = Logger.getLogger("Manager");

    @Override
    public void onEnable() {
        logger.info("Enabled Manager successfully!");

        try {
            Management.create();
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        if (Config.config.getBoolean("options.auto-logging")) {
            getServer().getPluginManager().registerEvents(new AutoLogger(), this);
        }

        new TaskManager(this, scheduler);
    }

    @Override
    public void onDisable() {
        logger.info("Disabled Manager successfully!");
        if (Config.config.getBoolean("options.auto-restart")) {
            AutoRestart.restart();
        }
    }
}
