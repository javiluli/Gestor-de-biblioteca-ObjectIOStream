package Recursos;

import java.io.FileInputStream;

import ObjectIOS.MiObjectInputStream;

public class Search {

	public static boolean buscarFichero(String s) {
		MiObjectInputStream oi = null;
		boolean b = false;
		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(s));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado - buscarFichero - " + e.toString());
		}
		return b;
	}

}
