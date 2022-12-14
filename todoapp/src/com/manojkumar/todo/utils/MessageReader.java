package com.manojkumar.todo.utils;

import java.util.ResourceBundle;

public interface MessageReader {

	public static final String MESSAGE_BUNDLE_FILE_NAME = "messages";

	public static String getValue(String key) {
		ResourceBundle rb = ResourceBundle.getBundle(MESSAGE_BUNDLE_FILE_NAME);
		return rb.getString(key);
	}

}
