package kr.or.connect.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	
	TodoDao dao;
	
	public TodoService(TodoDao dao){
		this.dao = dao;
	}

	public List<Todo> initList(){
		
		return dao.selectAll();
	}
	
	public List<Todo> addTodo(String addTodo){
		Todo todo = new Todo(0, addTodo);
		return dao.insert(todo);
		
		//fail때는 ???  
	}
	
	public Integer deleteTodo(Integer id){
		
		return dao.deleteById(id);
	}
	
	public Integer completedTodo(Integer id){
	
		return dao.completedById(id);
	}
	
	//filtering
	public List<Todo> activeList(Integer completed){
		
		return dao.activeAll(completed);
	}
	
	public List<Todo> completedList(Integer completed){
		
		return dao.completedAll(completed);
	}
	
	public Integer clearCompletedTodo(){
		return dao.clearCompletedTodo();
	}
}
