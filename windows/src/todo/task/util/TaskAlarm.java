/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.task.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huangyuhan
 */
public class TaskAlarm extends Thread{
    private ArrayList<Alarm> _alarmTable;
    private boolean _isStopped=false;
    private TaskAlarmListener _listener;
    private class Alarm{
        boolean isTurnedOn;
        Date date;
        int  taskID;
        public Alarm(Date date,int taskID){
            this.date = date;
            this.taskID = taskID;
        }
    }
    public static TaskAlarm globalAlarm;
    
    public TaskAlarm(){
        _alarmTable = new ArrayList<Alarm>();
        _alarmTable.add(new Alarm(new Date(),1));
    }
    @Override
    public void run(){
        Alarm __alarm;
        while(true){
            System.out.println("Alarm wakeup!At"+new Date());
            Iterator<Alarm> iterator = _alarmTable.iterator();
            while(iterator.hasNext()){
                __alarm = iterator.next();
                if(__alarm.isTurnedOn &&  __alarm.date.after(new Date())){
                    ring(__alarm);
                }
            }
            try {
                //线程休眠，防止资源占用过多；
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskAlarm.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized(this){
                if(_isStopped){
                    break;
                }
            }
        }
    }
    
    public void setStopped(){
        synchronized(this){
            this._isStopped = true;
        }
    }
    
    private void ring(Alarm alarm){
        alarm.isTurnedOn = false;
        //for debug
        System.out.println("TaskID: "+alarm.taskID+" ringed!");
        
        if(_listener!=null){
            _listener.ring(alarm.taskID);
        }
    }
    
    public void addAlarm(Date date, int taskID){
        Alarm newAlarm = new Alarm(date,taskID);
        newAlarm.isTurnedOn = true;
    }
}
