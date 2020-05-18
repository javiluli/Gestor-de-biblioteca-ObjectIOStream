//package Recursos;
//import java.io.EOFException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.util.Arrays;
//
//import Const;
//import Libro;
//
//public class Usuario {
//
//	String nombre;
//	int dni;
//	public int id;
//	Libro[] librosPrestados = new Libro[Const.MAXLIBROSPRES];
//
//	/**
//	 * Gets the tamano registro.
//	 *
//	 * @return the tamano registro
//	 */
//	int getTamanoRegistro() {
//		return ((Const.TAMANONOMBRE * 6) + (4 * 2));
//		// tamaño del array con el nombre + (tamaño de 1 int de matricula (4 bytes) *
//		// tamaño de 10 int de notas (4 bytes por cada nota))
//	}
//
//	/**
//	 * Pasar stringa array bytes.
//	 *
//	 * @param nombre the nombre
//	 * @return the byte[]
//	 */
//	byte[] pasarStringaArrayBytes(String nombre) {
//		byte nombreB[] = new byte[Const.TAMANONOMBRE];
//		for (int i = 0; i < nombre.length() && i < Const.TAMANONOMBRE; i++)
//			nombreB[i] = (byte) nombre.charAt(i);
//		for (int i = nombre.length(); i < Const.TAMANONOMBRE; i++)
//			nombreB[i] = (byte) 0;
//		return nombreB;
//	}
//
//	/**
//	 * Elimina del string los caracteres vacíos (0)
//	 * 
//	 * @param s
//	 * @return el string sin vacíos.
//	 */
//	String eliminarVacios(String s) {
//		StringBuffer sb = new StringBuffer(s);
//		int i = 0;
//		while (sb.charAt(i) != (char) 0)
//			i++;
//		sb.setLength(i);
//		return (sb.toString());
//	}
//
//	void escribir(RandomAccessFile f) throws IOException {
//		byte nombreBusuario[];
//		nombreBusuario = pasarStringaArrayBytes(nombre);
//		f.write(nombreBusuario);
//		f.writeInt(dni);
//		f.write(id);
//
//		byte nombreBLibro[];
//		for (int i = 0; i < librosPrestados.length; i++) {
//			nombreBLibro = pasarStringaArrayBytes(librosPrestados[i].titulo);
//			f.write(nombreBLibro);
//		}
//		// System.out.println ("tamano"+ nombreB.length);
//	}
//
//	boolean leer(RandomAccessFile f) throws IOException {
//		// devuelve true si lee algo y false si no devuelve nada
//		try {
//			byte nombreB[] = new byte[Const.TAMANONOMBRE];
//			f.read(nombreB, 0, Const.TAMANONOMBRE);
//			// nombre=new String(nombreB, "UTF-8"); //convierte el array de bytes en un
//			// string con la notación UTF-8
//			nombre = new String(nombreB, "ISO-8859-1"); // Mejor 8859 para ñ y acentos.
//			nombre = eliminarVacios(nombre);
//			dni = f.readInt();
//			id = f.readInt();
//
//			byte nombreBLibro[] = new byte[Const.TAMANONOMBRE];
//			for (int i = 0; i < librosPrestados.length; i++) {
//				f.read(nombreBLibro, 0, Const.TAMANONOMBRE);
//			}
//			return true;
//		} catch (EOFException e) {
//			return false;
//		}
//	}
//
//	void mostrar() {
//		System.out.println("Usuario [nombre=" + nombre + ", dni=" + dni + ", id=" + id + ", librosPrestados="
//				+ Arrays.toString(librosPrestados) + "]");
//	}
//
//	@Override
//	public String toString() {
//		return "Usuario [nombre=" + nombre + ", dni=" + dni + ", id=" + id + ", librosPrestados="
//				+ Arrays.toString(librosPrestados) + "]";
//	}
//
//}
