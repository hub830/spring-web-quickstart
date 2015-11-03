package sample.web.ui.mvc.contrroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.web.ui.service.TestAService;


@Controller
@RequestMapping(value = "test")
public class TestController {

	@Autowired
	private TestAService testAService;
	
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
