package nbu.bg.logisticscompany.annotation.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Is office employee.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('OFFICE_EMPLOYEE') or hasAuthority('ADMIN')")
public @interface isOfficeEmployee {
}