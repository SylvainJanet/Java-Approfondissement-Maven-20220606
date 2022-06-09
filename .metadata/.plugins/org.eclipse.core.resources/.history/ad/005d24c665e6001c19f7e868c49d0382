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

public class AddProduct extends JDialog {

	private static final long serialVersionUID = -9042487130279640665L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct(null);
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
	public AddProduct(Dialog d) {
		super(d);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

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

		textField_1 = new JTextField();
		textField_1.setBounds(137, 131, 189, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct.this.dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection cnx = DbConnection.getConnection()) {
					Product pToAdd = new Product();
					pToAdd.setDescription(textField.getText());
					pToAdd.setPrice(Double.parseDouble(textField_1.getText()));
					ProductDao dao = new ProductDao();
					dao.insert(pToAdd, cnx);
				} catch (ClassNotFoundException | SQLException | IOException | NumberFormatException e1) {
					e1.printStackTrace();
				}
				try {
					((GestionAdmin) AddProduct.this.getOwner()).updateJTable();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				AddProduct.this.dispose();
			}
		});
		btnNewButton_1.setBounds(237, 227, 89, 23);
		getContentPane().add(btnNewButton_1);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}
