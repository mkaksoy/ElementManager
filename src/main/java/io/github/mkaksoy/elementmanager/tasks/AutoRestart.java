package io.github.mkaksoy.elementmanager.tasks;

import io.github.mkaksoy.elementmanager.management.Config;

import java.io.IOException;

public class AutoRestart {

    public static void restart() {
        try {
            String command = "java -Xmx" + Config.config.getString("server.memory") +
                    "M -jar " + Config.config.getString("server.jar") +
                    (Config.config.getBoolean("server.gui") ? "" : " nogui");

            String terminalCommand = getTerminalCommand(command);
            Process process = Runtime.getRuntime().exec(terminalCommand);
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
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
