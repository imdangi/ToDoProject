package com.manojkumar.todo.todorepo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.manojkumar.todo.todoDto.ToDoDTO;
import com.manojkumar.todo.utils.Constants;

public class ToDoRepo implements ITodoRepo, Serializable {

	private static final long serialVersionUID = 1L;

	public static ToDoRepo repo = null;
	File file;

	private ToDoRepo() throws IOException {
		file = new File(Constants.PATH);
		file.createNewFile();
	}

	public static ToDoRepo getInstance() throws IOException {
		if (repo == null) {
			repo = new ToDoRepo();
		}
		return repo;
	}

	@Override
	public boolean addTask(ArrayList<ToDoDTO> todo) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
				objectOutputStream.writeObject(todo);
				return true;
			}
		}

	}

	@Override
	public ArrayList<ToDoDTO> printAllTask() throws IOException, ClassNotFoundException {

		ArrayList<ToDoDTO> tasks = new ArrayList<>();
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
				tasks = (ArrayList<ToDoDTO>) objectInputStream.readObject();
				return tasks;
			}
		}

	}

}
