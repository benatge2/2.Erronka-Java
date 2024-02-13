package Interfaz_tablas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

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
	private JTextField txtaux;
	private JTextField txtID;
	private static String dato=null;
	public static ResultSet SQLResult = null;
	private static String ID;
	private JTextField txtkarakteristika;
	private JButton btnDelete;

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
		SQLResult = tablas.tabla.actualizartabla("karakteristika",table,"idprodukto",String.valueOf(idprodukto));

		txtID = new JTextField();
		txtID.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtID.setColumns(10);
		txtID.setBounds(0,0,0,0);
		contentPane.add(txtID);

		TFInsert = new JTextField();
		TFInsert.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		TFInsert.setBounds(523, 49, 169, 36);
		contentPane.add(TFInsert);
		TFInsert.setColumns(10);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.update("karakteristika", "karakteristika1", "id", ID, TFInsert.getText());
				SQLResult = tablas.tabla.actualizartabla("karakteristika",table,"idprodukto",String.valueOf(idprodukto));
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnUpdate.setBounds(708, 49, 102, 36);
		contentPane.add(btnUpdate);

		JLabel lblNewLabel = new JLabel("VALOR:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(572, 23, 80, 24);
		contentPane.add(lblNewLabel);

		txtaux = new JTextField();
		txtaux.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtaux.setColumns(10);
		txtaux.setBounds(0,0,0,0);
		contentPane.add(txtaux);

		txtkarakteristika = new JTextField();
		txtkarakteristika.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtkarakteristika.setBounds(523, 108, 169, 36);
		contentPane.add(txtkarakteristika);
		txtkarakteristika.setColumns(10);

		JButton btnGehitu = new JButton("Gehitu");
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablas.modify.insertar_caracteristica(txtkarakteristika.getText(),String.valueOf(idprodukto))) {
					SQLResult = tablas.tabla.actualizartabla("karakteristika",table,"idprodukto",String.valueOf(idprodukto));
					System.out.println("Karakteristika gehitua..");
				}
			}
		});
		btnGehitu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnGehitu.setBounds(708, 105, 102, 39);
		contentPane.add(btnGehitu);

		btnDelete = new JButton("DETELE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.delete("karakteristika", "id", txtID.getText());
				SQLResult = tablas.tabla.actualizartabla("karakteristika",table,"idprodukto",String.valueOf(idprodukto));
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnDelete.setBounds(708, 8, 102, 39);
		contentPane.add(btnDelete);

		table.addMouseListener( new MouseAdapter()
		{
		    public void mousePressed(MouseEvent e)
		    {
		    	funtzioak.get_column(e,txtaux, SQLResult);
		    	String aux = txtaux.getText();
		    	if(!aux.equals("id")) {
		    		funtzioak.get_data(e, TFInsert, SQLResult);
		    		funtzioak.get_id(e, txtID, SQLResult);
		    		TFInsert.setEditable(true);
		    		ID = txtID.getText();
		    	}else {
		    		TFInsert.setText(" ");
		    		TFInsert.setEditable(false);
		    	}
		    }
		});
	}
}
