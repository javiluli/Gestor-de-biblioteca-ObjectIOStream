/**
 *
 * @author Javier Delgado Rodriguez
 */
package Recursos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ObjectIOS.MiObjectInputStream;
import ObjectIOS.MiObjectOutputStream;
import Objetos.Libro;
import Objetos.Usuario;

public class Backup {

	/**
	 * Reemplazo fich. Elimina el archivo antiguo de usuarios y en su lugar se
	 * renombra el auxiliar con el de usuarios eliminado
	 *
	 * @param FDATOS    the fdatos
	 * @param FDATOSAUX the fdatosaux
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void reemplazoFich(String FDATOS, String FDATOSAUX) throws IOException {
		File datos = new File(FDATOS);
		datos.delete();
		File datosAux = new File(FDATOSAUX);
		datosAux.renameTo(datos);
	}

	public static void backupLibro(String FDATOS, String FDATOSAUX) throws IOException {
		MiObjectOutputStream oo = new MiObjectOutputStream(new FileOutputStream(FDATOSAUX));
		MiObjectInputStream oi = null;
		boolean b = false;

		try {// comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(FDATOS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado");
		}

		if (b) {
			try {
				while (b) {// si el archivo existe, muestra en pantalla el listado de libros
					try {
						Libro l = (Libro) oi.readObject();
						oo.writeObject(l);

					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oo.close();
				oi.close();
			} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo de libros" + e.toString());
			}
		}
	}

	public static void backupUsuario(String FDATOS, String FDATOSAUX) throws IOException {
		MiObjectOutputStream oo = new MiObjectOutputStream(new FileOutputStream(FDATOSAUX));
		MiObjectInputStream oi = null;
		boolean b = false;

		try {// comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(FDATOS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado");
		}

		if (b) {
			try {
				while (b) {// si el archivo existe, muestra en pantalla el listado de libros
					try {
						Usuario u = (Usuario) oi.readObject();
						oo.writeObject(u);

					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oo.close();
				oi.close();
			} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo de usuarios" + e.toString());
			}
		}
	}
}
