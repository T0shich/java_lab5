package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки функциональности класса {@link Injector}.
 */
public class InjectorTest {

    /**
     * Проверяет базовое внедрение зависимостей в объект SomeBean.
     */
    @Test
    public void testInjection() {
        Injector injector = new Injector();
        SomeBean bean = new SomeBean();
        
        injector.inject(bean);
        
        try {
            bean.foo();
        } catch (NullPointerException e) {
            fail("Поля не были внедрены, foo() выбросил NullPointerException");
        }
    }

    /**
     * Вспомогательный класс для проверки внедрения в публичные поля.
     */
    public static class TestBean {
        @AutoInjectable
        public SomeInterface field;
    }

    /**
     * Проверяет внедрение зависимости в конкретное поле и корректность типа созданного объекта.
     */
    @Test
    public void testManualInjection() {
        Injector injector = new Injector();
        TestBean testBean = new TestBean();
        
        assertNull("Поле должно быть null до внедрения", testBean.field);
        
        injector.inject(testBean);
        
        assertNotNull("Поле должно быть инициализировано после внедрения", testBean.field);
        // Проверяем, что объект реализует нужный интерфейс
        assertTrue("Внедренный объект должен реализовывать SomeInterface", testBean.field instanceof SomeInterface);
    }
}
