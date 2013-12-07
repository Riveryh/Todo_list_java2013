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
        private TaskList __belongTo;
	
	
	
	//constructors
	public Task(){
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
                this.__belongTo = list;
		this.__title = title;
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
		return __title + "\n\t\t\t" + format.format(__dueDate);
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
	}
	public Date getDueDate() {
		return __dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.__dueDate = dueDate;
	}
	public Date getEstablishedDate() {
		return __establishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
		this.__establishedDate = establishedDate;
	}
	public String getTitle() {
		return __title;
	}
	public void setTitle(String title) {
		this.__title = title;
	}
	public String getDiscription() {
		return __discription;
	}
	public void setDiscription(String discription) {
		this.__discription = discription;
	}
	public boolean isCompleted() {
		return __isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.__isCompleted = isCompleted;
	}
        public TaskList getBelongTo(){
                return __belongTo;
        }
	
}
