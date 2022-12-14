package com.manojkumar.todo.todorepo;

import java.io.IOException;
import java.util.ArrayList;

import com.manojkumar.todo.todoDto.ToDoDTO;

public interface ITodoRepo {

	public boolean addTask(ArrayList<ToDoDTO> todo) throws IOException;

	public ArrayList<ToDoDTO> printAllTask() throws IOException, ClassNotFoundException;

}
