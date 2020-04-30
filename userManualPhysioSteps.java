package salud.isa.gsonMedDB;


import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class userManualPhysioSteps extends ElementodeCadenadeMando {
	private static final String USERMANUALPHYSIOSTEPS_TAGNAME = "userManualPhysioSteps";

	private static final String STEPTIT_FIELD_TAGNAME = "stepTitle";
	private static final String STEPIM_FIELD_TAGNAME = "stepImage";
	private static final String STEPTEX_FIELD_TAGNAME = "stepText";
	private static final String STEPPHY_FIELD_TAGNAME = "physioRef";
	
	private static final String FIELD_SEPARATOR = ";";

	public userManualPhysioSteps(ElementodeCadenadeMando s) {
		super(s);
	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		if(name.equals(USERMANUALPHYSIOSTEPS_TAGNAME)) {
			StringBuffer userManualPhysioSteps = new StringBuffer();
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				userManualPhysioSteps.append(readUserManualPhysioStepsEntry(reader)).append("\n");
				reader.endObject();
			}
			userManualPhysioSteps.append("\n");
			reader.endArray();
			return userManualPhysioSteps;
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

	private String readUserManualPhysioStepsEntry(JsonReader reader) throws IOException {
		String steptit = null;
		String stepim = null;
		String steptex = null;
		String stepphy = null;
		
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTIT_FIELD_TAGNAME)) {
				steptit = reader.nextString();
			} else if (name.equals(STEPIM_FIELD_TAGNAME)) {
				stepim = reader.nextString();
			} else if (name.equals(STEPTEX_FIELD_TAGNAME)) {
				steptex = reader.nextString();
			} else if (name.equals(STEPPHY_FIELD_TAGNAME)) {
				stepphy = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return steptit + FIELD_SEPARATOR +"  " + stepim+ FIELD_SEPARATOR +"  " + steptex+ FIELD_SEPARATOR +"  " + stepphy;
	}
}