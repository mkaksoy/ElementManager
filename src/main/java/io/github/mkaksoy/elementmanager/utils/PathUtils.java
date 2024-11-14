package io.github.mkaksoy.elementmanager.utils;

import java.io.File;

import static org.bukkit.Bukkit.getServer;

public class PathUtils {
    public static File management = new File(getServer().getWorldContainer(), "management");
    public static File config = new File(management, "config.yml");
    // Coming soon: public static File backups = new File(management, "backups");
    public static File logs = new File(management, "logs");
    public static File tasks = new File(management, "tasks");
}
