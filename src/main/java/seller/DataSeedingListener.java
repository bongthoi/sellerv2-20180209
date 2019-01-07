package seller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;


import seller.domain.Role;
import seller.domain.User;
import seller.domain.UserRole;
import seller.repository.RoleRepository;
import seller.repository.UserRepository;
import seller.repository.UserRoleRepository;

//@Component
public class DataSeedingListener  implements ApplicationListener<ContextRefreshedEvent>  {
	  @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private UserRoleRepository userroleRepository;
	    
	    @Autowired
	    private RoleRepository roleRepository;

	    @Autowired 
	    private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		   // Roles
        if (roleRepository.findOne("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN","admin"));
        }

        if (roleRepository.findOne("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER","member"));
        }

        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setUsername("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setFirstName("admin first name");
            admin.setLastName("admin last name");
            admin.setEnabled(1);
           /* HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findOne("ROLE_ADMIN"));
            roles.add(roleRepository.findOne("ROLE_MEMBER"));
            admin.setRoles(roles);*/
            userRepository.save(admin);
            
            HashSet<UserRole> userroles = new HashSet<>();
            userroles.add(new UserRole(null,admin.getUsername(),"ROLE_ADMIN"));
            userroles.add(new UserRole(null,admin.getUsername(),"ROLE_MEMBER"));
            userroleRepository.save(userroles);
        }

        // Member account
        if (userRepository.findByEmail("member@gmail.com") == null) {
            User user = new User();
            user.setUsername("member@gmail.com");
            user.setFirstName("member first name");
            user.setLastName("member last name");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setEnabled(1);
            /*HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findOne("ROLE_MEMBER"));
            user.setRoles(roles);*/
            userRepository.save(user);
            HashSet<UserRole> userroles = new HashSet<>();
            userroles.add(new UserRole(null,user.getUsername(),"ROLE_MEMBER"));
            userroleRepository.save(userroles);
        }
    }
		
}


