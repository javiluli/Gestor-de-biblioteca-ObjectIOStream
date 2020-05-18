package ObjectIOS;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {
	MiObjectOutputStream() throws IOException {
	}

	public MiObjectOutputStream(OutputStream os) throws IOException {
		super(os);
	}

	/*
	 * Cuando se ejecuta un writeObject de esta clase se invoca implicitamente a
	 * writeStreamHeader y como lo creo vac�o no se escribe la cabecera salvo que el
	 * fichero se escriba desde el inicio (porque est� vacio o porque no se a�ada),
	 * en ese caso autom�ticamente se a�ade la cabecera.
	 */
	protected void writeStreamHeader() throws IOException {
	}

}
