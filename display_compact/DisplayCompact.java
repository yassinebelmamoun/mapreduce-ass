package cs.bigdatacourse.lab2;

import java.io.*;

public class DisplayCompact{
   //input isd-history.txt
	public static void main(String[] args) throws IOException {		
		
		FileReader file = null;
		
		BufferedReader buffer = null;
		
		PrintWriter std = new PrintWriter("resultats_meteo.txt");
		
		for (String fileName : args) {
			int row_num = 0;
			String row;
			file = new FileReader(fileName);
			buffer = new BufferedReader(file);
			try {
				do {
					row = buffer.readLine();
					row_num++ ;
					if ((row.length() != 0) & (row_num >= 22)) {
						std.println("Code USAF: " + row.substring(0, 6));
						std.println("Nom: " + row.substring(13, 29+13));
						std.println("Pays: " + row.substring(43, 43+2));
						std.println("Altitude: " + row.substring(74, 74+7));
						}
					}
				while(row != null);
			}
			catch(Exception e){
			}
		
			finally 
			{
				if (file != null) try {file.close();} catch (Exception e){ 
				};
				if (buffer !=null) try {buffer.close();} catch (Exception e){
				} 
			}
		}
		std.close();
	}
}