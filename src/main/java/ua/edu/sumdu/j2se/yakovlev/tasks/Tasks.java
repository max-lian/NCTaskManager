package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

import static ua.edu.sumdu.j2se.yakovlev.tasks.TaskListFactory.createSameTaskList;

public class Tasks {
   public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        AbstractTaskList fromTo =  createSameTaskList(tasks);
        try {
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                LocalDateTime nextTimeAfter = task.nextTimeAfter(start);
                if (task.isActive() && nextTimeAfter != null && (nextTimeAfter.isBefore(end) || nextTimeAfter.isEqual(end) )) {
                    fromTo.add(task);
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage() + '\n' +  ex.getCause());
        }
        return fromTo;
    }
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        TreeMap<LocalDateTime,Set<Task>> treeMap = new TreeMap<>();
        for (Task task: tasks) {
            LocalDateTime nextTimeAfter = task.nextTimeAfter(start);
            if (task.isActive() && nextTimeAfter != null && (nextTimeAfter.isBefore(end) || nextTimeAfter.isEqual(end))){
                if(task.isRepeated()) {
                    LocalDateTime temp = LocalDateTime.from(nextTimeAfter);
                    while (temp.isBefore(end) || temp.isEqual(end)){
                        if(treeMap.containsKey(temp)){
                            treeMap.get(temp).add(task);
                        }
                        else{
                            Set taskSet = new HashSet<Task>();
                            taskSet.add(task);
                            treeMap.put(temp, taskSet);
                        }
                        temp = temp.plusSeconds(task.getRepeatInterval());
                    }
                }
                else{
                    if(treeMap.containsKey(task.getTime())){
                        treeMap.get(task.getTime()).add(task);
                    }
                    else{
                        Set taskSet = new HashSet<Task>();
                        taskSet.add(task);
                        treeMap.put(task.getTime(), taskSet);
                    }
                }
            }
        }
/*        System.out.println(start.toString() + '\n' + end.toString() + '\n');
        for (Map.Entry<LocalDateTime, Set<Task>> entry : treeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
        }*/
        return treeMap;
    }
}
