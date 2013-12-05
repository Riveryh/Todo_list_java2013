/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huangyuhan
 */
public class MyTaskTable extends javax.swing.JTable{
    private MyTableModel _model;
    public MyTaskTable(){
        super();
        this.setModel(_model = new MyTableModel());
        this.setRowHeight(50);
    }
    public MyTableModel getTableModel(){
        return this._model;
    }
}
