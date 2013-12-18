/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

/**
 * TaskBox的order是非负整数,代表该Task的排序
 * AddTaskBox的order是-2
 * TaskSplisterBox的order是-3
 * @author huangyuhan
 */
public interface ListBox {
    public int getOrder();
}
