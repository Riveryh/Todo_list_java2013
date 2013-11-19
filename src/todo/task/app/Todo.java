package todo.task.app;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import todo.task.model.TaskList;

public class Todo {
	public static String fileName = "TaskListFile.dat";
	
	public static void main(String[] arg) throws IOException, ClassNotFoundException{
		TaskList list;
		
		try{
			list = TaskList.open(new File(fileName));
		}catch(IOException e1){
			TaskList.save(new File(fileName), new TaskList(fileName));
			list = TaskList.open(new File(fileName));
		}
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		String cBuffer;
		StringTokenizer stk;
		printList(list);
		while((buffer = sc.nextLine()).compareToIgnoreCase("e")!=0){
			stk = new StringTokenizer(buffer);
			cBuffer = stk.nextToken();
			if(cBuffer.compareToIgnoreCase("a")==0){
				list.add(stk.nextToken());
			}else if(cBuffer.compareToIgnoreCase("d")==0){
				list.remove(Integer.parseInt(stk.nextToken()));
			}
			printList(list);
		}
	}
	
	public static void printList(TaskList list){
		System.out.println("------------TODO LIST-----------");
		System.out.println("Task                     DueDate");
		System.out.println(list);
		System.out.println("--------------------------------");
	}
}
