package com.todo;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class WebServiceTest {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/RestFulHandsOn/rest/ToDoService/todos";
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	private void init() {
		this.client = ClientBuilder.newClient();
	}
	
	public static void main(String[] args) {
		final WebServiceTest tester = new WebServiceTest();
		// Initialize the tester
		tester.init();
		//test get all todos
		tester.testGetAllToDos();
		tester.testGetToDo();
		tester.testUpdateToDo();
		tester.testAddToDo();
		tester.testDeleteToDo();
	}
	
	//Test: Get list of all todos
	//Test: Check if list is not empty
	private void testGetAllToDos() {
		final GenericType<List<ToDo>> list = new GenericType<List<ToDo>>() {};
		final List<ToDo> todos = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML).get(list);
		String result = PASS;
		if(todos.isEmpty()) {
			result = FAIL;
		}
		System.out.println("Test case name: testGelAllToDos, Result:" + result);
	}
	
	//Test: Get ToDo of id 1
	//Test: Check if ToDo is same as sample
	private void testGetToDo() {
		final ToDo sampleToDo = new ToDo();
		sampleToDo.setId(1);
		
		final ToDo todo = client.target(REST_SERVICE_URL).path("/{userid}").resolveTemplate("userid",  1)
				.request(MediaType.APPLICATION_XML).get(ToDo.class);
		String result = FAIL;
		if(sampleToDo != null && sampleToDo.getId() == todo.getId()) {
			result = PASS;
		}
		System.out.println("Test case name: testGetToDo, Result: " + result);
	}
	
	//Test: Update todo of id 1
	//Test: Check if result is success XML
	private void testUpdateToDo() {
		final Form form = new Form();
		form.param("id", "1");
		form.param("tododtls", "updated todo");
		form.param("statusdone", "false");
		
		final String callResult = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}
		System.out.println("Test case name: testUpdateToDo, Result: " + result);
	}
	
	//Test: Add ToDo of id 2
	//Test: Check if result is success XML.
	private void testAddToDo() {
		final Form form = new Form();
		form.param("id", "2");
		form.param("tododtls", "new todo");
		form.param("statusdone", "false");
		
		final String callResult = client.target(REST_SERVICE_URL).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}
		System.out.println("Test case name: testAddToDo, Result: " + result);
	}
	
	//Test: Delete ToDo of id 2
	//Test: Check if result is success XML
	private void testDeleteToDo() {
		final String callResult = client.target(REST_SERVICE_URL).path("/{userid}")
				.resolveTemplate("userid", 2).request(MediaType.APPLICATION_XML)
				.delete(String.class);
		
		String result = PASS;
		if(!SUCCESS_RESULT.equals(callResult)) {
			result = FAIL;
		}
		System.out.println("Test case name: testDeleteToDo, Result: " + result);
	}
}
