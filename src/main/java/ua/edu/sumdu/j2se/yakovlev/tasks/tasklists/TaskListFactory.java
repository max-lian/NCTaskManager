package ua.edu.sumdu.j2se.yakovlev.tasks.tasklists;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        if (type == ListTypes.types.ARRAY){
            return new ArrayTaskList();
        }
        else {
            return new LinkedTaskList();
        }
    }
}
