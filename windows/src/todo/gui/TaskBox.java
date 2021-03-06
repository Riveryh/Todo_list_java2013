/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this TaskBox file, choose Tools | Templates
 * and open the TaskBox in the editor.
 */
package todo.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import todo.task.model.Task;

/**
 *
 * @author huangyuhan
 */
public class TaskBox extends javax.swing.JPanel  implements ListBox{
    private Task _task;
    private TaskBox _thisBox = this;
    private DefaultCellEditor _dCE;
    private TaskListPanel _parent;
    private boolean _isExtended=false;
    
    static int _WIDTH = 280;
    static int _HEIGHT= 45;
    static int _EXTENDED_HEIGHT = 100;
    
    /**
     * 含参数构造方法，接受一个Task对象，将它的引用保存在TaskBox对象内部，
     * 并且将TaskBox中的文本框设置为task.title的内容.
     * 
     * @author: huangyuhan
     */
     public TaskBox(){
        super();
        initComponents();
     }
     /**
      * 此类型的构造方法在创建addTaskBox时候会被调用.
      * @param parent 
      */
     public TaskBox(TaskListPanel parent){
         this(parent.getNewTask(),parent);
     }
     /**
      * 此种类型的构造方法在panel初始化的时候被调用.
      * @param task
      * @param parent 
      */
     public TaskBox(Task task,TaskListPanel parent){
        this();
        _task = task;
        _parent = parent;
        this.refresh();
        _dCE = new DefaultCellEditor(new javax.swing.JTextField());        
        /**
         * 添加事件监听器.
         */
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.out.println("mouseClicked");
                _thisBox._task.setCompleted(_thisBox._checkBox.isSelected());
                _thisBox._taskTitleField.selectAll();
                _thisBox.refresh();
            }
        });
        this.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                _thisBox._task.setTitle(_thisBox._taskTitleField.getText());
                _thisBox.refresh();
            }
        });
        
        /**
         * 添加文本框的事件监听器.
         */
        this._taskTitleField.addFocusListener(new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                _thisBox._task.setTitle(_thisBox._taskTitleField.getText());
            }
        });
        
        /**
         * 添加checkBox的事件监听器.
         */
        this._checkBox.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                _thisBox._task.setCompleted(_thisBox._checkBox.isSelected());
            }           
        });
        
        /**
         * 初始化扩展面板内容，保持与task数据的一致.
         */
        this.setInputDate(_thisBox._task.getDueDate());
        this.setInputDiscription(_thisBox._task.getDiscription());
    }
    
    
    /**
     * 返回此TaskBox中的文本框.
     * @return 
     */
    public javax.swing.JTextField getTextField(){
        return this._taskTitleField;
    }   
    public javax.swing.JCheckBox getCheckBox(){
        return this._checkBox;
    }
    /**
     * 返回此TaskBox对象所对应的Task对象.
     * @return 
     */
    public Task getTask(){
        return this._task;
    } 
    /**
     * 添加和获取order次序.
     * @return 
     */
    public int getOrder(){
        return this._task.getOrder();
    }
    public void setOrder(int order){
        this._task.setOrder(order);
    }
  
    private void refresh(){
        if(_task!=null){
            _checkBox.setSelected(_task.isCompleted());
            _taskTitleField.setText(_task.getTitle());
        }
    }
   
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg); //To change body of generated methods, choose Tools | Templates.
        if((_checkBox!=null)&&(_taskTitleField!=null)){
            this._checkBox.setBackground(bg);
            this._taskTitleField.setBackground(bg);
            this.basicPanel.setBackground(bg);
            this.extendedPanel.setBackground(bg);
        }        
    }
            
            
           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basicPanel = new javax.swing.JPanel();
        _checkBox = new javax.swing.JCheckBox();
        _taskTitleField = new javax.swing.JTextField();
        dragLabel = new javax.swing.JLabel();
        extendedPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        monthComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        dateComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        discriptionTextField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Microsoft YaHei UI", 0, 15)); // NOI18N
        setMaximumSize(new java.awt.Dimension(280, 500));
        setPreferredSize(new Dimension(_WIDTH,_HEIGHT));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        basicPanel.setBackground(new java.awt.Color(255, 255, 255));
        basicPanel.setPreferredSize(new Dimension(_WIDTH,_HEIGHT));
        basicPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        _checkBox.setBackground(new java.awt.Color(255, 255, 255));
        _checkBox.setHideActionText(true);
        _checkBox.setMargin(new java.awt.Insets(4, 4, 4, 4));
        _checkBox.setMaximumSize(new java.awt.Dimension(40, 40));
        _checkBox.setMinimumSize(new java.awt.Dimension(40, 40));
        _checkBox.setPreferredSize(new java.awt.Dimension(40, 40));
        _checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _checkBoxActionPerformed(evt);
            }
        });
        basicPanel.add(_checkBox);
        _checkBox.addItemListener(new java.awt.event.ItemListener(){
            public void itemStateChanged(java.awt.event.ItemEvent e){
                // _thisBox.getTask().setCompleted((boolean)e.getStateChange());
            }
        });

        _taskTitleField.setBackground(getBackground());
        _taskTitleField.setFont(new java.awt.Font("STSong", 0, 22)); // NOI18N
        _taskTitleField.setText("jTextField2");
        _taskTitleField.setBorder(null);
        _taskTitleField.setPreferredSize(new java.awt.Dimension(170, 30));
        _taskTitleField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                _taskTitleFieldFocusGained(evt);
            }
        });
        basicPanel.add(_taskTitleField);

        dragLabel.setText(". . .");
        basicPanel.add(dragLabel);

        add(basicPanel);

        extendedPanel.setFont(new java.awt.Font("STXihei", 0, 15)); // NOI18N
        extendedPanel.setPreferredSize(new Dimension(_WIDTH,_EXTENDED_HEIGHT));
        extendedPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 20));

        jLabel2.setText("年：");
        extendedPanel.add(jLabel2);

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013", "2014", "2015", "2016", "2017" }));
        yearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearComboBoxActionPerformed(evt);
            }
        });
        extendedPanel.add(yearComboBox);

        jLabel3.setText("月：");
        extendedPanel.add(jLabel3);

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        extendedPanel.add(monthComboBox);

        jLabel4.setText("日：");
        extendedPanel.add(jLabel4);

        dateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        extendedPanel.add(dateComboBox);

        jLabel5.setText("备注：");
        extendedPanel.add(jLabel5);

        discriptionTextField.setText("添加备注");
        discriptionTextField.setPreferredSize(new java.awt.Dimension(150, 30));
        extendedPanel.add(discriptionTextField);

        submitButton.setText("do");
        submitButton.setActionCommand("Y");
        submitButton.setPreferredSize(new java.awt.Dimension(64, 30));
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitButtonMouseClicked(evt);
            }
        });
        extendedPanel.add(submitButton);

        add(extendedPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void yearComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearComboBoxActionPerformed

    private void _checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__checkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__checkBoxActionPerformed

    private void _taskTitleFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event__taskTitleFieldFocusGained
        this.doAdd();
    }//GEN-LAST:event__taskTitleFieldFocusGained

    private void submitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseClicked
        this.doSubmit();
    }//GEN-LAST:event_submitButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox _checkBox;
    private javax.swing.JTextField _taskTitleField;
    private javax.swing.JPanel basicPanel;
    private javax.swing.JComboBox dateComboBox;
    private javax.swing.JTextField discriptionTextField;
    private javax.swing.JLabel dragLabel;
    private javax.swing.JPanel extendedPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox monthComboBox;
    private javax.swing.JButton submitButton;
    private javax.swing.JComboBox yearComboBox;
    // End of variables declaration//GEN-END:variables

    private void doAdd() {
        this._isExtended = true;
        //放下扩展面板
        this.setPreferredSize(new java.awt.Dimension(_WIDTH,_HEIGHT+_EXTENDED_HEIGHT));
        this.updateUI();
    }

    private void doSubmit() {
        this._task.setDueDate(getInputDate());
        this._task.setDiscription(getInputDiscription());
                
        //收起扩展面板.        
        this.setPreferredSize(new java.awt.Dimension(_WIDTH,_HEIGHT));
        this.updateUI();
        this._isExtended = false;
    }
    /**
     * 此函数获得用户通过GUI输入的时间，翻译为Date对象.
     * 注意：不检查时间范围的合理性！
     * @return 
     */
    public Date getInputDate(){
        System.out.println("this function is uncompleted!");
        Date date = new Date();
        String buffer = new String();
        buffer += this.yearComboBox.getSelectedItem();
        buffer += "-"+this.monthComboBox.getSelectedItem();
        buffer += "-"+this.dateComboBox.getSelectedItem();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(buffer);
        } catch (ParseException ex) {
            Logger.getLogger(TaskBox.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Error!");
        }
        return date;
    }
    /**
     * 此函数通过GUI获取用户输入的备注信息.
     * @return 
     */
    public String getInputDiscription(){
        return this.discriptionTextField.getText();
    }
    public void setInputDate(Date date){
        yearComboBox.setSelectedItem( ((Integer)date.getYear()).toString());
        monthComboBox.setSelectedItem(((Integer)date.getMonth()).toString());
        dateComboBox.setSelectedItem(((Integer)date.getDate()).toString());
    }
    public void setInputDiscription(String discription){
        this.discriptionTextField.setText(discription);
    }

}
