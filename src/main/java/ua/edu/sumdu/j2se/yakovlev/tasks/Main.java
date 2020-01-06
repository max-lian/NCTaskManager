package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException {
		LinkedTaskList sad = new LinkedTaskList();
		sad.add(new Task("Jepa", LocalDateTime.now()));
		sad.add(new Task("Jepa", LocalDateTime.now()));
	}
}