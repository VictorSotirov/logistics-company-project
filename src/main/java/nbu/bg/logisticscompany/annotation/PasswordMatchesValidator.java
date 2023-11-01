package nbu.bg.logisticscompany.annotation;

import nbu.bg.logisticscompany.model.dto.UserRegisterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserRegisterDto user = (UserRegisterDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}