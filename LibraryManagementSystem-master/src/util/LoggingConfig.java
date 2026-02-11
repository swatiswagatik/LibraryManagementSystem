package util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Simple logging initializer to ensure INFO level messages printed.
 */
public class LoggingConfig {
    public static void init() {
        Logger root = Logger.getLogger("");
        root.setLevel(Level.INFO);
        for (java.util.logging.Handler h : root.getHandlers()) {
            if (h instanceof ConsoleHandler) {
                h.setLevel(Level.INFO);
                h.setFormatter(new SimpleFormatter());
            }
        }
    }
}