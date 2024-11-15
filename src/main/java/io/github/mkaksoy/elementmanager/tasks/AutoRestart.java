package io.github.mkaksoy.elementmanager.tasks;

import io.github.mkaksoy.elementmanager.utils.Config;
import io.github.mkaksoy.elementmanager.utils.levels.Error;
import io.github.mkaksoy.elementmanager.utils.levels.Fatal;

import java.io.IOException;

import static io.github.mkaksoy.elementmanager.Main.logger;

public class AutoRestart {

    public static void restart() {
        try {
            String command = "java -Xmx" + Config.config.getInt("server.memory") +
                    "M -jar " + Config.config.getString("server.jar") +
                    ((Config.config.getBoolean("server.gui")) ? "" : " nogui");

            String terminalCommand = getTerminalCommand(command);
            ProcessBuilder processBuilder = new ProcessBuilder(terminalCommand.split(" "));
            processBuilder.inheritIO();

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Server started successfully!");
            } else {
                System.out.println("Server start failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            logger.log(Fatal.FATAL, "Error restarting server.", e);
        }
    }

    private static String getTerminalCommand(String command) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return "cmd /c start cmd.exe /K \"echo Running command: " + command + " && " + command + "\"";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return "gnome-terminal -- bash -c \"echo 'Running command: " + command + "' && " + command + " && read -p 'Press Enter to exit...'\"";
        }

        return command;
    }
}
