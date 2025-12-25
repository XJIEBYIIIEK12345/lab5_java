package laba5;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Простой инжектор зависимостей на основе аннотации {@link AutoInjectable}
 * и файла конфигурации {@code inject.properties}.
 */
public class Injector {

    private final Properties config = new Properties();

    public Injector() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("inject.properties")) {
            if (in == null) throw new RuntimeException("Файл inject.properties не найден");
            config.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки inject.properties", e);
        }
    }

    /**
     * Внедряет зависимости в указанный объект.
     */
    public <T> T inject(T obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implClassName = config.getProperty(interfaceName);
                if (implClassName == null) {
                    throw new RuntimeException("Реализация не задана для: " + interfaceName);
                }
                try {
                    Class<?> implClass = Class.forName(implClassName);
                    Object instance = implClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(obj, instance);
                } catch (Exception e) {
                    throw new RuntimeException("Не удалось внедрить зависимость в поле: " + field.getName(), e);
                }
            }
        }
        return obj;
    }
}
