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
        private TaskList __parent;
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
	public Task(String title,TaskList list) {
		this();
                this.__parent = list;
		this.__title = title;
                this.__order = list.size()+1;
	}
	public Task( String title,Date dueDate,TaskList list) {
		this(title,list);
		this.__dueDate = dueDate;
	}
	public Task(String title, Date dueDate, String discription,TaskList list) {
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
                __parent.onTaskChanged(this);
		this.__startDate = startDate;
	}
	public Date getDueDate() {
		return __dueDate;
	}
	public void setDueDate(Date dueDate) {
                __parent.onTaskChanged(this);
		this.__dueDate = dueDate;
	}
	public Date getEstablishedDate() {
		return __establishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
                __parent.onTaskChanged(this);
		this.__establishedDate = establishedDate;
	}
	public String getTitle() {
		return __title;
	}
	public void setTitle(String title) {
                __parent.onTaskChanged(this);
		this.__title = title;
	}
	public String getDiscription() {
		return __discription;
	}
	public void setDiscription(String discription) {
                __parent.onTaskChanged(this);
		this.__discription = discription;
	}
	public boolean isCompleted() {
		return __isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
                __parent.onTaskChanged(this);
		this.__isCompleted = isCompleted;
	}
        public TaskList getBelongTo(){
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
