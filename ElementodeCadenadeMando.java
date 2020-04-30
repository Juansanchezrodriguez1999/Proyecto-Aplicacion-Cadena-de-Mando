package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ElementodeCadenadeMando {
	protected ElementodeCadenadeMando sig;
	
	public ElementodeCadenadeMando(ElementodeCadenadeMando s) {
		sig=s;
	}

	public StringBuffer leerJson(String name, JsonReader reader) throws IOException {
		return sig.leerJson(name, reader);
	}

}

