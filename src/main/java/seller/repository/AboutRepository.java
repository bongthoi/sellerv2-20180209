package seller.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;





import seller.domain.About;

import com.nurkiewicz.jdbcrepository.RowUnmapper;

@Repository
public class AboutRepository extends CustomizeRepository<About, String> {

	public AboutRepository() {
		super(ROW_MAPPER, ROW_UNMAPPER, "tb_about", "id");
	}

	public static final RowMapper<About> ROW_MAPPER = new RowMapper<About>() {
		@Override
		public About mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new About(rs.getString("id"),rs.getString("title"), rs.getString("description"),
					rs.getString("logo"), rs.getString("creator"),
					rs.getTimestamp("create_date"), rs.getString("content")

			);
		}
	};

	public static final RowUnmapper<About> ROW_UNMAPPER = new RowUnmapper<About>() {
		@Override
		public Map<String, Object> mapColumns(About a) {
			Map<String, Object> mapping = new LinkedHashMap<String, Object>();
			mapping.put("id", a.getId());
			mapping.put("title", a.getTitle());
			mapping.put("description", a.getDescription());
			mapping.put("logo", a.getLogo());
			mapping.put("creator", a.getCreator());
			mapping.put("create_date", a.getCreateDate());
			mapping.put("content", a.getContent());
			return mapping;
		}
	};
	@Override
	protected <S extends About> S postUpdate(S entity) {
		entity.setPersisted(true);
		return entity;
	}

	@Override
	protected <S extends About> S postCreate(S entity, Number generatedId) {			
		entity.setPersisted(true);
		return entity;
	}
}
