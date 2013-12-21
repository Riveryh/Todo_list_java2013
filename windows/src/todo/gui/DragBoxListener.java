/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author huangyuhan
 */
public class DragBoxListener extends MouseAdapter implements Serializable{
     /** 
      * 坐标点. 
      */  
    
        public static int VERTICAL = 1;
        public static int HORIZON = 2;
        public static double CRITICAL = 0.3;   //定义拉出距离占原来距离的百分之多少时判定为删除.
    
        private Point point = new Point(0, 0);  
        private Point startPoint = new Point(0,0);
        private Point startLocation = new Point(0,0);
        private boolean __isDragged;
        private Component __draggedComponent;
        private Color __backGroundColor;
        private static Color __changeOrderColor = new Color(163,216,216);
        private static Color __deletColor       = new Color(255,161,175);
        private int __dragType;//1为水平，2为数值.
        private static int OFFSET = 8;
        
        
        /** 
         * 当鼠标拖动时触发该事件。 记录下鼠标按下(开始拖动)的位置.
         */  
        @Override
        public void mouseDragged(MouseEvent e) {  
            
            /** 转换坐标系统 .
             *  @TODO ::在目前的情况下会出为题，为什么.
             */
           
            Point newPoint = SwingUtilities.convertPoint(e.getComponent(), e  
                        .getPoint(), e.getComponent().getParent());  


            if(0 == __dragType){
                if(abs(newPoint.getX()-point.getX()) > 
                        abs(newPoint.getY()-point.getY())){
                    __dragType = 1;//水平滑动删除
                }else{
                    __dragType = 2;//竖直滑动排序
                }
            }

            
            //判定用户操作为左右滑动
            if(__dragType==1){
                
                // 设置标签的新位置  
                e.getComponent().setLocation(startLocation.x+(newPoint.x-startPoint.x) 
                        , startLocation.y  );
               
                // 更改坐标点  
                point = newPoint; 
                if(__isDragged==false){
                    ((TaskListPanel)__draggedComponent.getParent()).onDragged(e);    
                }else{
                }
                if(abs(point.x-startPoint.x)>=CRITICAL*TaskBox._WIDTH){
                        ((TaskBox)__draggedComponent) .setBackground(__deletColor);
                }else{
                    ((TaskBox)__draggedComponent) .setBackground(__backGroundColor);
                }
                
            }else if(__dragType==2){  //判定用户操作为竖直滑动
                

                // 设置标签的新位置  
                e.getComponent().setLocation(startLocation.x + OFFSET
                        , startLocation.y  
                        + (newPoint.y - startPoint.y));  
                // 更改坐标点  
                point = newPoint;  
                // 设置颜色
                if(__isDragged==false){
                    ((TaskListPanel)__draggedComponent.getParent()).onDragged(e);
                    ((TaskBox)__draggedComponent) .setBackground(__changeOrderColor);
                }else{
                }
            }
            __isDragged = true;
        }  
  
        /** 
         * 当鼠标按下时触发该事件。 记录下鼠标按下(开始拖动)的位置.
         */  
        public void mousePressed(MouseEvent e) {  
            // 得到当前坐标点  
            __draggedComponent = e.getComponent();
            __backGroundColor = e.getComponent().getBackground();
            point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),  
                   e.getComponent().getParent());  
            startPoint = point;
            startLocation = e.getComponent().getLocation();
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
                int __maxSize;
                int __inSize;
                
                if(__dragType==2){
                    panel = (TaskListPanel)e.getComponent().getParent();
               
                    oldOrder = ((ListBox)__draggedComponent) .getOrder();

                    __maxSize = ((TaskListPanel)__draggedComponent.getParent()).getList().size();
                    __inSize  = ((TaskListPanel)__draggedComponent.getParent())
                                    .getIndexAtLocation(point);
                    if(__inSize > __maxSize){
                        newOrder=__maxSize-1;
                    }else{
                        newOrder=__inSize-1;
                    }
                    if(newOrder<0){
                        newOrder = 0;
                    }

                    if(newOrder>=0){
                        ((TaskListPanel)__draggedComponent.getParent())
                                .changeOrder(oldOrder, newOrder);                        
                    }
                }else if(__dragType==1){
                    if(abs(point.x-startPoint.x)>=CRITICAL*TaskBox._WIDTH){
                        ((TaskListPanel)__draggedComponent.getParent()).delet((ListBox)__draggedComponent);
                    }else{
                        ((TaskListPanel)__draggedComponent.getParent()).updateUI();
                    }
                }else{
                    System.out.println("__dragType unexpected!");
                }
            }
            e.getComponent().setBackground(__backGroundColor);
            __isDragged = false;
            __draggedComponent = null;
            __dragType = 0;
            startLocation = new Point(0,0);
        }

}
