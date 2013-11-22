package todo.task.util;

import java.util.Comparator;

import todo.task.model.Task;

public class TaskComparatorInDueDate implements Comparator<Task> {
	public int compare(Task o1, Task o2) {
		return -o1.getDueDate().compareTo(o2.getDueDate());
	}
}
