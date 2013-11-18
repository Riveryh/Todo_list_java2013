package todo.task.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task{
	static private int taskCounter=0;
	private int serialNumber;
	private Date startDate;
	private Date dueDate;
	private Date establishedDate;
	private String title;
	private String discription;
	private boolean isCompleted;
	
	
	
	//constructors
	public Task(){
		serialNumber = taskCounter;
		taskCounter+=1;
		this.establishedDate = new Date();
		this.dueDate         = new Date();
		this.startDate       = new Date();
		this.isCompleted	 = false;
		this.discription	 = null;
	}
	public Task(String title) {
		this();
		this.title = title;
	}
	public Task( String title,Date dueDate) {
		this(title);
		this.dueDate = dueDate;
	}
	public Task(String title, Date dueDate, String discription) {
		this(title,dueDate);
		this.discription = discription;
	}
	
	//ToString overWrite;
	public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return title + "\n\t\t\t" + format.format(dueDate);
	}
	
	
	//Getters and Setters;
	public int getSerialNumber() {
		return serialNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
