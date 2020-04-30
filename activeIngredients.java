package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class activeIngredients extends ElementodeCadenadeMando {
		private static final String ACTIVEINGREDIENTS_TAGNAME = "activeIngredients";
		
		private static final String NAME_FIELD_TAGNAME = "name";

		public activeIngredients(ElementodeCadenadeMando s) {
			super(s);
			
		}

		public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
			if(name.equals(ACTIVEINGREDIENTS_TAGNAME)) {
				StringBuffer activeingredients = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					reader.beginObject();
					activeingredients.append(readActiveIngredients(reader)).append("\n");
					reader.endObject();
				}
				activeingredients.append("\n");
				reader.endArray();
				return activeingredients;
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

		private String readActiveIngredients(JsonReader reader) throws IOException {
			String actIngr = null;
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(NAME_FIELD_TAGNAME)) {
					actIngr = reader.nextString();
				} else {
					reader.skipValue();
				}
			}

			return actIngr;
		}
}