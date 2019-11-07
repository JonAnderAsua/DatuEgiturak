package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class GraphHash<T> {
	
	private HashMap<String,ArrayList<String>> g;
	
	public void grafoaSortu(listaAktoreak lAktoreak) {
		// Post: aktoreen zerrendatik grafoa sortzen du
		// Adabegiak aktoreen izenak eta pelikulen izenburuak dira
		// KODEA OSATU
		for(int i=0;i<lAktoreak.luzera();i++) {
			Aktorea a=lAktoreak.posizioanBueltatu(i);
			listaFilmak l=a.pelikulenLista();
			ArrayList<String> listaPe=new ArrayList<String>();
			for(int j=0;j<l.luzera();j++) {
				Pelikula p=l.
			}
			g.put(a.getIzena(), listaPe);
		}
		
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
		
		Queue<String> ilara=new Queue<String>();
		ArrayList<String> aztertuak=new ArrayList<String>();
		
		ilara.add(a1);
		while(!ilara.isEmpty()) {
			ilara.add
		}
		
	}
}
