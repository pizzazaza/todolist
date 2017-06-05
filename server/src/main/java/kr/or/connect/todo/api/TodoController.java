package kr.or.connect.todo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;



@RestController
@RequestMapping("/api/todo")
public class TodoController {

	private final TodoService service;

	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping("/init") //all
	List<Todo> initList() {
		List<Todo> tmp;
		try{
			tmp = service.initList();
		}catch(EmptyResultDataAccessException e){
			tmp = null;
		}
		/*
		for(int i = 0; i < tmp.size(); i++){
			tmp.get(i).printFunc();
		}*/
		return tmp;
		//org.springframework.dao.EmptyResultDataAccessException
	}
	
	@RequestMapping("/add/{addTodo}")
	List<Todo> addTodo(@PathVariable String addTodo){
		return service.addTodo(addTodo);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteTodo(@PathVariable Integer id){
		service.deleteTodo(id);
	}
	
	@DeleteMapping("/clearCompleted")
	void deleteTodo(){
		service.clearCompletedTodo();
	}
	
	@PutMapping("/completed/{id}")
	void completedTodo(@PathVariable Integer id){
		service.completedTodo(id);
	}
	
	@PostMapping("/active")
	List<Todo> active(Integer completed){
		List<Todo> tmp;
		try{
			tmp = service.activeList(completed);
		}catch(EmptyResultDataAccessException e){
			tmp = null;
		}
		/*
		for(int i = 0; i < tmp.size(); i++){
			tmp.get(i).printFunc();
		}*/
		return tmp;
	}
	
	@PostMapping("/completed")
	List<Todo> completed(Integer completed){
		List<Todo> tmp;
		try{
			tmp = service.activeList(completed);
		}catch(EmptyResultDataAccessException e){
			tmp = null;
		}
		/*
		for(int i = 0; i < tmp.size(); i++){
			tmp.get(i).printFunc();
		}
		*/
		return tmp;
	}
	
}
