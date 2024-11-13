package io.github.mkaksoy.elementmanager.utils;

import java.util.logging.LogRecord;

public class Formatter extends java.util.logging.Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getLevel().getName() + ": " + record.getMessage() + "\n";
    }
}
