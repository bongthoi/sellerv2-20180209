package seller.domain;

import java.util.Date;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Persistable;
import org.springframework.format.annotation.DateTimeFormat;

public class User implements Persistable<String> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2458448494543707120L;
	private transient boolean persisted;
	@NotEmpty
	@Email
	private String  username;
	
	@NotEmpty
	@Size(min = 6, max = 20)
	private String password;
	
	@NotEmpty
	@Size(min = 6, max = 20)
	private String repassword;
	
	@NotEmpty
	private String FirstName;
	
	@NotEmpty
	private String LastName;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/YY")
	private Date birthday;
	
	private Date RegisterDate;
	
	@NotEmpty
	private String address;
	@NotEmpty
	private String phone;
	
	private int enabled;
	private Set<String> roles;
	
	public boolean isMatchPassword(){
        if (password == null) {
            return repassword == null;
        } else {
            return password.equals(repassword);
        }
    }
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return !persisted;
	}
	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}
	
	public boolean isPersisted() {
		return persisted;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public Date getRegisterDate() {
		return RegisterDate;
	}
	public void setRegisterDate(Date registerDate) {
		RegisterDate = registerDate;
	}
	
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	public User(String username, String password, String firstName,
			String lastName, Date birthday, Date registerDate, String address,
			String phone, int enabled) {
		super();
		this.username = username;
		this.password = password;
		FirstName = firstName;
		LastName = lastName;
		this.birthday = birthday;
		RegisterDate = registerDate;
		this.address = address;
		this.phone = phone;
		this.enabled = enabled;
		
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
