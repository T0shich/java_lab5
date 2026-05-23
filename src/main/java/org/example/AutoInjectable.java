package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


/**
 * Аннотация для автоматического внедрения зависимостей.
 * Маркирует поля, которые {@link Injector} должен инициализировать
 * экземпляром класса из файла конфигурации.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}