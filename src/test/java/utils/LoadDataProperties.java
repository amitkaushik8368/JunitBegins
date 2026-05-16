package utils;

import java.io.FileInputStream;

import java.util.Properties;

public class LoadDataProperties
{
    static Properties properties;
    static {
        properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
            properties.load(fis);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key)
    {
        return properties.getProperty(key);
    }
}
