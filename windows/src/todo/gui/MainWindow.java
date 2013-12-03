package todo.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import static todo.task.app.Todo.fileName;
import todo.task.model.TaskList;

public class MainWindow{

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		TaskList list;
		String fileName = "TaskListFile.dat";
                
		try{
			list = TaskList.open(new File(fileName));
		}catch(IOException e1){
			TaskList.save(new File(fileName), new TaskList(fileName));
			list = TaskList.open(new File(fileName));
		}
            
            
                JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame=new TaskListFrame(list);
                
                
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("exit");
	
	}

}
