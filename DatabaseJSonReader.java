package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

public class DatabaseJSonReader {
	ElementodeCadenadeMando cadenademando;


	public DatabaseJSonReader(ElementodeCadenadeMando cm) {
		cadenademando=cm;
	}

	public void setCadenaDeMando(ElementodeCadenadeMando ncm) {
		cadenademando=ncm;
	}
	
	public String parse(String jsonFileName) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			String name = reader.nextName();

			readData.append(cadenademando.leerJson(name,reader)).append("\n");
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}	
}
