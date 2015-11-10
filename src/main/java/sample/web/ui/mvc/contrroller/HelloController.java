package sample.web.ui.mvc.contrroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sample.web.ui.domain.Administrator;
import sample.web.ui.service.AdministratorService;


@Controller
public class HelloController {

	@Autowired
	private AdministratorService administratorService;
	
    @RequestMapping(value="", method=RequestMethod.GET)
    public String index(Model model) {
    	Iterable<Administrator> list = administratorService.findAll();
    	model.addAttribute("list",list);
        return "index";
    }
    @RequestMapping(value="index", method=RequestMethod.GET)
    public String index2() {
        return "index";
    }
    
    
}
