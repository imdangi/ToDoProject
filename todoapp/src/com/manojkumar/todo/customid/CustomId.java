package com.manojkumar.todo.customid;

import java.io.Serializable;

public class CustomId implements Serializable {

	private static final long serialVersionUID = 1L;
	int id = 0;

	public CustomId(int id) {
		this.id = this.id + id;
	}

	@Override
	public String toString() {
		return "CustomId [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

}
