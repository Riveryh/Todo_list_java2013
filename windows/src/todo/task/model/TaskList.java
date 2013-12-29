/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.task.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author huangyuhan
 */
public class TaskList extends LinkedList<Task>{
    public static TaskList todoList;
    public static TaskList doneList;
    public static TaskList deletedList;
    
    /**
     * 用于保存本list的文件
     * @author huangyuhan
     */
    protected File __file;
    
     /**
     * 保存方法，将指定的TaskList类或者本类保存到指定的file文件中；
     *
     * @author river
     * @since 2013-11-18
     */
    public static void save(File file, TodoTaskList list) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(list);
        oos.close();
    }

    public void save(File file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(this);
        oos.close();
    }

    public void save() throws IOException {
        if (__file != null) {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(__file));
            oos.writeObject(this);
            oos.close();
        } else {
            throw new IOException("The list file is not determined");
        }
    }
    
    /**
     * 打开文件方法，将指定的File文件中的TaskList对象读出，返回该对象的引用；
     *
     * @author river
     * @since 2013-11-18
     */
    public static TodoTaskList open(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        TodoTaskList list = (TodoTaskList) ois.readObject();
        ois.close();
        return list;
    }
}
