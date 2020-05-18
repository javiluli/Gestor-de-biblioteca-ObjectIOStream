/**
 *
 * @author Javier Delgado Rodriguez
 */
package Recursos;

/**
 * The Class Constantes.
 */
public class Const {
	// ######################################
	// Constantes sobre Usuarios
	// ######################################

	/** Cantidad maxima de usuarios que almacena la biblioteca */
	public final static int MAXUSUARIOS = 100;

	/** Conexion con el archico donde se almacenan los usuarios de la biblioteca */
	public final static String FUSUARIOS = "src\\datosUsuarios.txt";

	/**
	 * Conexion con un archivo auxiliar de los usuarios almacenados. Su uso esta mas
	 * orientado a cuando se realizan cambios en los datos de los usuarios, como en
	 * los libros que tiene en prestamos.
	 */
	public final static String FUSUARIOSAUX = "src\\datosUsuarios_aux.txt";

	/**
	 * Conexion con el archivo de backup de usuarios almacenados en la biblioteca
	 */
	public final static String FUSUARIOSBACKUP = "src\\datosUsuarios_backup.txt";

	// ######################################
	// Constantes sobre Libros
	// ######################################

	/** Cantidad maxima de libros almacenados en la biblioteca */
	public final static int MAXLIBROS = 2000;

	/** Cantidad maxima de libros que puede tener un usuario en prestamo */
	public final static int MAXLIBROSPRES = 5;

	/** Conexion con el archivo donde se almacenan los libros de la biblioteca. */
	public final static String FLIBROS = "src\\datosLibros.txt";

	/**
	 * Conexion con un archivo auxiliar de los libros almacenados. Su uso esta mas
	 * orientado a cuando se realizan cambios en los datos de los libros, como en
	 * saber si se encuentra en prestamo
	 */
	public final static String FLIBROSAUX = "src\\datosLibros_aux.txt";

	/** Conexion con el archivo de backup de libros almacenados en la biblioteca */
	public final static String FLIBROSBACKUP = "src\\datosLibros_backup.txt";

}
