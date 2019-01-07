package seller.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;




import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import seller.domain.User;

import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class UserRepository extends CustomizeRepository<User, String> {
	
	public UserRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, "tb_user", "username");
		// TODO Auto-generated constructor stub
	}
	public static final RowMapper<User> ROW_MAPPER = new RowMapper<User>(){

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {	//mapper when select
			
			User t= new User(
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("FirstName"),
					rs.getString("LastName"),
					rs.getTimestamp("birthday"),
					rs.getTimestamp("RegisterDate"),
					rs.getString("address"),
					rs.getString("phone"),
					rs.getInt("enabled"));
			t.setPersisted(true);
			return t;
		}
		
	};

	public static final RowUnmapper<User> ROW_UNMAPPER = new RowUnmapper<User>(){

		@Override
		public Map<String, Object> mapColumns(User t) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("username", t.getUsername());
			mapping.put("password", t.getPassword());
			mapping.put("FirstName", t.getFirstName());
			mapping.put("LastName", t.getLastName());
			mapping.put("birthday", t.getBirthday());
			mapping.put("RegisterDate", t.getRegisterDate());
			mapping.put("address", t.getAddress());
			mapping.put("phone", t.getPhone());
			mapping.put("enabled", t.getEnabled());
			return mapping;
		}
		
	};
	@Override
	protected <S extends User> S postUpdate(S entity) {
		entity.setPersisted(true);
		return entity;
	}

	@Override
	protected <S extends User> S postCreate(S entity, Number generatedId) {			
		entity.setPersisted(true);
		return entity;
	}
	public User findByEmail(String email){
		if(this.exists(email)){
			User user=this.findOne(email);
			/*List<Role> roles=(this.getJdbcTemplate().query("select t1.* from  tb_roles t1, "
					+ " (select * from tb_user_roles where username='admin@gmail.com')"
					+ "  t2 where t1.id=t2.role", RoleRepository.ROW_MAPPER));*/
			List<String> roles=this.getJdbcTemplate().queryForList("select t1.id from  tb_roles t1, "
					+ " (select * from tb_user_roles where username='"+email+"')"
					+ "  t2 where t1.id=t2.role",String.class);
			user.setRoles(new HashSet<>(roles));
			return user;
		}
		return null;
		
	}
	@Override
	public void disabled(String username) {
		String query="UPDATE "+this.getTable().getName() +""
				+ "   set enabled=0 where username=?";
		this.getJdbcTemplate().update(query, new Object[]{username});
	}
	@Override
	public void active(String username) {
		String query="UPDATE "+this.getTable().getName() +""
				+ "   set enabled=1 where username=?";
		this.getJdbcTemplate().update(query, new Object[]{username});
		
	}	
}