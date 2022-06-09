package exercice.dao.bonus;

import exercice.dao.bonus.interfaces.ITaskDao;
import exercice.modele.Task;

public class TaskDao extends GenericDao<Task> implements ITaskDao {

	public TaskDao() {
		super(Task.class);
	}

}
