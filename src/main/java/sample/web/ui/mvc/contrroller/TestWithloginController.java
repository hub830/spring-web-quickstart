package sample.web.ui.mvc.contrroller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.web.ui.domain.TestA;
import sample.web.ui.service.TestAService;

@Controller
@RequestMapping(value = "test/login")
public class TestWithloginController {

	@Autowired
	private TestAService testAService;

	@RequiresAuthentication
	@RequestMapping(value = "test1", method = RequestMethod.POST)
	public String test1(TestA testA) {
		testAService.save(testA);
		return "index";
	}

	@RequiresAuthentication
	@RequestMapping(value = "test2", method = RequestMethod.GET)
	public String index2() {
		testAService.test3(1l);
		return "index";
	}

}
