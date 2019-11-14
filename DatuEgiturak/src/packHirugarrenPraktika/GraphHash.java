package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class GraphHash{
	
	private HashMap<String,ArrayList<String>> g=new HashMap<String, ArrayList<String>>();
	
	public void grafoaSortu(listaAktoreak lAktoreak) {
		// Post: aktoreen zerrendatik grafoa sortzen du
		// Adabegiak aktoreen izenak eta pelikulen izenburuak dira
		// KODEA OSATU
		for(int i=0;i<lAktoreak.luzera();i++) {
			Aktorea a=lAktoreak.posizioanBueltatu(i);
			listaFilmak l=a.pelikulenLista();
			ArrayList<String> listaPe=new ArrayList<String>();
			
			for(int j=0;j<l.luzera();j++) { //Pelikulen zerrenda ArrayList<String> bihurtzen dugu
				Pelikula p=l.posizioanLortu(j);
				listaPe.add(p.getIzena());
				listaAktoreak lAkt=lAktoreak.pelikularenAktoreakBueltatu(p);
				ArrayList<String>listaAkt=new ArrayList<String>();
				
				for(int u=0;u<lAkt.luzera();u++) {
					Aktorea akt=lAkt.posizioanBueltatu(u);
					listaAkt.add(akt.getIzena());
				}
				
				g.put(p.getIzena(), listaAkt); //Pelikula bakoitzaren erlazioak sortzeko
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
		//Hay que hacer un backpointer
		Queue<String> ilara;
		HashMap aztertuak=new HashMap<String, ArrayList<String>>();
		if(a1.equals(a2)){
			return true;
		}
		else{
		
			ilara.add(a1);
			while(!ilara.isEmpty()) {
				
			}
		}
		
	}
}
