package io.github.mkaksoy.elementmanager.utils;

import io.github.mkaksoy.elementmanager.management.Manager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static io.github.mkaksoy.elementmanager.Main.logger;

public class Config {
    private static final File configFile = Manager.config;
    public static YamlConfiguration config;

    static {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
            logger.info("config.yml successfully loaded.");
        } catch (Exception e) {
            logger.severe("Error loading 'config.yml': " + e.getMessage());
        }
    }
}
