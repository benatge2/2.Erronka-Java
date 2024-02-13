package Interfaz_tablas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Langileak extends JFrame {

	private static final long serialVersionUID = 1L;
	private static String dato = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtColumna;
	private JTextField txtValor;
	private JTextField TFInsert;
	private JTextField txtID;
	public static ResultSet SQLResult = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Langileak frame = new Langileak(true);
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
	public Langileak(boolean admin) {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 63, 792, 498);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		SQLResult = tablas.tabla.actualizartabla("langileak", table);

		JButton btnExit = new JButton("Atzera");
		btnExit.setBounds(1062, 627, 156, 43);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz.Usuarios nF = new Interfaz.Usuarios(admin);
				nF.setVisible(true);
				setVisible(false); // set invisible
				dispose();// destroy
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(btnExit);

		txtColumna = new JTextField();
		txtColumna.setBounds(895, 361, 150, 35);
		txtColumna.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtColumna.setColumns(10);
		contentPane.add(txtColumna);

		JLabel lblNewLabel_1 = new JLabel("Zutabea:");
		lblNewLabel_1.setBounds(903, 329, 128, 30);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblNewLabel_1);

		JButton btnBilatu = new JButton("BILATU");
		btnBilatu.setBounds(981, 407, 180, 35);
		btnBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLResult = tablas.tabla.actualizartabla("langileak",table,String.valueOf(txtColumna.getText()),String.valueOf(txtValor.getText()));
			}
		});
		btnBilatu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btnBilatu);

		txtValor = new JTextField();
		txtValor.setBounds(1104, 361, 150, 35);
		txtValor.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtValor.setColumns(10);
		contentPane.add(txtValor);

		JLabel lblNewLabel_1_1 = new JLabel("Balorea:");
		lblNewLabel_1_1.setBounds(1112, 329, 128, 30);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblNewLabel_1_1);

		txtID = new JTextField();
		txtID.setBounds(992, 204, 150, 35);
		txtID.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtID.setColumns(10);
		contentPane.add(txtID);

		JLabel lblNewLabel = new JLabel("NAN:");
		lblNewLabel.setBounds(900, 210, 100, 25);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		JButton btnEzabatu = new JButton("EZABATU");
		btnEzabatu.setBounds(1150, 204, 110, 35);
		btnEzabatu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPane.add(btnEzabatu);
		btnEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.delete("langileak", "NAN", txtID.getText());
				SQLResult = tablas.tabla.actualizartabla("langileak", table);
			}
		});

		TFInsert = new JTextField();
		TFInsert.setFont(new Font("Times New Roman", Font.BOLD, 20));
		TFInsert.setBounds(992, 250, 150, 36);
		contentPane.add(TFInsert);
		TFInsert.setColumns(10);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblValor.setBounds(895, 255, 100, 25);
		contentPane.add(lblValor);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnUpdate.setBounds(1150, 250, 110, 35);
		contentPane.add(btnUpdate);

		JButton Update = new JButton("Actualizar");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLResult = tablas.tabla.actualizartabla("langileak", table);
			}
		});
		Update.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Update.setBounds(10, 11, 106, 23);
		contentPane.add(Update);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.LangileAgregar nF = new Interfaz_tablas.LangileAgregar();
				nF.setVisible(true);
			}
		});
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAgregar.setBounds(76, 605, 111, 30);
		contentPane.add(btnAgregar);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Update");
					tablas.modify.update("langileak", txtColumna.getText(), "NAN", txtID.getText(),
							TFInsert.getText());
					// String tabla, String columna , String columnaid, string id, String valor
					SQLResult = tablas.tabla.actualizartabla("langileak", table);
					// tabla.tabla.actualizartabla("konponenteak", table);
					btnUpdate.removeActionListener(null);

				}
		});

		table.addMouseListener( new MouseAdapter(){
		    public void mousePressed(MouseEvent e)
		    {
		    	funtzioak.get_column(e,txtColumna, SQLResult);
		        String aux = txtColumna.getText();
		        if(!aux.equals("NAN")) {
		    		funtzioak.get_data(e, TFInsert, SQLResult);
		    		TFInsert.setEditable(true);
		    		dato = TFInsert.getText();
		    	}else {
		    		TFInsert.setText(" ");
		    		TFInsert.setEditable(false);
		    	}
		        funtzioak.get_id(e, txtID, SQLResult);
		    }
		});
	}
}
