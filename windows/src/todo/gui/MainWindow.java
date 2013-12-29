package todo.gui;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static todo.task.app.Todo.fileName;
import todo.task.model.TaskList;
import todo.task.model.TodoTaskList;

public class MainWindow{
        static Point origin = new Point();  //全局的位置变量，用于表示鼠标在窗口上的位置
        /**
         * 程序入口，读取相应的设置以及文件，获得TaskList对象，并生成TaskListFrame;
         * @param args
         * @throws ClassNotFoundException
         * @throws IOException 
         */
	public static void mainWindow() {
            
		TodoTaskList todoList = null;
                TodoTaskList doneList = null;
		String todoListFileName = "TodoListFile.dat";
                String doneListFileName = "DoneListFile.dat";
                
		try{
			todoList = TodoTaskList.open(new File(todoListFileName));
		}catch(IOException e1){
                        try {
                            TodoTaskList.save(new File(todoListFileName), new TodoTaskList(todoListFileName));
                            todoList = TodoTaskList.open(new File(todoListFileName));
                        } catch(Exception e) {
                            
                        }
		} catch(Exception e) {
                    
                }
                try{
			doneList = TodoTaskList.open(new File(doneListFileName));
		}catch(IOException e1){
                        try {
                            TodoTaskList.save(new File(doneListFileName), new TodoTaskList(doneListFileName));
                            doneList = TodoTaskList.open(new File(doneListFileName));
                        } catch(Exception e) {
                            
                        }
		} catch(Exception e) {
                    
                }
                
                TaskList.todoList = todoList;
                TaskList.doneList = doneList;
                
                //设置界面样式为windows默认界面    
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (InstantiationException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch(Exception e) {
                    
                }
                
                UIManager.put("ScrollBar.width", new Integer(5)); //设置界面滚动条宽度
		
                //JFrame.setDefaultLookAndFeelDecorated(true);
		final JFrame frame=new TaskListFrame(todoList,doneList);
                //frame.setUndecorated(true);
                
                //因为去掉了标题栏，所以要添加鼠标监听器检测拖动
                frame.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                                origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
                                origin.y = e.getY();
                        }
                });
                frame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                        public void mouseDragged(java.awt.event.MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
                            
                                Point p = frame.getLocation();  //当鼠标拖动时获取窗口当前位置
                                //设置窗口的位置
                                //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                                frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
                        }
                });
		frame.setVisible(true);
                frame.setTitle("TODO");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
		System.out.println("exit");
	
	}
        
        public static void main(String[] args) {
            LoginWindow frame = new LoginWindow();
            frame.setVisible(true);
            frame.setTitle("TODO");
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
