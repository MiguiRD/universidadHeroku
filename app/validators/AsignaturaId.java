package validators;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AsignaturaIdValidator.class)
public @interface AsignaturaId
{
    String message() default "invalid_asignaturaId";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
