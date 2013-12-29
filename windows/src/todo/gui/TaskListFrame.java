/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import todo.task.model.TodoTaskList;
import todo.task.app.Todo;
import todo.task.model.DeletedTaskList;

/**
 *
 * @author huangyuhan
 */
public class TaskListFrame extends javax.swing.JFrame {
    
    private TodoTaskList _todoList;
    private TodoTaskList _doneList;
    private DeletedTaskList _deletedList;

    /**
     * 构造函数，接受TaskList参数并将其引用保存在类中,
     * 实际上不允许使用无参数构造函数，保留下来是GUI的要求.
     * 
     * @author: huangyuhan
     *
     */
    public TaskListFrame() {  
    }
    public TaskListFrame(TodoTaskList todoList,TodoTaskList doneList,DeletedTaskList deletedList){
        this();
        this.setUndecorated(true);
        _todoList = todoList;
        _doneList = doneList;
        _deletedList = deletedList;
        _todoList.updateDueDate();//刷新所有过期任务的时间到今天.
        initComponents();        
    }
    
    /**
     * 调用此方法将和服务器端同步三个list,
     * @author huangyuhan
     * @since 2013-12-29
     */
    public void syncAllList(){
        _todoList.httpSync();
        _doneList.httpSync();
        _deletedList.httpSync();
        //刷新数据之后重新刷新视图。
        taskListPanel1.reLoad();
        taskListPanel2.reLoad();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commandText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        todoListPane = new javax.swing.JScrollPane();
        taskListPanel2 = new todo.gui.TaskListPanel(this._todoList);
        doneListPane = new javax.swing.JScrollPane();
        taskListPanel1 = new todo.gui.TaskListPanel(this._doneList);

        commandText.setText("Input Commands");
        commandText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                commandTextMouseClicked(evt);
            }
        });
        commandText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                commandTextKeyPressed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setText("+");
        jButton1.setBorder(null);
        jButton1.setPreferredSize(new java.awt.Dimension(15, 15));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(255, 153, 51));
        submitButton.setText("debug");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(640, 480, 300, 400));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(TaskBox._WIDTH+19,TaskBox._HEIGHT*10+50));
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 233, 233), 2));
        jPanel2.setPreferredSize(new java.awt.Dimension(TaskBox._WIDTH+19,TaskBox._HEIGHT*10+50));

        jToggleButton1.setText("Done/Todo");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 51));
        jButton2.setText("X");
        jButton2.setBorder(null);
        jButton2.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(TaskBox._WIDTH+19,TaskBox._HEIGHT*10));
        jPanel1.setLayout(new java.awt.CardLayout());

        todoListPane.setBorder(null);
        todoListPane.setToolTipText("");
        todoListPane.setPreferredSize(new java.awt.Dimension(TaskBox._WIDTH,TaskBox._HEIGHT*10));

        taskListPanel2.setPreferredSize(new java.awt.Dimension(300, 80));
        todoListPane.setViewportView(taskListPanel2);
        taskListPanel2.getAccessibleContext().setAccessibleName("");

        jPanel1.add(todoListPane, "card2");
        todoListPane.getAccessibleContext().setAccessibleName("Todo");

        doneListPane.setBorder(null);
        doneListPane.setPreferredSize(new java.awt.Dimension(TaskBox._WIDTH,TaskBox._HEIGHT*10));

        taskListPanel1.setPreferredSize(new java.awt.Dimension(295, 80));
        doneListPane.setViewportView(taskListPanel1);

        jPanel1.add(doneListPane, "card3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(1, 1, 1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void commandTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_commandTextMouseClicked
        // TODO add your handling code here:
        commandText.selectAll();
    }//GEN-LAST:event_commandTextMouseClicked

    private void commandTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commandTextKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
            submitButton.doClick();
        }
    }//GEN-LAST:event_commandTextKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.taskListPanel2.addTask();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        taskListPanel1.reLoad();
        taskListPanel2.reLoad();
        ((java.awt.CardLayout)this.jPanel1.getLayout()).next(jPanel1);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        String commands = this.commandText.getText();
        Todo.commandMode(commands, _todoList);
        this.invalidate();
    }//GEN-LAST:event_submitButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();// 关闭当前窗口
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaskListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskListFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaskListFrame().setVisible(true);
            }
        });
    }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commandText;
    private javax.swing.JScrollPane doneListPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton submitButton;
    private todo.gui.TaskListPanel taskListPanel1;
    private todo.gui.TaskListPanel taskListPanel2;
    private javax.swing.JScrollPane todoListPane;
    // End of variables declaration//GEN-END:variables
}
