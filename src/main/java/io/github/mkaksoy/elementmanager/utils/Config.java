package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private static final File configFile = Paths.configFile;
    public static YamlConfiguration config;

    static {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
            Main.logger.info("config.yml successfully loaded.");
        } catch (Exception e) {
            Main.logger.severe("Error loading 'config.yml': " + e.getMessage());
        }
    }

    public static <T> T get(String path) {
        if (config == null) {
            throw new IllegalStateException("Configuration has not been loaded.");
        } else if (config.contains(path)) {
            @SuppressWarnings("unchecked")
            T value = (T) config.get(path);
            return value;
        } else {
            Main.logger.warning("Path '" + path + "' not found in config.yml.");
            return null;
        }
    }

}
