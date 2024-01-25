package tablas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBconnect;

public class modify {

	public static void update(String tabla, String columna, String columnaid, int id, String valor) {
	    String query = "UPDATE " + tabla + " SET " + columna + " = ? WHERE " + columnaid + " = "+id;
	    System.out.println(query);
	    try (DBconnect obJConnection = new DBconnect()){
	    	Connection connection = obJConnection.getConnection();
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, valor);
	        ps.executeUpdate();
	        ps.close();
	        connection.close();
	        obJConnection.close();
	    } catch (SQLException e) {
	        // Manejo de excepciones
	        e.printStackTrace();
	    }
	}


	public static void delete(String tabla, String columna, String valor) {
		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		Statement ps = null;
		try {
			String query = "DELETE FROM " + tabla + " WHERE " + columna + " = '" + valor + "'";
			ps = connection.createStatement();
			ps.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
