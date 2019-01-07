package seller.domain;

import java.math.BigDecimal;
import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Persistable;

public class Product  implements Persistable<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4886592322593759450L;


	private transient boolean persisted;

	@NotEmpty
	@EAN
	private String id;//EAN13
	
	private String FeatureImage;
	
	@NotEmpty
	private String ProductName;
	
	private String ProductDes;
	
	@Range(min=0,max=999999)
	private BigDecimal  SellPrice;
	
	
	private String Seller;
	
	private int enabled;
	
	public String getSeller() {
		return Seller;
	}
	public void setSeller(String seller) {
		Seller = seller;
	}
	
	
	
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
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductDes() {
		return ProductDes;
	}
	public void setProductDes(String productDes) {
		ProductDes = productDes;
	}
	

	
	public BigDecimal getSellPrice() {
		return SellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		SellPrice = sellPrice;
	}
	
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFeatureImage() {
		return FeatureImage;
	}
	public void setFeatureImage(String featureImage) {
		FeatureImage = featureImage;
	}
	
	
	
	public Product(String id,String featureImage, String productName, String productDes,
			BigDecimal sellPrice, String Seller, int enabled) {
		this.id = id;
		this.FeatureImage=featureImage;
		ProductName = productName;
		ProductDes = productDes;
		SellPrice = sellPrice;
		this.Seller=Seller;
		this.enabled = enabled;
		this.persisted = false;
		
	}
	public Product() {
		// TODO Auto-generated constructor stub
		this.SellPrice=new BigDecimal("0.00");
		
	}
}
