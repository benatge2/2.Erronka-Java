package Interfaz_tablas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Main.DB_conectable;
import producto.producto;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class LangileAgregar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNAN;
	private JTextField txtIzena;
	private JTextField txtAbizena;
	private JTextField txtMail;
	private JTextField txtDate;
	private JTextField txtTlf;
	private JTextField txtHerria;
	private JTextField txtPais;
	private JTextField txtKP;
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LangileAgregar frame = new LangileAgregar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean agregar(String NAN, String Izena, String Abizena, String Mail, String Date, String Tlf, String Herria,
			String Pais, String KP, String Contraseña) {
		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO langileak (NAN,Izena,Abizena,Korreoa,Jaiotze_data,telefonozenbakia,Herria,Herrialdea,KodigoPostala,Admin,Contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, NAN);
			stmt.setString(2, Izena);
			stmt.setString(3, Abizena);
			stmt.setString(4, Mail);
			stmt.setString(5, Date);
			stmt.setString(6, Tlf);
			stmt.setString(7, Herria);
			stmt.setString(8, Pais);
			stmt.setString(9, KP);
			stmt.setString(10, "0");
			stmt.setString(11, Contraseña);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Create the frame.
	 */
	public LangileAgregar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 906, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("NAN:*");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 91, 26);
		contentPane.add(lblNewLabel);

		JLabel lblIzena = new JLabel("IZENA:*");
		lblIzena.setHorizontalAlignment(SwingConstants.CENTER);
		lblIzena.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblIzena.setBounds(219, 11, 91, 26);
		contentPane.add(lblIzena);

		JLabel lblAbizena = new JLabel("ABIZENA:*");
		lblAbizena.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbizena.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAbizena.setBounds(440, 11, 91, 26);
		contentPane.add(lblAbizena);

		JLabel lblMail = new JLabel("MAIL:*");
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblMail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMail.setBounds(656, 11, 91, 26);
		contentPane.add(lblMail);

		JLabel lblJaiotzedata = new JLabel("JAIOTZE-DATA:*");
		lblJaiotzedata.setHorizontalAlignment(SwingConstants.CENTER);
		lblJaiotzedata.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblJaiotzedata.setBounds(10, 107, 152, 26);
		contentPane.add(lblJaiotzedata);

		JLabel lblTlf = new JLabel("TLF:*");
		lblTlf.setHorizontalAlignment(SwingConstants.CENTER);
		lblTlf.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTlf.setBounds(172, 107, 152, 26);
		contentPane.add(lblTlf);

		JLabel lblHerria = new JLabel("HERRIA:*");
		lblHerria.setHorizontalAlignment(SwingConstants.CENTER);
		lblHerria.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblHerria.setBounds(406, 107, 152, 26);
		contentPane.add(lblHerria);

		JLabel lblHerrialdea = new JLabel("HERRIALDEA:*");
		lblHerrialdea.setHorizontalAlignment(SwingConstants.CENTER);
		lblHerrialdea.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblHerrialdea.setBounds(646, 107, 152, 26);
		contentPane.add(lblHerrialdea);

		JLabel lblKodePostal = new JLabel("KODE POSTAL:*");
		lblKodePostal.setHorizontalAlignment(SwingConstants.CENTER);
		lblKodePostal.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblKodePostal.setBounds(10, 219, 152, 26);
		contentPane.add(lblKodePostal);

		JLabel lblContrasea = new JLabel("CONTRASEÑA:*");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblContrasea.setBounds(219, 219, 152, 26);
		contentPane.add(lblContrasea);

		txtNAN = new JTextField();
		txtNAN.setBounds(20, 37, 142, 31);
		contentPane.add(txtNAN);
		txtNAN.setColumns(10);

		txtIzena = new JTextField();
		txtIzena.setBounds(219, 37, 142, 31);
		contentPane.add(txtIzena);
		txtIzena.setColumns(10);

		txtAbizena = new JTextField();
		txtAbizena.setColumns(10);
		txtAbizena.setBounds(436, 37, 142, 31);
		contentPane.add(txtAbizena);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(656, 42, 201, 31);
		contentPane.add(txtMail);

		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(20, 132, 162, 31);
		contentPane.add(txtDate);

		txtTlf = new JTextField();
		txtTlf.setColumns(10);
		txtTlf.setBounds(219, 132, 171, 31);
		contentPane.add(txtTlf);

		txtHerria = new JTextField();
		txtHerria.setColumns(10);
		txtHerria.setBounds(440, 132, 142, 31);
		contentPane.add(txtHerria);

		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(656, 132, 142, 31);
		contentPane.add(txtPais);

		txtKP = new JTextField();
		txtKP.setColumns(10);
		txtKP.setBounds(20, 249, 142, 31);
		contentPane.add(txtKP);

		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(219, 249, 171, 31);
		contentPane.add(txtPass);

		JButton btnAdd = new JButton("SORTU");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(agregar(txtNAN.getText(),txtIzena.getText(),txtAbizena.getText(),txtMail.getText(),txtDate.getText(),txtTlf.getText(),txtHerria.getText(),txtPais.getText(),txtKP.getText(),txtPass.getText())) {
					System.out.println("Usuario agregado correctamente...");
				}else {
					System.out.println("Error al agregar el usuario...");
				}
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdd.setBounds(440, 239, 152, 47);
		contentPane.add(btnAdd);
	}

}
