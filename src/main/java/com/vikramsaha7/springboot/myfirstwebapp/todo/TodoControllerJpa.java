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

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	private TodoRepository todoRepository;
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository=todoRepository;
	}

	@RequestMapping("list-todos")
	public String getTodoList(ModelMap models) {
		String username=getLoggedInUsername();
		models.put("todos", todoRepository.findByUsername(username));
		return "todo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String addTodoForm(ModelMap model) {
		String username=(String) model.get("name");
		Todo todo=new Todo(0,username,"Default_description",LocalDate.now() ,false);
		model.put("todo", todo);
		return "addTodo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addTodoList(ModelMap models,@Valid Todo todo,BindingResult result){
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username = getLoggedInUsername();
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	@RequestMapping(value="delete-todo",method=RequestMethod.GET)
	public String deleteTodoList(@RequestParam int id){
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model){
		Todo todo=todoRepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "addTodo";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(@Valid Todo todo,BindingResult result){
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username=getLoggedInUsername();
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	private String getLoggedInUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}
}
