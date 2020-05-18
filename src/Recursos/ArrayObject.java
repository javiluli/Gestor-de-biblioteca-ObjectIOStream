/**
 *
 * @author Javier Delgado Rodriguez
 */
package Recursos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import ObjectIOS.MiObjectInputStream;
import Objetos.Libro;
import Objetos.Usuario;

// TODO: Auto-generated Javadoc
/**
 * The Class ReturnArr.
 */
public class ArrayObject {
	/**
	 * Ver libros.
	 *
	 * @return the libro[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Libro[] listadoLibros() throws IOException {
		MiObjectInputStream oi = null;
		boolean b = false;
		Libro[] libros = new Libro[Const.MAXLIBROS];

		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FLIBROS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado - listadoLibros");
		}

		if (b) { // si el archivo existe, muestra en pantalla el listado de libros
			try {
				int i = 0;
				while (b) {
					try {
						Libro libro = (Libro) oi.readObject();
						libros[i] = libro;
						i++;
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oi.close();
			} catch (Exception e) { // encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo - listadoLibros");
			}
		}
		return libros;
	}

	/**
	 * Ver usuarios.
	 *
	 * @return the usuario[]
	 * @throws Exception
	 */
	public static Usuario[] listadoUsuarios() throws IOException {
		MiObjectInputStream oi = null;
		boolean b = false;
		Usuario[] usuarios = new Usuario[Const.MAXUSUARIOS];

		try { // comprueba que el archivo FUSUARIOS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FUSUARIOS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado - listadoUsuarios - " + e.toString());
		}

		if (b) { // si el archivo existe, muestra en pantalla el listado de usuarios
			try {
				int i = 0;
				while (b) {
					try {
						Usuario u = (Usuario) oi.readObject();
						if (i < usuarios.length)
							usuarios[i] = u;
						i++;
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oi.close();
			} catch (Exception e) { // encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo - listadoUsuarios - " + e.toString());
			}
		}

		return usuarios;
	}
}
