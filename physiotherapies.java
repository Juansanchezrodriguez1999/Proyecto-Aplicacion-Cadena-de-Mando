package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class physiotherapies extends ElementodeCadenadeMando {
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";

	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";


	private static final String FIELD_SEPARATOR = ";";

	public physiotherapies(ElementodeCadenadeMando s) {
		super(s);
	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		if(name.equals(PHYSIOTHERAPIES_TAGNAME)) {
			StringBuffer physiotherapies = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				physiotherapies.append(readPhysiotherapiesEntry(reader)).append("\n");
				reader.endObject();
			}
			physiotherapies.append("\n");
			reader.endArray();
			return physiotherapies;
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

	private String readPhysiotherapiesEntry(JsonReader reader) throws IOException {
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
		return nameRef + FIELD_SEPARATOR +"  " + imagRef;
	}
}
