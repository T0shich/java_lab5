package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private Properties properties;

    public Injector() {
        properties = new Properties();
        try {
            properties.load(Injector.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.err.println("Не удалось найти или прочитать файл config.properties из ресурсов");
            e.printStackTrace();
        }
    }

    public <T> T inject(T object) {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implClassName = properties.getProperty(interfaceName);
                if (implClassName != null) {
                    try {
                        Class<?> clazz = Class.forName(implClassName);
                        Object implInstance = clazz.getDeclaredConstructor().newInstance();
                        field.setAccessible(true);
                        field.set(object, implInstance);
                    } catch (Exception e) {
                        System.err.println("Ошибка при создании или внедрении класса: " + implClassName);
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }
}