package ua.edu.sumdu.j2se.yakovlev.tasks;

import org.w3c.dom.Node;

public class LinkedTaskList extends AbstractTaskList {
    private TaskNode firstNode;
    private TaskNode lastNode;
    private int lenght = 0;

    public LinkedTaskList() {
    }

    public TaskNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(TaskNode firstNode) {
        this.firstNode = firstNode;
    }

    public TaskNode getLastNode() {
        return lastNode;
    }

    public void setLastNode(TaskNode lastNode) {
        this.lastNode = lastNode;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public void add(Task task){
        if (lenght == 0){
            lastNode = firstNode = new TaskNode(task, null, null);
        }
        else{
            TaskNode newTaskNode = new TaskNode(task, lastNode, null);
            lastNode.setRight(newTaskNode);
            lastNode = newTaskNode;
        }
        lenght++;
    }

    public boolean remove(Task task){
        TaskNode listTask = firstNode;
        while(listTask.getRight() != null || listTask == lastNode ){
            if(listTask.getTask().equals(task)){
                if(listTask.getLeft() != null) {
                    listTask.getLeft().setRight(listTask.getRight());
                }
                else{
                    firstNode = firstNode.getRight();
                    firstNode.setLeft(null);
                }
                if(listTask.getRight() != null) {
                    listTask.getRight().setLeft(listTask.getLeft());
                }
                else{
                    lastNode = lastNode.getLeft();
                    lastNode.setRight(null);
                }
                lenght--;
                return true;
            }
            listTask = listTask.getRight();
        }
        return false;
    }

    public int size(){
        return lenght;
    }

    public Task getTask(int index){
        TaskNode listTask = firstNode;
        for( int i = 0; i < index; i++){
            listTask = listTask.getRight();
        }
        return listTask.getTask();
    }

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList fromTo = new LinkedTaskList();
        TaskNode listTask = firstNode;
        for(int i = 0; i < lenght; i++){
            if(listTask.getTask().isRepeated()){
                if(listTask.getTask().nextTimeAfter(from) <= to && listTask.getTask().nextTimeAfter(from) != -1){
                    fromTo.add(listTask.getTask());
                }
            }
            else{
                if(listTask.getTask().getTime() > from && listTask.getTask().getTime() <= to && listTask.getTask().isActive()){
                    fromTo.add(listTask.getTask());
                }
            }
            listTask = listTask.getRight();
        }
        return fromTo;
    }

    public void printList(){
        TaskNode listTask = firstNode;
        for(int i = 0; i < lenght; i++){
            System.out.println(listTask.getTask().toString());
            listTask = listTask.getRight();
        }
    }
}
