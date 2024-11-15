package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.Main;
import io.github.mkaksoy.elementmanager.tasks.AutoLogger;
import io.github.mkaksoy.elementmanager.utils.Config;
import io.github.mkaksoy.elementmanager.utils.FileManager;
import io.github.mkaksoy.elementmanager.utils.levels.Error;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;

import static io.github.mkaksoy.elementmanager.Main.logger;
import static io.github.mkaksoy.elementmanager.Main.server;

public class Manager {
    public Plugin plugin;
    private final BukkitScheduler scheduler = server.getScheduler();
    private final TaskManager taskManager = new TaskManager(Main.getPlugin(Main.class), scheduler);

    public static File management = new File(server.getWorldContainer(), "management");
    public static File logs = new File(management, "logs");
    public static File tasks = new File(management, "tasks");
    public static File config = new File(management, "config.yml");

    public Manager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void create() {
        FileManager.createFolder(management, "management");
        FileManager.createFolder(logs, "logs");
        FileManager.createFolder(tasks, "tasks");

        FileManager.createFile(config, "config.yml", writeConfig());
    }

    public void start() {
        if (Config.config.getBoolean("options.auto-logging")) {
            server.getPluginManager().registerEvents(new AutoLogger(), plugin);
        }

        taskManager.start();
    }

    private Runnable writeConfig() {
        return () -> {
            try {
                YamlConfiguration config = new YamlConfiguration();
                config.set("options.auto-restart", true);
                config.set("options.auto-logging", true);
                config.set("logging.chat.enabled", true);
                config.set("logging.chat.forbidden", new String[]{});
                config.set("tasks.test.enabled", true);
                config.set("tasks.test.interval", 10);
                config.set("tasks.test.file", "test.command");
                config.set("server.jar", "server.jar");
                config.set("server.memory", 4096);
                config.set("server.gui", false);

                config.save(config.getCurrentPath());
            } catch (IOException e) {
                logger.log(Error.ERROR, "Error writing to 'config.yml' file.", e);
            }
        };
    }
}
