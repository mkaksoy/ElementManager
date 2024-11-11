package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.utils.Paths;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;

public class Management {
    private static final File configFile = Paths.configFile;
    private static final File managementDir = Paths.management;

    private static void createDirectories() {
        if (!managementDir.exists()) {
            boolean created = managementDir.mkdirs();
            if (created) {
                getLogger().info("Created 'management' folder successfully.");
            } else {
                getLogger().warning("Error creating 'management' folder.");
            }
        } else {
            getLogger().info("'management' folder already exists.");
        }
    }

    private static void createConfig() throws IOException {
        if (!configFile.exists()) {
            boolean created = configFile.createNewFile();
            if (created) {
                getLogger().info("Created 'config.yml' file successfully.");
                writeConfig();
            } else {
                getLogger().warning("Error creating 'config.yml' file.");
            }
        } else {
            getLogger().info("'config.yml' file already exists.");
        }
    }

    private static void writeConfig() {
        try {
            YamlConfiguration config = new YamlConfiguration();
            // Coming soon: config.set("options.enable-logging", true);
            config.set("options.auto-restart", true);
            config.set("server.jar", "server.jar");
            config.set("server.memory", 4096);
            config.set("server.gui", false);

            config.save(configFile);
            getLogger().info("Wrote to 'config.yml' successfully!");
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Error writing to 'config.yml' file.", e);
        }
    }

    public static void create() throws IOException {
        createDirectories();
        createConfig();
    }
}
