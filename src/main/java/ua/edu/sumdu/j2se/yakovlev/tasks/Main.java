package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Task[] tasks = new Task[3];
		tasks[0] = new Task("A", 5);
		tasks[1] = new Task("B", 10);
		tasks[2] = new Task("C", 15);
	    LinkedTaskList linkedTaskList = new LinkedTaskList();
		linkedTaskList.add(tasks[0]);
		linkedTaskList.add(tasks[1]);
		linkedTaskList.add(tasks[2]);
		try {
			LinkedTaskList copyLinkedList = linkedTaskList.clone();
			System.out.println(copyLinkedList.toString());
		}
		catch (CloneNotSupportedException ex){
			System.out.println("Не могу клонировать");
		}
	}
}
