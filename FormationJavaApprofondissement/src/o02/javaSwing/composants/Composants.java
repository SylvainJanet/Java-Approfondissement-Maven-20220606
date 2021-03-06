package o02.javaSwing.composants;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import o02.javaSwing.MainWindow;

public class Composants extends JDialog {

	private static final long serialVersionUID = -243914923975293417L;
	
	private JPanel contentPane;
	private JTextField txtFieldNumber1;
	private JTextField txtFieldNumber2;
	private JLabel lblResult;
	private JLabel lblMessageReceived = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Composants frame = new Composants(null, "");
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
	public Composants(MainWindow parent, String message) {
		super(parent);
		
		lblMessageReceived.setText(message);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtFieldNumber1 = new JTextField();
		txtFieldNumber1.setBounds(10, 11, 86, 20);
		contentPane.add(txtFieldNumber1);
		txtFieldNumber1.setColumns(10);

		JLabel lblNewLabel = new JLabel("+");
		lblNewLabel.setBounds(106, 14, 17, 14);
		contentPane.add(lblNewLabel);

		txtFieldNumber2 = new JTextField();
		txtFieldNumber2.setBounds(126, 11, 86, 20);
		contentPane.add(txtFieldNumber2);
		txtFieldNumber2.setColumns(10);

		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nbrTxt1 = txtFieldNumber1.getText();
				String nbrTxt2 = txtFieldNumber2.getText();

				try {
					int nbr1 = Integer.parseInt(nbrTxt1);
					int nbr2 = Integer.parseInt(nbrTxt2);

					int result = nbr1 + nbr2;

					lblResult.setText(String.valueOf(result));
					parent.setChildMessage(String.valueOf(result));

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(Composants.this, "Merci d'entrer des nombres", "Erreur ! ",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCompute.setBounds(230, 10, 89, 23);
		contentPane.add(btnCompute);

		lblResult = new JLabel("");
		lblResult.setBounds(345, 14, 46, 14);
		contentPane.add(lblResult);

		JCheckBox checkboxDisplayState = new JCheckBox("D??coch??");
		checkboxDisplayState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSelected = checkboxDisplayState.isSelected();
				checkboxDisplayState.setText(isSelected ? "Coch??" : "D??coch??");
			}
		});
		checkboxDisplayState.setBounds(6, 107, 97, 23);
		contentPane.add(checkboxDisplayState);
		
		lblMessageReceived.setBounds(210, 111, 46, 14);
		contentPane.add(lblMessageReceived);
	}
}
