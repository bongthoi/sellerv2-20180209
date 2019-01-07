package seller.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;

import seller.domain.UserRole;

@Repository
public class UserRoleRepository extends CustomizeRepository<UserRole,Integer> {
	public UserRoleRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, "tb_user_roles");
	}

	public static final RowMapper<UserRole> ROW_MAPPER = new RowMapper<UserRole>(){

		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {	//mapper when select
			
			UserRole a= new UserRole(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("role")
					);
			return a;
		}
	};	

	private static final RowUnmapper<UserRole> ROW_UNMAPPER = new RowUnmapper<UserRole>() {
		@Override
		public Map<String, Object> mapColumns(UserRole a) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", a.getId());
			mapping.put("username", a.getUsername());
			mapping.put("role", a.getRole());
			return mapping;
		}
	};	

	@Override
	protected <S extends UserRole> S postCreate(S entity, Number generatedId) {
		entity.setId(generatedId.intValue());
		return entity;
	}
	public boolean exists(String username,String role){
		int c=this.getJdbcTemplate().queryForObject("select count(*) from  tb_user_roles where username='"+username+"' and role='"+role+"'", int.class);
		return c>0;
		
	}
	public void delete(String username, String arr_role) {
		// TODO Auto-generated method stub
		String sql="delete from tb_user_roles where username='"+username+"' and role not in("+arr_role+")";
		this.getJdbcTemplate().update(sql);
	}
}
