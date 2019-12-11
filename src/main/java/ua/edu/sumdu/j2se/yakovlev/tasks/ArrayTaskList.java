package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Класс продукции со свойствами
 * <b>maker</b> и <b>price</b>.
 * @autor Яковлев М.М.
 * @version 2.1
 */

public class ArrayTaskList extends AbstractTaskList<Task> implements Iterable<Task>, Cloneable {
    /** Поле массив задач */
    private Task[] taskArray;

    /**
     * Конструктор - создание нового объекта
     */
    public ArrayTaskList() {
        taskArray = new Task[0];
    }

    public ArrayTaskList(Task[] taskArray){
        this.taskArray = taskArray;
        setLenght(taskArray.length);
    }

    /**
     * Метод для увеличения массива
     */
    private void makeArrayBigger(){
        Task[] newTaskArray = new Task[getLenght() + 1];
        for (int i = 0; i < getLenght(); i++){
            newTaskArray[i] = taskArray[i];
        }
        taskArray = newTaskArray;
        setLenght(getLenght() + 1);
    }

    /**
     * Метод для уменьшения массива
     * @param i - удаляемый элемент из массива
     * @return при успешном выполнении возвращает true
     */
    private boolean makeArraySmoller(int i){
        Task[] newTaskArray = new Task[getLenght() - 1];
        for (int j = 0; j < getLenght() - 1; j++){
            if( j < i){
                newTaskArray[j] = taskArray[j];
            }
            else{
                newTaskArray[j] = taskArray[j + 1];
            }
        }
        taskArray = newTaskArray;
        setLenght(getLenght() - 1);
        return true;
    }


    /**
     * Метод для добавления элементов в массив
     * Вызывает метод для увеличения массива, а затем добавляет в конец новый элемент
     * @see ArrayTaskList#makeArrayBigger()
     * @param task - добавляемая задача
     */
    public void add(Task task){
        makeArrayBigger();
        taskArray[getLenght() - 1] = task;
    }

    /**
     * Метод для удаления задачи из массива
     * при нахождении данной задачи в массиве вызывает метод для удаления данной задачи
     * @see ArrayTaskList#makeArraySmoller(int i)
     * @param task - удаляемая задача
     * @return возвращает true если задача была удалена
     */
    public boolean remove(Task task){
        boolean flag = false;
        for (int i = 0; i < getLenght(); i++){
            if (taskArray[i].equals(task)){
                flag = makeArraySmoller( i );
                break;
            }
        }
        return flag;
    }

    public boolean remove(int i) {
        return makeArraySmoller(i);
    }
    /**
     * Метод для получения задачи по индексу
     * @param index - индекс задачи
     * @return возвращает задачу по индексу
     * @throws IndexOutOfBoundsException метод бросает ошибку при выходе индекса за рамки
     */
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if( index < 0 || index >= getLenght()){
            throw new IndexOutOfBoundsException();
        }
        return taskArray[index];
    }

    @Override
    public Stream<Task> getStream() {
       ArrayList<Task> stream = new ArrayList<Task>();
        for (Task a: this) {
            stream.add(a);
        }
        return stream.stream();
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList tasks = (ArrayTaskList) o;
        return Arrays.equals(taskArray, tasks.taskArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taskArray);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "taskArray=" + Arrays.toString(taskArray) +
                '}';
    }

    public ArrayTaskList clone() throws CloneNotSupportedException {
        return (ArrayTaskList) super.clone();
    }

    public class ArrayTaskListIterator implements Iterator<Task> {
        private int count = -1;

        @Override
        public boolean hasNext() {
            return count < getLenght() - 1 ;
        }

        @Override
        public Task next() {
            count++;
            return getTask(count);
        }

        @Override
        public void remove() throws IllegalStateException {
            if(count == -1) throw new IllegalStateException("Can't remove until next");
            makeArraySmoller(count);
            count--;
        }
    }
}
