package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.Main;
import io.github.mkaksoy.elementmanager.utils.Paths;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Management {
    private static final File configFile = Paths.configFile;
    private static final File managementDir = Paths.management;

    private static void createDirectories() {
        if (!managementDir.exists()) {
            boolean created = managementDir.mkdir();
            if (!created) {
                Main.logger.warning("Error creating 'management' folder.");
            }
        }
    }

    private static void createConfig() throws IOException {
        if (!configFile.exists()) {
            boolean created = configFile.createNewFile();
            if (created) {
                writeConfig();
            } else {
                Main.logger.warning("Error creating 'config.yml' file.");
            }
        }
    }

    private static void writeConfig() {
        try {
            YamlConfiguration config = new YamlConfiguration();
            config.set("options.auto-restart", false);
            config.set("logging.chat.enabled", true);
            config.set("logging.chat.forbidden", new String[]{"shit", "ass", "fuck"});
            config.set("server.jar", "server.jar");
            config.set("server.memory", 4096);
            config.set("server.gui", false);

            config.save(configFile);
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, "Error writing to 'config.yml' file.", e);
        }
    }

    public static void create() throws IOException {
        createDirectories();
        createConfig();
    }
}
