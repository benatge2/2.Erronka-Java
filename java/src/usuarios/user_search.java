package usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class user_search {
	public static int buscar(String NAN) {
		int npedido = 0;

		try {
			DB.DBconnect obJConnection = new DB.DBconnect();
			Connection connection = obJConnection.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			double pt = 0;
			String query = "SELECT npedido,idusuario FROM pedidos WHERE idusuario = '" + NAN + "'";

			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			System.out.println("Estos son los pedido hechos por el usuario: " + NAN);

			int pos = 1;
			while (rs.next()) {
				System.out.print(pos + ": ");
				System.out.println(rs.getString("npedido"));
				pos++;
			}

			Scanner sc = new Scanner(System.in);
			System.out.println("Cual es el numero de pedido que quieres generar una factura?");
			int i = sc.nextInt();

			query = "SELECT npedido,idusuario FROM pedidos WHERE npedido = '" + i + "'";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs.next()) {
				npedido = rs.getInt("npedido");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Numero del pedido del usuario: " + NAN + " es :" + npedido);

		return npedido;
	}
}
