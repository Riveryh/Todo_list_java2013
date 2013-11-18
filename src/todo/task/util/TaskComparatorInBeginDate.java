package todo.task.util;

import java.util.Comparator;
import todo.task.model.Task;

public class TaskComparatorInBeginDate implements Comparator<Task>{
	@Override
	public int compare(Task o1, Task o2) {
		return -o1.getEstablishedDate().compareTo(o2.getEstablishedDate());
	}
}
