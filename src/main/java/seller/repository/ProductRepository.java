package seller.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import seller.domain.Product;

import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class ProductRepository extends CustomizeRepository<Product, String> {
	
	public ProductRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, "tb_product", "id");
		// TODO Auto-generated constructor stub
	}
	public static final RowMapper<Product> ROW_MAPPER = new RowMapper<Product>(){

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {	//mapper when select
			
			Product t= new Product(
					rs.getString("id"),
					rs.getString("FeatureImage"),
					rs.getString("ProductName"),
					rs.getString("ProductDes"),
					rs.getBigDecimal("SellPrice"),
					rs.getString("Seller"),
					rs.getInt("enabled"));
			t.setPersisted(true);
			return t;
		}
		
	};

	public static final RowUnmapper<Product> ROW_UNMAPPER = new RowUnmapper<Product>(){

		@Override
		public Map<String, Object> mapColumns(Product t) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", t.getId());
			mapping.put("FeatureImage", t.getFeatureImage());
			mapping.put("ProductName", t.getProductName());
			mapping.put("ProductDes", t.getProductDes());
			mapping.put("SellPrice", t.getSellPrice());
			mapping.put("Seller", t.getSeller());
			mapping.put("enabled", t.getEnabled());
			return mapping;
		}
		
	};
	@Override
	protected <S extends Product> S postUpdate(S entity) {
		entity.setPersisted(true);
		return entity;
	}

	@Override
	protected <S extends Product> S postCreate(S entity, Number generatedId) {			
		entity.setPersisted(true);
		return entity;
	}

	public String GenarateBarcodeWithoutCheckSum(){
		String sql="select getNextProductBarcodeWithoutChecksumDigit() as barcode ";
		return this.getJdbcTemplate().queryForObject(sql, String.class);
	}
}
