package o07.appli.frames;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import classes.Product;
import o05.jdbc.tools.DbConnection;
import o06.productDao.ProductDao;

public class EditProduct extends JDialog {

	private static final long serialVersionUID = 348206367244803081L;
	private final JPanel contentPanel = new JPanel();
	private Product pToEdit;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProduct frame = new EditProduct(null, new Product());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public EditProduct(Dialog d, Product p) {
		super(d);
		this.pToEdit = p;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(35, 69, 70, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(35, 134, 46, 14);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(137, 66, 189, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(pToEdit.getDescription());

		textField_1 = new JTextField();
		textField_1.setBounds(137, 131, 189, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(String.valueOf(pToEdit.getPrice()));

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProduct.this.dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection cnx = DbConnection.getConnection()) {
					pToEdit.setDescription(textField.getText());
					pToEdit.setPrice(Double.parseDouble(textField_1.getText()));
					ProductDao dao = new ProductDao();
					dao.update(pToEdit, cnx);
				} catch (ClassNotFoundException | NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
				((GestionAdmin) EditProduct.this.getOwner()).updateJTable();
				EditProduct.this.dispose();
			}
		});
		btnNewButton_1.setBounds(237, 227, 89, 23);
		getContentPane().add(btnNewButton_1);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

}
