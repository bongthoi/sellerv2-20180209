package seller.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import seller.domain.Contact;



import com.nurkiewicz.jdbcrepository.JdbcRepository;
import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class ContactRepository extends JdbcRepository<Contact, Integer>{
	
	public ContactRepository(){
		super(ROW_MAPPER,ROW_UNMAPPER,"tb_contacts");
	}
	
	public static final RowMapper<Contact> ROW_MAPPER = new RowMapper<Contact>(){

		@Override
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {	//mapper when select
			
			return new Contact(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("subject"),
					rs.getString("content"),
					rs.getString("phone"),
					rs.getString("address"),
					rs.getTimestamp("create_date")
					);
	
		}
	};	
	
	private static final RowUnmapper<Contact> ROW_UNMAPPER = new RowUnmapper<Contact>() {
		@Override
		public Map<String, Object> mapColumns(Contact c) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", c.getId());
			mapping.put("name", c.getName());
			mapping.put("email", c.getEmail());
			mapping.put("subject", c.getSubject());
			mapping.put("content", c.getContent());
			mapping.put("phone", c.getPhone());
			mapping.put("address", c.getAddress());
			mapping.put("create_date",c.getCreateDate());
			return mapping;
		}
	};	
	
	@Override
	protected <S extends Contact> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
}
