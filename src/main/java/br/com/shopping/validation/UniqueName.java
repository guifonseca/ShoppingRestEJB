package br.com.shopping.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
@Documented
public @interface UniqueName {
	String message() default "Nome ja cadastrado.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	@Target({ PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
		UniqueName[] value();
    }
}
