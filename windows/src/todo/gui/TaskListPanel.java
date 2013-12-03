/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.util.ArrayList;
import java.util.Iterator;
import todo.task.model.Task;
import todo.task.model.TaskList;

/**
 *
 * @author huangyuhan
 */
public class TaskListPanel extends javax.swing.JPanel {
    private TaskList _list;

    /**
     * Creates new form TaskListPanel
     */
    public TaskListPanel() {
    }
    public TaskListPanel(TaskList list){
        this();
        _list = list;
        ArrayList<TaskBox> taskBoxList= new ArrayList<TaskBox>();
        Iterator<Task> iterator=_list.iterator();
        while(iterator.hasNext()){
            TaskBox thisTaskBox=new TaskBox(iterator.next());
            taskBoxList.add(thisTaskBox);
            this.add(thisTaskBox);
        }
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("TaskListPanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel1)
                .addContainerGap(347, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
