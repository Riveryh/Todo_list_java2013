/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import javax.swing.table.DefaultTableModel;
import todo.task.model.Task;

/**
 *
 * @author huangyuhan
 */
public class MyTableModel extends DefaultTableModel{
   
    public MyTableModel(){
        super(0,1);
    }
    public void add(Task task){
        TaskBox[] _taskBox = new TaskBox[1];
        _taskBox[0]=new TaskBox(task);
        this.addRow(_taskBox);
    }
}
