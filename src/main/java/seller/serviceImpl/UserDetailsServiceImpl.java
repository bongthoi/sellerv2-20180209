package seller.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import seller.domain.User;
import seller.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		//Set<Role> roles = new HashSet<Role>(user.getRoles());
		
		/*for (Role role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getId()));
		}*/
		Set<String> roles=user.getRoles();
		for (String role : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role));
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	
}
