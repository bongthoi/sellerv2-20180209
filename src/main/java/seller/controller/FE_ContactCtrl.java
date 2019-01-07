package seller.controller;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import seller.domain.Contact;
import seller.layout.support.web.MessageHelper;
import seller.service.IContact;



@Controller
public class FE_ContactCtrl {

	@Autowired
	private IContact icontactImp;
	
	@GetMapping("contact/create")
    public String create(Model model) {

		model.addAttribute("contact", new Contact());
		model.addAttribute("headTitle", "layout.title.contact");
       
        return "client/contact/form";
    }
	
	 @PostMapping("contact/save")
	    public String save(Model model,@Valid Contact contact, BindingResult result, RedirectAttributes ra) {
		 if (result.hasErrors()) {
	            return "client/contact/form";
	        }
		 
		 try{
			//contact.setSubject("New Contact");
			contact.setCreateDate(new Date());
			icontactImp.save(contact);
			 
	    	 MessageHelper.addSuccessAttribute(ra, "send.success");
 	   }catch(Exception ex){
 		    MessageHelper.addErrorAttribute(ra, "send.fail");
 			throw(ex);
	    	   
 	   }
			model.addAttribute("headTitle", "Contact");
			
	        return "redirect:/contact/create";
	    }
}
