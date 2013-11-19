package todo.task.app;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import todo.task.model.*;
import todo.task.util.TaskComparatorInBeginDate;

public class TaskListTest {
	public static void main(String arg[]){
		TaskList list = new TaskList("data.dat");
		list.add(new Task("1  Hello,World!"));
		list.add(new Task("2  Print aaaa!"));
		list.add(1,new Task("1.5   Today",new Date()));
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(2).getTitle());
		System.out.println("_________________________");
		System.out.println(list);
		
		list.move(2, 1);

		System.out.println("_________________________");
		System.out.println(list);
		
		list.sort(new TaskComparatorInBeginDate());

		System.out.println("_________________________");
		System.out.println(list);
		
		File file = new File("data.dat");
		try{
			list.save();
			System.out.println("After saved:");
			System.out.println(TaskList.open(file));
		}catch(IOException e){
			System.out.println(e);
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}
	}
}
