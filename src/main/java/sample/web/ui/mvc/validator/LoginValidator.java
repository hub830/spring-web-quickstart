
package sample.web.ui.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sample.web.ui.mvc.command.LoginCommand;

public class LoginValidator implements Validator {
    public boolean supports(@SuppressWarnings("rawtypes") Class aClass) {
        return LoginCommand.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "请输入您的用户名.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "请输入您的密码.");
    }
}
