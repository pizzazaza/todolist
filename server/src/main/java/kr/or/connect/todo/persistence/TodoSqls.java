package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL = "SELECT * FROM Todo  ORDER BY date DESC";//
	static final String SELECT_BYID = "SELECT * FROM Todo WHERE id= :id";
	static final String INSERT_TODO = "INSERT INTO Todo VALUES ()";
	static final String DELETE_TODO = "DELETE FROM Todo WHERE id= :id";
	static final String COMPLETED_TODO = "UPDATE Todo SET completed = 1 WHERE id= :id";
	static final String ACTIVE_ALL = "SELECT * FROM Todo WHERE completed= :completed  ORDER BY date DESC";//
	static final String COMPLETED_ALL = "SELECT * FROM Todo WHERE completed= :completed  ORDER BY date DESC"; //
	static final String CLEARCOMPLETED_ALL = "DELETE FROM Todo WHERE completed= :completed";
}
