package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private static final File configFile = PathUtils.config;
    public static YamlConfiguration config;

    static {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
            Main.logger.info("config.yml successfully loaded.");
        } catch (Exception e) {
            Main.logger.severe("Error loading 'config.yml': " + e.getMessage());
        }
    }
}
