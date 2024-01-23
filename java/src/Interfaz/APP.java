package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.*;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.net.URL;

public class APP extends JFrame {

	public static String pasahitza;
	public static String nan;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NAN;
	private JPasswordField PASS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APP frame = new APP();
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
	public APP() {

		//setIconImage(Toolkit.getDefaultToolkit().getImage(APP.class.getResource("/imagenes/logo2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("HASI SAIOA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel.setBounds(293, 127, 747, 220);
		contentPane.add(lblNewLabel);

		NAN = new JTextField();
		NAN.setFont(new Font("Tahoma", Font.PLAIN, 25));
		NAN.setBounds(493, 396, 350, 41);
		contentPane.add(NAN);
		NAN.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("NAN:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(594, 358, 150, 34);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("PASAHITZA:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(587, 454, 167, 34);
		contentPane.add(lblNewLabel_2);

		JButton LOGIN = new JButton("LOGIN");
		LOGIN.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				pasahitza = PASS.getText();
				nan = NAN.getText();
				int usuario = usuarios.sesion.check_pass(nan, pasahitza);
				if (usuario == 0) {
					showMessageDialog(null, "Error en las credenciales.");
				} else if (usuario == 1) {
					//USUARIO
					Interfaz.Usuarios nF = new Interfaz.Usuarios(false);
					nF.setVisible(true);
					setVisible(false); //set invisible
					dispose();//destroy
				} else if (usuario == 2) {
					//ADMIN
					Interfaz.Usuarios nF = new Interfaz.Usuarios(true);
					nF.setVisible(true);
					setVisible(false); //set invisible
					dispose();//destroy
				}
			}
		});
		LOGIN.setFont(new Font("Times New Roman", Font.BOLD, 25));
		LOGIN.setBounds(587, 551, 167, 34);
		contentPane.add(LOGIN);

		PASS = new JPasswordField();
		PASS.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		PASS.setBounds(493, 487, 350, 41);
		contentPane.add(PASS);
	}
}
