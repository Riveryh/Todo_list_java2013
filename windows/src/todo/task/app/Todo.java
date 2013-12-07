package todo.task.app;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import todo.task.model.TaskList;

public class Todo {
        private static TaskList _list;
	public static String fileName = "TaskListFile.dat";
	
	public static void main(String[] arg) throws IOException, ClassNotFoundException, ParseException{
		
		
		try{
			_list = TaskList.open(new File(fileName));
		}catch(IOException e1){
			TaskList.save(new File(fileName), new TaskList(fileName));
			_list = TaskList.open(new File(fileName));
		}
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		printList(_list);
		
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
				_list.add(matcher.group());
				if((matcher=timePattern.matcher(buffer)).find()){
					Calendar today = Calendar.getInstance();
					today.add(Calendar.DATE, Integer.parseInt(matcher.group()));
					_list.get(0).setDueDate(today.getTime());
				}
				if((matcher=prioPattern.matcher(buffer)).find()){
					_list.move(0, Integer.parseInt(matcher.group()));
				}
			}else if((matcher=delPattern.matcher(buffer)).find()){
				_list.remove(Integer.parseInt(matcher.group()));
			}else if((matcher=movPattern.matcher(buffer)).find()){
				Matcher temp = Pattern.compile("\\d+").matcher(buffer);
				temp.find();
				int indexA = Integer.parseInt(temp.group());
				temp.find();
				int indexB = Integer.parseInt(temp.group());
				_list.move(indexA, indexB);
			}
			printList(_list);
			buffer = sc.nextLine();
		}
		_list.save();
	}
	
	public static void printList(TaskList list){
		System.out.flush();
		System.out.println("--------------------------------");
		System.out.print(list);
		System.out.println("--------------------------------");
	}
        
        public static void commandMode(String args,TaskList _list){
            
            String buffer;
            
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
           Pattern editPattern= Pattern.compile("(?<=edit\\s{1,10})\\d+\\s+\\b.*");
           
            Matcher matcher;
            matcher = exitPattern.matcher(buffer=args);
            if((matcher=addPattern.matcher(buffer)).find()){
                    _list.add(matcher.group());
                    if((matcher=timePattern.matcher(buffer)).find()){
                            Calendar today = Calendar.getInstance();
                            today.add(Calendar.DATE, Integer.parseInt(matcher.group()));
                            _list.get(0).setDueDate(today.getTime());
                    }
                    if((matcher=prioPattern.matcher(buffer)).find()){
                            _list.move(0, Integer.parseInt(matcher.group()));
                    }
            }else if((matcher=delPattern.matcher(buffer)).find()){
                    _list.remove(Integer.parseInt(matcher.group()));
            }else if((matcher=movPattern.matcher(buffer)).find()){
                    Matcher temp = Pattern.compile("\\d+").matcher(buffer);
                    temp.find();
                    int indexA = Integer.parseInt(temp.group());
                    temp.find();
                    int indexB = Integer.parseInt(temp.group());
                    _list.move(indexA, indexB);
            }else if((matcher=editPattern.matcher(buffer)).find()){
                    Matcher temp = Pattern.compile("\\d+").matcher(buffer);
                    temp.find();
                    int index = Integer.parseInt(temp.group());
                    temp = Pattern.compile("(?<=\\s{1,10}\\b).*").matcher(buffer);
                    String _newTitle = temp.group();
                    _list.edit(index, _newTitle);
            }else{}
            
            printList(_list);
            try {
                _list.save();
            } catch (IOException ex) {
                Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
