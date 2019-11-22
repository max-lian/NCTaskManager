package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskNode taskNode = (TaskNode) o;
        return Objects.equals(task, taskNode.task) &&
                Objects.equals(left, taskNode.left) &&
                Objects.equals(right, taskNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, left, right);
    }
}
