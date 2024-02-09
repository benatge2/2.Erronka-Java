package Main;

import java.util.Scanner;
import Interfaz.APP;
import factura.generador;
import usuarios.user_search;

public class main {

	public static void main(String[] args) {
		APP window = new APP();
		System.out.println("Current JVM version - " + System.getProperty("java.version"));
		window.setVisible(true);
	}
}
