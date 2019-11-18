package ua.edu.sumdu.j2se.yakovlev.tasks.tasklists;

import ua.edu.sumdu.j2se.yakovlev.tasks.Task;

public class LinkedTaskList extends AbstractTaskList {
    private TaskNode firstNode;
    private TaskNode lastNode;

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

    public void add(Task task){
        if (getLenght() == 0){
            lastNode = firstNode = new TaskNode(task, null, null);
        }
        else{
            TaskNode newTaskNode = new TaskNode(task, lastNode, null);
            lastNode.setRight(newTaskNode);
            lastNode = newTaskNode;
        }
        setLenght(getLenght()+1);
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
                setLenght(getLenght() - 1);;
                return true;
            }
            listTask = listTask.getRight();
        }
        return false;
    }

    public Task getTask(int index){
        TaskNode listTask = firstNode;
        for( int i = 0; i < index; i++){
            listTask = listTask.getRight();
        }
        return listTask.getTask();
    }

    public void printList(){
        TaskNode listTask = firstNode;
        for(int i = 0; i < getLenght(); i++){
            System.out.println(listTask.getTask().toString());
            listTask = listTask.getRight();
        }
    }
}
