package sample.web.ui.mvc.contrroller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.web.ui.service.TestAService;


@Controller
@RequestMapping(value = "test/noLogin")
public class TestController {
	Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestAService testAService;

    @RequestMapping(value="login", method=RequestMethod.GET)
    public String login() {
		UsernamePasswordToken token = new UsernamePasswordToken("test027", "111111", false);
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			log.debug(e.getMessage(),e);
		}catch (Exception e) {
			log.debug(e.getMessage(),e);
		}

        return "index";
    }
    
    @RequestMapping(value="test1", method=RequestMethod.GET)
    public String index() {
    	testAService.test2(1l);
        return "index";
    }
    
    @RequestMapping(value="test2", method=RequestMethod.GET)
    public String index2() {
    	testAService.test3(1l);
        return "index";
    }
    
    
}
