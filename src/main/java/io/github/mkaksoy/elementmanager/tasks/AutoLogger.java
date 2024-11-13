package io.github.mkaksoy.elementmanager.tasks;

import io.github.mkaksoy.elementmanager.utils.Config;
import io.github.mkaksoy.elementmanager.utils.FileLogger;
import io.github.mkaksoy.elementmanager.utils.levels.Message;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AutoLogger implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        if (Boolean.TRUE.equals(Config.get("logging.chat.enabled"))) {
            String player = event.getPlayer().getName();
            Component messageComponent = event.message();
            String message = messageComponent instanceof TextComponent ? ((TextComponent) messageComponent).content() : messageComponent.toString();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String time = LocalDateTime.now().format(formatter);

            List<String> forbiddenObject = Config.get("logging.chat.forbidden");

            if (forbiddenObject != null) {
                StringBuilder logMessage = new StringBuilder("[" + time + "] " + "<" + player + "> " + message);

                for (String forbiddenWord : forbiddenObject) {
                    if (message.contains(forbiddenWord)) {
                        logMessage.insert(0, "[FORBIDDEN] ");
                        break;
                    }
                }

                FileLogger.log(Message.CHAT_MESSAGE, logMessage.toString());
            }
        } else {
            System.err.println("Forbidden keywords are in unexpected format!");
        }
    }
}

