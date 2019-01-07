package seller.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seller.domain.Role;
import seller.repository.RoleRepository;
import seller.service.IRole;

@Service
public class IRoleImpl implements IRole {
	 @Autowired
	    private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}
