package todo.task.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static private int __taskCounter=0;
	private int __serialNumber;
	private Date __startDate;
	private Date __dueDate;
	private Date __establishedDate;
	private String __title;
	private String __discription;
	private boolean __isCompleted;
        private TodoTaskList __parent;
        private int __order;
	
	
	
	//constructors
	private Task(){
		__serialNumber = __taskCounter;
		__taskCounter+=1;
		this.__establishedDate = new Date();
		this.__dueDate         = new Date();
		this.__startDate       = new Date();
		this.__isCompleted	 = false;
		this.__discription	 = null;
	}
	public Task(String title,TodoTaskList list) {
		this();
                this.__parent = list;
		this.__title = title;
                this.__order = list.size()+1;
	}
	public Task( String title,Date dueDate,TodoTaskList list) {
		this(title,list);
		this.__dueDate = dueDate;
	}
	public Task(String title, Date dueDate, String discription,TodoTaskList list) {
		this(title,dueDate,list);
		this.__discription = discription;
	}
	
	//ToString overWrite;
	public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("E");
		return __title + "\n\t\t\t" + format.format(__dueDate)
                            + __order;
	}
	
	
	//Getters and Setters;
	public int getSerialNumber() {
		return __serialNumber;
	}
	public Date getStartDate() {
		return __startDate;
	}
	public void setStartDate(Date startDate) {
		this.__startDate = startDate;
                __parent.onTaskChanged(this);
	}
	public Date getDueDate() {
		return __dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.__dueDate = dueDate;
                __parent.onTaskChanged(this);
	}
	public Date getEstablishedDate() {
		return __establishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
		this.__establishedDate = establishedDate;
                __parent.onTaskChanged(this);
	}
	public String getTitle() {
		return __title;
	}
	public void setTitle(String title) {
		this.__title = title;
                __parent.onTaskChanged(this);
	}
	public String getDiscription() {
		return __discription;
	}
	public void setDiscription(String discription) {
		this.__discription = discription;
                __parent.onTaskChanged(this);
	}
	public boolean isCompleted() {
		return __isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.__isCompleted = isCompleted;
                __parent.onTaskChanged(this);
	}
        public void setCompleted(){
            this.__isCompleted = true;
            __parent.remove(this.__order);
            __parent = (TodoTaskList)TaskList.doneList;
            this.__order = 0;
            __parent.add(this);
        }
        public void setUncompleted(){
            this.__isCompleted = false;
            __parent.remove(this.__order);
            __parent = (TodoTaskList)TaskList.todoList;
            this.__order = 0;
            __parent.add(this);
        }
        public TodoTaskList getBelongTo(){
                return __parent;
        }
        public int getOrder(){
                return __order;
        }
        public void setOrder(int order){
                //__parent.onTaskChanged(this);
                this.__order = order;
        }
	
}
