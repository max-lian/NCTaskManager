package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Task task1 = new Task("task1", 10);
		Task task2 = new Task("task2", 12);
		Task task3 = new Task("task3", 13);
		ArrayTaskList taskList = new ArrayTaskList();
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);
		System.out.println(taskList.getTask(0).getTime());
		System.out.println(taskList.getTask(1).getTime());
		System.out.println(taskList.getTask(2).getTime());
	}
}
