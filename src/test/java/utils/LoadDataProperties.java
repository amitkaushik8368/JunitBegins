package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadDataProperties
{
    static Properties properties;
    public static void loadProperties() throws IOException {
        properties = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties"
        );
        properties.load(fis);
        fis.close();
    }

    public static String getValidUsername()
    {
        return properties.getProperty("valid.username");
    }
    public static String getURL()
    {
        return properties.getProperty("url");
    }
    public static String getInvalidUsername()
    {
        return properties.getProperty("invalid.username");
    }
    public static String getValidPassword()
    {
        return properties.getProperty("valid.password");
    }
    public static String getInvalidPassword()
    {
        return properties.getProperty("invalid.password");
    }
    public static String getBrowser()
    {
        return properties.getProperty("browser");
    }

}
