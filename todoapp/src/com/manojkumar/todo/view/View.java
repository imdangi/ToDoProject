package com.manojkumar.todo.view;

import static com.manojkumar.todo.utils.Constants.ADD_TASK;
import static com.manojkumar.todo.utils.Constants.DELETE_TASK;
import static com.manojkumar.todo.utils.Constants.EXIT;
import static com.manojkumar.todo.utils.Constants.PRINT_TASK;
import static com.manojkumar.todo.utils.Constants.SEARCH_TASK;
import static com.manojkumar.todo.utils.Constants.TASK_STATUS;
import static com.manojkumar.todo.utils.Constants.UPDATE_TASK;
import static com.manojkumar.todo.utils.Constants.PENDING;
import static com.manojkumar.todo.utils.Constants.COMPLETED;
import static com.manojkumar.todo.utils.MessageReader.getValue;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.manojkumar.todo.todoDto.ToDoDTO;
import com.manojkumar.todo.todorepo.ITodoRepo;
import com.manojkumar.todo.todorepo.ToDoRepo;

public class View {

	private static Scanner scanner = new Scanner(System.in);

	private static void updateTask() {
		ArrayList<ToDoDTO> tasks = new ArrayList<>();
		ArrayList<ToDoDTO> updatedTasks = new ArrayList<>();
		ToDoDTO taskObj = null;
		try {
			ITodoRepo repo = ToDoRepo.getInstance();
			tasks = repo.printAllTask();
			int check = 0;
			if (tasks.size() > 0) {
				System.out.println(getValue("update.input"));
				int checkId = scanner.nextInt();
				scanner.nextLine();
				for (ToDoDTO task : tasks) {
					if (task.getTaskId() == checkId) {
						check = 1;

						System.out.println(getValue("task.name"));
						String name = scanner.nextLine();
						System.out.println(getValue("task.desc"));
						String desc = scanner.nextLine();
						task.setTaskDesc(desc);
						task.setTaskName(name);
						taskObj = task;
						updatedTasks.add(taskObj);
					} else {
						updatedTasks.add(task);
					}
				}
				repo.addTask(updatedTasks);
				String result = (check == 1) ? getValue("update.done") : getValue("update.notdone");
				System.out.println(result);
				if (check == 1) {
					System.out.println(getValue("update.result"));
					customPrint(taskObj);
				}
			} else {
				System.out.println(getValue("task.empty"));
			}

		} catch (EOFException e) {
			System.out.println("No task available...");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void searchTask() {

		ArrayList<ToDoDTO> tasks = new ArrayList<>();
		try {
			ITodoRepo repo = ToDoRepo.getInstance();
			ToDoDTO taskObj = null;
			tasks = repo.printAllTask();
			int check = 0;
			if (tasks.size() > 0) {
				System.out.println(getValue("search.input"));
				int checkId = scanner.nextInt();
				scanner.nextLine();
				for (ToDoDTO task : tasks) {
					if (task.getTaskId() == checkId) {
						taskObj = task;
						check = 1;
					}
				}
				String result = (check == 1) ? getValue("search.found") : getValue("search.notfound");
				System.out.println(result);
				if (check == 1) {
					customPrint(taskObj);
				}
			} else {
				System.out.println(getValue("task.empty"));
			}

		} catch (EOFException e) {
			System.out.println("No task available...");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void updateTaskStatus() {
		ArrayList<ToDoDTO> tasks = new ArrayList<>();
		ArrayList<ToDoDTO> updatedTasks = new ArrayList<>();
		ToDoDTO taskObj = null;
		try {
			ITodoRepo repo = ToDoRepo.getInstance();
			tasks = repo.printAllTask();
			int check = 0;
			if (tasks.size() > 0) {
				System.out.println(getValue("update.input"));
				int checkId = scanner.nextInt();
				scanner.nextLine();
				for (ToDoDTO task : tasks) {
					if (task.getTaskId() == checkId) {
						check = 1;
						if (task.getTaskStatus().equals(PENDING)) {
							task.setTaskStatus(COMPLETED);
						} else {
							task.setTaskStatus(PENDING);
						}
						taskObj = task;
						updatedTasks.add(taskObj);
					} else {
						updatedTasks.add(task);
					}
				}
				repo.addTask(updatedTasks);
				String result = (check == 1) ? getValue("status.done") : getValue("status.notdone");
				System.out.println(result);
				if (check == 1) {
					System.out.println(getValue("status.result"));
					customPrint(taskObj);
				}
			} else {
				System.out.println(getValue("task.empty"));
			}

		} catch (EOFException e) {
			System.out.println("No task available...");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method will store the object date into file and also there is no way to
	 * append in file using object so first we access the data already present in
	 * file and store that data in file. Then we will append the new object in the
	 * array of data and at last we will write the complete array in the file.
	 */
	private static void addTask() {

		System.out.println(getValue("task.name"));
		String name = scanner.nextLine();
		System.out.println(getValue("task.desc"));
		String desc = scanner.nextLine();
		int systemGenerateId = 1;
		ToDoDTO todo = null;
		ArrayList<ToDoDTO> oldTask = new ArrayList<>();
		/*
		 * Id work
		 */

		try {

			ITodoRepo repo = ToDoRepo.getInstance();
			oldTask = repo.printAllTask();
			if (oldTask.isEmpty()) {

			} else {
				ToDoDTO t = oldTask.get(oldTask.size() - 1);
				systemGenerateId += t.getTaskId();
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
		 * Task adding
		 * 
		 */
		todo = new ToDoDTO(systemGenerateId, name, desc);
//		oldTask = new ArrayList<>();
		String result = getValue("record.notadded");
		try {
			/*
			 * Reading the task first, then appending If file is empty that means no tasks
			 * are there.
			 */
			ITodoRepo repo = ToDoRepo.getInstance();
			try {
				oldTask = repo.printAllTask();
				oldTask.add(todo);
			} catch (EOFException e) {
				oldTask.add(todo);
				System.out.println("No task present, task to be created");
			}
			result = repo.addTask(oldTask) ? getValue("record.added") : getValue("record.notadded");

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(result);

	}

	/*
	 * Printing all task in a friendly looking way.
	 */
	private static void customPrint(ToDoDTO tasks) {

		if (tasks != null) {
			System.out.println(getValue("print.taskid") + tasks.getTaskId());
			System.out.println(getValue("print.taskname") + tasks.getTaskName());
			System.out.println(getValue("print.taskdesc") + tasks.getTaskDesc());
			System.out.println(getValue("print.taskdate") + tasks.getDate());
			System.out.println(getValue("print.taskstatus") + tasks.getTaskStatus());
		} else {
			System.out.println("Empty Task");
		}
	}

	/*
	 * This method is used to print all the tasks available Use the ITodoRepo method
	 * of printAllmethods. It will return an <array list> of ToDoDTO objects with
	 * data . Loop through all objects one by one and print all the data.
	 */
	private static void printTask() {
		ArrayList<ToDoDTO> tasks;
		ITodoRepo repo;
		try {
			repo = ToDoRepo.getInstance();
			tasks = repo.printAllTask();

			for (ToDoDTO task : tasks) {
				System.out.println("=======================================================");
				customPrint(task);
			}
			if (tasks.isEmpty()) {
				System.out.println(getValue("task.empty"));
				System.out.println();
			} else {
				System.out.println("=======================================================");
				System.out.println();
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Empty task list");
		}

	}

	/*
	 * This method will delete task Step 1: Get data from the file Step 2: Check the
	 * task if present, remove from the array of objects. Step 3. Add this new array
	 * of objects to the file.
	 * 
	 */
	private static void deleteTask() {
		ArrayList<ToDoDTO> tasks = new ArrayList<>();
		ArrayList<ToDoDTO> newTasks = new ArrayList<>();

		try {
			ITodoRepo repo = ToDoRepo.getInstance();
			tasks = repo.printAllTask();
			int check = -1;
			if (tasks.size() > 0) {
				System.out.println(getValue("delete.input"));
				int checkId = scanner.nextInt();
				scanner.nextLine();
				for (ToDoDTO task : tasks) {
					if (task.getTaskId() != checkId) {
						newTasks.add(task);
					} else {
						check = 1;
					}
				}
				repo.addTask(newTasks);
				String result = (check == 1) ? getValue("task.deleted") : getValue("task.notdeleted");
				System.out.println(result);
			} else {
				System.out.println(getValue("task.empty"));
			}

		} catch (EOFException e) {
			System.out.println("No task available...");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {

			System.out.println(getValue("welcomemessage"));

			System.out.println(getValue("addtask"));
			System.out.println(getValue("deletetask"));
			System.out.println(getValue("searchtask"));
			System.out.println(getValue("updatetask"));
			System.out.println(getValue("printtask"));
			System.out.println(getValue("taskstatus"));
			System.out.println(getValue("exit"));

			System.out.print(getValue("choice") + " ");
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
				choice = -1;
			}
			switch (choice) {
			case ADD_TASK:
				addTask();
				break;

			case DELETE_TASK:
				deleteTask();
				break;

			case SEARCH_TASK:
				searchTask();
				break;

			case UPDATE_TASK:
				updateTask();
				break;

			case PRINT_TASK:
				printTask();
				break;

			case TASK_STATUS:
				updateTaskStatus();
				break;

			case EXIT:
				System.out.println(getValue("goingmessage"));
				break;

			default:
				System.out.println(getValue("invalidinput"));
				break;
			}
		} while (choice != EXIT);
		scanner.close();

	}

}
