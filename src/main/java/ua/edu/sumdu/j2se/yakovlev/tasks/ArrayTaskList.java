package ua.edu.sumdu.j2se.yakovlev.tasks;

public class ArrayTaskList {
    private Task[] taskArray;
    private int lenght;

    public void ArrayTaskList() {
        taskArray = new Task[0];
    }

    private void makeArrayBigger(){
        Task[] newTaskArray = new Task[lenght + 1];
        for (int i = 0; i < lenght; i++){
            newTaskArray[i] = taskArray[i];
        }
        taskArray = newTaskArray;
    }

    private boolean makeArraySmoller(int i){
        Task[] newTaskArray = new Task[lenght - 1];
        for ( int j = 0; j < newTaskArray.length; j++){
            if( j < i){
                newTaskArray[j] = taskArray[j];
            }
            else{
                newTaskArray[j] = taskArray[j + 1];
            }
        }
        taskArray = newTaskArray;
        return true;
    }

    public void add(Task task){
        makeArrayBigger();
        lenght++;
        taskArray[lenght - 1] = task;
    }

    public boolean remove(Task task){
        boolean flag = false;
        for (int i = 0; i < taskArray.length; i++){
            if (taskArray[i].equals(task)){
                flag = makeArraySmoller( i );
                lenght--;
                break;
            }
        }
        return flag;
    }

    public int size(){
        return lenght;
    }

    public Task getTask(int index){
        return taskArray[index];
    }

    public ArrayTaskList incoming(int from, int to){
        ArrayTaskList fromTo = new ArrayTaskList();
        for(int i = 0; i < lenght; i++){
            if(taskArray[i].isRepeated()){
                if(taskArray[i].nextTimeAfter(from) <= to && taskArray[i].nextTimeAfter(from) != -1){
                    fromTo.add(taskArray[i]);
                }
            }
            else{
                if(taskArray[i].getTime() > from && taskArray[i].getTime() <= to && taskArray[i].isActive()){
                    fromTo.add(taskArray[i]);
                }
            }
        }
        return fromTo;
    }
}
