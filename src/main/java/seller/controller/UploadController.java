package seller.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Upload Controller
 *
 * @author ltornquist
 * @version 2/7/17
 * @since 1.0.0
 */
@RestController
public class UploadController {

	   @RequestMapping(value = "upload/publicimg/",method = RequestMethod.POST, produces  = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	    public ResponseEntity<?> uploadFileimg(@RequestParam("img") MultipartFile file,
	    		@RequestParam(value ="folder",required = true) String foldersett,HttpServletRequest request) {
	    	String filename="";
	    	ServletContext servl  = request.getServletContext();
			String pathsave="/upload/";
		
			String savePath =servl.getRealPath(pathsave+foldersett);
			//System.out.println(savePath);
	        if (file.isEmpty()) {
	            return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
	        }

	        try {

	        	filename=   saveUploadedFiles(Arrays.asList(file),savePath);

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        	//System.out.println(filename);
	        return new ResponseEntity<Object>(filename, new HttpHeaders(), HttpStatus.OK);

	    }  
	   
	   
	   @RequestMapping(value = "upload/ByYearFolder/",method = RequestMethod.POST, produces  = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	    public ResponseEntity<?> ByYearFolder(@RequestParam("file") MultipartFile file,
	    		@RequestParam(value ="folder",required = true) String foldersett,HttpServletRequest request) {
	 			String full_filename = "";
	 			String jsontext="";
	        if (file.isEmpty()) {
	            return new ResponseEntity<Object>("please select a file!", HttpStatus.OK);
	        }
	        try {

	        	ServletContext servl  = request.getServletContext();
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    	Calendar cal = Calendar.getInstance();
				String timenow=dateFormat.format(cal.getTime());
				String full_path="/upload/"+foldersett+"/";
				String [] split_time=timenow.split("-");
				String extend_path=split_time[0]+ File.separator+ split_time[1]+ File.separator+ split_time[2];
				String savePath =servl.getRealPath(full_path) +File.separator +extend_path;
				//System.out.println(savePath);
				//System.out.println(servl.getRealPath("/resource/monitor/"));
				 File directories = new File(savePath);
			        if (!directories.exists()) {
			            if (directories.mkdirs()) {
			              //  System.out.println("Multiple directories are created!");
			            	String name =timenow+"_"+ file.getOriginalFilename();	
						     byte[] bytes = file.getBytes();
					            Path path = Paths.get(savePath + File.separator+ name);
					            Files.write(path, bytes);
					            full_filename=name;		
			            } else {
			               // System.out.println("Failed to create multiple directories!");
			            }
			        }else{//if exists
			         	String name =timenow+"_"+ file.getOriginalFilename();	
			         	 byte[] bytes = file.getBytes();
				            Path path = Paths.get(savePath + File.separator+ name);
				            Files.write(path, bytes);
				            full_filename=name;
			        }
			   jsontext =full_path + timenow.replaceAll("-", "/") +"/" +full_filename;      
	        	//full_filename=   saveUploadedFiles(Arrays.asList(file),savePath);

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        	System.out.println(jsontext);
	    
	        return new ResponseEntity<Object>(jsontext, new HttpHeaders(), HttpStatus.OK);

	    }  

    private String saveUploadedFiles(List<MultipartFile> files,String folder) throws IOException {

    	String filename="";
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			////System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
			String name =dateFormat.format(cal.getTime())+"_"+ file.getOriginalFilename();	
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + File.separator+ name);
            Files.write(path, bytes);
            filename=name;

        }
        return filename;
    }

}