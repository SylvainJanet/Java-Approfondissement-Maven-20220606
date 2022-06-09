package exercice.metier.bonus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import exercice.dao.bonus.TaskDao;
import exercice.dao.bonus.interfaces.ITaskDao;
import exercice.metier.bonus.interfaces.ITaskService;
import exercice.modele.Task;
import exercice.tools.DbConnection;

public class TaskService extends GenericService<Task> implements ITaskService {

	public TaskService() {
		super(Task.class);
	}

	private ITaskDao taskDao = new TaskDao();

	@Override
	public boolean done(long id) {
		try (Connection cnx = DbConnection.getConnection()) {
			Task taskToEdit = null;
			if ((taskToEdit = taskDao.findById(id, cnx)) != null) {
				taskToEdit.setDone(true);
				int res = taskDao.update(taskToEdit, cnx);
				return res != 0;
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// DOUBLE BONUS :

	@Override
	public Task addNewTask(String description) {
		Task taskAdded = null;
		try (Connection cnx = DbConnection.getConnection()) {
			Task taskToAdd = new Task();
			taskToAdd.setDescription(description);
			taskToAdd.setDone(false); // inutile : valeur par défaut lors de la création avec le new
			taskAdded = taskDao.insert(taskToAdd, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return taskAdded;
	}

}
