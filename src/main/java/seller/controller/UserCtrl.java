package seller.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




import org.springframework.web.util.UriComponentsBuilder;

import seller.domain.User;
import seller.domain.frmChangePassword;
import seller.layout.support.web.MessageHelper;
import seller.layout.support.web.PageWrapper;
import seller.service.IRole;
import seller.service.IUser;



@Controller
public class UserCtrl {

	@Autowired
	private IUser userService;
	
	@Autowired
	private IRole roleService;
	
	private void AddAtributeIntoForm(Model model){
		
		model.addAttribute("Listroles",roleService.findAll());

	}
	@RequestMapping(value={"admin/User"})
	public String index(Model model,	@RequestParam(value="page",required=false,defaultValue="1") int p){
		if(p==0){
			p=1;
		}
		UriComponentsBuilder  uri=UriComponentsBuilder.fromPath("admin/User");
		PageRequest pageRequest=new  PageRequest(p-1, PageWrapper.MAX_PAGE_ITEM_DISPLAY, Direction.DESC,"RegisterDate");
		Page<User> s=userService.findAll(pageRequest);
		PageWrapper<User> page = new PageWrapper<User>(s, uri.build().toString());
		//List<User> users=userService.findAll();
		model.addAttribute("headTitle", "User");
		model.addAttribute("users", page.getContent());
		 model.addAttribute("page", page);
		return "admin/User/list";
	}
	@GetMapping("admin/User/add")
	public String add(Model model,boolean isBidingResult){
		model.addAttribute("headTitle", "User Add new Item");
		model.addAttribute("h1", "Add item");
		this.AddAtributeIntoForm(model);
		if(!isBidingResult){
			model.addAttribute("user", new User());
		}
		return "admin/User/form";
	}
	
	@GetMapping("admin/User/edit")
	public String edit(Model model,boolean isBidingResult,@RequestParam(value="username") String username){
		model.addAttribute("headTitle", "User Edit Item");
		model.addAttribute("h1", "Edit item");
		this.AddAtributeIntoForm(model);
		if(!isBidingResult){
			model.addAttribute("user",userService.findByEmail(username));
			//System.out.println(userService.findByEmail(username).getRoles().toString());
			//System.out.println(userService.findByEmail(username).getRoles().size());
		}
		return "admin/User/form";
	}
	@GetMapping("admin/User/changepassword")
	public String changepassword(Model model,boolean isBidingResult,@RequestParam(value="username") String username){
		model.addAttribute("headTitle", "User Change Password");
		model.addAttribute("h1", "Change Password");
		if(!isBidingResult){
			model.addAttribute("frmPass",new frmChangePassword(username));
		}
		return "admin/User/changepassword";
	}
	@PostMapping("admin/User/SubmitChangePassword")
	public String SubmitChangePassword(@Valid @ModelAttribute("frmPass") frmChangePassword frmPass,BindingResult result, RedirectAttributes ra ,Model model){
		//  System.out.println(model.containsAttribute("frmPass"));
		 //System.out.println(user.getRoles().toString());
		  if(!frmPass.isMatchPassword()){
			  result.reject("global.error.user.repassword.missmatch", "Retype password miss match!");
		  }
		  if(!userService.isExists(frmPass.getUsername())){
			  result.reject("global.error.user.username.exists", "UserName is not exists!");

		  }
		  if (result.hasErrors()) {
			  			//model.addAttribute("frmPass",frm);
				  	  return changepassword(model,true,frmPass.getUsername());
			}
		  try{
			  //System.out.println(user.getRoles().toString());
			  userService.ChangePassword(frmPass);
			  MessageHelper.addSuccessAttribute(ra, "save.success");
		  }catch(Exception ex){
			  MessageHelper.addErrorAttribute(ra, "save.fail");
	  		throw(ex);
		  }
		  return "redirect:/admin/User";
	}
	@PostMapping("admin/User/save")
	public String Save(@Valid User user,BindingResult result, RedirectAttributes ra  ,Model model){
		 //System.out.println(user.getRoles().toString());
		  if(!user.isMatchPassword()){
			  result.reject("global.error.user.repassword.missmatch", "Retype password miss match!");
		  }
		
			  if(user.isNew()){
				  if(userService.isExists(user.getUsername())){
					  result.reject("global.error.user.username.exists", "UserName is exists!");
		
				  }
				  //System.out.println(result.getAllErrors().toString());
				  if (result.hasErrors()) {
				  	  return add(model,true);
				  	}
	
			  }else{//if update
				  if (result.hasErrors()) {
				  	  return edit(model,true,user.getUsername());
				  	}
			  }
		  try{
			  //System.out.println(user.getRoles().toString());
			  userService.save(user);
			  MessageHelper.addSuccessAttribute(ra, "save.success");
		  }catch(Exception ex){
			  MessageHelper.addErrorAttribute(ra, "save.fail");
	  		throw(ex);
		  }
		  return "redirect:/admin/User";
		
	}

	@PostMapping(value="admin/User/active")
	public String active(@RequestParam(value="arr_id") String[] arr_id,RedirectAttributes ra){
		try{
			  	
			userService.active(arr_id);
	    	    MessageHelper.addSuccessAttribute(ra, "active.success");
	    	  
  	   }catch(Exception ex){
  		   
  		    MessageHelper.addErrorAttribute(ra, "active.fail");
  			throw(ex);
	    	   
  	   }
		 return "redirect:/admin/User";
	}
	@PostMapping(value="admin/User/disabled")
	public String disabled(@RequestParam(value="arr_id") String[] arr_id,RedirectAttributes ra){
		try{
			  	
			userService.disabled(arr_id);
	    	    MessageHelper.addSuccessAttribute(ra, "disabled.success");
	    	  
  	   }catch(Exception ex){
  		   
  		    MessageHelper.addErrorAttribute(ra, "disabled.fail");
  			throw(ex);
	    	   
  	   }
		 return "redirect:/admin/User";
	}
	
	@PostMapping(value="admin/User/delete")
	public String delete(@RequestParam(value="arr_id") String[] arr_id,RedirectAttributes ra){
		try{
			  	
			userService.delete(arr_id);
	    	    MessageHelper.addSuccessAttribute(ra, "delete.success");
	    	  
  	   }catch(Exception ex){
  		   
  		    MessageHelper.addErrorAttribute(ra, "delete.fail");
  			throw(ex);
	    	   
  	   }
		 return "redirect:/admin/User";
	}
}
