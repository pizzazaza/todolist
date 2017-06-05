(function (window) {
	'use strict';

	// Your starting point. Enjoy the ride!

	//////////////////////////////////////////////////////////////////////////////
	//init handler
	$(function() { 
		initList();
	});

	//////////////////////////////////////////////////////////////////////////////
	//event handler
	//엔터입력으로 추가
	$('.new-todo').on('keydown',function(key){
		var inputVal;
		
		if(key.keyCode == 13){
			
			inputVal = $('.new-todo').val(); //input value 
			$('.new-todo').val('');
			var blankRemove = inputVal.trim();
			
			if(blankRemove == "") //blank check
				return;
			
			var addli = li_add_form; //new todo list format 
			if(listState != 2)
				liTagAddOne(inputVal, addli);
			
			insertTodo(inputVal);
			
		}
	});

	//checkbox 선택
	$(document).on('click', 'ul li input',function(){
		var ischecked = $(this).attr('checked');

		completedTodo($('li').index($(this).parent().parent()));
		if(typeof(ischecked) == 'undefined'){
			var checked = $(this).attr('checked', 'checked');
			var checked = $(this).parent().parent().attr('class', 'completed');
		}
	});

	//delete todo
	$(document).on('click', 'ul li div button.destroy',function(){

		var index = $('li').index($(this).parent().parent());
		
		deleteTodo(index);
		
		$(this).parent().parent().remove();
		itemCount();
	});

	//filters click
	$(document).on('click', 'li a',function(){
		var filter = $('li a').index($(this)); //0 all 1 active 2 completed
		$.each($('ul.todo-list li'), function(index, obj){
			obj.remove();
		});
		$($('a')[listState]).css('border-color', 'rgba(255,255,255,255)');
		listState = filter;
		//색칠 새로하기
		$($('a')[filter]).css('border-color', ' rgba(175, 47, 47, 0.2)');
		if(filter == 0){
			todoList.todos = [];
			initList();
		}
		else if(filter == 1){
			todoList.todos = [];
			activeTodoList();
		}
		else if(filter == 2){
			todoList.todos = [];
			completedTodoList();
		}
		return false;
	});

	$(document).on('click', '.clear-completed',function(){
		clearCompleted();
	});


})(window);

////////////////////////////////////////
//function 
function clearCompleted(){
	var deleteUrl = "/api/todo/clearCompleted";
	$.ajax({
		type : "DELETE",
		url : deleteUrl,
		error : function(err){
			console.log(err);
		},
		success : function(data){
			
			var list = $('.completed');
			$.each(list, function(index, val){
				var rmArr = $('li').index(val);
				todoList.todos.splice(rmArr, 1);
				val.remove();

			});
			itemCount();
		}
	});
	
}

function activeTodoList(){
	
	$.ajax({
		type : "POST",
		url : "/api/todo/active",
		dataType : "json",
		data: {completed: "0"},
		error : function(err){
			console.log(err);
		},
		success : function(data){
			todoList.todos = data;
			allList();
		}
	});
}

function completedTodoList(){
	
	$.ajax({
		type : "POST",
		url : "/api/todo/completed",
		dataType : "json",
		data: {completed: "1"},
		error : function(err){
			console.log(err);
		},
		success : function(data){
			todoList.todos = data;
			allList();
		}
	});
}

function completedTodo(index){
	
	var iden = todoList.todos[index].id;

	var completedUrl = '/api/todo/completed/'+iden;

	$.ajax({
		type : "PUT",
		url : completedUrl,
		dataType : "text",
		data : iden,
		error : function(err){
			console.log(err);
		},
		success : function(data){

		}
	});
	todoList.todos[index].completed = 1;
}


function deleteTodo(index){
	var iden = todoList.todos[index].id;
	
	var deleteUrl = "/api/todo/delete/"+iden;
	
	$.ajax({
		type : "DELETE",
		url : deleteUrl,
		error : function(err){
			console.log(err);
		},
		success : function(data){

		}
	})
	todoList.todos.splice(index, 1);
}

//itemCount 함수
function itemCount()
{
	$('.todo-count strong').text(todoList.todos.length);

}

function liTagAddOne(todo, addli){
	addli = addli.replace('<label></label>', '<label>' + todo + '</label>'); //inputVal add 
	var target = $('ul.todo-list li:first');
	addli = $(addli);
	
	$('ul.todo-list').prepend(addli);	
}

function liTagAdd(todo, addli){
	addli = addli.replace('<label></label>', '<label>' + todo + '</label>'); //inputVal add 
	var target = $('ul.todo-list li:first');
	addli = $(addli);
	
	$('ul.todo-list').append(addli);	
		
	
}

function initList(){

	$.ajax({
		type : "GET",
		url : "/api/todo/init",
		dataType : "JSON",
		contentType: "application/json",
		error : function(err){
			console.log(err);
		},
		success : function(data){
			todoList.todos = data;
			allList();
		}		
	});

}
function allList(){
	$.each(todoList.todos, function(index, val){
		var addli = '';
		if(val.completed == 0){ //not complete
			var addli = li_add_form;
		}
		else if(val.completed == 1){//completed
			var addli = li_compelet_form;
		}
		
		liTagAdd(val.todo, addli);
		itemCount();
		//count++
	});
}

function insertTodo(val){
	addUrl = "/api/todo/add/"+val;
	$.ajax({
		type : "put",
		url : addUrl,
		contentType: "application/json",
		error : function(err){
			console.log(err);
		},
		success : function(data){
			
			todoList.todos = [];
			
			for(var ind = 0; ind < data.length; ind++ ){
				todoList.todos.push(data[ind]);
			}
			itemCount();
		}
	});
}

/*

test code 작성 
*/