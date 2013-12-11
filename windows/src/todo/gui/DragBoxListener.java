/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author huangyuhan
 */
public class DragBoxListener extends MouseAdapter{
     /** 
      * 坐标点. 
      */  
        private Point point = new Point(0, 0);  
        private boolean __isDragged;
        private Component __draggedComponent;
  
        /** 
         * 当鼠标拖动时触发该事件。 记录下鼠标按下(开始拖动)的位置.
         */  
        @Override
        public void mouseDragged(MouseEvent e) {  
            // 转换坐标系统  
            __isDragged = true;
            Point newPoint = SwingUtilities.convertPoint(e.getComponent(), e  
                    .getPoint(), e.getComponent().getParent());  
            
            Point comPoint = e.getComponent().getLocation();
            
            // 设置标签的新位置  
            e.getComponent().setLocation(e.getComponent().getX()  
                    , e.getComponent().getY()  
                    + (newPoint.y - point.y));  
            // 更改坐标点  
            point = newPoint;  
        }  
  
        /** 
         * 当鼠标按下时触发该事件。 记录下鼠标按下(开始拖动)的位置.
         */  
        public void mousePressed(MouseEvent e) {  
            // 得到当前坐标点  
            __draggedComponent = e.getComponent();
            System.out.println(e.getComponent());
            point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),  
                   e.getComponent().getParent());  
        }  

        /**
         * 释放鼠标时根据鼠标的位置对任务进行重新排序.
         * @param e 
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            int oldOrder;
            int newOrder;
            
            if(true == __isDragged){
                System.out.println("released");
                TaskListPanel panel;
                panel = (TaskListPanel)e.getComponent().getParent();
               
                oldOrder = ((TaskBox)__draggedComponent) .getOrder();
                newOrder = ((TaskListPanel)__draggedComponent.getParent())
                                .getIndexAtLocation(point);
                ((TaskListPanel)__draggedComponent.getParent())
                                .changeOrder(oldOrder, newOrder);                        
            }
            __isDragged = false;
            __draggedComponent = null;
        }

}
