package producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.DB_conectable;

public class producto implements DB_conectable {
	private String mota;
	private String modelo;
	private int cant;
	private double prezioa;
	private String marka;

	public producto(String mota, String modelo, int cant, double prezioa, String marka) {
		this.mota = mota;
		this.modelo = modelo;
		this.cant = cant;
		this.prezioa = prezioa;
		this.marka = marka;
	}

	@Override
	public boolean buscar() {

		boolean respuesta = false;
		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT modelo FROM konponenteak WHERE modelo = '" + this.getModelo() + "'";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				query = "UPDATE konponenteak SET kantitatea = kantitatea + " + this.getCant() + " WHERE modelo = '" + this.getModelo() + "'";
				try (Statement declaracion = connection.createStatement()) {
	                int filasActualizadas = declaracion.executeUpdate(query);
	                System.out.println("Filas actualizadas: " + filasActualizadas);
	                respuesta = true;
	            }
			}else {
				respuesta = insertar();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return respuesta;
		}
		return respuesta;
	}

	@Override
	public boolean insertar() {

		DB.DBconnect obJConnection = new DB.DBconnect();
		Connection connection = obJConnection.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO konponenteak (konponentemota,img,modelo,kantitatea,prezioa,marka) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, this.getMota());
			stmt.setString(2, "placa1.png");
			stmt.setString(3, this.getModelo());
			stmt.setInt(4, this.getCant());
			stmt.setDouble(5, this.getPrezioa());
			stmt.setString(6, this.getMarka());
			stmt.executeUpdate();
			// stmt.setDate(3, new Date());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public double getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(double prezioa) {
		this.prezioa = prezioa;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}
}
