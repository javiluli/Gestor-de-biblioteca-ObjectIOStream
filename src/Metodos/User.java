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
 * The Class MetUsuarios.
 */
public class User {

	/**
	 * Ver listado usuarios.
	 * 
	 * @throws Exception
	 */
	public static void verListadoUsuarios() throws Exception {
		Usuario[] u = ArrayObject.listadoUsuarios();
		for (Usuario usuario : u) {
			if (usuario != null)
				System.out.println(usuario);
		}
	}

	/**
	 * Busqueda primer nulo.
	 *
	 * @return the int
	 * @throws Exception
	 */
	public int busquedaPrimerNulo() throws Exception {
		Usuario[] vu = ArrayObject.listadoUsuarios();
		int nnf = 0; // lugar donde se encuentra el primer Usuario Null
		for (int i = 0; i < vu.length; i++) {
			if (vu[i] == null) {
				return nnf = i;
			}
		}
		return nnf;
	}

	/**
	 * Lee los usuarios almacenados en el archivo de FUSUARIOS
	 *
	 * @return the int
	 */

	public int leerDatosUsuarios() {
		MiObjectInputStream oi = null;
		boolean b = false;
		try { // comprueba que el archivo FLIBROS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FUSUARIOS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado - altaUsuario" + e.toString());
		}

		int n = 0;
		if (b) { // si el archivo existe, leera los usuarios almacenados
			try {
				while (b) {
					try {
						Usuario u = (Usuario) oi.readObject();
						n++;
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oi.close();
			} catch (Exception e) {// encaso de encontrar el archivo pero no puede leerlo
				System.out.println("Problemas al leer el archivo - altaUsuario" + e.toString());
			}
		}
		return n;
	}

	/**
	 * Alta usuario.
	 *
	 * @param nombre the nombre
	 * @throws Exception the exception
	 */
	public void altaUsuario(String nombre) throws Exception {
		int n = leerDatosUsuarios();
		int bpn = busquedaPrimerNulo();

		if (bpn < n) {
			suplantarIdUsuer(nombre);
			Backup.reemplazoFich(Const.FUSUARIOS, Const.FUSUARIOSAUX);

		} else {
			System.out.println("Maximo de usuarios alcanzado");
		}

		Backup.backupUsuario(Const.FUSUARIOS, Const.FUSUARIOSBACKUP);
	}

	/**
	 * Archivo usuarios nulos. Genera un archivo de Usuarios siendo estos mismo
	 * todos nulos
	 *
	 * @throws Exception the exception
	 */
	public static void archivoUsuariosNulos() throws Exception {

		FileOutputStream fo = new FileOutputStream(Const.FUSUARIOS, true);
		MiObjectOutputStream oo = new MiObjectOutputStream(fo);

		int i = 0;
		while (i < Const.MAXUSUARIOS) {

			Usuario u = new Usuario();
			u = null;
			oo.writeObject(u);

			// Guardo los usuarios en el archivo
			i++;

		} // finaliza el while para la lectura
		oo.close();
	}

	/**
	 * Leera un array de objetos Usuarios y cuando se deesea agregar un usuario
	 * nuevo, este ocupara el lugar del primer nulo, asi se evita generar usuarios
	 * con ID de un mal orden. Esto generara el resto de objetos posibles como nulos
	 *
	 * @param nombre the nombre
	 * @throws Exception
	 */
	void suplantarIdUsuer(String nombre) throws Exception {
		Libro[] librosPres = new Libro[Const.MAXLIBROSPRES];
		int bpn = busquedaPrimerNulo();
		Usuario[] vu = ArrayObject.listadoUsuarios();

		MiObjectInputStream oi = null;

		try { // comprueba que el archivo FUSUARIOS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FUSUARIOS));
		} catch (Exception e) {
			System.out.println("Archivo no encontrado - suplantarIdUsuer - " + e.toString());
		}

		// busca donde se encuentra el primer usuario null del archivo para crear un
		// usuario nuevo en ese mismo lugar para obtener un id ordenado. Del mismo modo
		// la primera vez que esto se ustiliza llena el archivode usuarios de nulos pero
		// no elimna los usuarios que se encuentran por debajo
		Usuario u = (Usuario) oi.readObject();
		u = new Usuario(bpn, nombre, librosPres);
		vu[bpn] = u;

		try {
			FileOutputStream fo = new FileOutputStream(Const.FUSUARIOSAUX, true);
			MiObjectOutputStream oo = new MiObjectOutputStream(fo);
			int i = 0;
			while (i < vu.length) {
				u = vu[i];
				oo.writeObject(u);
				i++;
			} // finaliza el while para la lectura
			oo.close();

		} catch (Exception e) { // encaso de encontrar el archivos pero no puede leerlo
			System.out.println("Problemas al leer el archivo - suplantarIdUsuer - " + e.toString());
		}
		oi.close();
	}

	/**
	 * Elimina un usuario del archivo siempre que este exista y el mismo no tenga
	 * libros en prestamo
	 *
	 * @param id the id
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
//	FUSUARIOSAUX
	public void bajaUsuario(int id) throws IOException {
//		Usuario[] vu = ReturnArr.listadoUsuarios();

		MiObjectInputStream oi = null;
		boolean b = false, libPres = false;

		try { // comprueba que el archivo FUSUARIOS existe antes de leerlo
			oi = new MiObjectInputStream(new FileInputStream(Const.FUSUARIOS));
			b = true;
		} catch (Exception e) {
			System.out.println("Archivo no encontrado - bajaUsuario - " + e.toString());
		}

		if (b) { // si el archivo existe, muestra en pantalla el listado de usuarios
			FileOutputStream fo = new FileOutputStream(Const.FUSUARIOSAUX, true);
			MiObjectOutputStream oo = new MiObjectOutputStream(fo);

			try {
				int i = 0;
				while (b) {
					try {
						Usuario u = (Usuario) oi.readObject();
						if (i == id) {
							for (int j = 0; j < Const.MAXLIBROSPRES; j++) {
								if (u.getLibrosPrestados()[j] != null) {
									libPres = true;
								}
							}
						}

						// cambiara el Usuario por un null cuando el id sea el mismo y no tenga libros
						// en prestamo.
						if (i == id && !libPres) {
							u = null;
						}

						// Guardo los usuarios en el archivo
						oo.writeObject(u);

						i++;
					} catch (EOFException e) {
						b = false;
					}
				} // finaliza el while para la lectura
				oo.close();
				oi.close();
			} catch (Exception e) { // encaso de encontrar el archivos pero no puede leerlo
				System.out.println("Problemas al leer el archivo - bajaUsuario - " + e.toString());
			}

			/*
			 * Elimina el archivo antiguo de usuarios y en su lugar se renombra el auxiliar
			 * con el de usuarios eliminado
			 */
			Backup.reemplazoFich(Const.FUSUARIOS, Const.FUSUARIOSAUX);
		}
	}
}
