package usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javax.swing.JOptionPane.*;

public class sesion {
	public static int check_pass(String NAN, String pass) {

		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT Izena FROM langileak WHERE Contraseña = '" + pass + "' AND NAN = '" + NAN + "'";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (check_admin(NAN)) {
					showMessageDialog(null,
							"Ongi etorri " + rs.getString("Izena") + "\n Nivel de permiso: Administrador");
					return 2;
				} else {
					showMessageDialog(null, "Ongi etorri " + rs.getString("Izena") + "\n Nivel de permiso: Usuario");
					return 1;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	public static boolean check_admin(String NAN) {
		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT Admin FROM langileak WHERE NAN = '" + NAN + "'";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getBoolean("Admin");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
