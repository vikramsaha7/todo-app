package com.vikramsaha7.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	//list-todos
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String getTodoList(ModelMap models) {
		String username=getLoggedInUsername();
		models.put("todos", todoService.findByUsername(username));
		return "todo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String addTodoForm(ModelMap model) {
		//models.put("todos", todoService.findByUsername("vikram"));
		String username=(String) model.get("name");
		Todo todo=new Todo(0,username,"Default_description",LocalDate.now() ,false);
		model.put("todo", todo);
		return "addTodo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addTodoList(ModelMap models,@Valid Todo todo,BindingResult result){
		//models.put("todos", todoService.findByUsername("vikram"));
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username=(String) models.get("name");
		todoService.addTodo(username,todo.getDescription(),LocalDate.now().plusYears(1),false);
		return "redirect:list-todos";
	}
	@RequestMapping(value="delete-todo",method=RequestMethod.GET)
	public String deleteTodoList(@RequestParam int id){
		//delete todo logic
		todoService.removeTodo(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
		//delete todo logic
		Todo todo=todoService.findById(id);
		model.addAttribute("todo",todo);
		return "addTodo";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(@Valid Todo todo,BindingResult result){
		//delete todo logic
		if(result.hasErrors()) {
			return "addTodo";
		}
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	private String getLoggedInUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}
}
