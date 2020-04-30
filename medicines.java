package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class medicines  extends ElementodeCadenadeMando {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public medicines(ElementodeCadenadeMando s) {
		super(s);
		
	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		if(name.equals(MEDICINES_TAGNAME)) {
			StringBuffer medicineData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				medicineData.append(readMedicineEntry(reader)).append("\n");
				reader.endObject();
			}
			medicineData.append("\n");
			reader.endArray();
			return medicineData;
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

	private String readMedicineEntry(JsonReader reader) throws IOException {

		String medName = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(NAME_FIELD_TAGNAME)) {
				medName = reader.nextString();
			} else {
				reader.skipValue();
			}
		}

		return medName;
	}

}
