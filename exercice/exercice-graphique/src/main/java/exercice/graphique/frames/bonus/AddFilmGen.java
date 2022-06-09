package exercice.graphique.frames.bonus;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import exercice.dao.bonus.FilmDaoWithGen;
import exercice.metier.bonus.FilmServiceWithGen;
import exercice.metier.bonus.interfaces.IFilmServiceWithGen;
import exercice.modele.Film;

public class AddFilmGen extends JDialog {

	private static final long serialVersionUID = -1412357309109631521L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	private IFilmServiceWithGen filmService = new FilmServiceWithGen(new FilmDaoWithGen());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddFilmGen dialog = new AddFilmGen(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddFilmGen(Dialog f) {
		super(f);
		setTitle("Ajout d'un film - BONUS générique");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 83, 300, 0 };
		gbl_contentPanel.rowHeights = new int[] { 46, 40, 46, 40, 46, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPanel.add(panel, gbc_panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Titre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 21, 58, 14);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPanel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 15, 280, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPanel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Vu");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 21, 58, 14);
		panel_2.add(lblNewLabel_1);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		contentPanel.add(panel_3, gbc_panel_3);
		panel_3.setLayout(null);

		JCheckBox chckbxNewCheckBox = new JCheckBox("");

		chckbxNewCheckBox.setBounds(6, 16, 97, 23);
		panel_3.add(chckbxNewCheckBox);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		contentPanel.add(panel_4, gbc_panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Note");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 21, 58, 14);
		panel_4.add(lblNewLabel_2);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 4;
		contentPanel.add(panel_5, gbc_panel_5);
		panel_5.setLayout(null);

		JSlider slider = new JSlider();
		slider.setEnabled(false);
		slider.setValue(5);
		slider.setMaximum(10);
		slider.setBounds(10, 11, 280, 26);
		slider.setPaintLabels(true);
		panel_5.add(slider);

		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					slider.setEnabled(true);
				} else {
					slider.setEnabled(false);
				}
			}
		});

		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for (int i = 0; i <= 10; i++) {
			labelTable.put(new Integer(i), new JLabel(String.valueOf(i)));
		}

		slider.setLabelTable(labelTable);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Film fToAdd = new Film();
						fToAdd.setTitle(textField.getText());
						fToAdd.setWatched(chckbxNewCheckBox.isSelected());
						if (chckbxNewCheckBox.isSelected()) {
							fToAdd.setRating(slider.getValue());
						}

						filmService.insert(fToAdd);
						try {
							((BonusFilmGen) AddFilmGen.this.getOwner()).refreshFilmList();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						AddFilmGen.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddFilmGen.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
