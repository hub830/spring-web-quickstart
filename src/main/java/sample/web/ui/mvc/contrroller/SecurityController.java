package sample.web.ui.mvc.contrroller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.web.ui.domain.Administrator;
import sample.web.ui.exception.CaptchaException;
import sample.web.ui.mvc.command.LoginCommand;
import sample.web.ui.mvc.command.SignupCommand;
import sample.web.ui.mvc.validator.LoginValidator;
import sample.web.ui.security.UsernamePasswordCaptchaToken;
import sample.web.ui.service.AdministratorService;
import sample.web.ui.utils.CaptchaUtil;

@Controller
public class SecurityController {
	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

	private LoginValidator loginValidator = new LoginValidator();

	@Autowired
	AdministratorService administratorService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Model model, @ModelAttribute LoginCommand command) {
		return "security/signin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request, @ModelAttribute LoginCommand command, BindingResult errors) {
		loginValidator.validate(command, errors);
		if (errors.hasErrors()) {
			return showLoginForm(model, command);
		}
		UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(command.getName(), command.getPassword(), command.isRememberMe(), command.getCaptcha());
		try {
			SecurityUtils.getSubject().login(token);
		} catch (CaptchaException e) {
			errors.reject(null, "无效的验证码,请重新输入.");
		} catch (AuthenticationException e) {
			errors.reject(null, "密码错误,请重新输入.");
		}

		if (errors.hasErrors()) {
			return showLoginForm(model, command);
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String showSignupForm(Model model, @ModelAttribute SignupCommand command) {
		return "security/signup";
	}
//	@Valid
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String showSignupForm(Model model,@Valid @ModelAttribute SignupCommand command, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.rejectValue("name", "test.REFUSE", "用户名已存在,请重新输入!");
			return showSignupForm(model, command);
		}
		if (administratorService.findByName(command.getName()) != null) {
			errors.rejectValue("name", null, "用户名已存在,请重新输入!");
			return showSignupForm(model, command);
		}
		try {
			Administrator admin = administratorService.createAdmin(command.getName(), command.getPassword(), null, false);
			SecurityUtils.getSubject().login(new UsernamePasswordToken(admin.getName(), command.getPassword()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

	@RequestMapping("/captcha")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {

		// 设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		// 不缓存此内容
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		try {

			HttpSession session = request.getSession();

			CaptchaUtil tool = new CaptchaUtil();
			StringBuffer code = new StringBuffer();
			BufferedImage image = tool.genRandomCodeImage(code);
			session.removeAttribute(CaptchaUtil.KEY_CAPTCHA);
			session.setAttribute(CaptchaUtil.KEY_CAPTCHA, code.toString());

			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "JPEG", response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
*/
}
