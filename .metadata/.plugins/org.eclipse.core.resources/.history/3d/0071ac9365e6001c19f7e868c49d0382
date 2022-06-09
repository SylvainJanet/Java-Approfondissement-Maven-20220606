package o07.appli;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import classes.Cart;
import classes.CartLine;
import classes.Product;
import o05.jdbc.tools.DbConnection;
import o06.productDao.ProductDao;
import o07.appli.frames.GestionAdmin;

public class App7 extends JFrame {

	private static final long serialVersionUID = 2650007083401571064L;
	private JPanel contentPane;
	private JTextField textField;
	private Cart myCart = new Cart();
	private JLabel lblNewLabel_2;
	private JList<Product> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App7 frame = new App7();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void refreshCartCount() {
		String text = null;
		int count = myCart.count();
		if (count == 0) {
			text = "Votre panier est vide";
		} else {
			text = "Votre panier contient ";
			text += count;
			text += count == 1 ? " produit" : " produits";
		}
		lblNewLabel_2.setText(text);
	}

	private void addProduct() {
		if (list.getSelectedIndex() != -1) {
			String qtyStr = JOptionPane.showInputDialog(this, "Combien voulez vous ajouter ?",
					"Nombre de produit à ajouter");
			try {
				int qty = Integer.parseInt(qtyStr);
				Product pToAdd = list.getSelectedValue();
				myCart.addLine(new CartLine(pToAdd, qty));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void removeProduct() {
		if (list.getSelectedIndex() != -1) {
			Product pToRemove = list.getSelectedValue();
			if (myCart.getLines().contains(new CartLine(pToRemove, 1))) {
				String qtyStr = JOptionPane.showInputDialog(this, "Combien voulez vous retirer ?",
						"Nombre de produit à retirer");
				try {
					int qty = Integer.parseInt(qtyStr);
					myCart.removeLine(new CartLine(pToRemove, qty));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Le produit n'est pas dans la liste !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void displayCart() {
		if (myCart.count() == 0) {
			JOptionPane.showMessageDialog(this, "Votre panier est vide !");
		} else {
			// JOptionPane.showMessageDialog(ListeCourse.this,
			// myCart.toString().replace("\t", " "));
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<table><tr><th>Contenu du panier</th><th></th><th></th></tr>");
			sb.append("<tr><td>" + "Description" + "</td><td>" + "Quantité" + "</td><td>" + "Prix" + "</td></tr>");
			for (CartLine l : myCart.getLines()) {
				sb.append("<tr><td>" + l.getProduct().getDescription() + "</td><td>" + l.getQty() + "</td><td>"
						+ l.getTotal() + "</td></tr>");
			}
			sb.append("</table>");
			sb.append("<p> -- TOTAL PANIER -- ");
			sb.append(myCart.getTotal());
			sb.append("</p>");
			sb.append("</html>");
			JOptionPane.showMessageDialog(this, sb.toString());
		}
	}

	public void refreshProductList() {
		try (Connection cnx = DbConnection.getConnection()) {
			ProductDao dao = new ProductDao();
			List<Product> products = dao.findAll(cnx);
			list.setListData(products.toArray(new Product[products.size()]));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refreshProductListAfterSearch() {
		try (Connection cnx = DbConnection.getConnection()) {
			ProductDao dao = new ProductDao();
			List<Product> products = dao.findByDescription(textField.getText(), cnx);
			list.setListData(products.toArray(new Product[products.size()]));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public App7() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Gestion des produits");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPasswordField pwd = new JPasswordField(10);
				int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
				// le mot de passe est codé en dur ici
				// à ne pas faire dans un vrai programme
				// pour des raisons de sécurité
				if (action >= 0 && pwd.getPassword() != null && new String(pwd.getPassword()).equals("admin")) {
					GestionAdmin frame = new GestionAdmin(App7.this);
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(App7.this, "Mot de passe incorrect", "Erreur !",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Quitter");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App7.this.dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(307, 11, 117, 217);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 50, 50));

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct();
				refreshCartCount();
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Retirer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeProduct();
				refreshCartCount();
			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Afficher");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCart();
			}
		});
		panel.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("Search :");
		lblNewLabel.setBounds(10, 11, 50, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshProductListAfterSearch();
			}
		});
		textField.setBounds(70, 8, 100, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Liste des produits");
		lblNewLabel_1.setBounds(180, 11, 117, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Votre panier est vide");
		lblNewLabel_2.setBounds(10, 214, 196, 14);
		contentPane.add(lblNewLabel_2);

		list = new JList<Product>();
		list.setBounds(20, 36, 247, 157);
		contentPane.add(list);
		refreshProductList();
	}

}
