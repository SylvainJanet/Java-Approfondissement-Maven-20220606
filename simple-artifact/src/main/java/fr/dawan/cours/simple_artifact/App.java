package fr.dawan.cours.simple_artifact;

import ij.IJ;
import ij.ImagePlus;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ImagePlus imp = IJ.openImage();
		imp.show();
	}
}
