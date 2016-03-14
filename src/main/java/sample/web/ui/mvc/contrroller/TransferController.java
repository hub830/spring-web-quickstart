package sample.web.ui.mvc.contrroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.web.ui.domain.Transfer;
import sample.web.ui.service.TransferService;
  
@Controller
@RequestMapping("transfer")
final class TransferController {
	@Autowired
	private TransferService service;

    @RequestMapping
    public String list(Model model,Long id) {
    	List<Transfer> list = service.findUnprocessed();
    	model.addAttribute("list", list);
    	return "transfer/list";
    }

    @RequestMapping
    @ResponseBody
    public String step1(Model model,Long id) {
    	service.step1(id);
    	return "success";
    }

    @RequestMapping
    @ResponseBody
    public String step2(Model model,Long id,String captch) {
    	service.step2(id, captch);
    	return "success";
    }
    
}
