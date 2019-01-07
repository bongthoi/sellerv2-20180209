package seller.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Persistable;

public class About implements Persistable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4137423360456870999L;
	private transient boolean persisted;
	/**
	 * 
	 */
	private String id;

	@NotEmpty
	private String title;

	private String description;
	private String logo;

	private String creator;
	private Date createDate;
	private String content;
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
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
	
	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public About() {
		super();
		// TODO Auto-generated constructor stub
	}
	public About(String id, String title, String description, String logo,
			String creator, Date createDate, String content)

	{
		this.id = id;
		this.title = title;
		this.description = description;
		this.logo = logo;
		this.creator = creator;
		this.createDate = createDate;
		this.content = content;
		this.persisted=false;

	}
	@Override
	public String toString() {
		return "About [id=" + id + ", title=" + title + ", description="
				+ description + ", logo=" + logo + ", creator=" + creator
				+ ", createDate=" + createDate + ", content=" + content + "]";
	}

	
}
