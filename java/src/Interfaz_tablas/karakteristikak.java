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
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class karakteristikak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField TFInsert;
	private JTextField txtID;
	private static String dato=null;
	private static String ID;
	private JTextField txtkarakteristika;

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
		setBounds(100, 100, 879, 471);
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
		TFInsert.setBounds(639, 49, 102, 36);
		contentPane.add(TFInsert);
		TFInsert.setColumns(10);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.update("karakteristika", "karakteristika1", "id", Integer.valueOf(ID), TFInsert.getText());
				tablas.tabla.actualizartabla("karakteristika",table,"idprodukto",String.valueOf(idprodukto));
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnUpdate.setBounds(751, 49, 102, 36);
		contentPane.add(btnUpdate);

		JButton btnGet = new JButton("GET DATA");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				int columna = table.getSelectedColumn();
				ID = String.valueOf(table.getValueAt(fila, 2));
				dato = String.valueOf(table.getValueAt(fila, columna));
				System.out.println(dato);
				TFInsert.setText(String.valueOf(dato));
			}
		});

		btnGet.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnGet.setBounds(512, 49, 117, 36);
		contentPane.add(btnGet);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(670, 23, 41, 24);
		contentPane.add(lblNewLabel);

		txtkarakteristika = new JTextField();
		txtkarakteristika.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtkarakteristika.setBounds(523, 108, 169, 36);
		contentPane.add(txtkarakteristika);
		txtkarakteristika.setColumns(10);

		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablas.modify.insertar_caracteristica(txtkarakteristika.getText(),ID)) {
					System.out.println("Karakteristika gehitua..");
				}
			}
		});
		btnGehitu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnGehitu.setBounds(708, 105, 102, 39);
		contentPane.add(btnGehitu);
	}
}
