package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;
    private static final String CONFIG_FILE = "src/main/resources/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static int getViewportWidth() {
        return Integer.parseInt(getProperty("viewport.width"));
    }

    public static int getViewportHeight() {
        return Integer.parseInt(getProperty("viewport.height"));
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.onFailure"));
    }

    public static String getScreenshotDir() {
        return getProperty("screenshot.dir");
    }
}