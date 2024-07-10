package pja.edu.pl.s27591.hairadise.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordChecker.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheck {
    String message() default "Password must include lowercase letters, uppercase letters, " +
            "digits and special character to ensure security.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
