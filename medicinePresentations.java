package salud.isa.gsonMedDB;



import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class medicinePresentations extends ElementodeCadenadeMando {
	private static final String MEDPRES_TAGNAME = "medicinePresentations";

	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String POSOLREF_FIELD_TAGNAME = "posologyRef";	

	private static final String FIELD_SEPARATOR = ";";

	public medicinePresentations(ElementodeCadenadeMando s) {
		super(s);
	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		if(name.equals(MEDPRES_TAGNAME)) {
			StringBuffer medicinePresentationData = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				medicinePresentationData.append(medicinePresentationEntry(reader)).append("\n");
				reader.endObject();
			}
			medicinePresentationData.append("\n");
			reader.endArray();
			return medicinePresentationData;
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

	private String medicinePresentationEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String actRef = null;
		String inhRef = null;
		String dose = null;
		String posRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				actRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				inhRef = new String(res);
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				dose = new String(res);
			} else if (name.equals(POSOLREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				posRef = new String(res);
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR +"  "  + actRef + FIELD_SEPARATOR+"  " + inhRef + FIELD_SEPARATOR+"  " + dose +FIELD_SEPARATOR+"  " + posRef;
	}
}
