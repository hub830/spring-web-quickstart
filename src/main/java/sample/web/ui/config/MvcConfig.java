package sample.web.ui.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	

	@Autowired
	private MessageSource messageSource ;
	
	@Override
	public Validator getValidator() {
	    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	    validator.setValidationMessageSource( messageSource);
	    return validator;
	}
}
