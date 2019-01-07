package seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FE_HomeCtrl {

	
	
	@GetMapping("/")
    public String create(Model model) {

		
		model.addAttribute("headTitle", "Home");
        return "client/index";
    }
	
	 
}
