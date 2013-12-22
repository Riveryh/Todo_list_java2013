package todo.task.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import todo.task.util.TaskListListener;

public class TodoTaskList extends TaskList {

    private File __file;
    
    //必须将__listener标记为transient属性，不然序列化的时候会把整个taskListPanel都写进去！
    private transient TaskListListener __listener;  


    /**
     * 注意：此构造方法只会在TaskListFile.dat不存在的时候才会被调用, order是从0开始，index也从0开始！
     *
     * @param filePath
     */
    public TodoTaskList(String filePath) {
        __file = new File(filePath);
    }

    /**
     * 自动生成的序列化ID；
     */
    private static final long serialVersionUID = -3614853231075393715L;

    /**
     * 不同参数的add()方法
     *
     * @author river
     * @param task
     * @return
     * @since 2013-11-18
     */
    @Override
    public void add(int order, Task task) {
        Task temp;
        Iterator<Task> iterator = this.iterator();
        System.out.println("Task Added!");
        super.add(order, task);
        for (int __index = 0; __index < this.size(); __index++) {
            /**
             * 对原有task进行遍历，保持index和order的一致性.
             */
            this.get(__index).setOrder(__index);
        }

        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Task task) {
        this.add(size(), task);
        return true;
    }

    /*
     public void add(String title){
     this.add(new Task(title,this));
     }
     public void add(String title,Date dueDate){
     this.add(new Task(title,dueDate,this));
     }
     */
    public Task remove(int index) {
        System.out.println("TaskListRemove!");
        //先删除掉要删除的task；
        Task task = super.remove(index);
        for (int __index = 0; __index < this.size(); __index++) {
            /**
             * 对原有task进行遍历，保持index和order的一致性.
             */
            this.get(__index).setOrder(__index);
        }
        try {
            this.save();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return task;
    }

    public void edit(int index, String title) {
        this.get(index).setTitle(title);
    }

    /**
     * 排序函数,输入为Comparator类，按照其compare()方法进行排序 TODO:排序之后原有的任务顺序被打乱，如何恢复?
     *
     * @author river
     * @since 2013-11-18
     */
    public void sort(Comparator<Task> comparator) {
        Collections.sort(this, comparator);
    }

    /**
     * 重写toString函数， 返回值为list中各个task的toString()返回值用换行符相连接形成的字符串
     *
     * @author river
     * @since 2013-11-18
     */
    public String toString() {
        Task task;
        ListIterator<Task> iterator = this.listIterator();
        String buffer = "";
        while (iterator.hasNext()) {
            task = iterator.next();
            buffer = buffer + this.indexOf(task) + "  "
                    + task.getTitle() + "\t\t"
                    + (new SimpleDateFormat("E")).format(task.getDueDate())
                    + task.getOrder() + "\n";
        }
        return buffer + "";
    }
    
    public Task getNewTask(){
        Task __task = new Task("new Task"+size(),this);
        this.add(__task);
        return __task;
    }
    public Task getNewTask(String title,int order) {
        Task __task = new Task(title,this);
        this.add(order,__task);
        //根据前后关系调整任务的截止日期
        if(order>0){
            __task.setDueDate(this.get(order-1).getDueDate());
        }
        return __task;
    }

    /**
     * 将位置在indexA的task移动到indexB，如果index超出范围则 抛出异常IndexOutOfBoundsException；
     *
     * @author river
     * @since 2013-11-18
     */
    public void move(int indexA, int indexB) throws IndexOutOfBoundsException {
        Task task = this.get(indexA);
        this.remove(indexA);
        this.add(indexB, task);//add()方法会自动调用save(),所以无需单独调用save();
    }

    /**
     * 此方法更改任务列表中的某一个任务的优先级，并对其他任务的优先级做出调整.
     *
     * @param oldOrder
     * @param newOrder
     * @author huangyuhan
     * @since 2013-12-11
     */
    public void changeOrder(int oldOrder, int newOrder) {
        Task temp = null;
        temp = this.remove(oldOrder);
        this.add(newOrder, temp);
    }

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

    public void onTaskChanged(Task task) {
        if(__listener!=null){
            __listener.onTaskListChanged();
        }
        try {
            this.save();
        } catch (IOException ex) {
            Logger.getLogger(TodoTaskList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTaskListListener(todo.task.util.TaskListListener l) {
        if (__listener == null) {
            __listener = l;
        }
    }
    
    public void updateDueDate(){
        if(this==TaskList.doneList) return;  //已完成列表不进行时间更新.
        Iterator<Task> it = this.iterator();
        Task _task=null;
        Date today = new Date();
        while(it.hasNext()){
            _task = it.next();
            if(_task.getDueDate().before(today)){
                _task.setDueDate(today);
            }
        }
    }
    
   

    
}
