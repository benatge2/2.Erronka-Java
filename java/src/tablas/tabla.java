package tablas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class tabla {

	public static ResultSet actualizarcomponente(JTable table, String zutabe,String valor) {
		String x = "caracteristicas";
		String query;
		ResultSet rs = null;
		if(zutabe == null) {
			query = "select p.idprodukto, p.konponenteMota, p.img, p.modelo, p.kantitatea, p.prezioa, p.marka, "
					+ "COALESCE(GROUP_CONCAT(c.karakteristika1), 'Sin características') AS caracteristicas from konponenteak p "
					+ "LEFT JOIN karakteristika c ON p.idprodukto = c.idprodukto GROUP BY p.idprodukto;";
		}else if (zutabe.equals(x)) {
			query = "select p.idprodukto, p.konponenteMota, p.img, p.modelo, p.kantitatea, p.prezioa, p.marka, "
					+ "COALESCE(GROUP_CONCAT(c.karakteristika1), 'Sin características') AS caracteristicas from konponenteak p "
					+ "RIGHT JOIN karakteristika c ON p.idprodukto = c.idprodukto WHERE c.karakteristika1 = UPPER('"+valor+"') GROUP BY p.idprodukto;";
			System.out.println(query);
		}else {
			query = "select p.idprodukto, p.konponenteMota, p.img, p.modelo, p.kantitatea, p.prezioa, p.marka, "
					+ "COALESCE(GROUP_CONCAT(c.karakteristika1), 'Sin características') AS caracteristicas from konponenteak p "
					+ "LEFT JOIN karakteristika c ON p.idprodukto = c.idprodukto WHERE p."+zutabe+" = UPPER('"+valor+"') GROUP BY p.idprodukto;";
		}
		try
		{
		   Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception a)
		{
		   a.printStackTrace();
		}
		// Establecemos la conexión con la base de datos.
		try {
			DB.DBconnect obJConnection = new DB.DBconnect();
			Connection connection = obJConnection.getConnection();
			Statement st;
			//Preparamos la consulta
			DefaultTableModel model = new DefaultTableModel();
			TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
			table.setRowSorter(order);
			st = connection.createStatement();
			rs = st.executeQuery (query);
			ResultSetMetaData metaData = rs.getMetaData();
			table.setModel(model);
			int columnCount = metaData.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return rs;
	}

	public static ResultSet actualizartabla(String opciontabla, JTable table) {
		ResultSet rs = null;
		try
		{
		   Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception a)
		{
		   a.printStackTrace();
		}
		// Establecemos la conexión con la base de datos.
		try {
			DB.DBconnect obJConnection = new DB.DBconnect();
			Connection connection = obJConnection.getConnection();
			Statement st;
			//Preparamos la consulta
			DefaultTableModel model = new DefaultTableModel();
			TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
			table.setRowSorter(order);
			st = connection.createStatement();
			rs = st.executeQuery ("select * from "+opciontabla);
			ResultSetMetaData metaData = rs.getMetaData();
			table.setModel(model);
			int columnCount = metaData.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return rs;
	}

	public static ResultSet actualizartabla(String opciontabla, JTable table, String identificador, String id) {
		ResultSet rs = null;
		try
		{
		   Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception a)
		{
		   a.printStackTrace();
		}
		// Establecemos la conexión con la base de datos.
		try {
			DB.DBconnect obJConnection = new DB.DBconnect();
			Connection connection = obJConnection.getConnection();
			Statement st;
			//Preparamos la consulta
			DefaultTableModel model = new DefaultTableModel();
			TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
			table.setRowSorter(order);
			st = connection.createStatement();
			String query = "select * from "+opciontabla +" WHERE "+identificador+" = '"+id+"'";
			System.out.println(query);
			rs = st.executeQuery (query);
			ResultSetMetaData metaData = rs.getMetaData();
			table.setModel(model);
			int columnCount = metaData.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return rs;
	}

	public static void Select (JTable table,String query) {
		try
		{
		   Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception a)
		{
		   a.printStackTrace();
		}
		// Establecemos la conexión con la base de datos.
		try {
			query = "SELECT t1.nPedido, t1.nProducto, t1.cantidad, t2.modelo, t2.prezioa FROM pedidos_producto t1 LEFT JOIN konponenteak t2 ON t1.nProducto = t2.idprodukto WHERE t1.nPedido = " + query+";";
			DB.DBconnect obJConnection = new DB.DBconnect();
			Connection connection = obJConnection.getConnection();
			Statement st;
			ResultSet rs;
			//Preparamos la consulta
			DefaultTableModel model = new DefaultTableModel();
			TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
			table.setRowSorter(order);
			st = connection.createStatement();
			rs = st.executeQuery (query);
			System.out.println(query);
			ResultSetMetaData metaData = rs.getMetaData();
			table.setModel(model);
			int columnCount = metaData.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	/*public static void tabla_bistaratu(String tabla) {
		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM " + tabla;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();

			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println();
				for (int i = 1; i <= columns; i++) {
					System.out.print("|" + rs.getString(i));
				}
				System.out.print("|");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
