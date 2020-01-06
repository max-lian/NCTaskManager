package ua.edu.sumdu.j2se.yakovlev.tasks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.Map;

public class TaskIO {

    public static void write(AbstractTaskList<Task> tasks, OutputStream outStr) throws IOException {
        DataOutputStream out = new DataOutputStream(outStr);
        out.writeInt(tasks.getLenght());
        for(Task task:tasks){
            //task.
            out.writeInt(task.getTitle().length());
            out.writeUTF(task.getTitle());
            out.writeBoolean(task.isActive());
            out.writeLong(task.getRepeatInterval());
            if(task.isRepeated()){
                out.writeLong(task.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli()/1000);
                out.writeLong(task.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli()/1000);
            }
            else{
                out.writeLong(task.getTime().toInstant(ZoneOffset.UTC).toEpochMilli()/1000);
            }
        }
    }
    public static void read(AbstractTaskList<Task> tasks, InputStream inStr) throws IOException {
        DataInputStream in = new DataInputStream(inStr);
        int len = in.readInt();
        for(int i = 0; i < len; i++){
            in.readInt();
            Task task = new Task();
            task.setTitle(in.readUTF());
            task.setActive(in.readBoolean());
            long interval  = in.readLong();
            if(interval == 0){
                task.setTime( LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC));
            }
            else{
                task.setTime(LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC),
                        (int) interval);
            }
            tasks.add(task);
        }
    }
    public static void writeBinary(AbstractTaskList<Task> tasks, File file) throws IOException {
        OutputStream a = new FileOutputStream(file);
        DataOutputStream out = new DataOutputStream(a);
        out.writeInt(tasks.getLenght());
        for(Task task:tasks){
            //task.
            out.writeInt(task.getTitle().length());
            out.writeChars(task.getTitle());
            out.writeBoolean(task.isActive());
            out.writeLong(task.getRepeatInterval());
            if(task.isRepeated()){
                out.writeLong(task.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli()/1000);
                out.writeLong(task.getEndTime().toInstant(ZoneOffset.UTC).toEpochMilli()/1000);
            }
            else{
                out.writeLong(task.getTime().toInstant(ZoneOffset.UTC).toEpochMilli()/1000);
            }
        }

    }
    public static void readBinary(AbstractTaskList<Task> tasks, File file) throws IOException {
        InputStream a = new FileInputStream(file);
        DataInputStream in = new DataInputStream(a);
        int len = in.readInt();
        for(int i = 0; i < len; i++){
            in.readInt();
            Task task = new Task();
            task.setTitle(in.readUTF());
            task.setActive(in.readBoolean());
            long interval  = in.readLong();
            if(interval == 0){
                task.setTime( LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC));
            }
            else{
                task.setTime(LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC),
                        LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC),
                        (int) interval);
            }
        }
    }

    public static void write(AbstractTaskList<Task> tasks, Writer out) throws IOException {
        //try(FileWriter file = new FileWriter("file1.txt")) {
            JSONObject jsonObject = new JSONObject();
            JSONArray taskList = new JSONArray();
            for (Task task : tasks) {
                JSONObject object = new JSONObject();
                object.put("title", task.getTitle());
                object.put("active", task.isActive());
                object.put("interval", task.getRepeatInterval());
                if (task.isRepeated()) {
                    object.put("start time", task.getStartTime());
                    object.put("end time", task.getEndTime());
                } else {
                    object.put("time", task.getTime());
                }
                taskList.add(object);
            }
            jsonObject.put("task list", taskList);
            out.write(jsonObject.toJSONString());
            out.close();

    }
    public static void read(AbstractTaskList<Task> tasks, Reader in) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(in);
            JSONArray taskList = (JSONArray) obj.get("task list");
            Iterator<JSONObject> iterator = taskList.iterator();
            while (iterator.hasNext()) {
                Task task = new Task();
                JSONObject jsonObject = iterator.next();
                System.out.printf(String.valueOf(jsonObject));
                task.setTitle((String) jsonObject.get("Title"));
                task.setActive((Boolean) jsonObject.get("active"));
                int interval = (int) jsonObject.get("interval");
                if (interval == 0) {
                    task.setTime((LocalDateTime) jsonObject.get("time"));
                } else {
                    task.setTime((LocalDateTime) jsonObject.get("start time"),
                            (LocalDateTime) jsonObject.get("end time"),
                            interval);
                }
                tasks.add(task);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeText(AbstractTaskList<Task> tasks, File file){

    }
    public static void readText(AbstractTaskList<Task> tasks, File file){

    }


}
