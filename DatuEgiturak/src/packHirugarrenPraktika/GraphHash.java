package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphHash {
	
	private HashMap<String,ArrayList<String>> g;
	
	public void grafoaSortu(listaAktoreak lAktoreak) {
		// Post: aktoreen zerrendatik grafoa sortzen du
		// Adabegiak aktoreen izenak eta pelikulen izenburuak dira
		// KODEA OSATU
	}
	
	public void print() {
		int i=1;
		for(String s: g.keySet()) {
			System.out.println("Element: " + i++ + "" + s + "-->");
			for(String k: g.get(s)) {
				System.out.println(k + "###");
			}
			System.out.println();
		}
	}
	
	public boolean konektatuta(String a1, String a2) {
		//Kodea osatu
	}
}
