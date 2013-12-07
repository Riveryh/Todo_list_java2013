/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import static java.lang.Math.max;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import todo.task.model.Task;
import todo.task.model.TaskList;

/**
 *
 * @author huangyuhan
 */
public class TaskListPanel extends javax.swing.JPanel {
    
    private TaskList _list;
    private int _taskBoxCount=0;
    private int _vgap=1;    //表示两个taskBox之间的间距；
    
    
    static int _WIDTH = TaskBox._WIDTH;
    static int _HEIGHT = TaskBox._HEIGHT;
    static Dimension _DIMENSION = new Dimension(_WIDTH,_HEIGHT);

    /**
     * Creates new form TaskListPanel
     */
    public TaskListPanel() {
        initComponents();
    }
    public TaskListPanel(TaskList list){
        this();
        this._list=list;
        Iterator<Task> _iterator = _list.iterator();
        while(_iterator.hasNext()){
            this.add(new TaskBox(_iterator.next()));
        }
        
    }

    @Override
    public Component add(Component comp) {
        System.out.println("add detected!");
        _taskBoxCount++;
        this.setLayout(new GridLayout(max(_taskBoxCount,8),0,0,_vgap));
        this.setSize(new java.awt.Dimension(_WIDTH,_HEIGHT*_taskBoxCount+_vgap*_taskBoxCount));
        Component _comp = super.add(comp); //To change body of generated methods, choose Tools | Templates.
        this.updateUI();
        return _comp;
    }
    /**
     * 以task为参数的添加方法.
     * @param task 
     */
    public void add(Task task){
        this._list.addLast(task);
        this.add(new TaskBox(task));
        try {
            this._list.save();
        } catch (IOException ex) {
            Logger.getLogger(TaskListPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * 无参数添加方法，调出AddTaskBox.
     */
    public void addTask(){
        java.awt.GridLayout layout;
        layout = new java.awt.GridLayout(_taskBoxCount+1, 1, 0, _vgap);
        this.setLayout(layout);
        layout.addLayoutComponent("AddBox",new AddTaskBox());
    }
    
    
    public TaskList getTaskList(){
        return _list;
    }
    
    /**
     * task内容发生改变之后调用此方法进行保存.
     */
    public void onTaskChanged(){
        try {
            _list.save();
        } catch (IOException ex) {
            Logger.getLogger(TaskListPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public java.awt.Dimension getPreferredSize(){
        return new java.awt.Dimension(_WIDTH,_HEIGHT*_taskBoxCount+_vgap*_taskBoxCount);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(_WIDTH,_HEIGHT*_taskBoxCount+_vgap*_taskBoxCount));
        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
