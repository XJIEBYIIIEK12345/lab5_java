package laba5;

import java.lang.annotation.*;

/**
 * Помечает поле для автоматического внедрения зависимости.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {}