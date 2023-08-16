package com.vikramsaha7.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos=new ArrayList<Todo>();
	private static int todosCount=0;
	static {
		todos.add(new Todo(++todosCount,"vikram","Node Js course",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todosCount,"vikram","Express Js course",LocalDate.now().plusYears(2),false));
	}
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	public void addTodo(String username, String description, LocalDate targetDate, boolean b) {
		// TODO Auto-generated method stub
		todos.add(new Todo(++todosCount,username,description,targetDate,b));
	}
	public void removeTodo(int id) {
		Predicate<? super Todo>predicate = todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo>predicate = todo -> todo.getId()==id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	
	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		removeTodo(todo.getId());
		todos.add(todo);
		
	}
}
