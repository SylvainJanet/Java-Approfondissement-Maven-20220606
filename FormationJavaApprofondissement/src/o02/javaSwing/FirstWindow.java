package o02.javaSwing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FirstWindow extends JFrame {

	private static final long serialVersionUID = 7992017263157846988L;

	public FirstWindow() {
		super("Titre de la fenêtre");

		setVisible(true); // par défaut, visible est à false et rien n'est affiché
		setSize(800, 600);
		// concept : conteneur

		/*
		 * 
		 * Un conteneur est un objet qui permet de regrouper différents composants
		 * graphiques Le plus simple : JPanel Il en existe : - un qui permet de rajouter
		 * des barres de défilement - un qui permet d'utiliser un système d'onglets - un
		 * qui permet de regrouper 2 composant en laissant possibilité à l'utilisateur
		 * de modifier leur taille ...
		 * 
		 */

		JPanel panel = new JPanel();
		panel.setLayout(null); // les layout sont des gestionnaires de positionnement
		// null est équivalent à AbsoluteLayout, qui correspond à un placement par
		// coordonnées

		// concept : composant
		// composant : un élément graphique qui compose notre application
		// par exemple :
		// JLabel : un label, du texte
		// JButton : un bouton

		JLabel lblPressButton = new JLabel("Appuyez sur le bouton");
		lblPressButton.setForeground(Color.CYAN);
		lblPressButton.setBounds(111, 240, 143, 65);

		JButton button = new JButton();
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(433, 161, 213, 144);
		button.setText("Cliquez ici");

		panel.add(lblPressButton);
		panel.add(button);

		setContentPane(panel);
	}

	public static void main(String[] args) {
		FirstWindow.setDefaultLookAndFeelDecorated(true);
		/*
		 * 
		 * Swing c'est une API incluse dans Java qui permet des interface graphiques
		 * 
		 * Dans un premier temps, Java proposait l'API AWT, dans le package java.awt
		 * 
		 * AWT utilisait beacoup de fonctions de l'OS pour générer l'afficher ->
		 * problème de dépendance à l'OS, surtout valable pour la taille et le
		 * positionnement des éléments
		 * 
		 * Swing a été conçu pour palier à ce problème en étant écrite entièrement en
		 * java et en utilisant au minimum les fonctions de l'OS Quelques fenêtres et
		 * boîtes de dialogues sont en relation avec l'OS
		 * 
		 * Tous les éléments swing font partie d'un package : javax.swing Certains
		 * éléments de AWT sont encore utilisés
		 * 
		 * La classe de base d'une application est la classe JFrame. (en réalité il y a
		 * aussi JWindow et JApplet pour les webapp)
		 * 
		 */

		@SuppressWarnings("unused")
		FirstWindow firstWindow = new FirstWindow();

	}
}
