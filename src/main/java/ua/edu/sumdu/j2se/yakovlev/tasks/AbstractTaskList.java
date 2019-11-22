package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.Iterator;

public abstract class AbstractTaskList<Object> implements Iterable<Task>{
    private int lenght;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    public abstract AbstractTaskList createList();

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int size(){
        return  lenght;
    }

    public AbstractTaskList incoming(int from, int to){
        AbstractTaskList fromTo = createList();
        Iterator<Task> iterator = (Iterator<Task>) iterator();
        while(iterator.hasNext()){
            Task task = iterator.next();
            if(task.isActive() && task.nextTimeAfter(to) <= from && task.nextTimeAfter(to) > to){
                fromTo.add(task);
            }
        }
        return fromTo;
    }
}
