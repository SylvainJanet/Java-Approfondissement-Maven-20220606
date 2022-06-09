package o03.threads.runnable;

public class MyRunnable implements Runnable {

	// il s'agit d'une interface fonctionnelle qui ne contient
	// qu'une méthode, qui nous permet de définir le traitement
	// effectué lors de l'exécution du Thread
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(i);			
		}
		
	}

}
