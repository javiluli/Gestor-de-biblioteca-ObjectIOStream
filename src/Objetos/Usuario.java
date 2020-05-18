/*
 *
 * @author Javier Delgado Rodriguez
 */
package Objetos;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Class Usuario.
 */
public class Usuario implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	public int id;

	/** The nombre. */
	public String nombre;
	public final static int MAXLIBROSPRES = 5;
	/** The Libros prestados. */
	Libro[] librosPrestados = new Libro[MAXLIBROSPRES];

	/**
	 * Instantiates a new usuario.
	 */
	public Usuario() {
	}

	public Usuario(int id, String nombre, Libro[] librosPrestados) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.librosPrestados = librosPrestados;
	}

	public Libro[] getLibrosPrestados() {
		return librosPrestados;
	}

	public void setLibrosPrestados(Libro[] librosPrestados) {
		this.librosPrestados = librosPrestados;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Nombre: " + nombre + "| Libros prestados: " + Arrays.toString(librosPrestados);
	}

}
