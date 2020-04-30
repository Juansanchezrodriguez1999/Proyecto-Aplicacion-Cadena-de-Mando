package salud.isa.gsonMedDB;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			userManualSteps mans = new userManualSteps(null);
			userManualPhysioSteps usm = new userManualPhysioSteps(mans);
			medicinePresentations medp = new medicinePresentations(usm);
			posologies pos = new posologies (medp);
			inhalers inh = new inhalers (pos);
			physiotherapies phy = new physiotherapies(inh);
			activeIngredients act = new activeIngredients(phy);
			medicines me = new medicines(act);
			rescueMedicinesPresentations rm =new rescueMedicinesPresentations(me);
			DatabaseJSonReader ccm = new DatabaseJSonReader(rm);
			System.out.println(ccm.parse("./datos.json"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}