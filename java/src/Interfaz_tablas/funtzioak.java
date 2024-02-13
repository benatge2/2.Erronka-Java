package Interfaz_tablas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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

	public static void get_column(MouseEvent e,JTextField txt, ResultSet SQLResult) {
		JTable source = (JTable)e.getSource();
		int row = source.rowAtPoint( e.getPoint() );
		int columna = source.columnAtPoint( e.getPoint() );

		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSetMetaData rsMetaData = SQLResult.getMetaData();

		    String nombre2 = rsMetaData.getColumnName(columna + 1);
		    txt.setText(nombre2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (! source.isRowSelected(row))
		    source.changeSelection(row, columna, false, false);
	}

	public static void get_data(MouseEvent e,JTextField txt, ResultSet SQLResult) {
		JTable source = (JTable)e.getSource();
		int row = source.rowAtPoint( e.getPoint() );
		int columna = source.columnAtPoint( e.getPoint() );

		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSetMetaData rsMetaData = SQLResult.getMetaData();
			txt.setText(String.valueOf(source.getValueAt(row, columna)));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (! source.isRowSelected(row))
		    source.changeSelection(row, columna, false, false);

	}

	public static void get_id(MouseEvent e,JTextField txt, ResultSet SQLResult) {
		JTable source = (JTable)e.getSource();
		int row = source.rowAtPoint( e.getPoint() );
		int columna = source.columnAtPoint( e.getPoint() );

		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSetMetaData rsMetaData = SQLResult.getMetaData();
			txt.setText(String.valueOf(source.getValueAt(row, 0)));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (! source.isRowSelected(row))
		    source.changeSelection(row, columna, false, false);

	}

	public static String[] load_data(JTextField TFInsert, JTable table, String tabla,String columnaid,int pos) {
		// detectar casilla clicada
		String buelta[] = new String [2];
		int fila = table.getSelectedRow(); // primero, obtengo la fila seleccionada
		int columna = table.getSelectedColumn();// luego, obtengo la columna seleccionada
		if (fila > 0 && columna > 0) {
			String dato = String.valueOf(table.getValueAt(fila, columna)); // por ultimo, obtengo el valor de la celda
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
					buelta [0] = nombre;
					String ID = String.valueOf(table.getValueAt(fila, pos));
					stmt = connection.createStatement();
					SQLResult = stmt.executeQuery("select "+columnaid+" from "+tabla+" WHERE "+columnaid+" = " + ID);
					SQLResult.next();
					int id = SQLResult.getInt(columnaid);
					buelta[1]= String.valueOf(id);
					connection.close();
					obJConnection.close();
					stmt.close();
					SQLResult.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return buelta;
	}

}
