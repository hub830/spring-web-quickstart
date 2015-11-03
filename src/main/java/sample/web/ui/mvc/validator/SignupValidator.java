
package sample.web.ui.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sample.web.ui.mvc.command.SignupCommand;

public class SignupValidator implements Validator {

//    private static final String SIMPLE_EMAIL_REGEX = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}";

    public boolean supports(Class<?> aClass) {
        return SignupCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "请输入您的用户名.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "请输入您的密码.");
    }
}
