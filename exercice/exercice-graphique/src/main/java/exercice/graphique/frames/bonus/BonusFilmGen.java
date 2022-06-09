package exercice.graphique.frames.bonus;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import exercice.dao.bonus.FilmDaoWithGen;
import exercice.graphique.App;
import exercice.metier.bonus.FilmServiceWithGen;
import exercice.metier.bonus.interfaces.IFilmServiceWithGen;
import exercice.modele.Film;

public class BonusFilmGen extends JDialog {

	private static final long serialVersionUID = 3636850451656908615L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane panel;
	private IFilmServiceWithGen filmService = new FilmServiceWithGen(new FilmDaoWithGen());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BonusFilmGen dialog = new BonusFilmGen(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshFilmList() {
		refreshFilmList(filmService.findAll());
	}

	public void refreshFilmList(List<Film> l) {
		String[] columns = { "Id", "Titre", "Vu", "Rating" };
		Object[][] data = new Object[l.size()][4];
		int i = 0;
		for (Film p : l) {
			data[i][0] = p.getId();
			data[i][1] = p.getTitle();
			data[i][2] = p.isWatched();
			data[i][3] = p.getRating();
			i++;
		}
		table.setModel(new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = -1646094382640529576L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, String.class, Boolean.class, Integer.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(25);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
	}

	/**
	 * Create the dialog.
	 */
	public BonusFilmGen(Frame f) {
		super(f);
		setTitle("Bonus - Film générique");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 616, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JScrollPane();
		panel.setBounds(10, 11, 376, 217);
		contentPane.add(panel);

		table = new JTable();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(396, 11, 194, 217);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 25, 25));

		JButton btnNewButton = new JButton("Ajouter film");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFilmGen frame = new AddFilmGen(BonusFilmGen.this);
				frame.setVisible(true);
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Modifier film");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(BonusFilmGen.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Film filmSelect = new Film();
					filmSelect.setId((Long) table.getValueAt(pos, 0));
					filmSelect.setTitle((String) table.getValueAt(pos, 1));
					filmSelect.setWatched((Boolean) table.getValueAt(pos, 2));
					filmSelect.setRating((Integer) table.getValueAt(pos, 3));
					EditFilmGen frame = new EditFilmGen(BonusFilmGen.this, filmSelect);
					frame.setVisible(true);
				}

			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Supprimer film");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(BonusFilmGen.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					long idToDel = ((Long) table.getValueAt(pos, 0));
					int res = JOptionPane
							.showConfirmDialog(
									BonusFilmGen.this, "Etes vous sur de vouloir supprimer ce film : '"
											+ (String) table.getValueAt(pos, 1) + "' ?",
									"Valider", JOptionPane.OK_OPTION);
					if (res == JOptionPane.OK_OPTION) {
						filmService.delete(idToDel);
						refreshFilmList();
					}
				}
			}
		});
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("J'ai vu ce film");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(BonusFilmGen.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					long idWatched = ((Long) table.getValueAt(pos, 0));
					String ratingStr = JOptionPane.showInputDialog(BonusFilmGen.this, "Note ?");
					try {
						int rating = Integer.parseInt(ratingStr);
						filmService.watched(idWatched, rating);
						refreshFilmList();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(BonusFilmGen.this, "La note doit être un nombre entier !",
								"Erreur !", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		panel.add(btnNewButton_3);

		refreshFilmList();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					((App) BonusFilmGen.this.getOwner()).refreshFilmList();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
