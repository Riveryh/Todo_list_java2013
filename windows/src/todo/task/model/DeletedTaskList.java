/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.task.model;

import java.io.File;
import java.util.Iterator;

/**
 *
 * @author huangyuhan
 */
public class DeletedTaskList extends TaskList{
    /**
     * 此列表存储了用户已经删除过的task，通过同步告诉服务器端删除掉这些task.
     * @author huangyuhan
     * @return 
     * @since 2013-12-29
     */
    public void httpSync(){
        Iterator<Task> it = this.iterator();
        Task _task=null;
        while(it.hasNext()){
                Task.httpRemoveTask(_task.getTaskId());
                it.remove();
        }
    }
    
    public DeletedTaskList(String filePath) {
        __file = new File(filePath);
    }
    
}
