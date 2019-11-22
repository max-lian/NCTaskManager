package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.Iterator;

public class ArrayTaskListIterator implements Iterator<Task> {
    private int count = 0;
    private int lenght;
    private ArrayTaskList taskArray;

    public ArrayTaskListIterator(Task[] taskArray, int lenght) {
        this.taskArray = new ArrayTaskList(taskArray);
        this.lenght = lenght;
    }

    @Override
    public boolean hasNext() {
        return count < lenght - 1;
    }

    @Override
    public Task next() {
        count++;
        return taskArray.getTask(count-1);
    }

    @Override
    public void remove() {
        taskArray.remove(count);
    }
}
