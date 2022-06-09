package exercice.metier.bonus.interfaces;

import exercice.modele.Task;

public interface ITaskService extends IGenericService<Task> {

	boolean done(long id);

	// DOUBLE BONUS :
	
	Task addNewTask(String description);
}
