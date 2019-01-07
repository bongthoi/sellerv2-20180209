package seller.repository;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;

import com.nurkiewicz.jdbcrepository.MissingRowUnmapper;
import com.nurkiewicz.jdbcrepository.RowUnmapper;
import com.nurkiewicz.jdbcrepository.TableDescription;
import com.nurkiewicz.jdbcrepository.sql.SqlGenerator;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CustomizeRepository<T extends Persistable<ID>, ID extends Serializable> implements PagingAndSortingRepository<T, ID>, InitializingBean, BeanFactoryAware{

	public static Object[] pk(Object... idValues) {
		return idValues;
	}

	private final TableDescription table;

	private final RowMapper<T> rowMapper;
	private final RowUnmapper<T> rowUnmapper;
	
	private SqlGenerator sqlGenerator = new SqlGenerator();
	private BeanFactory beanFactory;
	private JdbcTemplate jdbcTemplateObject;
//	private  JdbcTemplate jdbcTemplateObject; 

	public CustomizeRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, SqlGenerator sqlGenerator, TableDescription table) {
		Assert.notNull(rowMapper);
		Assert.notNull(rowUnmapper);
		Assert.notNull(table);

		this.rowUnmapper = rowUnmapper;
		this.rowMapper = rowMapper;
		this.sqlGenerator = sqlGenerator;
		this.table = table;
	}

	public CustomizeRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, TableDescription table) {
		this(rowMapper, rowUnmapper, null, table);
	}

	public CustomizeRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName, String idColumn) {
		this(rowMapper, rowUnmapper, null, new TableDescription(tableName, idColumn));
	}

	public CustomizeRepository(RowMapper<T> rowMapper, RowUnmapper<T> rowUnmapper, String tableName) {
		this(rowMapper, rowUnmapper, new TableDescription(tableName, "id"));
	}

	public CustomizeRepository(RowMapper<T> rowMapper, TableDescription table) {
		this(rowMapper, new MissingRowUnmapper<T>(), null, table);
	}

	public CustomizeRepository(RowMapper<T> rowMapper, String tableName, String idColumn) {
		this(rowMapper, new MissingRowUnmapper<T>(), null, new TableDescription(tableName, idColumn));
	}

	public CustomizeRepository(RowMapper<T> rowMapper, String tableName) {
		this(rowMapper, new MissingRowUnmapper<T>(), new TableDescription(tableName, "id"));
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		obtainJdbcTemplate();
		if (sqlGenerator == null) {
			obtainSqlGenerator();
		}
	}

	public void setSqlGenerator(SqlGenerator sqlGenerator) {
		this.sqlGenerator = sqlGenerator;
	}

	public void setjdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	protected TableDescription getTable() {
		return table;
	}

	private void obtainSqlGenerator() {
		try {
			sqlGenerator = beanFactory.getBean(SqlGenerator.class);
		} catch (NoSuchBeanDefinitionException e) {
			sqlGenerator = new SqlGenerator();
		}
	}

	private void obtainJdbcTemplate() {
		try {
			jdbcTemplateObject = beanFactory.getBean(JdbcTemplate.class);
		} catch (NoSuchBeanDefinitionException e) {
			final DataSource dataSource = beanFactory.getBean(DataSource.class);
			jdbcTemplateObject = new JdbcTemplate(dataSource);
		}
	}
	public JdbcTemplate getJdbcTemplate(){
		if(null==jdbcTemplateObject){
			obtainJdbcTemplate();
		}
		return jdbcTemplateObject;
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public long count() {
		return jdbcTemplateObject.queryForObject(sqlGenerator.count(table), Long.class);
	}

	@Override
	public void delete(ID id) {
		jdbcTemplateObject.update(sqlGenerator.deleteById(table), idToObjectArray(id));
	}

	@Override
	public void delete(T entity) {
		jdbcTemplateObject.update(sqlGenerator.deleteById(table), idToObjectArray(entity.getId()));
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		for (T t : entities) {
			delete(t);
		}
	}

	@Override
	public void deleteAll() {
		jdbcTemplateObject.update(sqlGenerator.deleteAll(table));
	}

	@Override
	public boolean exists(ID id) {
		return jdbcTemplateObject.queryForObject(sqlGenerator.countById(table), Integer.class, idToObjectArray(id)) > 0;
	}

	@Override
	public List<T> findAll() {
		return jdbcTemplateObject.query(sqlGenerator.selectAll(table), rowMapper);
	}

	@Override
	public T findOne(ID id) {
		final Object[] idColumns = idToObjectArray(id);
		final List<T> entityOrEmpty = jdbcTemplateObject.query(sqlGenerator.selectById(table), idColumns, rowMapper);
		return entityOrEmpty.isEmpty() ? null : entityOrEmpty.get(0);
	}

	private static <ID> Object[] idToObjectArray(ID id) {
		if (id instanceof Object[])
			return (Object[]) id;
		else
			return new Object[]{id};
	}

	private static <ID> List<Object> idToObjectList(ID id) {
		if (id instanceof Object[])
			return Arrays.asList((Object[]) id);
		else
			return Collections.<Object>singletonList(id);
	}

	@Override
	public <S extends T> S save(S entity) {
		if (entity.isNew()) {
			return create(entity);
		} else {
			return update(entity);
		}
	}

	protected <S extends T> S update(S entity) {
		final Map<String, Object> columns = preUpdate(entity, columns(entity));
		final List<Object> idValues = removeIdColumns(columns);
		final String updateQuery = sqlGenerator.update(table, columns);
		for (int i = 0; i < table.getIdColumns().size(); ++i) {
			columns.put(table.getIdColumns().get(i), idValues.get(i));
		}
		final Object[] queryParams = columns.values().toArray();
		jdbcTemplateObject.update(updateQuery, queryParams);
		return postUpdate(entity);
	}

	protected Map<String,Object> preUpdate(T entity, Map<String, Object> columns) {
		return columns;
	}

	protected <S extends T> S create(S entity) {
		final Map<String, Object> columns = preCreate(columns(entity), entity);
		if (entity.getId() == null) {
			return createWithAutoGeneratedKey(entity, columns);
		} else {
			return createWithManuallyAssignedKey(entity, columns);
		}
	}

	private <S extends T> S createWithManuallyAssignedKey(S entity, Map<String, Object> columns) {
		final String createQuery = sqlGenerator.create(table, columns);
		final Object[] queryParams = columns.values().toArray();
		jdbcTemplateObject.update(createQuery, queryParams);
		return postCreate(entity, null);
	}

	private <S extends T> S createWithAutoGeneratedKey(S entity, Map<String, Object> columns) {
		removeIdColumns(columns);
		final String createQuery = sqlGenerator.create(table, columns);
		final Object[] queryParams = columns.values().toArray();
		final GeneratedKeyHolder key = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				final String idColumnName = table.getIdColumns().get(0);
				final PreparedStatement ps = con.prepareStatement(createQuery, new String[]{idColumnName});
				for (int i = 0; i < queryParams.length; ++i) {
					ps.setObject(i + 1, queryParams[i]);
				}
				return ps;
			}
		}, key);
		return postCreate(entity, key.getKey());
	}

	private List<Object> removeIdColumns(Map<String, Object> columns) {
		List<Object> idColumnsValues = new ArrayList<Object>(columns.size());
		for (String idColumn : table.getIdColumns()) {
			idColumnsValues.add(columns.remove(idColumn));
		}
		return idColumnsValues;
	}

	protected Map<String, Object> preCreate(Map<String, Object> columns, T entity) {
		return columns;
	}

	private LinkedHashMap<String, Object> columns(T entity) {
		return new LinkedHashMap<String, Object>(rowUnmapper.mapColumns(entity));
	}

	protected <S extends T> S postUpdate(S entity) {
		return entity;
	}

	/**
	 * General purpose hook method that is called every time {@link #create} is called with a new entity.
	 * <p/>
	 * OVerride this method e.g. if you want to fetch auto-generated key from database
	 *
	 *
	 * @param entity Entity that was passed to {@link #create}
	 * @param generatedId ID generated during INSERT or NULL if not available/not generated.
	 * todo: Type should be ID, not Number
	 * @return Either the same object as an argument or completely different one
	 */
	protected <S extends T> S postCreate(S entity, Number generatedId) {
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		List<S> ret = new ArrayList<S>();
		for (S s : entities) {
			ret.add(save(s));
		}
		return ret;
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		final List<ID> idsList = toList(ids);
		if (idsList.isEmpty()) {
			return Collections.emptyList();
		}
		final Object[] idColumnValues = flatten(idsList);
		return jdbcTemplateObject.query(sqlGenerator.selectByIds(table, idsList.size()), rowMapper, idColumnValues);
	}

	private static <T> List<T> toList(Iterable<T> iterable) {
		final List<T> result = new ArrayList<T>();
		for (T item : iterable) {
			result.add(item);
		}
		return result;
	}

	private static <ID> Object[] flatten(List<ID> ids) {
		final List<Object> result = new ArrayList<Object>();
		for (ID id : ids) {
			result.addAll(idToObjectList(id));
		}
		return result.toArray();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return jdbcTemplateObject.query(sqlGenerator.selectAll(table, sort), rowMapper);
	}

	@Override
	public Page<T> findAll(Pageable page) {
		String query = sqlGenerator.selectAll(table, page);
		return new PageImpl<T>(jdbcTemplateObject.query(query, rowMapper), page, count());
	}
	
	/*custome more function
	 * 
	 * 
	 * */
	protected String limitClause(Pageable page) {
		final int offset = page.getPageNumber() * page.getPageSize();
		return " LIMIT " + offset + SqlGenerator.COMMA + page.getPageSize();
	}
	protected String sortingClauseIfRequired(Sort sort) {
		if (sort == null) {
			return "";
		}
		StringBuilder orderByClause = new StringBuilder();
		orderByClause.append(" ORDER BY ");
		for(Iterator<Sort.Order> iterator = sort.iterator(); iterator.hasNext();) {
			final Sort.Order order = iterator.next();
			orderByClause.
					append(order.getProperty()).
					append(" ").
					append(order.getDirection().toString());
			if (iterator.hasNext()) {
				orderByClause.append(SqlGenerator.COMMA);
			}
		}
		return orderByClause.toString();
	}
	protected String GenarateQuery(String whereQuery,Pageable page){
		return sqlGenerator.selectAll(table) + " "+ whereQuery + " " +  sortingClauseIfRequired(page.getSort()) + " " +  limitClause(page);
	}
	public T findOneQuery(String query){
		final List<T> entityOrEmpty = jdbcTemplateObject.query(query, rowMapper);
		return entityOrEmpty.isEmpty() ? null : entityOrEmpty.get(0);
	}
	public Page<T> findQuery(String whereQuery,Pageable page) {
		//String query = sqlGenerator.selectAll(table, page);
		long count=0;
		count = jdbcTemplateObject.queryForObject("select count(*) from " +this.getTable().getName() + whereQuery, Long.class);
		String queryForsortAndLimit= GenarateQuery(whereQuery,page);
		return new PageImpl<T>(jdbcTemplateObject.query(queryForsortAndLimit, rowMapper), page, count);
	}
	public List<T> findWhereQuery(String whereQuery) {
		String query=sqlGenerator.selectAll(table) + " "+ whereQuery;
		return jdbcTemplateObject.query(query,rowMapper);
	}
	public void active(String id) {
		String query="UPDATE "+this.getTable().getName() +""
				+ "   set enabled=1 where id=?";
		jdbcTemplateObject.update(query, new Object[]{id});
		
	}

	public void disabled(String id) {
		String query="UPDATE "+this.getTable().getName() +""
				+ "   set enabled=0 where id=?";
		jdbcTemplateObject.update(query, new Object[]{id});
		
	}
}