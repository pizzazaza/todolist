package kr.or.connect.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import kr.or.connect.todo.service.TodoService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@Transactional
public class TodoTest {

	@Autowired
	TodoDao dao;
	@Autowired
	TodoService service;
	
	@Test
	public void addTodoTest() throws Exception {
		service.addTodo("test");
	}
	@Test
	public void addTest() throws Exception {
		Todo todo = new Todo(0, "test");
		dao.insert(todo);
	}

}