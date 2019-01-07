package seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testctrl {

	@RequestMapping(value={"/admin/test"})
	public String index(Model model){
		model.addAttribute("headTitle", "test");
		return "admin/test/text";
	}
	@RequestMapping(value={"/user/test"})
	public String index2(Model model){
		model.addAttribute("headTitle", "test user");
		return "admin/test/text";
	}
}
