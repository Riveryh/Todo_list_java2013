package todo.task.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
         * @param task
         * @return 
	 * @since 2013-11-18
	 */
        @Override
        public void add(int order,Task task){
            Task temp;            
            Iterator<Task> iterator = this.iterator();
            System.out.println("Task Added!");
            while(iterator.hasNext()){
                temp = iterator.next();
                /**
                 * 
                 * 对原有task进行遍历，如果原来的order大于等于新task的order，
                 * 那么原来的order就+1.
                 */
                if(temp.getOrder()>=order){
                    temp.setOrder(temp.getOrder()+1);
                }
            }
            //最后将新的task的order设为新的order;
            task.setOrder(order);
            super.add(task);
            try {
			this.save();
		} catch (IOException e) {
			e.printStackTrace();
                }
        }
        
        @Override
	public boolean add(Task task){
		this.add(1,task);		
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
	
	
	public Task remove(int index){
             Task temp;
             int order = this.get(index).getOrder();
             Iterator<Task> iterator = this.iterator();
             System.out.println("TaskListRemove!");
             while(iterator.hasNext()){
                    temp = iterator.next();
                    /**
                     * 
                     * 对原有task进行遍历，如果原来的order大于要删除的task的order，
                     * 那么原来的order就-1.
                     */
                    if(temp.getOrder()>order){
                        temp.setOrder(temp.getOrder()-1);
                    }
                }
                //最后删除掉要删除的task；
		Task task = super.remove(index);
            
		try {
			this.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
        
        public void edit(int index,String title){
            this.get(index).setTitle(title);
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
		Task task;
		ListIterator<Task> iterator = this.listIterator();
		String buffer="";
		while(iterator.hasNext()){
			task = iterator.next();
			buffer = buffer + this.indexOf(task) + "  " 
                                 + task.getTitle() + "\t\t" 
				 + (new SimpleDateFormat("E")).format(task.getDueDate())
                                 + task.getOrder() +"\n";
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
		this.add(indexB,task);//add()方法会自动调用save(),所以无需单独调用save();
	}
        
        /**
         * 此方法更改任务列表中的某一个任务的优先级，并对其他任务的优先级做出调整.
         * @param oldOrder
         * @param newOrder 
         * @author huangyuhan
         * @since 2013-12-11
         */
        public void changeOrder(int oldOrder,int newOrder){
            Task temp = null;
            int index = 0;
            try {
                index = this.getTaskIndexByOrder(oldOrder);
            } catch (Exception ex) {
                Logger.getLogger(TaskList.class.getName()).log(Level.SEVERE, null, ex);
            }
            temp = this.remove(index);            
            this.add(newOrder,temp);
        }
        
        /**
         * 通过task的order获取相应的Task类.
         * @param order
         * @return
         * @throws Exception 
         */
        public int getTaskIndexByOrder(int order) throws Exception{
            for(int i=0;i<this.size();i++){
                if(this.get(i).getOrder() == order){
                    return i;
                }else{
                    
                }
            }
            throw new Exception("TaskNotFound!");
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
