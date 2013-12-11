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
import java.awt.event.MouseEvent;
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

    /**
     * TaskListPanel的add方法：
     * 用来添加TaskBox或者AddTaskBox组件.
     * 
     * @param comp
     * @return 
     * @author huangyuhan
     */
    @Override
    public Component add(Component comp) {
        DragBoxListener dragListener = new DragBoxListener();
        System.out.println("add detected!");
        _taskBoxCount++;
        //this.setLayout(new GridLayout(max(_taskBoxCount,8),0,0,_vgap));
        this.setLayout(null);
        //this.add(new javax.swing.JButton());
        this.setSize(new java.awt.Dimension(_WIDTH,(_HEIGHT+80)*_taskBoxCount+_vgap*_taskBoxCount));
        Component _comp = super.add(comp); //To change body of generated methods, choose Tools | Templates.
        //comp.setLocation(100,100);
        /**
         * 注册motionListener用来检测drag事件，
         * 注册mouseListener用来检测click事件，以记录初始坐标和释放坐标.
         */
        comp.addMouseMotionListener(dragListener);//添加鼠标拖动监听器.
        comp.addMouseListener(dragListener);
        this.updateUI();
        return _comp;
    }


    @Override
    public void updateUI() {
        java.awt.Point p;
        /**
         * 先按照order顺序对TaskBox进行排序.
         */
        for(int i=0 ; i<_taskBoxCount; i++){
            Component comp = this.getComponent(i);
             //设置每个组件的大小和位置;
            comp.setBounds(0,(((TaskBox)comp).getOrder()-1)*(_HEIGHT+3*_vgap)
                               ,_WIDTH,_HEIGHT);
        }
        super.updateUI(); //To change body of generated methods, choose Tools | Templates.
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
    
    /**
     * 获得此TaskListPanel所对应的TaskList.
     * @return 
     */
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
    
    /**
     * 此方法被DragBoxListener调用，当拖拽时调用
     * 用来产生拖拽动画，改善用户体验.
     * 
     * @param e 
     * @author huangyuhan
     */
    public void onDragged(MouseEvent e){
    }
    /**
     * 此方法被DragBoxListener调用，当拖拽结束时调用,
     * 其作用是对TaskBox进行重新排序,
     * onDragged方法重写之后需要改写此方法.
     * @param e 
     * @author huangyuhan
     */
    public void onDragRelesed(MouseEvent e){
        
    }
    
    public int getIndexAtLocation(java.awt.Point point){
        int index;
        index = (int) (point.getY()/(_HEIGHT+_vgap)) + 1;
        return index;
    }
    
    /**
     * 此方法对此ListPanel中的任务的优先级进行更改.
     * @param oldOrder
     * @param newOrder 
     */
    public void changeOrder(int oldOrder, int newOrder){
        this._list.changeOrder(oldOrder, newOrder);
        this.updateUI();
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
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
