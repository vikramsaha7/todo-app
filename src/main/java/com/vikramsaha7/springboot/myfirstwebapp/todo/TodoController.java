package com.vikramsaha7.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
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
		models.put("todos", todoService.findByUsername("vikram"));
		return "todo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String addTodoForm() {
		//models.put("todos", todoService.findByUsername("vikram"));
		return "addTodo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addTodoList(@RequestParam String description,ModelMap models) {
		//models.put("todos", todoService.findByUsername("vikram"));
		String username=(String) models.get("name");
		todoService.addTodo(username,description,LocalDate.now().plusYears(1),false);
		return "redirect:list-todos";
	}
}
