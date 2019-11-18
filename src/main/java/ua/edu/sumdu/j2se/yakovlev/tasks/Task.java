package ua.edu.sumdu.j2se.yakovlev.tasks;

import java.util.Objects;

public class Task{
    private String title;
    private int time;
    private int interval;
    private int start;
    private int end;
    private boolean repeat;
    private boolean active = false;

    public Task(String title, int time) throws IllegalArgumentException {
        if(time < 0){
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        repeat = false;
    }

    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        if(start < 0 || end < 0 || interval <= 0 ){
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.interval = interval;
        this.start = start;
        this.end = end;
        repeat = true;
    }

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

    public int getTime() {
        if (repeat) {
            return start;
        }
        else {
            return time;
        }
    }

    public void setTime(int time) throws IllegalArgumentException{
        if(time < 0){
            throw new IllegalArgumentException();
        }
        repeat = false;
        this.time = time;
    }

    public int getStartTime() {
        if (repeat) {
            return start;
        }
        else {
            return time;
        }
    }

    public int getEndTime() {
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

    public void setTime(int start, int end, int interval) throws IllegalArgumentException {
        if(start < 0 || end < 0 || interval <= 0 ){
            throw new IllegalArgumentException();
        }
        repeat = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.time = 0;
    }

    public boolean isRepeated() {
        return repeat;
    }

    public int nextTimeAfter(int current) {
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time &&
                interval == task.interval &&
                start == task.start &&
                end == task.end &&
                repeat == task.repeat &&
                active == task.active &&
                Objects.equals(title, task.title);
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
}
