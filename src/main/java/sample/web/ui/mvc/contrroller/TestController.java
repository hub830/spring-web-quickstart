package sample.web.ui.mvc.contrroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
  
import java.time.LocalDate;
import java.time.LocalDateTime;
  
@RestController
@RequestMapping("test")
final class TestController {
  
    @RequestMapping(value = "1")
    @ResponseBody
    public String processDate() {
    	return "aa";
        //Do stuff
    }
  
}
