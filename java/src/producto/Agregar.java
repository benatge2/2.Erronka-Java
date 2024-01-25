package producto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Agregar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMota;
	private JLabel lblImagen;
	private JTextField txtImg;
	private JLabel lblModelo;
	private JTextField txtModelo;
	private JLabel lblMarka;
	private JTextField txtMarka;
	private JLabel lblDeskripzioa;
	private JLabel lblCantidad;
	private JTextField txtCant;
	private JLabel lblPrezioa;
	private JTextField txtPrezioa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Agregar frame = new Agregar(true);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Agregar(boolean admin, JTable table) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnExit = new JButton("Atzera");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); //set invisible
				dispose();//destroy
			}
		});
		btnExit.setBounds(575, 400, 119, 30);
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 25));
		contentPane.add(btnExit);

		JLabel lblNewLabel = new JLabel("Konponente Mota*");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 128, 21);
		contentPane.add(lblNewLabel);

		txtMota = new JTextField();
		txtMota.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtMota.setBounds(10, 31, 128, 21);
		contentPane.add(txtMota);
		txtMota.setColumns(10);

		lblImagen = new JLabel("Imagen");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblImagen.setBounds(165, 11, 128, 21);
		contentPane.add(lblImagen);

		txtImg = new JTextField();
		txtImg.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtImg.setColumns(10);
		txtImg.setBounds(165, 31, 128, 21);
		contentPane.add(txtImg);

		lblModelo = new JLabel("MODELO*");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblModelo.setBounds(325, 11, 128, 21);
		contentPane.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtModelo.setColumns(10);
		txtModelo.setBounds(325, 33, 128, 21);
		contentPane.add(txtModelo);

		lblMarka = new JLabel("MARKA*");
		lblMarka.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarka.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMarka.setBounds(479, 11, 128, 21);
		contentPane.add(lblMarka);

		txtMarka = new JTextField();
		txtMarka.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtMarka.setColumns(10);
		txtMarka.setBounds(479, 33, 128, 21);
		contentPane.add(txtMarka);

		lblDeskripzioa = new JLabel("Deskripzioa");
		lblDeskripzioa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeskripzioa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDeskripzioa.setBounds(10, 71, 128, 21);
		contentPane.add(lblDeskripzioa);

		lblCantidad = new JLabel("Cantidad*");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCantidad.setBounds(10, 222, 128, 21);
		contentPane.add(lblCantidad);

		txtCant = new JTextField();
		txtCant.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtCant.setColumns(10);
		txtCant.setBounds(10, 242, 128, 21);
		contentPane.add(txtCant);

		lblPrezioa = new JLabel("Prezioa*");
		lblPrezioa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrezioa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPrezioa.setBounds(165, 222, 128, 21);
		contentPane.add(lblPrezioa);

		txtPrezioa = new JTextField();
		txtPrezioa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		txtPrezioa.setColumns(10);
		txtPrezioa.setBounds(165, 242, 128, 21);
		contentPane.add(txtPrezioa);

		JTextArea txtDesc = new JTextArea();
		txtDesc.setBounds(10, 91, 283, 120);
		contentPane.add(txtDesc);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				producto produktu = new producto(txtMota.getText(), txtModelo.getText(), txtDesc.getText(), Integer.valueOf(txtCant.getText()), Double.valueOf(txtPrezioa.getText()), txtMarka.getText());
				if(produktu.buscar()) {
					System.out.println("Produktua ondo gehitu da.");
					tablas.tabla.actualizarcomponente(table);
				}else {
					System.out.println("Produktua ez da gehitu.");
				}
			}
		});
		btnInsert.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnInsert.setBounds(395, 243, 119, 30);
		contentPane.add(btnInsert);
	}
}
