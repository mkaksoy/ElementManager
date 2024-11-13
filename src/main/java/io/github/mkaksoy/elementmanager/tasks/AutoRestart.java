package io.github.mkaksoy.elementmanager.tasks;

import io.github.mkaksoy.elementmanager.utils.Config;

import java.io.IOException;

public class AutoRestart {

    public static void restart() {
        try {
            String command = "java -Xmx" + Config.get("server.memory") +
                    "M -jar " + Config.get("server.jar") +
                    (Boolean.TRUE.equals(Config.get("server.jar")) ? "" : " nogui");

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
            e.fillInStackTrace();
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
