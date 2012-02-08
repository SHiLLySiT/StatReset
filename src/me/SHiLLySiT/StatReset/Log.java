package me.SHiLLySiT.StatReset;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Log {
	@SuppressWarnings("unused")
	private static StatReset plugin;
	public static Logger log =  Logger.getLogger("Minecraft");
	public static String prefix;
	
    public static void initialize(StatReset instance, Logger newLog) {
        plugin = instance;
        Log.log = newLog;
        prefix = "[StatReset] ";
    }

    public static Logger getLog() {
        return log;
    }

    public static void info(String message) {
        log.info(prefix + message);
    }

    public static void error(String message) {
        log.severe(prefix + message);
    }

    public static void warning(String message) {
        log.warning(prefix + message);
    }

    public static void config(String message) {
        log.config(prefix + message);
    }

    public static void log(Level level, String message) {
        log.log(level, prefix + message);
    }
}