package seller.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Persistable;

public class Contact implements Persistable<Integer> {

	/**
	 * 99999
	 */
	private static final long serialVersionUID = 7944702452622141286L;
	private transient boolean persisted;

	
	private Integer id;
	@NotEmpty
	private String name;
	@Email
	private String email;
	@NotEmpty
	private String subject;
	@NotEmpty
	private String content;
	private String phone;
	private String address;
	private Date createDate;


	
	
	
	
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
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Contact(Integer id, String name, String email, String subject,
			String content, String phone, String address, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.phone = phone;
		this.address = address;
		this.createDate = createDate;
		this.persisted=false;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
