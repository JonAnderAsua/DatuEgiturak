package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;


public class GraphHash{
	
	private HashMap<String,ArrayList<String>> g=new HashMap<String, ArrayList<String>>();
	private HashMap<String, Double> pageRank = this.pageRank();
	
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
	
	public HashMap<String,Double> pageRank(){
		HashMap<String,Double> lag = new HashMap<String, Double>();
		HashMap<String,Double> emaitza = new HashMap<String, Double>();
		double errorea = 100;
		double p=0.85;
		int kopOsoa=this.g.size();
		double hasierakoBalioa = 1/kopOsoa;
		Iterator<String> itr = this.g.keySet().iterator(); //hasieraketa egiteko 
		Iterator<String> itr2 = this.g.keySet().iterator();
		
		while(itr.hasNext()) { //lehenengo iterazioa, adabegiak balio bera dute
			String next = itr.next();
			lag.put(next,hasierakoBalioa);
		}
		
		while (errorea > 0.0001) { 
			String unekoa = itr2.next();
			double puntUnekoa = lag.get(unekoa);
			ArrayList<String> erlazioak = this.g.get(unekoa); 
			int listarenLuzera = erlazioak.size();
			Iterator<String> itrErlazioak = erlazioak.iterator();
			
			while (itrErlazioak.hasNext()) { //Puntuazioa banatzeko
				String erlazioaLag = itrErlazioak.next();
				if(!emaitza.containsKey(erlazioaLag)) {
					emaitza.put(erlazioaLag, puntUnekoa/listarenLuzera);
				}
				else {
					double eguneratu = emaitza.get(erlazioaLag) + (puntUnekoa/listarenLuzera) ;
					emaitza.put(erlazioaLag, eguneratu);
				}
			}
			
			Iterator<String> itrFor = emaitza.keySet().iterator();
			
			while (itrFor.hasNext()) { // balio bakoitzari formula aplikatu
				String unekoaFor = itrFor.next();
				double balioa = emaitza.get(unekoaFor);
				balioa = (balioa * p) + ((1 - p) / kopOsoa);
				emaitza.put(unekoaFor, balioa);
			}
			
			Iterator<String> itrErrorea = emaitza.keySet().iterator();
			double unekoa1Balioa = 0;
			
			while (itrErrorea.hasNext()) {
				String unekoa1 = itrErrorea.next();
				unekoa1Balioa = unekoa1Balioa + Math.abs(emaitza.get(unekoa1) - lag.get(unekoa1));
			}
			
			lag = emaitza;
			errorea = unekoa1Balioa;
		}
		return lag;
	}
	
	public ArrayList<Bikote> bilatzea(String gakoHitz){
		HashMap<String,Double> lag = new HashMap<String, Double>();
		Iterator<String> itr = this.pageRank.keySet().iterator();
		ArrayList<Bikote> emaitza = new ArrayList<Bikote>();
		while(itr.hasNext()) { 
			String unekoa = itr.next();
			if(unekoa.contains(gakoHitz.toLowerCase())){
				lag.put(unekoa, this.pageRank.get(unekoa));
			}
		}
		
		lag = sortByValue(lag); //Hasha ordenatzeko
		
		Iterator <String> itrOrd = lag.keySet().iterator(); 
		
		HashMap<String, Double> hash = new HashMap<String, Double>();
		
		if(itr.hasNext()) {
			String unekoa = itr.next();
			if(ListaAktoreakOsoa.getNireListaAktoreakOsoa().badagoString(unekoa)) { //Aktore bat bada
				Iterator<Pelikula> itrP = ListaAktoreakOsoa.getNireListaAktoreakOsoa().aktoreaBilatuIzenez(unekoa).pelikulenLista().getIteradorea();
				while(itrP.hasNext()) {
					Pelikula uPelikula = itrP.next();
					String izena = uPelikula.getIzena();
					hash.put(izena,pageRank.get(uPelikula));
				}
			}
			else { //Pelikula bat bada
				Pelikula p = new Pelikula (unekoa);
				Iterator<Aktorea> itrA= ListaAktoreakOsoa.getNireListaAktoreakOsoa().pelikularenAktoreak(p).getIteradorea();
				while (itrA.hasNext()) {
					Aktorea a = itrA.next();
					String izenaA = a.getIzena();
					hash.put(izenaA, pageRank.get(izenaA));				
				}
			}
		}
		
		hash.sortByValue(hash);
		
		Iterator<String> itrEmaitza = hash.keySet().iterator();
		
		while(itr.hasNext()) {
			String unekoBik = itr.next();
			Bikote sartzeko = new Bikote(unekoBik, pageRank.get(unekoBik));
			emaitza.add(sartzeko);
		}
		
		return emaitza;
	}
	
	public HashMap<String, Double> sortByValue(HashMap<String, Double> hm)  { 
        List<Map.Entry<String, Double> > list = new LinkedList<Map.Entry<String, Double> >(hm.entrySet()); 
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
            public int compare(Map.Entry<String, Double> o2,  Map.Entry<String, Double> o1)  { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
        for (Map.Entry<String, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
}
