package Metodos;

import Objetos.Usuario;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * Dc2.- (dif 5) Realizar una aplicación para gestionar una biblioteca. En la
	 * biblioteca se dispone de libros (no más de 2000), de los que se tiene
	 * almacenado el código (int entre 0 y 1999) y el título. La biblioteca tiene
	 * usuarios. Cada usuario está identificado por un identificador (int) y puede
	 * tener prestados un máximo de 5 libros, también se almacena el nombre del
	 * usuario. Se supondrá que no hay más de 100 usuarios. La aplicación debe
	 * mostrar un menú que permita realizar las siguientes operaciones:
	 * 
	 * 1.- Alta de libros. Se pedirá el código y el título y se guardará como
	 * almacenado
	 * 
	 * 2.- Alta de usuarios. Se pedirá el nombre del mismo y se le asignará un
	 * identificador (entre 0 y 99 ) de entre los disponibles (no asignado a otro
	 * usuario)
	 * 
	 * 3.- Baja de usuarios. Se pedirá el identificador y, si no tiene libros en
	 * préstamo, se le dará de baja. Si tiene libros en préstamo no se le podrá dar
	 * de baja y se mostrará un mensaje indicándolo.
	 * 
	 * 4.- Préstamo de libros. Se pedirá por teclado el identificador del usuario y
	 * el código del libro y si el libro está disponible (se supone que sólo hay un
	 * ejemplar de cada libro) se le prestará al usuario.
	 * 
	 * 5.- Devolución de libro. Se pedirá por teclado el identificador del usuario y
	 * el código del libro que devuelve y, si lo tenía en préstamo, se considerará
	 * devuelto.
	 * 
	 * 6.- Consulta de libro. Se pedirá por teclado el código de un libro y se
	 * indicará si está o no en préstamo y en caso de estar prestado el código y
	 * nombre del usuario que lo tiene.
	 * 
	 * 7.- Listado de usuarios. Se mostrarán en pantalla los identificadores y
	 * nombres de usuarios y los títulos de los libros que posee cada usuario.
	 * 
	 * 8.- Listado de libros no prestados: Se mostrará en pantalla los códigos y
	 * títulos de todos los libros no prestados.
	 * 
	 * 0.- Fin de la aplicación.
	 * 
	 * Se deberá realizar cada opción en un método diferente.
	 */

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		Menu m = new Menu();
		m.menu();
		

	}
}
