package Interfaz_tablas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pedidos_clientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton Update;
	private JButton btnExit;
	private JButton btnCrearFactura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pedidos_clientes frame = new pedidos_clientes(10, true);
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
	public pedidos_clientes(int npedido,boolean admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 42, 750, 500);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		tablas.tabla.Select(table,String.valueOf(npedido));

		Update = new JButton("Actualizar");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.tabla.Select(table,"SELECT t1.nPedido, t1.nProducto, t1.cantidad, t2.modelo, t2.prezioa FROM pedidos_producto t1 JOIN konponenteak t2 ON t1.nProducto = t2.idprodukto WHERE t1.nPedido = " + npedido);
			}
		});
		Update.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Update.setBounds(10, 11, 106, 23);
		contentPane.add(Update);

		btnExit = new JButton("Atzera");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.Pedidos nF = new Interfaz_tablas.Pedidos(admin);
				nF.setVisible(true);
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnExit.setBounds(995, 629, 150, 30);
		contentPane.add(btnExit);

		btnCrearFactura = new JButton("CREAR FACTURA");
		btnCrearFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				factura.generador.crear_factura(npedido);;
			}
		});
		btnCrearFactura.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCrearFactura.setBounds(957, 83, 200, 40);
		contentPane.add(btnCrearFactura);
	}

}
