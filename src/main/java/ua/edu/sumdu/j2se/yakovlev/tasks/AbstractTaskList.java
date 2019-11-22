package ua.edu.sumdu.j2se.yakovlev.tasks;

public abstract class AbstractTaskList<Object> {
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
