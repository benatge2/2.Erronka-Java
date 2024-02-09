package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios frame = new Usuarios(true);
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
	public Usuarios(boolean admin) {
		LocalDate fecha = LocalDate.now();
		System.out.println(fecha);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz.APP nF = new Interfaz.APP();
				nF.setVisible(true);
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});

		JButton btnHornitzaileak = new JButton("Hornitzaileak");
		btnHornitzaileak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.hornitzaile nF = new Interfaz_tablas.hornitzaile(admin);
				nF.setVisible(true);
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});
		btnHornitzaileak.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnHornitzaileak.setBounds(79, 75, 261, 183);
		contentPane.add(btnHornitzaileak);

		JButton btnBezeroak = new JButton("Bezeroak");
		btnBezeroak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.bezeroak nF = new Interfaz_tablas.bezeroak(admin);
				nF.setVisible(true);
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});
		btnBezeroak.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnBezeroak.setBounds(512, 75, 261, 183);
		contentPane.add(btnBezeroak);

		JButton btnProduktuak = new JButton("Produktuak");
		btnProduktuak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.Konponenteak nF = new Interfaz_tablas.Konponenteak(admin);
				nF.setVisible(true);
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});
		btnProduktuak.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnProduktuak.setBounds(928, 75, 261, 183);
		contentPane.add(btnProduktuak);

		JButton btnEskaerak = new JButton("Eskaerak");
		btnEskaerak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_tablas.Pedidos nF = new Interfaz_tablas.Pedidos(admin);
				nF.setVisible(true);
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});
		btnEskaerak.setFont(new Font("Times New Roman", Font.BOLD, 38));
		btnEskaerak.setBounds(512, 431, 261, 183);
		contentPane.add(btnEskaerak);


		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnExit.setBounds(1123, 631, 131, 39);
		contentPane.add(btnExit);
		if(admin) {
			JButton btnLangileak = new JButton("Langileak");
			btnLangileak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Interfaz_tablas.Langileak nF = new Interfaz_tablas.Langileak(admin);
					nF.setVisible(true);
					setVisible(false); //set invisible
					dispose();//destroy
				}
			});
			btnLangileak.setFont(new Font("Times New Roman", Font.BOLD, 38));
			btnLangileak.setBounds(299, 431, 261, 183);
			contentPane.add(btnLangileak);

			//eskaeren botoia mugitu
			btnEskaerak.setBounds(737, 429, 261, 183);
		}
	}

}
