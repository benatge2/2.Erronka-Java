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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
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
	private JButton btnVer_tabla_1;
	private JButton btnEntregado;
	private JButton btnEnviado;
	private JButton btnPreparado;
	public static ResultSet SQLResult = null;

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
		SQLResult = tablas.tabla.actualizartabla("pedidos", table);

		table.addMouseListener( new MouseAdapter()
		{
		    public void mousePressed(MouseEvent e)
		    {
		        funtzioak.get_data(e, txtNIF, SQLResult);
		        funtzioak.get_id(e, txtPedido, SQLResult);
		    }
		});

		JButton Update = new JButton("Actualizar");
		Update.setBounds(10, 23, 106, 23);
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.tabla.actualizartabla("pedidos", table);
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


		btnVer_tabla = new JButton("Ver pedidos");
		btnVer_tabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLResult = tablas.tabla.actualizartabla("pedidos", table,"idusuario",txtNIF.getText());
			}
		});
		btnVer_tabla.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnVer_tabla.setBounds(965, 151, 170, 35);
		contentPane.add(btnVer_tabla);

		txtPedido = new JTextField();
		txtPedido.setHorizontalAlignment(SwingConstants.CENTER);
		txtPedido.setFont(new Font("Times New Roman", Font.BOLD, 30));
		txtPedido.setColumns(10);
		txtPedido.setBounds(900, 259, 300, 60);
		contentPane.add(txtPedido);

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
		btnVer_tabla_1.setBounds(965, 330, 170, 35);
		contentPane.add(btnVer_tabla_1);

		btnEntregado = new JButton("Entregado");
		btnEntregado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.update("pedidos", "estado", "npedido", txtPedido.getText(), "Entregado");
				tablas.tabla.actualizartabla("pedidos", table);
			}
		});
		btnEntregado.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEntregado.setBounds(560, 588, 170, 35);
		contentPane.add(btnEntregado);

		btnEnviado = new JButton("Envidado");
		btnEnviado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.update("pedidos", "estado", "npedido", txtPedido.getText(), "Envidado");
				tablas.tabla.actualizartabla("pedidos", table);
			}
		});
		btnEnviado.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEnviado.setBounds(360, 588, 170, 35);
		contentPane.add(btnEnviado);

		btnPreparado = new JButton("Preparado");
		btnPreparado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablas.modify.update("pedidos", "estado", "npedido",txtPedido.getText(), "Preparado");
				SQLResult = tablas.tabla.actualizartabla("pedidos", table);
			}
		});
		btnPreparado.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnPreparado.setBounds(160, 588, 170, 35);
		contentPane.add(btnPreparado);
	}
}
