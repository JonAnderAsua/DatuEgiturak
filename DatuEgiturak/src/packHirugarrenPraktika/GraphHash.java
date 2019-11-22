package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GraphHash{
	
	private HashMap<String,ArrayList<String>> g=new HashMap<String, ArrayList<String>>();
	
	public void grafoaSortu(listaAktoreak lAktoreak) {
		// Post: aktoreen zerrendatik grafoa sortzen du
		// Adabegiak aktoreen izenak eta pelikulen izenburuak dira
		
		for(int i=0;i<=lAktoreak.luzera();i++) {	
			Aktorea a=lAktoreak.posizioanBueltatu(i);
			listaFilmak l=a.pelikulenLista();
			ArrayList<String> listaPe=new ArrayList<String>();
			
			for(int j=0;j<l.luzera();j++) { //Pelikulen zerrenda ArrayList<String> bihurtzen dugu
				Pelikula p=l.posizioanLortu(j);
				listaPe.add(p.getIzena());
				listaAktoreak lAkt=lAktoreak.pelikularenAktoreakBueltatu(p);
				ArrayList<String>listaAkt=new ArrayList<String>();
				
				for(int u=0;u<=lAkt.luzera();u++) { //Pelikula bakoitzaren aktoreen zerrenda ArrayList<String> bihurtzen du
					Aktorea akt=lAkt.posizioanBueltatu(u);
					listaAkt.add(akt.getIzena());
				}
				
				g.put(p.getIzena(), listaAkt); //Pelikula bakoitzaren erlazioak sortzeko
			}
			g.put(a.getIzena(), listaPe);
		}
	}
	
	public void grafoaSortuHashetik() {
		Object[] lAktoreak = ListaAktoreakOsoa.getNireListaAktoreakOsoa().keySet();
		
		for(int i=0;i<lAktoreak.length;i++) {
			String izena = lAktoreak [i].toString();
			Aktorea a=ListaAktoreakOsoa.getNireListaAktoreakOsoa().aktoreaBilatuIzenez(izena);
			listaFilmak l=a.pelikulenLista();
			ArrayList<String> listaPe=new ArrayList<String>();
			
			for(int j=0;j<l.luzera();j++) { //Pelikulen zerrenda ArrayList<String> bihurtzen dugu
				Pelikula p=l.posizioanLortu(j);
				listaPe.add(p.getIzena());
				listaAktoreak lAkt=ListaAktoreakOsoa.getNireListaAktoreakOsoa().pelikularenAktoreak(p);
				ArrayList<String>listaAkt=new ArrayList<String>();
				
				for(int u=0;u<=lAkt.luzera();u++) { //Pelikula bakoitzaren aktoreen zerrenda ArrayList<String> bihurtzen du
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
			System.out.println("Element: " + i++ + "-" + s + "-->");
			for(String k: g.get(s)) {
				System.out.println(k + "###");
			}
			System.out.println();
		}
	}
	
	public boolean konektatuta(String a1, String a2) {
		//Aurre:
		//Post: Bi String emanda grafoaren bitartez konektatuta dauden ala ez adierazten du, 
		//konektatuta badaude true bueltatuko du, bestela false
		Queue<String> aztertuGabe=new LinkedList<String>();
		ArrayList<String> aztertuak=new ArrayList<String>();
		aztertuGabe.add(a1);
		String lag=a1;
		if(g.containsKey(a1)&&g.containsKey(a2)) {
			while(!lag.equals(a2) && !aztertuGabe.isEmpty()) {
				lag=aztertuGabe.remove();
				ArrayList<String> lista=g.get(lag);
				for(int i=0;i<lista.size();i++) {
					String sartzeko=lista.get(i);
					if(!aztertuak.contains(sartzeko)) {
						if(!aztertuGabe.contains(sartzeko)) {
							aztertuGabe.add(sartzeko);
						}
					}
					if(!aztertuak.contains(lag)) {
						aztertuak.add(lag);
					}
				}
			}
			if(lag.contentEquals(a2)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void denboraNeurtu(int n) {
		//Aurre: g sortuta dago, grafoa sortu deitu du
		StopWatch erloju = new StopWatch();
		Object[] l=g.keySet().toArray();	
		Random r=new Random();
		for(int i=0;i<=n;i++) {
			int a=r.nextInt(l.length+1); 
			String ak1=l[a].toString();
			int b=r.nextInt(l.length+1);
			String ak2=l[b].toString();
			if(ak1!=ak2) {
				konektatuta(ak1,ak2);
			}
		}
		double denbora=erloju.elapsedTime();
		System.out.println(denbora/100);
	}
	
}
