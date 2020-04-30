package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class posologies  extends ElementodeCadenadeMando {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	public posologies(ElementodeCadenadeMando s) {
		super(s);

	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		if(name.equals(POSOLOGIES_TAGNAME)) {
			StringBuffer posologieData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				posologieData.append(readPosologieEntry(reader)).append("\n");
				reader.endObject();
			}
			posologieData.append("\n");
			reader.endArray();
			return posologieData;
		}
		else {
			if(sig!=null) {
				StringBuffer readData = super.leerJson(name, reader);
				return readData;
			}
			else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
				StringBuffer acabado = new StringBuffer();
				return acabado;
			}

		}
	}

	private String readPosologieEntry(JsonReader reader) throws IOException {

		String description = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(DESCRIPTION_FIELD_TAGNAME)) {
				description = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return description;
	}

}
