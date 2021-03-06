package exercice.metier.bonus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import exercice.dao.bonus.interfaces.ITaskDao;
import exercice.metier.bonus.interfaces.ITaskService;
import exercice.modele.Task;
import exercice.tools.DbConnection;

public class TaskService extends GenericService<Task> implements ITaskService {

	public TaskService(ITaskDao dao) {
		super(Task.class, dao);
	}

	@Override
	public boolean done(long id) {
		try (Connection cnx = DbConnection.getConnection()) {
			Task taskToEdit = null;
			if ((taskToEdit = dao.findById(id, cnx)) != null) {
				taskToEdit.setDone(true);
				int res = dao.update(taskToEdit, cnx);
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
			taskAdded = dao.insert(taskToAdd, cnx);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return taskAdded;
	}

}
