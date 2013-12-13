/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import static java.lang.Math.max;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import todo.task.model.Task;
import todo.task.model.TaskList;

/**
 *
 * @author huangyuhan
 */
public class TaskListPanel extends javax.swing.JPanel implements 
            todo.task.util.TaskListListener{
    
    private TaskList _list;
    private int _taskBoxCount=0;
    private int _vgap=3;    //表示两个taskBox之间的间距；
    private int _zOrder;
    private LayoutManager layout;
    private HashMap<Integer,ListBox> _mapOrderToBox;
    private LinkedList<Component> _boxes;
    
    private DragBoxListener dragListener = new DragBoxListener();
  
    
    
    static int _WIDTH = TaskBox._WIDTH;
    static int _HEIGHT = TaskBox._HEIGHT;
    static Dimension _DIMENSION = new Dimension(_WIDTH,_HEIGHT);

    /**
     * Creates new form TaskListPanel
     */
    public TaskListPanel() {
        initComponents();
        _boxes = new LinkedList<Component>();
    }
    public TaskListPanel(TaskList list){
        this();
        this._list=list;
        Task __task;
        ListBox __box;
        Iterator<Task> _iterator = _list.iterator();
        while(_iterator.hasNext()){
            __box = (ListBox) this.add(__task=_iterator.next());
        }
        _list.addTaskListListener(this);
    }

    /**
     * TaskListPanel的add方法：
     * 用来添加TaskBox或者AddTaskBox组件
     * 此方法不应该被外部直接调用！
     * 此方法只是改变了显示内容，并不会对数据产生任何改变!.
     * 
     * @param comp
     * @return 
     * @author huangyuhan
     */
    @Override
    public Component add(Component comp) {
        //System.out.println("add detected!");
        _taskBoxCount++;
        //this.setSize(new java.awt.Dimension(_WIDTH,(_HEIGHT)*_taskBoxCount+_vgap*_taskBoxCount+100));
        Component _comp = super.add(comp); //To change body of generated methods, choose Tools | Templates.
        
        //this.updateUI();
        return _comp;
    } 
    
    /**
     * 以task为参数的添加方法
     * 此方法会改变Panel内部的_boxes list；
     * @param task 
     */
    public Component add(Task task){
        Component comp;
        comp = this.add(new TaskBox(task,this));
        _boxes.add(((ListBox)comp).getOrder(),comp);    //将模块添加到内部的数组中,并且顺序就是task的顺序，这样就保持了一致性.
        /**
         * 注册motionListener用来检测drag事件，
         * 注册mouseListener用来检测click事件，以记录初始坐标和释放坐标.
         */
        comp.addMouseMotionListener(dragListener);//添加鼠标拖动监听器.
        comp.addMouseListener(dragListener);
        super.updateUI();
        return comp;
    }
    /**
     * 无参数添加方法，调出AddTaskBox.
     */
    public void addTask(){
        /*
        AddTaskBox addBox = new AddTaskBox(this);       
        */
        this.add(getNewTask());
    }
    
    
    public Point getTaskBoxLocation(TaskBox box){       
        return new Point(0,(box.getOrder()-1)*(_HEIGHT+_vgap));
    }
    
    public Task getNewTask(){
        return _list.getNewTask();
    }
    

    @Override
    public void remove(Component comp) {
        comp.removeMouseListener(dragListener);
        comp.removeMouseMotionListener(dragListener);
        super.remove(comp); 
    }
    

    @Override
    public void updateUI() {
        /**
         * 先按照order顺序对TaskBox进行排序.
         * TODO:add操作在box很多的时候非常耗时间，需要优化.
         */
        Component temp;
        Component temp2;
        this.removeAll();//移除所有boxes的显示.
        
        this._taskBoxCount = 0;
        if(_boxes!=null){
            Iterator<Component> iterator = _boxes.iterator();
            while(iterator.hasNext()){
                temp = iterator.next();
                /**
                 * TODO: 此处添加判断:是否为同一天的task，不是则加入一条分割线.
                 */
                this.add(temp);
            }
        }
        super.updateUI(); 
    }
    
    
    
    
    
    /**
     * 获得此TaskListPanel所对应的TaskList.
     * @return 
     */
    public TaskList getTaskList(){
        return _list;
    }
    
    public int getTaskBoxCount(){
        return _taskBoxCount;
    }
    
    /**
     * task内容发生改变之后调用此方法进行保存.
     */
    @Override
    public void onTaskListChanged(){
        this.updateUI();
    }
    
    /**
     * 此方法被DragBoxListener调用，当拖拽时调用
     * 用来产生拖拽动画，改善用户体验.
     * 
     * @param e 
     * @author huangyuhan
     */
    public void onDragged(MouseEvent e){
        _zOrder = getComponentZOrder(e.getComponent());
        //仅当拖拽开始时才设置层次，以减少资源损耗.
        if(_zOrder!=0){
            this.setComponentZOrder(e.getComponent(), 0);
        }
    }
    /**
     * 此方法被DragBoxListener调用，当拖拽结束时调用,
     * 其作用是对TaskBox进行重新排序,
     * onDragged方法重写之后需要改写此方法.
     * @param e 
     * @author huangyuhan
     */
    public void onDragRelesed(MouseEvent e){
        this.setComponentZOrder(e.getComponent(),_zOrder);
        _zOrder = 0;
    }
    
    
    /**
     * 此方法对此ListPanel中的任务的优先级进行更改.
     * @param oldOrder
     * @param newOrder 
     */
    public void changeOrder(int oldOrder, int newOrder){
        ListBox __box;
        
        _boxes.add(newOrder,_boxes.remove(oldOrder));
        this._list.changeOrder(oldOrder, newOrder);
        
        System.out.println(new Date().getTime());
        this.updateUI();       
        System.out.println(new Date().getTime());
    }
    
    public int getIndexAtLocation(java.awt.Point point){
        int index;
        index = (int) (point.getY()/(_HEIGHT+_vgap)) + 1;
        return index;
    }
    
    public TaskList getList(){
        return this._list;
    }
    
    
    public java.awt.Dimension getPreferredSize(){
        return new java.awt.Dimension(_WIDTH,80+_HEIGHT*_taskBoxCount+_vgap*_taskBoxCount);
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
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, _vgap));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    void delet(ListBox __box) {
        System.out.println("remove"+_boxes.remove(__box));//先从内部的模块list中删除.
        System.out.println(_boxes);
        _list.remove(__box.getOrder());//再从_list数据列表中删除.
        this.updateUI();
    }

    
}
