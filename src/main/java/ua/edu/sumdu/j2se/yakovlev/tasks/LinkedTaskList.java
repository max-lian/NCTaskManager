package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.*;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList<Task> implements Iterable<Task>, Cloneable{
    private TaskNode firstNode;
    private TaskNode lastNode;

    private TaskNode getFirstNode() {
        return firstNode;
    }

    private TaskNode getLastNode() {
        return lastNode;
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
        setLenght(getLenght() + 1);
    }

    public boolean remove(Task task){
        TaskNode listTask = firstNode;
        while(listTask.getRight() != null || listTask == lastNode ){
            if(listTask.getTask().equals(task)){
                return remove(listTask);
            }
            listTask = listTask.getRight();
        }
        return false;
    }

    public boolean remove(TaskNode task){
        if(firstNode == lastNode){
            setLenght(0);
            firstNode = lastNode = null;
            return true;
        }
        if(task.getLeft() != null) {
            task.getLeft().setRight(task.getRight());
        }
        else{
            firstNode = firstNode.getRight();
            firstNode.setLeft(null);
        }
        if(task.getRight() != null) {
            task.getRight().setLeft(task.getLeft());
        }
        else{
            lastNode = lastNode.getLeft();
            lastNode.setRight(null);
        }
        setLenght(getLenght() - 1);;
        return true;

    }

    public Task getTask(int index){
        TaskNode listTask = firstNode;
        for( int i = 0; i < index; i++){
            listTask = listTask.getRight();
        }
        return listTask.getTask();
    }

    @Override
    public Stream<Task> getStream() {
        LinkedList<Task> stream = new LinkedList<Task>();
        for (Task a: this) {
            stream.add(a);
        }
        return stream.stream();
    }

    public void printList(){
        TaskNode listTask = firstNode;
        for(int i = 0; i < getLenght(); i++){
            System.out.println(listTask.getTask().toString());
            listTask = listTask.getRight();
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new LinkedTaskListIterator();
    }

    @Override
    public String toString() {
        String res =  "LinkedTaskList{taskList=";
        LinkedTaskListIterator iterator = (LinkedTaskListIterator) iterator();
        while (iterator.hasNext()){
            res = res + iterator.next().toString();
        }
        res = res + "}";
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        if (that.getLenght() == 0 && getLenght() == 0) return true;
        System.out.println(!that.firstNode.equals(firstNode));
        System.out.println(!that.lastNode.equals(lastNode));
        System.out.println(getLenght() != that.getLenght());
        if (!that.firstNode.equals(firstNode) || !that.lastNode.equals(lastNode)|| getLenght() != that.getLenght()){
            return false;
        }
        LinkedTaskListIterator thisIterator = (LinkedTaskListIterator) iterator();
        LinkedTaskListIterator thatIterator = (LinkedTaskListIterator) that.iterator();
        for(int i = 0; i < getLenght(); i++){
            if(!(thisIterator.next().equals(thatIterator.next()))) return false;
        }
        return true;
    }

    public LinkedTaskList clone() throws CloneNotSupportedException {
        return (LinkedTaskList) super.clone();
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstNode, lastNode);
    }

    public class LinkedTaskListIterator implements Iterator<Task>{
        private TaskNode actualNode = new TaskNode(null, null, null);

        @Override
        public boolean hasNext() {
            if(actualNode == lastNode){
                return false;
            }
            return true;
        }

        @Override
        public Task next() {
            if(actualNode.equals(new TaskNode(null,null,null))){actualNode = firstNode; }
            else{actualNode = actualNode.getRight();}
            return actualNode.getTask();
        }

        @Override
        public void remove() throws IllegalStateException {
            if(actualNode.equals(new TaskNode(null,null,null))) throw new IllegalStateException("Can't remove until next");
            LinkedTaskList.this.remove(actualNode);
        }
    }
}
