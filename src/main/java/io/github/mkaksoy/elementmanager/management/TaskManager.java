package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.Main;
import io.github.mkaksoy.elementmanager.utils.Config;
import io.github.mkaksoy.elementmanager.utils.levels.Error;
import io.github.mkaksoy.elementmanager.utils.levels.Info;
import io.github.mkaksoy.elementmanager.utils.levels.Warning;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TaskManager {

    private final Plugin plugin;
    private final BukkitScheduler scheduler;
    private static final Logger logger = Main.logger;
    private final ConfigurationSection tasks = Config.config.getConfigurationSection("tasks");

    public TaskManager(Plugin plugin, BukkitScheduler scheduler) {
        this.plugin = plugin;
        this.scheduler = scheduler;
    }

    public void start() {
        initializeTasks();
    }

    private void initializeTasks() {
        if (tasks != null) {
            for (String taskName : tasks.getKeys(false)) {
                boolean enabled = tasks.getBoolean(taskName + ".enabled", false);
                String fileName = tasks.getString(taskName + ".file", null);
                long interval = tasks.getLong(taskName + ".interval", 0L) * 20;

                if (enabled && fileName != null) {
                    scheduler.runTaskTimer(plugin, new TaskRunner(taskName, fileName), 0, interval);
                    logger.log(Info.INFO, "Scheduled task: " + taskName + " with interval: " + interval/20 + " seconds");
                }
            }
        } else {
            logger.log(Warning.WARNING, "No tasks found in the configuration.");
        }
    }

    private record TaskRunner(String taskName, String fileName) implements Runnable {

        @Override
            public void run() {
                logger.log(Info.INFO, "Running task: " + taskName);
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                File taskFile = new File(Manager.tasks, fileName);

                for (String line : parseCommand(taskFile)) {
                    Bukkit.dispatchCommand(console, line);
                }
            }

            private String[] parseCommand(File source) {
                ArrayList<String> lines = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(source))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (IOException e) {
                    logger.log(Error.ERROR, "Error while running tasks: " + e.getMessage(), e);
                }
                return lines.toArray(new String[0]);
            }
        }
}
