package Metodos;

import java.io.File;

import Recursos.Const;

/**
 *
 * @author Javier Delgado Rodriguez
 */

/**
 * The Class Menu.
 */
public class Menu {

	/**
	 * Opciones menu.
	 */
	public static void opcionesMenu() {
		System.out.println("1.- Alta de libros");
		System.out.println("2.- Alta de usuarios");
		System.out.println("3.- Baja de usuarios");
		System.out.println("4.- Préstamo de libros");
		System.out.println("5.- Devolución de libro");
		System.out.println("6.- Consulta de un libro");
		System.out.println("7.- Listado de usuarios");
		System.out.println("8.- Listado de libros no prestados");
		System.out.println("0.- Salir de la aplicacion");
	}

	/**
	 * Menu.
	 * 
	 * @throws Exception
	 */
	public void menu() throws Exception {
		Teclado t = new Teclado();
		Book ml = new Book();
		User mu = new User();
		PrestamoLibro pl = new PrestamoLibro();
		int id, codigo, n;

		// Comprobar que el fichero de Usuarios existe. Si este no existe, llamra al
		// metodo para crea un archivo lleno de usuarios nulos
		File f = new File(Const.FUSUARIOS);
		boolean bfile = f.exists();
		if (!bfile) {
			User.archivoUsuariosNulos();
		}

		f = new File(Const.FLIBROS);
		bfile = f.exists();
		if (!bfile) {
			f.createNewFile();
		}
		// =============================================

		do {
			opcionesMenu();
			n = t.leerInt();
			switch (n) {
			case 1:
				System.out.println("Introducir nombre del libro: ");
				ml.altaLibro(t.leerString().trim().toUpperCase());
				break;
			case 2:
				// Falta pulir a la hora de dar de alta a un usuario cuando hay espacios libres
				// entre medias del primero y el ultimo usuario ordenado
				System.out.println("Introducir nombre del usuario: ");
				mu.altaUsuario(t.leerString().trim().toUpperCase());
				break;
			case 3:
				System.out.println("Id del usuario a dar de baja: ");
				mu.bajaUsuario(t.leerInt());
				break;
			case 4:
				System.out.println("Codigo del libro");
				codigo = t.leerInt();
				boolean vp = PrestamoLibro.verificarPrestamo(codigo);
				System.out.println(vp);
				if (!vp) {
					System.out.println("ID del usuario");
					id = t.leerInt();
					boolean rp = pl.recuentoPrestamo(id);

					if (!vp && rp) {
						pl.prestarLibro(id, codigo);
					}
				}
				break;
			case 5:
				System.out.println("Codigo del libro a buscar");
				codigo = t.leerInt();
				vp = PrestamoLibro.verificarPrestamo(codigo);
				System.out.println(vp);
				// NOTA PARA SOLUCIONAR: aunque el libro no exista, este pedira el usuairo al
				// que le sera prestado (posible solucion, crear metodo que antes verifique si
				// dicho libro existe, y despues si este existe, verificar sie sta prestado)
				if (vp) {
					System.out.println("ID del usuario");
					id = t.leerInt();
					DevolverLibro.devolucionLibro(codigo, id);
				} else {
					System.out.println("Libro no prestado");
				}
				break;
			case 6:
				System.out.println("Codigo del libro a buscar");
				codigo = t.leerInt();
				vp = PrestamoLibro.verificarPrestamo(codigo);
				if (vp)
					Book.solicitarLibro(codigo);
				else {
					System.out.println("El libro no se encuntra en prestamo");
				}
				break;
			case 7:
				User.verListadoUsuarios();

				break;
			case 8:
				ml.verLibros();
				break;
			case 0:
//			System.out.println("Salir de la aplicacion");
				break;
			}
		} while (n != 0);
	}
}
