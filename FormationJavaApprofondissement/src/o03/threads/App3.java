package o03.threads;

import o03.threads.runnable.MyRunnable;

public class App3 {

	public static void main(String[] args) {
		/*
		 * 
		 * Un thread est une unité d'exécution faisant partie d'un programme Cette unité
		 * fonctionne de façon autonome aux autres threads.
		 * 
		 * Un programme peut effectuer plusieurs opérations en même temps
		 * 
		 * Le système alloue un temps de traitement pour chaque thread, et passe d'un
		 * thread à l'autre
		 * 
		 * ... thread 1 ... thread 2 ... thread 1 ... thread 3 ... thread 1
		 * 
		 * La JVM créé plusieurs threads pour ses propres besoins : le thread
		 * d'exécution du code java, mais aussi d'autres thread, par exemple pour
		 * l'exécution du ramasse miettes (Garbage Collector)
		 * 
		 * Pour créer nos propres Threads, on utilise la classe Thread de
		 * java.lang.Thread et l'interface Runnable de java.lang.Runnable
		 * 
		 * Thread : créer un Thread Runnable : définir l'opération qui sera effectuée
		 * 
		 */

		System.out.println("_____ Threads _____");
		Thread thread = new Thread(new MyRunnable());

		thread.start();

		System.out.println("_____ Utilisation de plusieurs threads _____");

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println("Thread1 - " + i);
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					System.out.println("Thread2 - " + i);
				}
			}
		});

		thread1.start();
		thread2.start();

		System.out.println("_____ Multithreading et opération lentes _____");

		Thread thread3 = new Thread(() -> {
			for (int i = 0; i < 2; i++) {
				System.out.println("Thread3 - " + i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread thread4 = new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				System.out.println("Thread4 - " + i);
			}
		});

		thread3.start();
		thread4.start();

	}

}
