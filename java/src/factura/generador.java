package factura;

import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.itextpdf.text.*;
//import com.itextpdf.text.Document;

public class generador {
	public static void crear_factura(int nPedido, String NAN) {
		try {
			LocalDate fecha = LocalDate.now();
			// Nombre del PDF
			String file_name = ".\\res\\facturas\\factura" + nPedido + ".pdf";

			// Crear el documento PDF
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file_name));

			// Abrir el documento
			document.open();

			// Agregar contenido
			PdfContentByte cb = writer.getDirectContent();
			Image logo = Image.getInstance(".\\res\\imagenes\\logo2.png");
			logo.scaleAbsolute(100, 50); // Escalar la imagen para ajustarla al tamaño deseado
			logo.setAbsolutePosition(50, 750); // Posición de la imagen en la página
			document.add(logo);

			// Agregar encabezado
			PdfContentByte canvas = writer.getDirectContent();
			//ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase("BIABE II\nCuenca, Avenida de Castilla-La Mancha.​\nTlf:654 54 54 54"), 550, 800, 0);
			ColumnText ct = new ColumnText(canvas);
			ct.setSimpleColumn(350, 800, 700, 0);

			Phrase phrase = new Phrase();
			phrase.add(new Chunk("BIABE II\n"));
			phrase.add(new Chunk("Cuenca, Avenida de Castilla-La Mancha.\n"));
			phrase.add(new Chunk("Tlf:654 54 54 54\n"));
			phrase.add(Chunk.NEWLINE);
			phrase.add(new Chunk("NAN: "+NAN));
			phrase.add(Chunk.NEWLINE);
			phrase.add(new Chunk("Numero pedido: "+nPedido));
			phrase.add(Chunk.NEWLINE);
			phrase.add(new Chunk("Fecha del pedido: "+fecha));
			ct.addElement(phrase);
			ct.go();

			// Mostrar el texto alineado en el canvas
			//ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase, 550, 800, 0);

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
				pt = pt+precio;
			}
			DecimalFormat df = new DecimalFormat("#.00");
			pt = Math.round(pt * 100.0) / 100.0;

			table.addCell("");
			table.addCell("Precio total:");
			table.addCell(pt + "$");

			table.setTotalWidth(PageSize.A4.getWidth() - document.leftMargin() - document.rightMargin());
			table.writeSelectedRows(0, -1, document.leftMargin(), 650, writer.getDirectContent());

			 PdfContentByte cd = writer.getDirectContent();
			 Anchor anchor = new Anchor("BIABE-II.com", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE, BaseColor.BLUE));
			 anchor.setReference("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley");
		     ColumnText.showTextAligned(cd, Element.ALIGN_RIGHT, anchor, document.right(), document.bottom() - 10, 0);

			//document.add(table);

			// cerrar documento
			System.out.println("factura creada...");
			document.close();
			//mover_factura.factura(String.valueOf(nPedido));
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
