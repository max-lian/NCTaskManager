package ua.edu.sumdu.j2se.yakovlev.tasks;

public class TaskNode {
    private Task task;
    private TaskNode left;
    private TaskNode right;

    public TaskNode(Task task, TaskNode left, TaskNode right) {
        this.task = task;
        this.left = left;
        this.right = right;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskNode getLeft() {
        return left;
    }

    public void setLeft(TaskNode left) {
        this.left = left;
    }

    public TaskNode getRight() {
        return right;
    }

    public void setRight(TaskNode right) {
        this.right = right;
    }
}
