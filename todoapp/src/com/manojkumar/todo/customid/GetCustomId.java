package com.manojkumar.todo.customid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.manojkumar.todo.utils.Constants;

public class GetCustomId implements Serializable {

	private static final long serialVersionUID = 1L;
	static GetCustomId obj = null;
	File file;

	private GetCustomId() throws IOException {
		file = new File(Constants.PATH_ID);
		file.createNewFile();
	}

	public static GetCustomId getInstance() throws IOException {
		if (obj == null) {
			obj = new GetCustomId();
		}
		return obj;
	}

	public boolean addId(CustomId idObj) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
				objectOutputStream.writeObject(idObj);
				return true;
			}
		}

	}

	public CustomId returnIdObj() throws IOException, ClassNotFoundException {
		CustomId idObj;
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
				idObj = (CustomId) objectInputStream.readObject();
				return idObj;
			}
		}
	}

}
