package todo.task.app;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import todo.task.model.TaskList;

public class Todo {
	public static String fileName = "TaskListFile.dat";
	
	public static void main(String[] arg) throws IOException, ClassNotFoundException, ParseException{
		TaskList list;
		
		try{
			list = TaskList.open(new File(fileName));
		}catch(IOException e1){
			TaskList.save(new File(fileName), new TaskList(fileName));
			list = TaskList.open(new File(fileName));
		}
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		printList(list);
		
		/**
		 * 此处为各类命令的正则表达式形式；
		 * 语法为：
		 * add [任务内容] [ in(...)days ],[prio=...]
		 * del [任务序号]
		 * mov [原任务序号] [新任务序号]
		 * exit/e （退出）
		 */
		Pattern exitPattern = Pattern.compile("\\b(e|exit)\\b.*");
		Pattern addPattern  = Pattern.compile("(?<=(add\\s{1,10}))\\b\\S*\\b");
		Pattern delPattern  = Pattern.compile("(?<=del\\s{1,10})\\d");
		Pattern movPattern  = Pattern.compile("(?<=mov\\s{1,10})\\d\\s+\\d");
		Pattern timePattern  = Pattern.compile("(?<=in)\\d+(?=(day|days))");
		Pattern prioPattern  = Pattern.compile("(?<=prio=)\\d+");
		
		Matcher matcher = exitPattern.matcher(buffer=sc.nextLine());
		while(!exitPattern.matcher(buffer).find()){
			if((matcher=addPattern.matcher(buffer)).find()){
				list.add(matcher.group());
				if((matcher=timePattern.matcher(buffer)).find()){
					Calendar today = Calendar.getInstance();
					today.add(Calendar.DATE, Integer.parseInt(matcher.group()));
					list.get(0).setDueDate(today.getTime());
				}
				if((matcher=prioPattern.matcher(buffer)).find()){
					list.move(0, Integer.parseInt(matcher.group()));
				}
			}else if((matcher=delPattern.matcher(buffer)).find()){
				list.remove(Integer.parseInt(matcher.group()));
			}else if((matcher=movPattern.matcher(buffer)).find()){
				Matcher temp = Pattern.compile("\\d+").matcher(buffer);
				temp.find();
				int indexA = Integer.parseInt(temp.group());
				temp.find();
				int indexB = Integer.parseInt(temp.group());
				list.move(indexA, indexB);
			}
			printList(list);
			buffer = sc.nextLine();
		}
	}
	
	public static void printList(TaskList list){
		System.out.flush();
		//System.out.println("------------TODO LIST-----------");
		//System.out.println("Task                     DueDate");
		System.out.println("--------------------------------");
		System.out.print(list);
		System.out.println("--------------------------------");
	}
}
