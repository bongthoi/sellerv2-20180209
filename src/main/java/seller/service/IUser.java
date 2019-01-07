package seller.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import seller.domain.User;
import seller.domain.frmChangePassword;

public interface IUser {
	List<User> findAll();
	boolean isExists(String username);
	User save(User user);
	User findOne(String username);
	User findByEmail(String username);
	void active(String[] arr_id);
	void disabled(String[] arr_id);
	void delete(String[] arr_id);
	Page<User> findAll(Pageable pageable);
	void ChangePassword(frmChangePassword frm);
}
