package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.utils.Paths;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;

public class Config {
    private static final File configFile = Paths.configFile;
    public static YamlConfiguration config;

    static {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch (Exception e) {
            getLogger().warning("Error loading 'config.yml': " + e.getMessage());
        }
    }
}
