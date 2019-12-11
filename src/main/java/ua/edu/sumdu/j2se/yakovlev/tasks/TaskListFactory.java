package ua.edu.sumdu.j2se.yakovlev.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
        if (type == ListTypes.types.ARRAY){
            return new ArrayTaskList();
        }
        else {
            return new LinkedTaskList();
        }
    }

    public static AbstractTaskList createSameTaskList(Iterable<Task> tasks){
        Class cls = tasks.getClass();
        if(cls.getSimpleName().equals("LinkedTaskList")) return new LinkedTaskList();
        else return new ArrayTaskList();
    }
}
