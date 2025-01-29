package Utils;

public class Logger {
    public static void logError(String message) {
        System.err.println("ERROR: " + message);
    }

    public static void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
}