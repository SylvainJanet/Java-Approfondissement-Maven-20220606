package exercice.graphique.frames.bonus;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import exercice.dao.bonus.TaskDao;
import exercice.metier.bonus.TaskService;
import exercice.metier.bonus.interfaces.ITaskService;
import exercice.modele.Task;

public class Tasks extends JDialog {

	private static final long serialVersionUID = -1254563634055812746L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane panel;
	private ITaskService taskService = new TaskService(new TaskDao());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Tasks dialog = new Tasks(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshTaskList() {
		refreshTaskList(taskService.findAll());

	}

	public void refreshTaskList(List<Task> l) {
		String[] columns = { "Id", "Description", "Fait" };
		Object[][] data = new Object[l.size()][3];
		int i = 0;
		for (Task p : l) {
			data[i][0] = p.getId();
			data[i][1] = p.getDescription();
			data[i][2] = p.isDone();
			i++;
		}
		table.setModel(new DefaultTableModel(data, columns) {
			private static final long serialVersionUID = -1646094382640529576L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, String.class, Boolean.class };

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
	}

	/**
	 * Create the dialog.
	 */
	public Tasks(Frame f) {
		super(f);
		setTitle("Bonus - tâches");
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

		JButton btnNewButton = new JButton("Ajouter tâche");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTask frame = new AddTask(Tasks.this);
				frame.setVisible(true);
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Modifier tâche");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(Tasks.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Task taskSelect = new Task();
					taskSelect.setId((Long) table.getValueAt(pos, 0));
					taskSelect.setDescription((String) table.getValueAt(pos, 1));
					taskSelect.setDone((Boolean) table.getValueAt(pos, 2));
					EditTask frame = new EditTask(Tasks.this, taskSelect);
					frame.setVisible(true);
				}

			}
		});
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Supprimer tâche");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(Tasks.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					long idToDel = ((Long) table.getValueAt(pos, 0));
					int res = JOptionPane
							.showConfirmDialog(Tasks.this,
									"Etes vous sur de vouloir supprimer cette tâche : '"
											+ (String) table.getValueAt(pos, 1) + "' ?",
									"Valider", JOptionPane.OK_OPTION);
					if (res == JOptionPane.OK_OPTION) {
						taskService.delete(idToDel);
						refreshTaskList();
					}
				}
			}
		});
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("J'ai effectué cette tâche");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = table.getSelectedRow();
				if (pos == -1) {
					JOptionPane.showMessageDialog(Tasks.this, "Vous devez selectionner une ligne", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					long idDone = ((Long) table.getValueAt(pos, 0));
					taskService.done(idDone);
					refreshTaskList();

				}
			}
		});
		panel.add(btnNewButton_3);

		refreshTaskList();

	}
}
