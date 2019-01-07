package seller.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class frmChangePassword {
	
	@NotEmpty
	@Email
	private String  username;
	
	@NotEmpty
	@Size(min = 6, max = 20)
	private String newpassword;
	
	@NotEmpty
	@Size(min = 6, max = 20)
	private String renewpassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getRenewpassword() {
		return renewpassword;
	}

	public void setRenewpassword(String renewpassword) {
		this.renewpassword = renewpassword;
	}

	public frmChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isMatchPassword() {
		  if (newpassword == null) {
	            return renewpassword == null;
	        } else {
	            return newpassword.equals(renewpassword);
	        }
	}

	public frmChangePassword(String username) {
		super();
		this.username = username;
	}
	
	
}
