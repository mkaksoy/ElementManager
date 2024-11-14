package io.github.mkaksoy.elementmanager.management;

import io.github.mkaksoy.elementmanager.Main;
import io.github.mkaksoy.elementmanager.utils.Config;
import io.github.mkaksoy.elementmanager.utils.PathUtils;
import io.github.mkaksoy.elementmanager.utils.levels.Levels;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    private final Plugin plugin;
    private final BukkitScheduler scheduler;

    public TaskManager(Plugin plugin, BukkitScheduler scheduler) {
        this.plugin = plugin;
        this.scheduler = scheduler;
        initializeTasks();
    }

    private void initializeTasks() {
        var tasksSection = Config.config.getConfigurationSection("tasks");

        if (tasksSection != null) {
            for (String taskName : tasksSection.getKeys(false)) {
                boolean enabled = tasksSection.getBoolean(taskName + ".enabled", false);
                String fileName = tasksSection.getString(taskName + ".file", null);
                long interval = tasksSection.getLong(taskName + ".interval", 600L) * 20; // Default to 600 seconds

                if (enabled && fileName != null) {
                    scheduler.runTaskTimer(plugin, new TaskRunner(taskName, fileName), 0, interval);
                    Main.logger.log(Levels.TASK, "Scheduled task: " + taskName + " with interval: " + interval/20 + " seconds");
                }
            }
        } else {
            Main.logger.warning("No tasks found in the configuration.");
        }
    }

    private record TaskRunner(String taskName, String fileName) implements Runnable {

        @Override
            public void run() {
                Main.logger.log(Levels.TASK, "Running task: " + taskName);
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                File taskFile = new File(PathUtils.tasks, fileName);

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
                    Main.logger.log(Levels.ERROR, "Error while running tasks: " + e.getMessage(), e);
                }
                return lines.toArray(new String[0]);
            }
        }
}
