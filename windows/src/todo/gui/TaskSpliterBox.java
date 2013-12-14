/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author huangyuhan
 */
public class TaskSpliterBox extends javax.swing.JPanel {
    
    private static Calendar _earliestCal;

    /**
     * Creates new form TaskSpliterBox
     */
    private TaskSpliterBox() {
        initComponents();
    }
    public TaskSpliterBox(Date date){
        this();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(_earliestCal==null){
            _earliestCal = cal;
        }else if(cal.before(_earliestCal)){
            _earliestCal = cal;
        }
        
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        
        if(cal.get(Calendar.WEEK_OF_YEAR) - _earliestCal.get(Calendar.WEEK_OF_YEAR) == 1){
            //如果时间跨度超过一周，那么标签前面就要加上下周两个字;
            this.setText("下"+cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.CHINESE));
        }else if(cal.get(Calendar.WEEK_OF_YEAR) == _earliestCal.get(Calendar.WEEK_OF_YEAR)){
            this.setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.CHINESE));
        }else{
            this.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CHINESE)
                         + cal.getDisplayName(Calendar.DATE, Calendar.SHORT, Locale.CHINESE) );
        }
        
    }

    private void setText(String s)  {
        this.jLabel1.setText(s);
    }
    
    public static void reset(){
        _earliestCal = null;
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

        setFont(new java.awt.Font("华康俪金黑W8", 0, 18)); // NOI18N
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 18)); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        add(jLabel1);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
