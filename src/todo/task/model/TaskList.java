package todo.task.model;


import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

public class TaskList extends LinkedList<Task> {

	/**
	 * 自动生成的序列化ID；
	 */
	private static final long serialVersionUID = -3614853231075393715L;
	public boolean add(Task task){
		this.addFirst(task);
		return true;
	}
	/**
	 * 含有优先级参数的添加任务方法
	 * 
	 * @author river
	 * @since 2013-11-18
	 */
	public boolean add(Task task,int priority){
		this.add(priority,task);
		return true;
	}
	public void add(String title){
		this.add(new Task(title));
	}
	public void add(String title,Date dueDate){
		this.add(title,dueDate);
	}
	
	/**
	 * 排序函数,输入为Comparator类，按照其compare()方法进行排序
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
	
}
