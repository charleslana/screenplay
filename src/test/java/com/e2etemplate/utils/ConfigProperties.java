package com.e2etemplate.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigProperties.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo application.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Integer getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static Boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
