/**
 *
 * @author Javier Delgado Rodriguez
 */
package Metodos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ObjectIOS.MiObjectInputStream;
import ObjectIOS.MiObjectOutputStream;
import Objetos.Libro;
import Objetos.Usuario;
import Recursos.Const;
import Recursos.Backup;
import Recursos.ArrayObject;

// TODO: Auto-generated Javadoc
/**
 * The Class MetLibros.
 */
public class Book {

	/**
	 * Alta libro.
	 *
	 * @param titulo the titulo
	 * @throws Exception the exception
	 */
	public void altaLibro(String titulo) throws Exception {
		MiObjectInputStream oi = null;
		boolean b = false;
		int i = 0, clibros = 0;

		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FLIBROS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado");
		}

		if (b) { // si el archivo existe, muestra en pantalla el listado de libros
			try {
				while (b) {
					try {
						Libro l = (Libro) oi.readObject();
						// asigna el codigo si el titulo del libro es el mismo, y si el titulo es
						// distinto el codigo cambia de forma secuencia sin repetir y de manera
						// secuencia
						if (!l.titulo.equals(titulo) && l.codigo == i) {
							i++; // codigo del libro, el codigo sera igual si el nombre del libro se repite
						}
						clibros++; // contador de la cantidad de libros en la biblioteca
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oi.close();
			} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo");
			}
		}
		FileOutputStream fo = new FileOutputStream(Const.FLIBROS, true);
		MiObjectOutputStream oo = new MiObjectOutputStream(fo);

		if (clibros < Const.MAXLIBROS) {
			// almacena los libros en el fichero FLIBROS
			Libro l = new Libro(i, titulo, false);
			oo.writeObject(l);
		} else {
			System.out.println("Maximo de libros alcanzado");
		}
		oo.close();
		Backup.backupLibro(Const.FLIBROS, Const.FLIBROSBACKUP);
	}

	/**
	 * Ver libros.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void verLibros() throws IOException {
		MiObjectInputStream oi = null;
		boolean b = false;

		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FLIBROS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado");
		}

		if (b) { // si el archivo existe, muestra en pantalla el listado de libros
			try {
				int i = 1;
				while (b) {
					try {
						Libro l = (Libro) oi.readObject();
						if (!l.isPrestado())
							System.out.println(l);
						i++;
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oi.close();
			} catch (Exception e) { // encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo" + e.toString());
			}
		}
	}

	/**
	 * Solicitar libro. ARREGLAR este metodo
	 *
	 * @param codigo the codigo
	 * @throws Exception
	 */
	static void solicitarLibro(int codigo) throws Exception {
		Libro[] vl = ArrayObject.listadoLibros();

		Usuario[] vu = ArrayObject.listadoUsuarios();
		for (int i = 0; i < vu.length; i++) {

			for (int j = 0; j < Usuario.MAXLIBROSPRES; j++) {

				// evito la busqueda excesiba en usuarios nulos
				try {
					if (vu[i].getLibrosPrestados()[j] != null) {
						if (vu[i].getLibrosPrestados()[j].codigo == vl[codigo].codigo) {
							System.out.println("Usuario: " + vu[i].nombre);
							System.out.println("ID del usuario: " + vu[i].id);

						}
					}
				} catch (NullPointerException e) {

				}
			}
		}
	}
}
