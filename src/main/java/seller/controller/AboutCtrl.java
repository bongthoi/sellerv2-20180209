package seller.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import seller.domain.About;
import seller.layout.support.web.MessageHelper;
import seller.service.IAbout;

@Controller
public class AboutCtrl {

	@Autowired
	private IAbout aboutService;

	@RequestMapping(value = { "admin/about" }, method = RequestMethod.GET)
	public ModelAndView index(Locale locale) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("headTitle", "About");
		mav.addObject("about",aboutService.findOne("about-001") );
		// System.out.println(aboutService.findByLang(locale.toString()).toString());

		mav.setViewName("admin/about/about");
		return mav;
	}

	@PostMapping("admin/about/save")
	public String save(@Valid @ModelAttribute About about,
			BindingResult result, RedirectAttributes ra,Model model) {
		//System.out.println(about.toString() +"1111");
		if (result.hasErrors()) {
			
			//System.out.println(result.getErrorCount());
			//System.out.println(result.getAllErrors());
			MessageHelper.addErrorAttribute(model, "save.fail");
			return "admin/about/about";
		} else
		{	
			
		 About aboutPersisted=aboutService.findOne(about.getId());
		 about.setCreateDate(aboutPersisted.getCreateDate());
		 about.setCreator(aboutPersisted.getCreator());
		 about.setPersisted(true);
			aboutService.save(about);
		MessageHelper.addSuccessAttribute(ra, "save.success");
		return "redirect:/admin/about";
		}
	}
}
