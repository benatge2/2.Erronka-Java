package Interfaz_tablas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class funtzioak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ActionListener myActionListener = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					funtzioak frame = new funtzioak();
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
	public funtzioak() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	public static void load_data(JTextField TFInsert, JTable table, JButton btnUpdate, String tabla,String columnaid,int pos) {
		// detectar casilla clicada
		int fila = table.getSelectedRow(); // primero, obtengo la fila seleccionada
		int columna = table.getSelectedColumn();
		if (fila > 0 && columna > 0) {// luego, obtengo la columna seleccionada
			String dato = String.valueOf(table.getValueAt(fila, columna)); // por ultimo, obtengo el valor
				//System.out.println(fila+columna+dato);															// de la celda

			if (String.valueOf(dato) != null) {
				TFInsert.setText(String.valueOf(dato));
				DB.DBconnect obJConnection = new DB.DBconnect();
				Connection connection = obJConnection.getConnection();
				Statement stmt;
				try {
					stmt = connection.createStatement();
					ResultSet SQLResult = stmt.executeQuery("select * from " + tabla);
					ResultSetMetaData rsMetaData = SQLResult.getMetaData();
					String nombre = rsMetaData.getColumnName(columna + 1);

					String ID = String.valueOf(table.getValueAt(fila, pos));
					stmt = connection.createStatement();
					SQLResult = stmt.executeQuery("select "+columnaid+" from "+tabla+" WHERE "+columnaid+" = " + ID);
					SQLResult.next();
					int id = SQLResult.getInt(columnaid);

					myActionListener = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tablas.modify.update(tabla, nombre, columnaid, id, TFInsert.getText());
							// String tabla, String columna , String columnaid, int id, String valor
							tablas.tabla.actualizarcomponente(table);
							//tabla.tabla.actualizartabla("konponenteak", table);
							// Después de que se ha ejecutado el código, puedes eliminar el ActionListener
							btnUpdate.removeActionListener(myActionListener);
						}
					};

					btnUpdate.addActionListener(myActionListener);

					connection.close();
					obJConnection.close();
					stmt.close();
					SQLResult.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
