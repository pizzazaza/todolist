package kr.or.connect.todo.domain;

import java.util.Date;

public class Todo {
	private Integer id;
	private String todo;
	private Integer completed;
	private Date date;
	
	
	public Todo(){
		
	}
	
	public Todo(Integer com, String todoS){
		this.completed = com;
		this.todo = todoS;
		this.id = null;
		this.date = new Date();
		
	}

	public void printFunc(){
		System.out.println("id: "+id);
		System.out.println("todo: "+todo);
		System.out.println("completed: "+ completed);
		System.out.println("data: "+date.toString());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
