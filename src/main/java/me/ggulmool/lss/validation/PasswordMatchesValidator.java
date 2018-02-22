package me.ggulmool.lss.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import me.ggulmool.lss.model.User;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final User user = (User) obj;
        if (user.getPassword() == null) {
            return false;
        }
        return user.getPassword().equals(user.getPasswordConfirmation());
    }

}
