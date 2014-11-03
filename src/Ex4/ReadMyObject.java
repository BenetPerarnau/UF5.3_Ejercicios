package Ex4;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class ReadMyObject extends ObjectInputStream {

	
	public ReadMyObject(InputStream in) throws IOException {
		super(in);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void readStreamHeader() throws IOException,
			StreamCorruptedException {
		reset();
	}

	

}
