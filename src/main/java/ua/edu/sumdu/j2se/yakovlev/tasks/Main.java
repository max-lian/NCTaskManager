package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException {
		LinkedTaskList sad = new LinkedTaskList();
		Class cls = sad.getClass();
		System.out.println(cls.getSimpleName());
	}
}