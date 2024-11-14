package io.github.mkaksoy.elementmanager.utils.levels;

import java.util.logging.Level;

public class Levels extends Level {
    public static final Level CHAT_MESSAGE = new Levels("Chat Message", 850);
    public static final Level TASK = new Levels("TASK", 850);
    public static final Level ERROR = new Levels("ERROR", 1100);

    protected Levels(String name, int value) {
        super(name, value);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
