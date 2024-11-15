package io.github.mkaksoy.elementmanager;

import io.github.mkaksoy.elementmanager.management.Manager;
import io.github.mkaksoy.elementmanager.tasks.AutoRestart;
import io.github.mkaksoy.elementmanager.utils.Config;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static final Logger logger = Logger.getLogger("Manager");
    public static Server server;

    @Override
    public void onEnable() {
        logger.log(Level.INFO, "Enabled Manager successfully!");

        server = getServer();

        Manager manager = new Manager(this);
        manager.create();
        manager.start();
    }

    @Override
    public void onDisable() {
        logger.log(Level.INFO, "Disabled Manager successfully!");
        if (Config.config.getBoolean("options.auto-restart")) {
            AutoRestart.restart();
        }
    }
}
