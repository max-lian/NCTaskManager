package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
    private String title;
    private LocalDateTime time;
    private int interval;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean repeat;
    private boolean active = false;

    public Task(String title, LocalDateTime time) throws IllegalArgumentException {
        if(time == null) throw new IllegalArgumentException();
        this.title = title;
        this.time = time;
        repeat = false;
    }

    public Task(Task oldTask) {
        this.title = oldTask.getTitle();
        this.time = oldTask.getTime();
        this.interval = oldTask.getRepeatInterval();
        this.start = oldTask.getStartTime();
        this.end = oldTask.getEndTime();
        this.repeat = oldTask.isRepeated();
        this.active = oldTask.isActive();
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if(start == null || end == null || interval <= 0) throw new IllegalArgumentException();
        this.title = title;
        this.interval = interval;
        this.start = start;
        this.end = end;
        repeat = true;
    }

 /*   public Task(String title, LocalDateTime start, LocalDateTime end, int interval, boolean active) throws IllegalArgumentException {
        this.title = title;
        this.interval = interval;
        this.start = start;
        this.end = end;
        repeat = true;
        this.active = active;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (repeat) {
            return start;
        }
        else {
            return time;
        }
    }

    public void setTime(LocalDateTime time) throws IllegalArgumentException{
        repeat = false;
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (repeat) {
            return start;
        }
        else {
            return time;
        }
    }

    public LocalDateTime getEndTime() {
        if (repeat) {
            return end;
        }
        else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (repeat) {
            return interval;
        }
        else {
            return 0;
        }
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        repeat = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeat;
    }

/*    public int nextTimeAfter(int current) {
        if(!active){
            return -1;
        }
        if (repeat) {
            if(start > current) {
                return start;
            }
            if (end <= current)
                return -1;
            else{
                int reptime = start;
                while(reptime <= current) {
                    reptime += interval;
                }
                if(reptime <= end) {
                    return reptime;
                }
                else{
                    return -1;
                }
            }
        }
        else {
            if (time > current) {
                return time;
            }
            else {
                return -1;
            }
        }
    }*/

    public LocalDateTime nextTimeAfter(LocalDateTime current){
        if(!active){
            return null;
        }
        if (repeat) {
            if(current.isBefore(start) ){
                return start;
            }
            if(current.isAfter(end)){
                return null;
            }
            else{
                LocalDateTime temp = start.plusSeconds(0);
                while (temp.isBefore(current) || temp.equals(current)){
                    temp = temp.plusSeconds(interval);
                }
                if(end.isAfter(temp) || end.isEqual(temp)) return temp;
                else return null;
            }
        }
        else{
            if(time.isAfter(current)){
                return time;
            }
            else{
                return null;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        if(repeat != task.repeat) return false;
        else{
            if (repeat){
                return interval == task.interval &&
                        start.equals( task.start) &&
                        end.equals(task.end) &&
                        active == task.active &&
                        Objects.equals(title, task.title);
            }
            else{
                return time.equals(task.time) &&
                        active == task.active &&
                        Objects.equals(title, task.title);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, interval, start, end, repeat, active);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", interval=" + interval +
                ", start=" + start +
                ", end=" + end +
                ", repeat=" + repeat +
                ", active=" + active +
                '}';
    }

    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}
