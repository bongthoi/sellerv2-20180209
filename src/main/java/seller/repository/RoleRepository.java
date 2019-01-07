package seller.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import seller.domain.Role;

import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class RoleRepository extends CustomizeRepository<Role, String> {
	
	public RoleRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, "tb_roles", "id");
		// TODO Auto-generated constructor stub
	}
	public static final RowMapper<Role> ROW_MAPPER = new RowMapper<Role>(){

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {	//mapper when select
			
			Role t= new Role(
					rs.getString("id"),
					rs.getString("name"));
			t.setPersisted(true);
			return t;
		}
		
	};

	public static final RowUnmapper<Role> ROW_UNMAPPER = new RowUnmapper<Role>(){

		@Override
		public Map<String, Object> mapColumns(Role t) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", t.getId());
			mapping.put("name", t.getName());
			return mapping;
		}
		
	};
	@Override
	protected <S extends Role> S postUpdate(S entity) {
		entity.setPersisted(true);
		return entity;
	}

	@Override
	protected <S extends Role> S postCreate(S entity, Number generatedId) {			
		entity.setPersisted(true);
		return entity;
	}

}