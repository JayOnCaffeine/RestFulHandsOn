package com.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ToDoDao {
	private final String proj_home = System.getenv("PROJ_HOME");
	
	public List<ToDo> getAllToDo() {
		final List<ToDo> toDoList = new ArrayList<ToDo>();
		try {
			final File toDoFile = new File(proj_home + "/RestFulHandsOn/ToDo.dat");
			if(!toDoFile.exists()) {
				final ToDo toDo = new ToDo(1, "Complete REST", false);
				toDoList.add(toDo);
				saveToDoList(toDoList);
			} else {
				final FileInputStream fis = new FileInputStream(toDoFile);
				final ObjectInputStream ois = new ObjectInputStream(fis);
				toDoList.addAll((List<ToDo>) ois.readObject());
				ois.close();
			}
		} catch(final IOException io) {
			io.printStackTrace();
		} catch(final ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return toDoList;
	}
	
	public ToDo getToDo(final int id) {
		final List<ToDo> todoList = getAllToDo();
		
		for(ToDo todo : todoList) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}
	
	public int addToDo(final ToDo newToDo) {
		final List<ToDo> todoList = getAllToDo();
		
		newToDo.setId(todoList.size()+1);
		todoList.add(newToDo);
		saveToDoList(todoList);
		
		return newToDo.getId();
	}
	
	public int updateToDo(final ToDo updatedToDo) {
		final List<ToDo> todoList = getAllToDo();
		
		for(int i = 0; i < todoList.size(); i++) {
			if(todoList.get(i).getId() == updatedToDo.getId()) {
				todoList.set(i, updatedToDo);
				saveToDoList(todoList);
				return updatedToDo.getId();
			}
		}
		return 0;
	}
	
	public int deleteToDo(final int id) {
		final List<ToDo> todoList = getAllToDo();
		
		for(int i = 0; i < todoList.size(); i++) {
			if(todoList.get(i).getId() == id) {
				todoList.remove(i);
				saveToDoList(todoList);
				return id;
			}
		}
		return 0;
	}
	
	private void saveToDoList(final List<ToDo> toDoList) {
		try {
			final File file = new File(proj_home + "/RestFulHandsOn/ToDo.dat");
			final FileOutputStream fos = new FileOutputStream(file);
			final ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(toDoList);
			oos.close();
		} catch(final FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(final IOException io) {
			io.printStackTrace();
		}
	}

}
