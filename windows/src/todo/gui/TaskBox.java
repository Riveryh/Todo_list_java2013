/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this TaskBox file, choose Tools | Templates
 * and open the TaskBox in the editor.
 */
package todo.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.event.CellEditorListener;
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
        _taskTitleField.setText(_task.getTitle());
        _dCE = new DefaultCellEditor(new javax.swing.JTextField());
    }
    
    public javax.swing.JTextField getTextBox(){
        return this._taskTitleField;
    }
    public Task getTask(){
        return this._task;
    }
    

    
    @Override
    /**
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
        
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
       
    }
            
            
            
            
            
    public javax.swing.JCheckBox getCheckBox(){
        return this._checkBox;
    }
    public javax.swing.JTextField getTextField(){
        return this._taskTitleField;
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

        _taskTitleField.setBackground(getBackground());
        _taskTitleField.setFont(new java.awt.Font("Microsoft YaHei", 0, 22)); // NOI18N
        _taskTitleField.setText("jTextField2");
        _taskTitleField.setBorder(null);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_checkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(_taskTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_checkBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(_taskTitleField)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void _taskTitleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__taskTitleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__taskTitleFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox _checkBox;
    private javax.swing.JTextField _taskTitleField;
    // End of variables declaration//GEN-END:variables


}
