package org.example;

/**
 * Класс, демонстрирующий работу внедрения зависимостей.
 * Использует аннотацию {@link AutoInjectable} для автоматической инициализации полей.
 */
public class SomeBean {
    /** Поле первого интерфейса */
    @AutoInjectable
    private SomeInterface field1;

    /** Поле второго интерфейса */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Выполняет действия, используя внедренные зависимости.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}