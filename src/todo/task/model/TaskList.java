package todo.task.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class TaskList extends LinkedList<Task> {
	
	
	private File __file;
	
	public TaskList(String filePath){
		__file = new File(filePath);
	}

	/**
	 * 自动生成的序列化ID；
	 */
	private static final long serialVersionUID = -3614853231075393715L;
	
	/**
	 * 不同参数的add()方法,继承的add()方法只能使用Task作为参数,或者（index,Task）作为参数；
	 * 
	 * @author river
	 * @since 2013-11-18
	 */
	public boolean add(Task task){
		this.addFirst(task);
		try {
			this.save();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void add(String title){
		this.add(new Task(title));
	}
	public void add(String title,Date dueDate){
		this.add(new Task(title,dueDate));
	}
	
	
	public Task remove(int index){
		Task task = super.remove(index);
		try {
			this.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	/**
	 * 排序函数,输入为Comparator类，按照其compare()方法进行排序
	 * TODO:排序之后原有的任务顺序被打乱，如何恢复?
	 * @author river
	 * @since 2013-11-18
	 */
	public void sort(Comparator<Task> comparator){
		Collections.sort(this,comparator);
	}
	
	/**
	 * 重写toString函数，
	 * 返回值为list中各个task的toString()返回值用换行符相连接形成的字符串
	 * @author river
	 * @since 2013-11-18
	 */
	public String toString(){
		ListIterator<Task> iterator = this.listIterator();
		String buffer="";
		while(iterator.hasNext()){
			buffer = buffer + iterator.next() + "\n";
		}
		return buffer+"";
	}
	
	/**
	 * 将位置在indexA的task移动到indexB，如果index超出范围则
	 * 抛出异常IndexOutOfBoundsException；
	 * 
	 * @author river
	 * @since 2013-11-18
	 */
	public void move(int indexA, int indexB)throws IndexOutOfBoundsException{
		Task task = this.get(indexA);
		this.remove(indexA);
		this.add(indexB,task);
	}
	
	/**
	 * 保存方法，将指定的TaskList类或者本类保存到指定的file文件中；
	 * @author river
	 * @since 2013-11-18
	 */
	public static void save(File file,TaskList list)throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file) );
		oos.writeObject(list);
		oos.close();
	}
	public void save(File file)throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file) );
		oos.writeObject(this);
		oos.close();
	}
	public void save()throws IOException{
		if(__file!=null){
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(__file) );
			oos.writeObject(this);
			oos.close();
		}else{
			throw new IOException("The list file is not determined");
		}
	}
	

	/**
	 * 打开文件方法，将指定的File文件中的TaskList对象读出，返回该对象的引用；
	 * @author river
	 * @since 2013-11-18
	 */
	public static TaskList open(File file)throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file) );
		TaskList list = (TaskList) ois.readObject();
		ois.close();
		return list;
	}
}
