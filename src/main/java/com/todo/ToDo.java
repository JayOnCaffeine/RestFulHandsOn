package com.todo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "todo")
public class ToDo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5768826756874737303L;

	private int id;
	private String toDoDtls;
	private boolean statusDone = false;
	
	public ToDo() {}
	
	public ToDo(final int id, final String toDoDtls, final boolean statusDone) {
		this.id = id;
		this.toDoDtls = toDoDtls;
		this.statusDone = statusDone;
	}
	
	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(final int id) {
		this.id = id;
	}
	
	public String getToDoDtls() {
		return toDoDtls;
	}
	
	@XmlElement
	public void setToDoDtls(final String toDoDtls) {
		this.toDoDtls = toDoDtls;
	}
	
	public boolean getStatusDone() {
		return statusDone;
	}
	
	@XmlElement
	public void setStatusDone(final boolean statusDone) {
		this.statusDone = statusDone;
	}
	
	@Override
	public boolean equals(final Object object) {
		if(object == null) {
			return false;
		} else if(!(object instanceof ToDo)) {
			return false;
		} else {
			ToDo todo = (ToDo) object;
			if(id == todo.getId() && toDoDtls.equals(todo.getToDoDtls()) && 
					statusDone == todo.statusDone) {
				return true;
			}
		}
		
		return false;
	}
	
}
