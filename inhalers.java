package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class inhalers extends ElementodeCadenadeMando {
	private static final String INHALERS_TAGNAME = "inhalers";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";


	private static final String FIELD_SEPARATOR = ";";

	public inhalers(ElementodeCadenadeMando s) {
		super(s);
	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		if(name.equals(INHALERS_TAGNAME)) {
			StringBuffer inhalers = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				inhalers.append(readinhalersEntry(reader)).append("\n");
				reader.endObject();
			}
			inhalers.append("\n");
			reader.endArray();
			return inhalers;
		}
		else {
			if(sig!=null) {
				return super.leerJson(name, reader);

			}
			else {
				reader.skipValue();
				System.err.println("Category " + name + " not processed.");
				StringBuffer acabado = new StringBuffer();
				return acabado;
			}

		}
	}

	private String readinhalersEntry(JsonReader reader) throws IOException {
		String nameRef = null;
		String imagRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				nameRef = reader.nextString();
			} else if (name.equals(IMAGE_FIELD_TAGNAME)) {
				imagRef = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return nameRef + FIELD_SEPARATOR+"  " + imagRef;
	}
}
