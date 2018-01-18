package fgopkg;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RessourcesImport {
	public RessourcesImport(List<String> arg) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("matlistBDD.csv"));
        //Scanner scanner = new Scanner(new FileReader("Project_0/WSEL Pairing System/WSELBDD.csv"));
		scanner.useDelimiter(";");
        while(scanner.hasNext()){
            arg.add(scanner.next());
        }
        scanner.close();
		
		arg.add("Crystallized Lore");
		arg.add("Phoenix Feather");
		arg.add("Seed of Yggdrassil");
		arg.add("Proof of Hero");
		arg.add("Void's Dust");
		arg.add("Dragon's Reverse Scale");
		arg.add("Claw of Chaos");
		arg.add("Ghost Lantern");
	}
}
