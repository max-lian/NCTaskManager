package ua.edu.sumdu.j2se.yakovlev.tasks;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		LinkedTaskList a = createA();
		LinkedTaskList b = a.clone();
		System.out.println(a.toString());
		System.out.println(b.toString());
	}

	private static LinkedTaskList createA() {
		LinkedTaskList list = new LinkedTaskList();
		Task A = new Task("A", 10);
		IntStream.range(0, 10).forEach(i -> list.add(A));
		return list;
	}

	private static LinkedTaskList createB() {
		LinkedTaskList list = new LinkedTaskList();
		Task B = new Task("B", 15);
		IntStream.range(0, 10).forEach(i -> list.add(B));
		return list;
	}

	private static void modify(LinkedTaskList list) {
		IntStream.range(0, list.size()).mapToObj(i -> list.getTask(0)).forEach(list::remove);
		Task A = new Task("A", 10);
		IntStream.range(0, 10).forEach(i -> list.add(A));
	}
}
