package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static ua.edu.sumdu.j2se.yakovlev.tasks.TaskListFactory.createSameTaskList;

public class Tasks {
   public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        AbstractTaskList fromTo =  createSameTaskList(tasks);
        Iterator<Task> iterator = tasks.iterator();
        while(iterator.hasNext()) {
            Task task = iterator.next();
            if (task.isActive() && (task.nextTimeAfter(end).isBefore(start) || task.nextTimeAfter(end).equals(start)) && task.nextTimeAfter(end).isAfter(end)) {
                fromTo.add(task);
            }
        }
        return fromTo;
    }
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        TreeMap<Date,Set<Task>> treeMap = new TreeMap<>();
        for (Task task:
             tasks) {
            if (task.isActive() && (task.nextTimeAfter(end).isBefore(start) || task.nextTimeAfter(end).equals(start)) && task.nextTimeAfter(end).isAfter(end)) {

            }
        }
        return treeMap;
    }
}
