package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.Main;
import io.github.mkaksoy.elementmanager.utils.FileUtils;
import io.github.mkaksoy.elementmanager.utils.PathUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.util.logging.Level;

public class Management {

    private static void createDirectories() {
        FileUtils.createFolder(PathUtils.management, "management");
        FileUtils.createFolder(PathUtils.logs, "logs");
        FileUtils.createFolder(PathUtils.tasks, "tasks");
    }

    private static void createConfig() throws IOException {
        FileUtils.createFile(PathUtils.config, "config.yml", writeConfig());
    }

    private static Runnable writeConfig() {
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

                config.save(PathUtils.config);
            } catch (IOException e) {
                Main.logger.log(Level.SEVERE, "Error writing to 'config.yml' file.", e);
            }
        };
    }

    public static void create() throws IOException {
        createDirectories();
        createConfig();
    }
}
