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

/**
 * The Class PrestamoLibro.
 */
public class PrestamoLibro {

	/**
	 * Comprobar si el libro obtenido por parametro se encuentra en prestamo o no.
	 * En el caso de que el archivo no exista o el libro no exista devolvera false
	 *
	 * @param codigo the codigo
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	static boolean verificarPrestamo(int codigo) throws IOException {
		Libro[] vl = ArrayObject.listadoLibros();
		try {
			return vl[codigo].isPrestado();
		} catch (NullPointerException e) {
			System.out.println("El libro o el archivo no existe");
		}
		return true;
	}

	/**
	 * Revisa la cantidad de libros que tiene el usuatio [id] en prestamo.
	 *
	 * @param id the id
	 * @return
	 * @throws Exception
	 */
	boolean recuentoPrestamo(int id) throws Exception {
		boolean solicitaPrestamo = false;
		Usuario[] vu = ArrayObject.listadoUsuarios();
		try {
			for (int i = 0; i < Const.MAXLIBROSPRES; i++) {
				if (vu[id].getLibrosPrestados()[i] == null) {
					solicitaPrestamo = true;
				}
			}
			if (solicitaPrestamo) {
				System.out.println("Puede pedir mas prestamos");
			} else {
				System.out.println("No puede pedir mas prestamos");
			}
		} catch (NullPointerException e) {
			System.out.println("El usuario seleccionado no existe");
		}

		return solicitaPrestamo;
	}

	/**
	 * Verifica que el usuario [id] tiene en prestamo el libro [codigo]
	 *
	 * @param id     the id
	 * @param codigo the codigo
	 * @throws Exception
	 */
	void verificarPrestamoUsuario(int id, int codigo) throws Exception {
		boolean tieneLibro = false;
		Usuario[] vu = ArrayObject.listadoUsuarios();
		System.out.println(vu[id].nombre + " / " + codigo);

		for (int i = 0; i < vu.length; i++) {
			if (vu[id].getLibrosPrestados()[i] != null)
				if (vu[id].getLibrosPrestados()[i].codigo == codigo) {
					tieneLibro = true;
				}
		}

		if (tieneLibro) {
			System.out.println("el usuario tiene el libro");
		} else {
			System.out.println("El usuario NO tiene el libro");
		}
	}

	/**
	 * Prestamo libro.
	 *
	 * @param id     the id del usuario
	 * @param codigo the codigo del libro
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void prestarLibro(int id, int codigo) throws IOException {

		// escritura de los libros
		MiObjectInputStream oi = null;
		boolean b = false;
		boolean lpre = false; // indica si el libro se encuentra ya en prestamo o no

		// ==================================================================================
		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FLIBROS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado");
		}

		Libro librop = null; // almacena el objeto Libro que se va a tomar prestado
		if (b) { // si el archivo existe, muestra en pantalla el listado de libros
			FileOutputStream fo = new FileOutputStream(Const.FLIBROSAUX, true);
			MiObjectOutputStream oo = new MiObjectOutputStream(fo);
			try {
				while (b) {
					try {
						Libro l = (Libro) oi.readObject();
						if (l.codigo == codigo) {
							lpre = l.isPrestado();
							if (!lpre)
								l.setPrestado(true);
							librop = l;
						}
						oo.writeObject(l);
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oo.close();
				oi.close();
			} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo en PrestamoLibro.prestarLibro - 1");
			}
		}

		/*
		 * Elimina el archivo antiguo de usuarios y en su lugar se renombra el auxiliar
		 * con el de usuarios eliminado
		 */
		Backup.reemplazoFich(Const.FLIBROS, Const.FLIBROSAUX);

		// =========================================================
		// Asigancion del libro prestado al usuario que lo adquiere

		b = false;
		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FUSUARIOS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado");
		}
		Libro[] librosPres = new Libro[Const.MAXLIBROSPRES];

		int c = 0; // cantidad de espacios nulos en Libro[] librosPres

		if (b) { // si el archivo existe, muestra en pantalla el listado de libros
			try {
				FileOutputStream fo = new FileOutputStream(Const.FUSUARIOSAUX, true);
				MiObjectOutputStream oo = new MiObjectOutputStream(fo);
				while (b) {
					try {
						Usuario u = (Usuario) oi.readObject();

						// asocia el ID de la lista de Usuarios al ID que obtiene por parametro
						if (u.id == id) {

							// guarda los libros ya prestados mas el siguente que se desea prestar
							for (int j = 0; j < librosPres.length; j++) {
								librosPres[j] = u.getLibrosPrestados()[j];
							}

							// obtiene la posicion de los nulos para posterior mente cambiar por un Libro
							for (int j = 0; j < Const.MAXLIBROSPRES; j++) {
								if (u.getLibrosPrestados()[j] != null) {
									c++;
								}
							}

							// coloca el libro que se toma prestado en la posicion C, que es el equivalente
							// al primer null que encuentra en la lista de libros que tiene el usuario
							librosPres[c] = librop;

							// se le asigna un array de libros al usuario
							u.setLibrosPrestados(librosPres);
						}
						oo.writeObject(u);
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oo.close();
				oi.close();
			} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo - PrestamoLibro.prestarLibro - " + e.toString());
			}
		}

		/*
		 * Elimina el archivo antiguo de usuarios y en su lugar se renombra el auxiliar
		 * con el de usuarios eliminado
		 */
		Backup.reemplazoFich(Const.FUSUARIOS, Const.FUSUARIOSAUX);

	}
}
