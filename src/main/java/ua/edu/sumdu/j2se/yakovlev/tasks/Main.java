package ua.edu.sumdu.j2se.yakovlev.tasks;

import ua.edu.sumdu.j2se.yakovlev.tasks.tasklists.LinkedTaskList;

public class Main {

	public static void main(String[] args) {
		Task task1 = new Task("task1", 10);
		Task task2 = new Task("task2", 12);
		Task task3 = new Task("task3", 13);
		LinkedTaskList linkedList = new LinkedTaskList();
		linkedList.add(task1);
        linkedList.add(task2);
        linkedList.add(task3);
        linkedList.printList();
        linkedList.remove(task2);
        System.out.println(linkedList.getLenght());
        linkedList.printList();
        linkedList.remove(task1);
        System.out.println(linkedList.getLenght());
        linkedList.printList();
        System.out.println(linkedList.getLenght());
	}
}
