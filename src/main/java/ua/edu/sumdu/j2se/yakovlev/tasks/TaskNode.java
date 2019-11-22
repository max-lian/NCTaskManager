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

    protected Task getTask() {
        return task;
    }

    protected void setTask(Task task) {
        this.task = task;
    }

    protected TaskNode getLeft() {
        return left;
    }

    protected void setLeft(TaskNode left) {
        this.left = left;
    }

    protected TaskNode getRight() {
        return right;
    }

    protected void setRight(TaskNode right) {
        this.right = right;
    }
}
