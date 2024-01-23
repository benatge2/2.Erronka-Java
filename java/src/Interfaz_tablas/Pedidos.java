package Interfaz_tablas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Pedidos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtNIF;
	private JButton btnExit;
	private JButton btnGetData;
	private JButton btnVer_tabla;
	private JTextField txtPedido;
	private JButton btnGetData2;
	private JButton btnVer_tabla_1;
	private JButton btnEntregado;
	private JButton btnEnviado;
	private JButton btnPreparado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedidos frame = new Pedidos(true);
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
	public Pedidos(boolean admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 66, 792, 498);
		contentPane.add(scrollPane);


		table = new JTable();
		scrollPane.setViewportView(table);
		tabla.tabla.actualizartabla("pedidos", table);

		JButton Update = new JButton("Actualizar");
		Update.setBounds(10, 23, 106, 23);
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.tabla.actualizartabla("pedidos", table);
			}
		});
		Update.setFont(new Font("Times New Roman", Font.BOLD, 15));
		contentPane.add(Update);

		txtNIF = new JTextField();
		txtNIF.setBounds(900, 80, 300, 60);
		txtNIF.setHorizontalAlignment(SwingConstants.CENTER);
		txtNIF.setFont(new Font("Times New Roman", Font.BOLD, 30));
		contentPane.add(txtNIF);
		txtNIF.setColumns(10);

		btnExit = new JButton("Atzera");
		btnExit.setBounds(1100, 635, 150, 30);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz.Usuarios nF = new Interfaz.Usuarios(admin);
				nF.setVisible(true);
				setVisible(false); // set invisible
				dispose();// destroy
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btnExit);

		btnGetData = new JButton("Get DNI");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String dato = String.valueOf(table.getValueAt(fila, 2));
				if (String.valueOf(dato) != null) {
					txtNIF.setText(String.valueOf(dato));
				}
			}
		});
		btnGetData.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnGetData.setBounds(965, 151, 170, 35);
		contentPane.add(btnGetData);

		btnVer_tabla = new JButton("Ver pedidos");
		btnVer_tabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.tabla.actualizartabla("pedidos", table,"idusuario",txtNIF.getText());
			}
		});
		btnVer_tabla.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnVer_tabla.setBounds(965, 209, 170, 35);
		contentPane.add(btnVer_tabla);

		txtPedido = new JTextField();
		txtPedido.setHorizontalAlignment(SwingConstants.CENTER);
		txtPedido.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtPedido.setColumns(10);
		txtPedido.setBounds(900, 259, 300, 60);
		contentPane.add(txtPedido);

		btnGetData2 = new JButton("Get nPedido");
		btnGetData2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				String dato = String.valueOf(table.getValueAt(fila, 1));
				if (String.valueOf(dato) != null) {
					txtPedido.setText(String.valueOf(dato));
				}
			}
		});
		btnGetData2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnGetData2.setBounds(965, 339, 170, 35);
		contentPane.add(btnGetData2);

		btnVer_tabla_1 = new JButton("Ver pedido");
		btnVer_tabla_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.pedidos_clientes nF = new Interfaz_tablas.pedidos_clientes(Integer.valueOf(txtPedido.getText()),admin);
				nF.setVisible(true);
				setVisible(false); // set invisible
				dispose();// destroy
			}
		});
		btnVer_tabla_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnVer_tabla_1.setBounds(965, 404, 170, 35);
		contentPane.add(btnVer_tabla_1);

		btnEntregado = new JButton("Entregado");
		btnEntregado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.modify.update("pedidos", "estado", "npedido", Integer.valueOf(txtPedido.getText()), "Entregado");
				tabla.tabla.actualizartabla("pedidos", table);
			}
		});
		btnEntregado.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEntregado.setBounds(560, 588, 170, 35);
		contentPane.add(btnEntregado);

		btnEnviado = new JButton("Envidado");
		btnEnviado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.modify.update("pedidos", "estado", "npedido", Integer.valueOf(txtPedido.getText()), "Envidado");
				tabla.tabla.actualizartabla("pedidos", table);
			}
		});
		btnEnviado.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEnviado.setBounds(360, 588, 170, 35);
		contentPane.add(btnEnviado);

		btnPreparado = new JButton("Preparado");
		btnPreparado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabla.modify.update("pedidos", "estado", "npedido", Integer.valueOf(txtPedido.getText()), "Preparado");
				tabla.tabla.actualizartabla("pedidos", table);
			}
		});
		btnPreparado.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnPreparado.setBounds(160, 588, 170, 35);
		contentPane.add(btnPreparado);
	}
}
