package io.github.mkaksoy.elementmanager.utils.levels;

import java.util.logging.Level;

public class Message extends Level {
    public static final Level CHAT_MESSAGE = new Message("Chat Message", 850);

    protected Message(String name, int value) {
        super(name, value);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
