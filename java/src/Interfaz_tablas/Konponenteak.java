package Interfaz_tablas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Konponenteak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
					Konponenteak frame = new Konponenteak(true);
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
	public Konponenteak(Boolean admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 792, 498);
		contentPane.add(scrollPane);

		DefaultTableModel modelo = new DefaultTableModel();
		JTable table = new JTable(modelo);
		scrollPane.setViewportView(table);
		tabla.tabla.actualizarcomponente(table);
		//tabla.tabla.actualizartabla("konponenteak", table);

		JButton Update = new JButton("Actualizar");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.tabla.actualizarcomponente(table);
				//tabla.tabla.actualizartabla("konponenteak", table);
			}
		});
		Update.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Update.setBounds(10, 11, 106, 23);
		contentPane.add(Update);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				producto.Agregar nF = new producto.Agregar(true,table);
				nF.setVisible(true);
			}
		});
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAgregar.setBounds(105, 562, 111, 30);
		contentPane.add(btnAgregar);

		JButton btnExit = new JButton("Atzera");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz.Usuarios nF = new Interfaz.Usuarios(admin);
				nF.setVisible(true);
				setVisible(false); // set invisible
				dispose();// destroy
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnExit.setBounds(1083, 627, 156, 43);
		contentPane.add(btnExit);

		txtID = new JTextField();
		txtID.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtID.setBounds(970, 133, 150, 35);
		contentPane.add(txtID);
		txtID.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(941, 133, 28, 35);
		contentPane.add(lblNewLabel);

		JButton btnEzabatu = new JButton("EZABATU");
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.modify.delete("konponenteak", "idproducto", dato);
				tabla.modify.delete("karakteristika", "idproducto", dato);
			}
		});
		btnEzabatu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnEzabatu.setBounds(1126, 133, 128, 35);
		contentPane.add(btnEzabatu);

		if (admin) {

			TFInsert = new JTextField();
			TFInsert.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			TFInsert.setBounds(970, 67, 150, 36);
			contentPane.add(TFInsert);
			TFInsert.setColumns(10);

			JButton btnUpdate = new JButton("UPDATE");
			btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
			btnUpdate.setBounds(1126, 67, 128, 35);
			contentPane.add(btnUpdate);

			JButton btnGet = new JButton("GET DATA");
			btnGet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Interfaz_tablas.funtzioak.load_data(TFInsert, table, btnUpdate);
					int fila = table.getSelectedRow();
					dato = String.valueOf(table.getValueAt(fila, 0));
					System.out.println(dato);
					txtID.setText(String.valueOf(dato));
				}
			});

			btnGet.setFont(new Font("Times New Roman", Font.BOLD, 15));
			btnGet.setBounds(825, 67, 120, 36);
			contentPane.add(btnGet);
		}

	}
}
