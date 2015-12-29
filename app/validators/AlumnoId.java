package validators;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlumnoIdValidator.class)
public @interface AlumnoId
{
    String message() default "invalid_alumnoId";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
