package todo.task.app;
import java.util.Date;

import todo.task.model.*;
import todo.task.util.TaskComparatorInBeginDate;

public class TaskListTest {
	public static void main(String arg[]){
		TaskList list = new TaskList();
		list.add(new Task("1  Hello,World!"));
		list.add(new Task("2  Print aaaa!"));
		list.add(new Task("1.5   Today",new Date()),1);
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
	}
}
