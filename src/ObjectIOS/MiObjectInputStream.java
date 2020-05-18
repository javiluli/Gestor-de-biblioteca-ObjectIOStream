package ObjectIOS;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.InputStream;

public class MiObjectInputStream extends ObjectInputStream {
	MiObjectInputStream() throws IOException {
	}

	public MiObjectInputStream(InputStream os) throws IOException {
		super(os);
	}

	/*
	 * Cuando se ejecuta un readObject de esta clase se invoca implicitamente a
	 * readStreamHeader y como lo creo vacío no se lee la cabecera
	 */
	protected void readStreamHeader() throws IOException {
	}
}
