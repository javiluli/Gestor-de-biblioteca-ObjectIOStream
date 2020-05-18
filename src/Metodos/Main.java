package Metodos;

import Objetos.Usuario;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * Dc2.- (dif 5) Realizar una aplicaci�n para gestionar una biblioteca. En la
	 * biblioteca se dispone de libros (no m�s de 2000), de los que se tiene
	 * almacenado el c�digo (int entre 0 y 1999) y el t�tulo. La biblioteca tiene
	 * usuarios. Cada usuario est� identificado por un identificador (int) y puede
	 * tener prestados un m�ximo de 5 libros, tambi�n se almacena el nombre del
	 * usuario. Se supondr� que no hay m�s de 100 usuarios. La aplicaci�n debe
	 * mostrar un men� que permita realizar las siguientes operaciones:
	 * 
	 * 1.- Alta de libros. Se pedir� el c�digo y el t�tulo y se guardar� como
	 * almacenado
	 * 
	 * 2.- Alta de usuarios. Se pedir� el nombre del mismo y se le asignar� un
	 * identificador (entre 0 y 99 ) de entre los disponibles (no asignado a otro
	 * usuario)
	 * 
	 * 3.- Baja de usuarios. Se pedir� el identificador y, si no tiene libros en
	 * pr�stamo, se le dar� de baja. Si tiene libros en pr�stamo no se le podr� dar
	 * de baja y se mostrar� un mensaje indic�ndolo.
	 * 
	 * 4.- Pr�stamo de libros. Se pedir� por teclado el identificador del usuario y
	 * el c�digo del libro y si el libro est� disponible (se supone que s�lo hay un
	 * ejemplar de cada libro) se le prestar� al usuario.
	 * 
	 * 5.- Devoluci�n de libro. Se pedir� por teclado el identificador del usuario y
	 * el c�digo del libro que devuelve y, si lo ten�a en pr�stamo, se considerar�
	 * devuelto.
	 * 
	 * 6.- Consulta de libro. Se pedir� por teclado el c�digo de un libro y se
	 * indicar� si est� o no en pr�stamo y en caso de estar prestado el c�digo y
	 * nombre del usuario que lo tiene.
	 * 
	 * 7.- Listado de usuarios. Se mostrar�n en pantalla los identificadores y
	 * nombres de usuarios y los t�tulos de los libros que posee cada usuario.
	 * 
	 * 8.- Listado de libros no prestados: Se mostrar� en pantalla los c�digos y
	 * t�tulos de todos los libros no prestados.
	 * 
	 * 0.- Fin de la aplicaci�n.
	 * 
	 * Se deber� realizar cada opci�n en un m�todo diferente.
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
