package factura;

import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import com.itextpdf.text.*;
//import com.itextpdf.text.Document;

public class generador {
	public static void crear_factura(int nPedido) {
		try {
			// nombre del pdf
			String File_name = ".\\res\\facturas\\factura" + nPedido + ".dpf";
			// Cargar documento PDF
			Document document = new Document();

			PdfWriter.getInstance(document, new FileOutputStream(File_name));
			// abrir documento
			document.open();

			// agregar contenido

			// agregar logo
			document.add(Image.getInstance(".\\res\\imagenes\\logo3.JPG"));

			// agregar texto
			// Paragraph para = new Paragraph("Paragrafo de prueba factura");
			// document.add(para);

			// crear un tabla en pdf
			PdfPTable table = new PdfPTable(3);
			PdfPCell c1 = new PdfPCell(new Phrase("Producto"));
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Cantidad"));
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Prezioa"));
			table.addCell(c1);

			table.setHeaderRows(1);

			// DB conexioa tablan sartzeko

			DB.DBconnect obJConnection = new DB.DBconnect();
			Connection connection = obJConnection.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;

			// select de cual pedido quieres implementar en la tabla
			// cambiar una vez tengamos las tablas de los pedidos de la web
			double pt = 0;
			//String query = "SELECT nombre_producto,cantidad,modelo,precio FROM pedidos_producto WHERE nPedido = " + nPedido;
			String query = "SELECT t1.nProducto, t1.cantidad, t2.modelo, t2.prezioa FROM pedidos_producto t1 JOIN konponenteak t2 ON t1.nProducto = t2.idprodukto WHERE t1.nPedido = " + nPedido;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				double precio = ((rs.getDouble("t2.prezioa")) * rs.getInt("t1.cantidad"));
				table.addCell(rs.getString("t2.modelo"));
				table.addCell(rs.getString("t1.cantidad"));
				table.addCell(precio + "$");
				pt += precio;
			}
			DecimalFormat df = new DecimalFormat("#.00");
			pt = Math.round(pt * 100.0) / 100.0;

			table.addCell("");
			table.addCell("Precio total:");
			table.addCell(pt + "$");

			document.add(table);

			// cerrar documento
			System.out.println("factura creada...");
			document.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
