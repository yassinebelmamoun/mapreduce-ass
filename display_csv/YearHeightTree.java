package cs.bigdatacourse.lab2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class YearHeightTree{
	public static void main(String[] args) throws FileNotFoundException {

		BufferedReader buffer_reader = null;
		FileReader file_reader = null;

		PrintWriter std = new PrintWriter("result_trees.txt");
		
		String year = new String();
		String height = new String();

		for (String file_name: args) {

			// Initialisation
			buffer_reader = null;
			file_reader = null;
			String row;

			file_reader = new FileReader(file_name);
			buffer_reader = new BufferedReader(file_reader);

			try {
				do {
					row = buffer_reader.readLine();
					HashMap<String, String> tree_data = Tree.get_data(row);

					year = tree_data.get("ANNEE");
					height = tree_data.get("HAUTEUR");
					
					std.println("The Year: " + year);
					std.println("The Height: " + height);
				}
				while(row != null);
			} catch(Exception e){
			}
			finally {
				if (file_reader != null) try {file_reader.close();} catch (Exception e){};if (buffer_reader !=null) try {buffer_reader.close();} catch (Exception e){}
			}
		}
		std.close();
		}
}