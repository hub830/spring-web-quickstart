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
@RequestMapping("/api/datetime/")
final class DateTimeController {
  
    @RequestMapping(value = "date")
    @ResponseBody
    public String processDate(@RequestParam("date") LocalDate date) {
    	System.out.println(date);
    	return "aa";
        //Do stuff
    }
  
    @RequestMapping(value = "datetime")
    @ResponseBody
    public String processDateTime(@RequestParam("datetime") LocalDateTime dateAndTime) {
    	System.out.println(dateAndTime);
    	return "aa";
        //Do stuff
    }
}
