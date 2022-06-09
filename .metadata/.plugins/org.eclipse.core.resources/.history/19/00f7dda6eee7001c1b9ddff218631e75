package exercice.console.bonus;

import java.io.IOException;
import java.util.Scanner;

import exercice.metier.bonus.TaskService;
import exercice.metier.bonus.interfaces.ITaskService;
import exercice.modele.Task;
import exercice.tools.DisplayConsoleTools;
import exercice.tools.InputTools;

public class Taches {

	private static ITaskService taskService = new TaskService();

	public static void displayTasks() {
		DisplayConsoleTools.displayEntity(Task.class, taskService.findAll());
	}

	public static void addTask(Scanner sc) throws IOException {
		System.out.println("Description ?");
		sc.nextLine();
		String description = sc.nextLine();
		boolean done = InputTools.readBooleanYN(sc, "Done ? (Y/N)");
		Task taskToAdd = new Task(0, description, done);
		taskService.insert(taskToAdd);
		System.out.println("Film ajouté.");
		System.in.read();

	}

	public static void editTask(Scanner sc) throws IOException {
		long id = InputTools.readLong(sc, "Id du film ?");
		Task taskToEdit = taskService.findById(id);
		if (taskToEdit == null) {
			System.out.println("Tâche non trouvée !");
		} else {
			System.out.println("Description ?");
			sc.nextLine();
			String description = sc.nextLine();
			boolean done = InputTools.readBooleanYN(sc, "Done ? (Y/N)");
			taskToEdit.setDescription(description);
			taskToEdit.setDone(done);
			taskService.update(taskToEdit);
			System.out.println("Tâche modifiée.");
		}
		System.in.read();
	}

	public static void taskDone(Scanner sc) throws IOException {
		long id = InputTools.readLong(sc, "Id de la tâche effectuée ?");
		if (!taskService.done(id)) {
			System.out.println("Tâche non trouvée !");
		} else {
			System.out.println("Tâche modifiée.");
		}
		System.in.read();
	}

	public static void removeTask(Scanner sc) throws IOException {
		long id = InputTools.readLong(sc, "Id de la tâche ?");
		int res = taskService.delete(id);
		if (res == 0) {
			System.out.println("Tâche non trouvée !");
		} else {
			System.out.println("Tâche supprimée.");
		}
		System.in.read();
	}

	public static void run(Scanner sc) {
		int choice = 0;
		try {
			displayTasks();
			do {
				DisplayConsoleTools.displayMenuTasks();
				choice = (int) InputTools.readBoundedLong(sc, "Votre choix ?", 1, 6);
				switch (choice) {
				case 1:
					displayTasks();
					break;
				case 2:
					addTask(sc);
					break;
				case 3:
					editTask(sc);
					break;
				case 4:
					removeTask(sc);
					break;
				case 5:
					taskDone(sc);
					break;
				}
			} while (choice != 6);
			System.out.println("Au revoir !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
