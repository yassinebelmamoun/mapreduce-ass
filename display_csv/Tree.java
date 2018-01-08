package cs.bigdatacourse.lab2;

import java.util.HashMap;

public class Tree{
	public static HashMap<String, String> get_data(String line) {
		
		HashMap<String, String> tree_data = new HashMap<String, String>();
		tree_data.clear();

		String[] data = line.split(";");
		String[] columns_name 	= {"GEOPOINT", "ARRONDISSEMENT", "GENRE", "ESPECE", "FAMILLE", "ANNEE", "HAUTEUR", "CIRCONFERENCE", "ADRESSE", "NOM", "VARIETE", "OBJECTID", "NOM_E"};
        
        int inc = 0;
        
        for (String word:columns_name ) {
        		tree_data.put(word, data[inc]);
        		inc ++;
        }
        return tree_data;
	}		
}
