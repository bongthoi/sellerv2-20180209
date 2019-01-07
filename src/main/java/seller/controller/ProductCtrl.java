package seller.controller;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import seller.domain.Product;
import seller.layout.support.web.PageWrapper;
import seller.service.IProduct;
import seller.layout.support.web.EAN13Helper;
import seller.layout.support.web.MessageHelper;

@Controller
public class ProductCtrl {

	@Autowired
	private IProduct iProductImpl;
	
	@RequestMapping(value="admin/products")
	private String getContacts(Model model,	@RequestParam(value="page",required=false,defaultValue="1") int p) {
		
		if(p==0){
			p=1;
		}
		UriComponentsBuilder  uri=UriComponentsBuilder.fromPath("admin/products");
		PageRequest pageRequest=new  PageRequest(p-1, PageWrapper.MAX_PAGE_ITEM_DISPLAY, Direction.DESC,"id");
		Page<Product> s=iProductImpl.findAll(pageRequest);
		PageWrapper<Product> page = new PageWrapper<Product>(s, uri.build().toString());
		model.addAttribute("products",page.getContent());
		
		model.addAttribute("headTitle", "products");
		 model.addAttribute("page", page);

		return "admin/product/list";
	}
	
	@RequestMapping("admin/product/add")
	private String add(Model model,boolean isBidingResult){
		
		
		model.addAttribute("headTitle", "layout.title.contact");
		if(!isBidingResult){
			model.addAttribute("product", new Product());
		}
        return "admin/product/form";
	}
	
	@GetMapping("admin/product/edit/{id}")
	public String edit(Model model,boolean isBidingResult,@PathVariable(value="id") String id){
		model.addAttribute("headTitle", "Product Edit Item");
		model.addAttribute("h1", "Edit item");
		if(!isBidingResult){
			model.addAttribute("product",iProductImpl.findByid(id));
		}
		return "admin/product/form";
	}
	
	@PostMapping("admin/product/save")
	public String Save(@Valid Product product,BindingResult result, RedirectAttributes ra,Locale locale,Principal productsession,Model model){
	
		  if(product.isNew()){
			  if (result.hasErrors()) {
			  	  return add(model,true);
			  	}
			  if(iProductImpl.isExists(product.getId())){
				  result.reject("global.error.product.id.exists", "#id is exists!");
				  //System.out.println("#!3");
				  return add(model,true);
			  }
			  //System.out.println("!@3");
		  }else{
			  if (result.hasErrors()) {
				  return edit(model,true,product.getId());
			  	
			  	}
		  }
		  try{
			 
			  if(product.isNew()){//set defaul value when insert
				  product.setSeller(productsession.getName());
			  }else{//set value when update object
				  product.setSeller(productsession.getName());
				  
				  Product productpersit=iProductImpl.findByid(product.getId());
				  product.setSeller(productpersit.getSeller());
				 
			  }
			  iProductImpl.save(product);
			  MessageHelper.addSuccessAttribute(ra, "save.success");
		  }catch(Exception ex){
			  MessageHelper.addErrorAttribute(ra, "save.fail");
	  		throw(ex);
		  }
		  return "redirect:/admin/products";
		
	}
	@PostMapping(value="admin/product/active")
	public String active(@RequestParam(value="arr_id") String[] arr_id,RedirectAttributes ra){
		try{
			  	
			iProductImpl.active(arr_id);
	    	    MessageHelper.addSuccessAttribute(ra, "active.success");
	    	  
  	   }catch(Exception ex){
  		   
  		    MessageHelper.addErrorAttribute(ra, "active.fail");
  			throw(ex);
	    	   
  	   }
		 return "redirect:/admin/products";
	}
	@PostMapping(value="admin/product/disabled")
	public String disabled(@RequestParam(value="arr_id") String[] arr_id,RedirectAttributes ra){
		try{
			  	
			iProductImpl.disabled(arr_id);
	    	    MessageHelper.addSuccessAttribute(ra, "disabled.success");
	    	  
  	   }catch(Exception ex){
  		   
  		    MessageHelper.addErrorAttribute(ra, "disabled.fail");
  			throw(ex);
	    	   
  	   }
		 return "redirect:/admin/products";
	}
	
	@PostMapping(value="admin/product/delete")
	public String delete(@RequestParam(value="arr_id") String[] arr_id,RedirectAttributes ra){
		try{
			  	
			iProductImpl.delete(arr_id);
	    	    MessageHelper.addSuccessAttribute(ra, "delete.success");
	    	  
  	   }catch(Exception ex){
  		   
  		    MessageHelper.addErrorAttribute(ra, "delete.fail");
  			throw(ex);
  	   }
		 return "redirect:/admin/products";
	}
	
	
	
	@GetMapping(value="admin/product/get/barcode")
	@ResponseBody
	public String getbarcode(){
		String EAN13="";
		try{
			String BarCodeWithoutChecksum=iProductImpl.GenarateBarcodeWithoutCheckSum();
			if(!BarCodeWithoutChecksum.isEmpty()){
				EAN13=EAN13Helper.calculateCodeWithcheckSum(BarCodeWithoutChecksum);
			}
			
  	   }catch(Exception ex){
  		   throw(ex);
  	   }
		 return EAN13;
	}
}