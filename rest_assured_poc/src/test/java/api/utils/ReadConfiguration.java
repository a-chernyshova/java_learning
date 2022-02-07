package api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfiguration {
    private Properties properties;
    private static String appUrl = null;
    private static String userName = null;
    private static String userPass = null;
    String propertyFilePath = "resources/config.properties";

    public ReadConfiguration() {
        InputStream reader;
        try {
            reader = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Configuration not found at %s", propertyFilePath));
        }
    }

    public void  setAppUrl(String url) {
        appUrl = url;
    }

    public String  getAppUrl() {
        String url = System.getProperty("url");
        if (url == null)
            return properties.getProperty("app.url");
        else if (url != null)
            return url;
        else
            throw  new RuntimeException("url was not found in the config file");
    }

    public String  getUserName() {
        String url = System.getProperty("username");
        if (url == null)
            return properties.getProperty("username");
        else if (url != null)
            return url;
        else
            throw  new RuntimeException("username was not found in the config file");
    }

    public String  getUserPass() {
        String url = System.getProperty("userpass");
        if (url == null)
            return properties.getProperty("userpass");
        else if (url != null)
            return url;
        else
            throw  new RuntimeException("userpass was not found in the config file");
    }
}
