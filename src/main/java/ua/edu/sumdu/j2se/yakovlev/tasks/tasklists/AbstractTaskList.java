package ua.edu.sumdu.j2se.yakovlev.tasks.tasklists;

import ua.edu.sumdu.j2se.yakovlev.tasks.Task;

public abstract class AbstractTaskList {
    private int lenght;

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public int size(){
        return  lenght;
    }

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to){
        ArrayTaskList fromTo = new ArrayTaskList();
        for(int i = 0; i < lenght; i++){
            if(getTask(i).isRepeated()){
                if(getTask(i).nextTimeAfter(from) <= to && getTask(i).nextTimeAfter(from) != -1){
                    fromTo.add(getTask(i));
                }
            }
            else{
                if(getTask(i).getTime() > from && getTask(i).getTime() <= to && getTask(i).isActive()){
                    fromTo.add(getTask(i));
                }
            }
        }
        return fromTo;
    }

    }
