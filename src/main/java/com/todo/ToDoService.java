package com.todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/ToDoService")
public class ToDoService {

	final ToDoDao toDoDao = new ToDoDao();
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_XML)
	public List<ToDo> getToDos() {
		return toDoDao.getAllToDo();
	}
	
	@GET
	@Path("/todos/{todoid}")
	@Produces(MediaType.APPLICATION_XML)
	public ToDo getToDo(@PathParam("todoid") int id) {
		return toDoDao.getToDo(id);
	}
	
	@PUT
	@Path("/todos")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createToDo(@FormParam("id") int id, @FormParam("tododtls") String toDoDtls, 
			@FormParam("statusdone") boolean statusDone,
			@Context HttpServletResponse response) throws IOException {
		final ToDo todo = new ToDo(id, toDoDtls, statusDone);
		
		if(toDoDao.addToDo(todo) > 0) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
	@POST
	@Path("/todos")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateToDo(@FormParam("id") int id, @FormParam("tododtls") String toDoDtls, 
			@FormParam("statusdone") boolean statusDone, 
			@Context HttpServletResponse response) throws IOException {
		final ToDo todo = new ToDo(id, toDoDtls, statusDone);
		
		if(toDoDao.updateToDo(todo) > 0) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
	@DELETE
	@Path("/todos/{todoid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteToDo(@PathParam("todoid") int id) {
		if(toDoDao.deleteToDo(id) > 0) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
	@OPTIONS
	@Path("/todos")
	@Produces(MediaType.APPLICATION_XML)
	public String getSupportedOperations(){
	      return "<operations>GET, PUT, POST, DELETE</operations>";
	}
	
}
