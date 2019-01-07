package seller.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import seller.domain.User;
import seller.domain.UserRole;
import seller.domain.frmChangePassword;
import seller.repository.UserRepository;
import seller.repository.UserRoleRepository;
import seller.service.IUser;

@Service
public class IUserImpl implements IUser {
	 @Autowired
	 private UserRepository userRepository;
	 
	    @Autowired
	    private UserRoleRepository userroleRepository;
	    
	    @Autowired 
	    private PasswordEncoder passwordEncoder;
	@Override
	@Transactional
	public User save(User user) {
		// TODO Auto-generated method stub
		  if(user.isNew()){//set defaul value when insert
			  user.setRegisterDate(new Date());
		  }else{
			User userpersit=userRepository.findOne(user.getUsername());
			  user.setRegisterDate(userpersit.getRegisterDate());
			  user.setPassword(userpersit.getPassword());
		  }
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		StringBuilder bu=new StringBuilder();
		/*for (int i=0;i<user.getRoles().size();i++) {
			if(!userroleRepository.exists(user.getUsername(), user.getRoles().get(i).getId())){
				userroleRepository.save(new UserRole(null,user.getUsername(),user.getRoles().get(i).getId()));
			}
			bu.append("'"+user.getRoles().get(i).getId()+"'");
			if(i<user.getRoles().size()-1){
				bu.append(",");
			}
		}*/
		for (String role : user.getRoles()) {
			if(!userroleRepository.exists(user.getUsername(),role)){
				userroleRepository.save(new UserRole(null,user.getUsername(),role));
			}
			bu.append("'"+role+"'");
			bu.append(",");
		}
		if(bu.length()>0){
			bu.deleteCharAt(bu.length()-1);
			//System.out.println(bu.toString());
			userroleRepository.delete( user.getUsername(),bu.toString());
		}
		return user;
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	@Override
	public boolean isExists(String username) {
		// TODO Auto-generated method stub
		return userRepository.exists(username);
	}
	

	@Override
	@Transactional
	public void active(String[] arr_id) {
		// TODO Auto-generated method stub
		if(arr_id.length>0){
			for(int i=0;i<arr_id.length;i++){
				userRepository.active(arr_id[i]);
			}
		}
	}

	@Override
	@Transactional
	public void disabled(String[] arr_id) {
			// TODO Auto-generated method stub
		if(arr_id.length>0){
			for(int i=0;i<arr_id.length;i++){
				userRepository.disabled(arr_id[i]);
			}
		}
		
	}

	@Override
	@Transactional
	public void delete(String[] arr_id) {
		// TODO Auto-generated method stub
		if(arr_id.length>0){
			for(int i=0;i<arr_id.length;i++){
				userRepository.delete(arr_id[i]);
			}
		}
		
	}
	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findAll(pageable);
	}
	@Override
	public User findByEmail(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(username);
	}
	@Override
	public User findOne(String username) {
		// TODO Auto-generated method stub
		return userRepository.findOne(username);
	}
	@Override
	@Transactional
	public void ChangePassword(frmChangePassword frm) {
		User user=userRepository.findOne(frm.getUsername());
		user.setPassword(passwordEncoder.encode(frm.getNewpassword()));
		userRepository.save(user);
		
	}

}
