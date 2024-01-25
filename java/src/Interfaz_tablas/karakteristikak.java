package Interfaz_tablas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class karakteristikak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField TFInsert;
	private JTextField txtID;
	private static String dato=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					karakteristikak frame = new karakteristikak(true,1);
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
	public karakteristikak(boolean admin, int idprodukto) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 831, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 490, 385);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		tablas.tabla.actualizartabla("karakteristika",table,"idprodukto",String.valueOf(idprodukto));

		TFInsert = new JTextField();
		TFInsert.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		TFInsert.setBounds(970, 67, 150, 36);
		contentPane.add(TFInsert);
		TFInsert.setColumns(10);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnUpdate.setBounds(713, 49, 102, 36);
		contentPane.add(btnUpdate);

		JButton btnGet = new JButton("GET DATA");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.funtzioak.load_data(TFInsert, table, btnUpdate,"karakteristika","id",2);
				int fila = table.getSelectedRow();
				dato = String.valueOf(table.getValueAt(fila, 0));
				System.out.println(dato);
				txtID.setText(String.valueOf(dato));
			}
		});

		btnGet.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnGet.setBounds(509, 49, 102, 36);
		contentPane.add(btnGet);
	}
}
