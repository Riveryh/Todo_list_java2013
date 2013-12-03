/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import todo.task.model.Task;

/**
 *
 * @author huangyuhan
 */
public class TaskBox extends javax.swing.JTextField{
    private Task _task;
    public TaskBox(Task task){
        super();
        _task = task;
        this.setText(_task.getTitle());
        this.setVisible(true);
    }
}
