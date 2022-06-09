package exercice.tools;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputTools {

	public static long readLong(Scanner sc, String label) {
		long res;
		while (true) {
			System.out.print(label);
			try {
				res = sc.nextLong();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Merci d'entrer un nombre");
				sc.next();
			}
		}
		return res;
	}

	public static boolean readBooleanYN(Scanner sc, String label) {
		while (true) {
			System.out.print(label);
			String input = sc.nextLine();
			if (input.trim().toLowerCase().equals("y")) {
				return true;
			}
			if (input.trim().toLowerCase().equals("n")) {
				return false;
			}
			System.out.println("Merci d'entrer Y ou N");
		}
	}

	public static long readPositiveLong(Scanner sc, String label) {
		long res;
		while (true) {
			res = readLong(sc, label);
			if (res >= 0)
				break;
			System.out.println("Merci d'entrer un nombre strictement positif");
		}
		return res;
	}

	public static long readBoundedLong(Scanner sc, String label, int min, int max) {
		long res;
		while (true) {
			res = readLong(sc, label);
			if (res >= min && res <= max)
				break;
			System.out.println("Merci d'entrer un nombre entre " + min + " et " + max);
		}
		return res;
	}
}
