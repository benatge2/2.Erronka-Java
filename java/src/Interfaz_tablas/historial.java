package Interfaz_tablas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.EventQueue;

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

public class historial extends JFrame {

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
					historial frame = new historial(true,"1");
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
	public historial(boolean admin,String idproducto) {
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 944, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 63, 792, 498);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		SQLResult = tablas.tabla.actualizartabla("historial", table, "idproducto", idproducto);

		JButton Update = new JButton("Actualizar");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLResult = tablas.tabla.actualizartabla("historial", table, "idproducto", idproducto);
			}
		});
		Update.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Update.setBounds(10, 11, 106, 23);
		contentPane.add(Update);
	}
}

