package sample.web.ui.mvc.contrroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
  
@Controller
@RequestMapping("test")
final class TestController {
  
    @RequestMapping(value = "1")
    @ResponseBody
    public String step1() {
    	return "test/1";
    }
}
