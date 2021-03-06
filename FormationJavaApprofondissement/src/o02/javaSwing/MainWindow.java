package o02.javaSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import o02.javaSwing.composants.Composants;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -8938139065169024806L;
	
	private JPanel contentPane;
	private JTextField txtFieldMessageToChild;
	private JLabel lblChildResponse = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Fichier");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Nouvelle fenêtre");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Composants frame = new Composants(MainWindow.this, txtFieldMessageToChild.getText());
				frame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Exemple");
		mnNewMenu.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Option 1");
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Option 2");
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Quitter");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this fait référence à l'instance de la classe anonyme implémentant
				// l'interface
				// ActionListener qu'on est en train de définir.
				// Pour faire référence au this de la classe MainWindow, il faut préfixer
				// MainWindow.this
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(MainWindow.this,
						"Etes vous sur de vouloir quitter ?", "Confirmer", JOptionPane.YES_NO_OPTION)) {
					MainWindow.this.dispose();
				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPressButton = new JLabel("Appuyez sur le bouton");
		lblPressButton.setBounds(64, 88, 132, 14);
		contentPane.add(lblPressButton);

		JButton btnDisplayMessage = new JButton("Cliquez ici");
		// ActionListener est une interface
		// pour créer un écouteur d'évenement qui doit l'implémenter, on devrait créer
		// une classe
		// implémenter la méthode, et l'instancier dans les lignes de code qui suivent
		// On peut utiliser une classe anonyme, où on décrit une classe sans la créer,
		// on créé directement
		// l'instance de la classe.
//		btnDisplayMessage.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				// code executé lorsque l'évenement se produit
//				// cf le design pattern Publisher/Listener
//				System.out.println("Le bouton a été pressé");
//			}
//		});

		// Lorsqu'on a une interface fonctionnelle (c'est à dire une interface qui ne
		// contient qu'une seule
		// signature de méthode) on peut également utiliser une fonction lambda :
		btnDisplayMessage.addActionListener((ActionEvent arg0) -> System.out.println("Le bouton a été pressé"));
		btnDisplayMessage.setBounds(206, 84, 89, 23);
		contentPane.add(btnDisplayMessage);
		
		txtFieldMessageToChild = new JTextField();
		txtFieldMessageToChild.setBounds(140, 151, 86, 20);
		contentPane.add(txtFieldMessageToChild);
		txtFieldMessageToChild.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Child result : ");
		lblNewLabel.setBounds(238, 26, 89, 14);
		contentPane.add(lblNewLabel);
		
		lblChildResponse.setBounds(337, 26, 46, 14);
		contentPane.add(lblChildResponse);
	}
	
	public void setChildMessage(String result) {
		this.lblChildResponse.setText(result);
	}
}
