package seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DashBoardCtrl {

	@RequestMapping(value={"admin/DashBoard","admin","admin/"})
	public ModelAndView DashBoard(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("headTitle", "DashBoard");
		mav.setViewName("admin/home/DashBoard");
		return mav;
	}
	 @GetMapping("/403")
	    public String accessDenied() {
	        return "403";
	    }
	 @GetMapping("/role")
	    public String getrole() {
	        return "role";
	    }
}
