package Metodos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import ObjectIOS.MiObjectInputStream;
import ObjectIOS.MiObjectOutputStream;
import Objetos.Libro;
import Objetos.Usuario;
import Recursos.Const;
import Recursos.Backup;
import Recursos.ArrayObject;

public class DevolverLibro {

	// NOTA: procurar dividir el metodo en varios metodos mas pequeños

	static void devolucionLibro(int codigo, int id) throws Exception {
		boolean devolucion = false;
		Libro[] vl = ArrayObject.listadoLibros();
		// nusca una coincidencia entre el ID del usuario, sus libros rpestados y el
		// codigo del libro que se quiere devolver. Si el usuario introducido no tiene
		// el libro que se quiere devovler este no sera devuelto
		Usuario[] vu = ArrayObject.listadoUsuarios();
		try {
			for (int i = 0; i < Const.MAXLIBROSPRES; i++) {

				if (vu[id].getLibrosPrestados()[i] != null) {
					if (vu[id].getLibrosPrestados()[i].codigo == vl[codigo].codigo) {
						devolucion = true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("El usuario no existe");
		}

		// escritura de los libros a devolver
		MiObjectInputStream oi = null;
		boolean b = false;
		if (devolucion) {
			// ==================================================================================
			try { // comprueba que el archivo FLIBROS existe antes de leerlo
				oi = new MiObjectInputStream(new FileInputStream(Const.FLIBROS));
				b = true;
			} catch (Exception e) {
				System.out.println("Archivo no encontrado");
			}
//			Libro librop = null;
			// Prestamo de libro
			int i = 0;
			if (b) { // si el archivo existe, muestra en pantalla el listado de libros
				FileOutputStream fo = new FileOutputStream(Const.FLIBROSAUX, true);
				MiObjectOutputStream oo = new MiObjectOutputStream(fo);
				try {
					while (b) {
						try {
							Libro l = (Libro) oi.readObject();
							if (l.codigo == codigo) {
								l.setPrestado(false);
							}
							oo.writeObject(l);
							i++;
						} catch (EOFException e) {
							b = false;
						}
					} // finaliza el while para la lectura
					oi.close();
					oo.close();
				} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
					System.out.println("Problemas al leer el archivo");
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
						i = 0;
						try {
							Usuario u = (Usuario) oi.readObject();
							if (u.id == id) {
								for (int j = 0; j < librosPres.length; j++) {
									if (u.getLibrosPrestados()[j] != null) {
										if (u.getLibrosPrestados()[j].codigo == codigo) {
											u.getLibrosPrestados()[j] = null;
										}
									}
								}
							}
							oo.writeObject(u);
							i++;
						} catch (EOFException e) {
							b = false;
						}
					} // finaliza el while para la lectura
					oo.close();
					oi.close();
				} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
					System.out.println("Problemas al leer el archivo de usuarios - 1" + e.toString());
				}
			}
			/*
			 * Elimina el archivo antiguo de usuarios y en su lugar se renombra el auxiliar
			 * con el de usuarios eliminado
			 */
			Backup.reemplazoFich(Const.FUSUARIOS, Const.FUSUARIOSAUX);

		}
	}
}
