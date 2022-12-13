package com.manojkumar.todo.todoDto;

import java.io.Serializable;
import java.util.Date;

import com.manojkumar.todo.utils.Constants;

public class ToDoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	String taskName;
	String taskDesc;
	String taskStatus;
	Date date;
	int taskId;

	private ToDoDTO() {

		this.date = new Date();
		this.taskStatus = Constants.PENDING;
	}

	public ToDoDTO(int taskId, String name, String desc) {
		this();
		this.taskId = taskId;
		this.taskName = name;
		this.taskDesc = desc;
	}

	@Override
	public String toString() {
		return "ToDoDTO [taskName=" + taskName + ", taskDesc=" + taskDesc + ", taskStatus=" + taskStatus + ", date="
				+ date + ", taskId=" + taskId + "]";
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTaskId() {
		return taskId;
	}

}
