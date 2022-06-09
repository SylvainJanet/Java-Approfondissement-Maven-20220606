package o07.appli.frames;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classes.Product;
import o05.jdbc.tools.DbConnection;
import o06.productDao.ProductDao;
import o07.appli.App7;

public class GestionAdmin extends JDialog {

	private static final long serialVersionUID = 4079481141425869660L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JScrollPane panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAdmin frame = new GestionAdmin(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void updateJTable() {
		List<Product> lp = new ArrayList<Product>();
		try (Connection cnx = DbConnection.getConnection()) {
			ProductDao dao = new ProductDao();
			lp = dao.findAll(cnx);
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateJTable(lp);
	}

	public void updateJTableAfterSearch() {
		List<Product> lp = new ArrayList<Product>();
		try (Connection cnx = DbConnection.getConnection()) {
			ProductDao dao = new ProductDao();
			lp = dao.findByDescription(textField.getText(), cnx);
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateJTable(lp);
	}

	public void updateJTable(List<Product> lp) {
		// JTable : à mettre dans le ViewPort d'un JScrollPane
		// Première étape : modifier la propriété MODEL dans le design pour y mettre les
		// différents colonnes
		// Ensuite : y rajouter les données dans le premier du constructeur qui attend
		// un Object[][]

		// la JTable attend un tableau de tableau

		// data[i] représente la ième ligne
		// data[0][0] : id de la première ligne
		// data[0][1] : description de la première ligne
		// data[0][2] : prix de la première ligne
		String[] columns = { "Id", "Description", "Prix" };
		Object[][] data = new Object[lp.size()][3];
		int i = 0;
		for (Product p : lp) {
			data[i][0] = p.getId();
			data[i][1] = p.getDescription();
			data[i][2] = p.getPrice();
			i++;
		}
		table.setModel(new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = -1646094382640529576L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, String.class, Double.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public GestionAdmin(Frame f) {
		super(f);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		panel = new JScrollPane();
		panel.setBounds(10, 43, 289, 207);
		contentPanel.add(panel);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setRowHeight(20);
		table.setRowMargin(0);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setBounds(10, 11, 269, 185);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Id", "Description", "Price" }) {
			private static final long serialVersionUID = -1646094382640529576L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, String.class, Double.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setViewportView(table);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJTableAfterSearch();
			}
		});

		textField.setBounds(69, 12, 105, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setBounds(10, 14, 49, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Liste des produits");
		lblNewLabel_1.setBounds(184, 14, 115, 14);
		contentPanel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(309, 11, 115, 239);
		contentPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 50, 50));

		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct frame = new AddProduct(GestionAdmin.this);
				frame.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(GestionAdmin.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Product prodSelect = new Product();
					prodSelect.setId((Long) table.getValueAt(pos, 0));
					prodSelect.setDescription((String) table.getValueAt(pos, 1));
					prodSelect.setPrice((Double) table.getValueAt(pos, 2));
					EditProduct frame = new EditProduct(GestionAdmin.this, prodSelect);
					frame.setVisible(true);
				}
			}
		});
		panel_1.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(GestionAdmin.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					long idToDel = ((Long) table.getValueAt(pos, 0));
					int res = JOptionPane
							.showConfirmDialog(GestionAdmin.this,
									"Etes vous sur de vouloir supprimer ce produit : '"
											+ (String) table.getValueAt(pos, 1) + "' ?",
									"Valider", JOptionPane.OK_OPTION);
					if (res == JOptionPane.OK_OPTION) {
						try (Connection cnx = DbConnection.getConnection()) {
							ProductDao dao = new ProductDao();
							dao.delete(idToDel, cnx);
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						updateJTable();
					}
				}
			}
		});
		panel_1.add(btnNewButton_2);

		updateJTable();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					((App7) GestionAdmin.this.getOwner()).refreshProductList();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	}
}
