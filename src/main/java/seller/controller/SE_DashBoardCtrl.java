package seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SE_DashBoardCtrl {

	@RequestMapping(value={"seller/DashBoard","seller","seller/"})
	public ModelAndView DashBoard(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("headTitle", "DashBoard");
		mav.setViewName("seller/home/DashBoard");
		return mav;
	}
	 
}
