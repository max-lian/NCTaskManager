package ua.edu.sumdu.j2se.yakovlev.tasks;
/**
 * Класс продукции со свойствами
 * <b>maker</b> и <b>price</b>.
 * @autor Яковлев М.М.
 * @version 2.1
 */

public class ArrayTaskList extends AbstractTaskList{
    /** Поле массив задач */
    private Task[] taskArray;
    /** Поле длинна */
    private int lenght;

    /**
     * Конструктор - создание нового объекта
     */
    public ArrayTaskList() {
        taskArray = new Task[0];
    }

    /**
     * Метод для увеличения массива
     */
    private void makeArrayBigger(){
        Task[] newTaskArray = new Task[lenght + 1];
        for (int i = 0; i < lenght; i++){
            newTaskArray[i] = taskArray[i];
        }
        taskArray = newTaskArray;
    }

    /**
     * Метод для уменьшения массива
     * @param i - удаляемый элемент из массива
     * @return при успешном выполнении возвращает true
     */
    private boolean makeArraySmoller(int i){
        Task[] newTaskArray = new Task[lenght - 1];
        for (int j = 0; j < newTaskArray.length; j++){
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


    /**
     * Метод для добавления элементов в массив
     * Вызывает метод для увеличения массива, а затем добавляет в конец новый элемент
     * @see ArrayTaskList#makeArrayBigger()
     * @param task - добавляемая задача
     */
    public void add(Task task){
        makeArrayBigger();
        lenght++;
        taskArray[lenght - 1] = task;
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
        for (int i = 0; i < taskArray.length; i++){
            if (taskArray[i].equals(task)){
                flag = makeArraySmoller( i );
                lenght--;
                break;
            }
        }
        return flag;
    }

    /**
     * Метод для получения длинны массива задач
     * @return возвращает количество задач
     */
    public int size(){
        return lenght;
    }

    /**
     * Метод для получения задачи по индексу
     * @param index - индекс задачи
     * @return возвращает задачу по индексу
     * @throws IndexOutOfBoundsException метод бросает ошибку при выходе индекса за рамки
     */
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if( index < 0 || index >= lenght){
            throw new IndexOutOfBoundsException();
        }
        return taskArray[index];
    }


    /**
     * Метод для поиска задач, которые запланированы для выполнения в период времени от from до to
     * @param from - начало временного диапазона
     * @param to - конец временного диапазона
     * @return Возвращает подмножество активных задач, которые запланированы для выполнения в период времени от from до to
     */
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
