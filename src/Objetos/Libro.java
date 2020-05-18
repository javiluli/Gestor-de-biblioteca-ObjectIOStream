/**
 *
 * @author Javier Delgado Rodriguez
 */
package Objetos;

import java.io.Serializable;

/**
 * The Class Libro.
 */
public class Libro implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cod libro. */
	public int codigo;

	/** The titulo. */
	public String titulo;

	/** The prestado. */
	boolean prestado;

	/**
	 * Instantiates a new libro.
	 */
	public Libro() {
	}

	/**
	 * Instantiates a new libro.
	 *
	 * @param codigo   the codigo
	 * @param titulo   the titulo
	 * @param prestado the prestado
	 */
	public Libro(int codigo, String titulo, boolean prestado) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.prestado = prestado;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	@Override
	public String toString() {
		return "Codigo: " + codigo + " | Prestado: " + prestado + " | Titulo: " + titulo;
	}

}
