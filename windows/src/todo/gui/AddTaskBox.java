/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import todo.task.model.Task;

/**
 *
 * @author huangyuhan
 */
public class AddTaskBox extends TaskBox{
    private AddTaskBox _thisBox = this;
    private TaskListPanel _parent ;
    

    /**
     * Creates new form AddTaskBox
     */
    public AddTaskBox() {
    }
    public AddTaskBox(TaskListPanel parent){
        this._parent = parent;
        initComponents();
        this.taskBox1.getTextField().setText("New Task");
        this.taskBox1.getTextField().addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent e){
                _thisBox.doAdd();
            }
        });
        this.taskBox1.setOrder(parent.getTaskBoxCount()+1);
    }
    
  
    public int getOrder(){
        return this.taskBox1.getOrder();
    }
    
    public void doAdd(){
        TaskListPanel _listPanel = (TaskListPanel) this.getParent();
        if(this.getTitle().equalsIgnoreCase("")){
            
        }else{
            _listPanel.add(generateTask());
            _listPanel.remove(this);
            _listPanel.getList().remove(this.taskBox1.getTask());
        }
    }
    
    
    
    public Task generateTask(){
        Task _task;
        _task = new Task(this.taskBox1.getTextField().getText(),((TaskListPanel) this.getParent()).getTaskList());
        return _task;
    }
    
    public String getTitle(){
        return this.taskBox1.getTextField().getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskBox1 = new todo.gui.TaskBox(_parent);

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(taskBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, -1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private todo.gui.TaskBox taskBox1;
    // End of variables declaration//GEN-END:variables
}
