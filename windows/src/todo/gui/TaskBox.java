/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this TaskBox file, choose Tools | Templates
 * and open the TaskBox in the editor.
 */
package todo.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import todo.task.model.Task;

/**
 *
 * @author huangyuhan
 */
public class TaskBox extends javax.swing.JPanel implements ListCellRenderer
                                                            ,TableCellRenderer
                                                            ,TableCellEditor{
    private Task _task;
    private TaskBox _thisBox = this;
    private DefaultCellEditor _dCE;
    
    static int _WIDTH = 220;
    static int _HEIGHT= 50;
    
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
    public TaskBox(Task task){
        this();
        _task = task;
        this.refresh();
        _dCE = new DefaultCellEditor(new javax.swing.JTextField());
        
        
        /**
         * 添加事件监听器.
         */
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                _thisBox._task.setCompleted(_thisBox._checkBox.isSelected());
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
                /**
                 * 此处将update消息传递给上层容器，由listPanel执行save()等操作.
                 */
                ((TaskListPanel)_thisBox.getParent()).onTaskChanged();
            }
        });
        
        /**
         * 添加checkBox的事件监听器.
         */
        this._checkBox.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                _thisBox._task.setCompleted(_thisBox._checkBox.isSelected());
                ((TaskListPanel)_thisBox.getParent()).onTaskChanged();
            }           
        });
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
    
    
     
    
    private void refresh(){      
        _checkBox.setSelected(_task.isCompleted());
        _taskTitleField.setText(_task.getTitle());
    }
    
    
    
    @Override
    /**
     * (已废弃）
     * 实现此方法是为了实现javax.swing.TableCellRenderer接口,
     * 此方法会被myTaskTable调用，其返回值是用于放在cell中显示的Component对象,
     * 其接受的参数中的value字段，是对应的MyTableModel中的TaskBox对象.
     * 
     * @author: huangyuhan
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //this._taskTitleField.
        TaskBox _taskBox = (TaskBox)value;
        if (isSelected) {
             _taskBox.setBackground(list.getSelectionBackground());
             _taskBox.setForeground(list.getSelectionForeground());
             _taskBox.getTextField();
        }
        else {
             _taskBox.setBackground(list.getBackground());
             _taskBox.setForeground(list.getForeground());
        }
        _taskBox.setEnabled(list.isEnabled());
        _taskBox.setFont(list.getFont());
        _taskBox.setOpaque(true);
        return _taskBox;
    }
    /**
     * (已废弃）
     * 实现此方法是为了实现javax.swing.TableCellEditor接口,
     * 此方法会被myTaskTable调用，其返回值是用产生可放置在表格中编辑的对象;
     * 其接受的参数中的value字段，是对应的MyTableModel中的TaskBox对象.
     * 
     * @author: huangyuhan
     */
    public Component getTableCellRendererComponent(JTable table,
                                        Object value,
                                        boolean isSelected,
                                        boolean hasFocus,
                                        int row,
                                        int column) {
        //this._taskTitleField.
        TaskBox _taskBox = (TaskBox)value;
        if (isSelected) {
             _taskBox.setBackground(table.getSelectionBackground());
             _taskBox.setForeground(table.getSelectionForeground());
             _taskBox.getTextField();
        }
        else {
             _taskBox.setBackground(table.getBackground());
             _taskBox.setForeground(table.getForeground());
        }
        _taskBox.setEnabled(table.isEnabled());
        _taskBox.setFont(table.getFont());
        _taskBox.setOpaque(true);
        return _taskBox;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table,
                                      Object value,
                                      boolean isSelected,
                                      int row,
                                      int column){
        _thisBox=(TaskBox)value;
        return _thisBox;
    }
    
    
    @Override
    public Object getCellEditorValue() {
        return _thisBox;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        //return _dCE.isCellEditable(anEvent);
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        ///return _dCE.shouldSelectCell(anEvent);
        return false;
    }

    @Override
    public boolean stopCellEditing() {
       // return _dCE.stopCellEditing();
        String buffer = _thisBox.getTextField().getText();
        _thisBox.getTask().setTitle(buffer);
        return true;
    }

    @Override
    public void cancelCellEditing() {
        
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        //_thisBox._taskTitleField.addActionListener((ActionListener)l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
       //thisBox._taskTitleField.removeActionListener((ActionListener)l);
    }
            
            
            
           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _taskTitleField = new javax.swing.JTextField();
        _checkBox = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Microsoft YaHei UI", 0, 15)); // NOI18N
        setPreferredSize(new java.awt.Dimension(_WIDTH,_HEIGHT));

        _taskTitleField.setBackground(getBackground());
        _taskTitleField.setFont(new java.awt.Font("Microsoft YaHei", 0, 22)); // NOI18N
        _taskTitleField.setText("jTextField2");
        _taskTitleField.setBorder(null);
        _taskTitleField.setPreferredSize(new Dimension(_WIDTH,_HEIGHT));
        _taskTitleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _taskTitleFieldActionPerformed(evt);
            }
        });

        _checkBox.setBackground(new java.awt.Color(255, 255, 255));
        _checkBox.setHideActionText(true);
        _checkBox.setMargin(new java.awt.Insets(4, 4, 4, 4));
        _checkBox.setMaximumSize(new java.awt.Dimension(40, 40));
        _checkBox.setMinimumSize(new java.awt.Dimension(40, 40));
        _checkBox.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(_checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(_taskTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(_checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(_taskTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        _checkBox.addItemListener(new java.awt.event.ItemListener(){
            public void itemStateChanged(java.awt.event.ItemEvent e){
                // _thisBox.getTask().setCompleted((boolean)e.getStateChange());
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void _taskTitleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__taskTitleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__taskTitleFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox _checkBox;
    private javax.swing.JTextField _taskTitleField;
    // End of variables declaration//GEN-END:variables


}
