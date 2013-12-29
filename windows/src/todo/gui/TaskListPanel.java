/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import todo.task.model.Task;
import todo.task.model.TaskList;
import todo.task.model.TodoTaskList;

/**
 *
 * @author huangyuhan
 */
public class TaskListPanel extends javax.swing.JPanel implements 
            todo.task.util.TaskListListener{
    /**
     * 此TaskListPanel对应的TaskList
     * @author huangyuhan
     */
    private TodoTaskList _list;
    private int _taskBoxCount=0;
    /**
     * 界面上表示两个taskBox之间的像素间距
     * @author huangyuhan
     */
    private int _vgap=2;    
    private int _zOrder;
    /**
     * 缓存各个TaskBox组件，按照对应的task的order顺序存储.
     * @author huangyuhan
     */
    private LinkedList<Component> _taskBoxes;
    
    
  
    
    
    static int _WIDTH = TaskBox._WIDTH;
    static int _HEIGHT = TaskBox._HEIGHT;
    static Dimension _DIMENSION = new Dimension(_WIDTH,_HEIGHT);

    /**
     * Creates new form TaskListPanel
     */
    public TaskListPanel() {
        initComponents();
        _taskBoxes = new LinkedList<Component>();
    }
    public TaskListPanel(TodoTaskList list){
        this();
        this._list=list;
        this.reLoad();
    }
    
    public void reLoad(){
        Task __task;
        ListBox __box;
        this.removeAll();
        this._taskBoxes = new LinkedList<Component>();
        Iterator<Task> _iterator = _list.iterator();
        while(_iterator.hasNext()){
            __box = (ListBox) this.add(__task=_iterator.next());
        }
        _list.addTaskListListener(this);
        updateUI();
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
        _taskBoxes.add(((ListBox)comp).getOrder(),comp);    //将模块添加到内部的数组中,并且顺序就是task的顺序，这样就保持了一致性.
              
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
        updateUI();
    }
    public void addTask(String title,int lastOrder){
        this.add(getNewTask(title,lastOrder+1));
        updateUI();
    }
    
    
    public Point getTaskBoxLocation(TaskBox box){       
        return new Point(0,(box.getOrder()-1)*(_HEIGHT+_vgap));
    }
    
    public Task getNewTask(){
        return _list.getNewTask();
    }
    public Task getNewTask(String title,int order){
        return _list.getNewTask(title,order);
    }
    
    /**
     * 输入鼠标位置点（相对于panel），返回该点放下鼠标应该有的order号
     * @param p
     * @return 
     */
    public int getOrderAt(Point p){
        int __order = 0;
        
        /** 初值赋为该box原来的序号
        *   当鼠标移动很快的时候这一步可能会抛出异常
        *   用户在拖拽的时候把鼠标移有Task的范围会出bug!.
        */
        
        try{
            __order=((TaskBox)this.getComponentAt(100,p.y)).getOrder(); 
        }catch(java.lang.ClassCastException e){
        }catch(java.lang.NullPointerException e){
        }
        
        Point __location;
        TaskBox __taskBox;
        Iterator<Component> it = _taskBoxes.iterator();
        while(it.hasNext()){
            __taskBox  = (TaskBox) it.next();
            if(!__taskBox.isDragged()){
                __location = __taskBox.getLocation();
                if(__location.y-0.1*TaskBox._HEIGHT<=p.y && 
                        __location.y+0.9*TaskBox._HEIGHT>p.y){
                    __order = __taskBox.getOrder();
                }else if(__location.y>p.y+TaskBox._HEIGHT){
                    break;
                }
            }
        }
        
        return __order;
    }
    
    

    /**
     * 刷新当前视图中的所有组件的界面，
     * 根据任务的前后日期关系会在界面中插入相应的日期分割标签。
     * 注意：如果通过add(Task)方法和delet(ListBox)以外的方法增加或删除了任务，
     * 需要调用reLoad()方法重新扫描整个TaskList，否则会出现显示与内容不同步的问题。
     * @author huangyuhan
     */
    @Override
    public void updateUI() {
        int _newPanelHeight;
        TaskBox temp = null;  //存储当前box
        TaskBox temp2 = null; //存储上一个box
        this.removeAll();//移除所有boxes的显示.
        TaskSpliterBox.reset();
        
        this._taskBoxCount = 0;
        if(_taskBoxes!=null){
            Iterator<Component> iterator = _taskBoxes.iterator();
            while(iterator.hasNext()){
                temp = (TaskBox)iterator.next();
                /**
                 * 此处判断:是否为同一天的task，不是则加入一条分割线.
                 */
                if(temp2 != null){  //如果不是第一个box，那么判断两个box是否是同一天的
                    if(temp.getDueDate().getDate()==temp2.getDueDate().getDate()){
                        //如果是同一天，则不加入分割线.
                    }else{
                        //如果此panel显示的列表是未完成列表，那么要加入新建Box.
                        if(this._list == TaskList.todoList){
                            this.add(new AddTaskBox(this,temp2.getOrder()));
                        }
                        this.add(new TaskSpliterBox(temp.getDueDate()));
                    }           
                }else{      //如果是第一天，那么必须加入第一天的时间标签
                    this.add(new TaskSpliterBox(temp.getDueDate()));
                }
                temp2 = temp;
                this.add(temp);
            }
            if(this._list == TaskList.todoList){
                if(temp2!=null){
                    this.add(new AddTaskBox(this,temp2.getOrder()));
                }else{
                    this.add(new AddTaskBox(this,-1));
                }
            }  
        }
        super.updateUI(); 
    }
    
    
    
    
    
    /**
     * 获得此TaskListPanel所对应的TaskList.
     * @return 
     */
    public TodoTaskList getTaskList(){
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
        this._list.get(oldOrder).setDueDate(_list.get(newOrder).getDueDate());
        _taskBoxes.add(newOrder,_taskBoxes.remove(oldOrder));
        this._list.changeOrder(oldOrder, newOrder);
        
        System.out.println(new Date().getTime());
        this.updateUI();       
        System.out.println(new Date().getTime());
    }
    
    /**
     * 返回特定点（以taskListPanel为坐标系的坐标）上的组件的顺序
     * @param point
     * @return 
     */
    public int getIndexAtLocation(java.awt.Point point){
        int index;
        index = (int) (point.getY()/(_HEIGHT+_vgap)) + 1;
        return index;
    }
    
    public TodoTaskList getList(){
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

        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(_WIDTH,_HEIGHT*_taskBoxCount+_vgap*_taskBoxCount));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, _vgap));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    void delet(ListBox __box) {
        System.out.println("remove"+_taskBoxes.remove(__box));//先从内部的模块list中删除.
        System.out.println(_taskBoxes);
        _list.remove(__box.getOrder());//再从_list数据列表中删除.
        this.updateUI();
    }

    
}
