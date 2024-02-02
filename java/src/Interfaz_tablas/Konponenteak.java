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
import javax.swing.SwingConstants;

public class Konponenteak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TFInsert;
	private JTextField txtID;
	private static String dato=null;
	private JTextField txtColumna;
	private JTextField txtValor;

	private static ActionListener myActionListener = null;

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
		tablas.tabla.actualizarcomponente(table,null,null);
		//tabla.tabla.actualizartabla("konponenteak", table);

		JButton Update = new JButton("Actualizar");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.tabla.actualizarcomponente(table,null,null);
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
				tablas.modify.delete("konponenteak", "idprodukto", dato);
				tablas.modify.delete("karakteristika", "idprodukto", dato);
				tablas.tabla.actualizarcomponente(table,txtColumna.getText(),txtValor.getText());
			}
		});
		btnEzabatu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnEzabatu.setBounds(1126, 133, 128, 35);
		contentPane.add(btnEzabatu);

		JButton btnKarakteristika = new JButton("CARACTERISTICAS");
		btnKarakteristika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				karakteristikak nF = new karakteristikak(admin,Integer.valueOf(dato));
				nF.setVisible(true);
			}
		});
		btnKarakteristika.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnKarakteristika.setBounds(951, 179, 180, 35);
		contentPane.add(btnKarakteristika);

		txtColumna = new JTextField();
		txtColumna.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtColumna.setColumns(10);
		txtColumna.setBounds(855, 291, 150, 35);
		contentPane.add(txtColumna);

		txtValor = new JTextField();
		txtValor.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtValor.setColumns(10);
		txtValor.setBounds(1064, 291, 150, 35);
		contentPane.add(txtValor);

		JLabel lblNewLabel_1 = new JLabel("Zutabea:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(863, 259, 128, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Balorea:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(1072, 259, 128, 30);
		contentPane.add(lblNewLabel_1_1);

		JButton btnBilatu = new JButton("BILATU");
		btnBilatu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBilatu.setBounds(941, 337, 180, 35);
		contentPane.add(btnBilatu);

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
			//btnUpdate.addActionListener(myActionListener);


			JButton btnGet = new JButton("GET DATA");
			btnGet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Interfaz_tablas.funtzioak.load_data(TFInsert, table,"konponenteak","idprodukto",0);
					int fila = table.getSelectedRow();
					dato = String.valueOf(table.getValueAt(fila, 0));
					System.out.println(dato);
					txtID.setText(String.valueOf(dato));
				}
			});

			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String valores[] = Interfaz_tablas.funtzioak.load_data(TFInsert, table, "konponenteak","idprodukto",0);
					System.out.println("Update");
					if(valores[0] != "idprodukto") {
						tablas.modify.update("konponenteak", valores[0], "idprodukto", Integer.valueOf(valores[1]), TFInsert.getText());
						// String tabla, String columna , String columnaid, int id, String valor
						tablas.tabla.actualizarcomponente(table,null,null);
						//tabla.tabla.actualizartabla("konponenteak", table);
						// Después de que se ha ejecutado el código, puedes eliminar el ActionListener
						//btnUpdate.removeActionListener(myActionListener);
						btnUpdate.removeActionListener(null);

					}
				}
			});

			btnGet.setFont(new Font("Times New Roman", Font.BOLD, 15));
			btnGet.setBounds(825, 67, 120, 36);
			contentPane.add(btnGet);
		}

	}
}
