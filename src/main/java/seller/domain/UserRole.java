package seller.domain;

import org.springframework.data.domain.Persistable;

public class UserRole  implements Persistable<Integer>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2104879398587512784L;
	private Integer id;
	private String username;
	private String  role;
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return id == null || id == 0;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserRole(Integer id, String username, String role) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
	}
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

}
