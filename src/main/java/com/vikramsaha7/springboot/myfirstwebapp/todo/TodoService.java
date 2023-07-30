package com.vikramsaha7.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos=new ArrayList<Todo>();
	private static int todosCount=0;
	static {
		todos.add(new Todo(++todosCount,"vikram","Node Js course",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todosCount,"vikram","Express Js course",LocalDate.now().plusYears(2),false));
	}
	public List<Todo> findByUsername(String username){
		return todos;
	}
	public void addTodo(String username, String description, LocalDate targetDate, boolean b) {
		// TODO Auto-generated method stub
		todos.add(new Todo(++todosCount,username,description,targetDate,b));
	}
}
