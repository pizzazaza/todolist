package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.domain.Todo;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Todo> rowMapper;
	

	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingGeneratedKeyColumns("id");
		rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
	}

	public List<Todo> selectById(Integer id){
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.query(TodoSqls.SELECT_BYID, params, rowMapper);	
	}//사용안
	
	public List<Todo> selectAll() {
		Map<String, Object> params = new HashMap<>();
		return jdbc.query(TodoSqls.SELECT_ALL, params, rowMapper);
	}
	
	public List<Todo> insert(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		Integer insertID = insertAction.executeAndReturnKey(params).intValue(); 
		return selectAll();
	}
	
	public Integer deleteById(Integer id){
		
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(TodoSqls.DELETE_TODO, params);
	}
	
	public Integer completedById(Integer id){
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(TodoSqls.COMPLETED_TODO, params);
	}
	
	public List<Todo> activeAll(Integer completed){
		Map<String, ?> params = Collections.singletonMap("completed", completed);
		return jdbc.query(TodoSqls.ACTIVE_ALL, params, rowMapper);
	}
	
	public List<Todo> completedAll(Integer completed){
		Map<String, ?> params = Collections.singletonMap("completed", completed);
		return jdbc.query(TodoSqls.COMPLETED_ALL, params, rowMapper);
	}

	public Integer clearCompletedTodo(){
		Map<String, ?> params = Collections.singletonMap("completed", 1);
		return jdbc.update(TodoSqls.CLEARCOMPLETED_ALL, params);
	}
}
