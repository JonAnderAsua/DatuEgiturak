package packHirugarrenPraktika;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ListaAktoreakOsoa {
	private HashMap<String,Aktorea> map=null;
	private static ListaAktoreakOsoa nireListaAktoreakOsoa;
	
	private ListaAktoreakOsoa() {
		this.map=new HashMap<String,Aktorea>();
	}
	
	public static ListaAktoreakOsoa getNireListaAktoreakOsoa() {
		if(nireListaAktoreakOsoa==null) {
			nireListaAktoreakOsoa=new ListaAktoreakOsoa();
		}
		return nireListaAktoreakOsoa;
	}
	
	public void cargarLista(String nomF){  
		//Aurre:
		//Post: Fitxategia existitzen bada bi HashMap sortzen ditu, bat pelikula guztiekin eta beste bat aktore guztiekin
		try {
			Scanner entrada = new Scanner(new FileReader(nomF));      
			String linea;     
			while (entrada.hasNext()) {         
				linea = entrada.nextLine();         
				String[]a=linea.split("\\s--->\\s");
				String[]aktoreak=a[1].split("\\s&&&\\s");
				Pelikula peli=ListaPelikulakOsoa.getNireListaPelikulakOsoa().gehituPelikula(a[0]);
				for(int i=0;i<aktoreak.length;i++){
					ListaAktoreakOsoa.getNireListaAktoreakOsoa().aktoreaGehitu(aktoreak[i]);
					Aktorea akt=new Aktorea(aktoreak[i]);
					akt.gehituPelikula(peli);
				}
			}
		entrada.close(); 
		}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void aktoreaGehitu(String pAktorea){
		//Aurre:
		//Post: Aktorea ez badago HashMapean txertatzen du
		Aktorea a=new Aktorea(pAktorea);
		if (!this.map.containsKey(a)){ 
			this.map.put(pAktorea,a);
		}
	}
	
	public Aktorea aktoreaBilatu(Aktorea pAktorea){
		//Aurre:
		//Post: Aktorea HashMapean bilatzen du, aurkitzen badu Aktore hori bueltatzen du, bestela null bueltatzen du
		Aktorea emaitza=null;
		if(this.map.containsKey(pAktorea)){
			emaitza=pAktorea;
		}
		return emaitza;
	}
	
	public listaAktoreak ordenatu() {
		//Aurre
		//Post: Mergesort teknika erabiliz HashMapa lista bat bihurtzen du eta ordenatzen du
		listaAktoreak l= new listaAktoreak();
		ArrayList<String> listaBerria = new ArrayList<String>(map.keySet());
		l=ListaAktoreakOsoa.getNireListaAktoreakOsoa().stringetikAktorera(listaBerria);
		return l;
	}
	
	public listaAktoreak stringetikAktorera(ArrayList<String> pArray) {
		listaAktoreak l=new listaAktoreak();
		for(int i=0;i<=pArray.size();i++) {
			String izena=pArray.get(i);
			Aktorea a=new Aktorea(izena);
			l.aktoreaGehitu(a);
		}
		return l;
	}
	
	public listaAktoreak pelikularenAktoreak(Pelikula pPelikula) {
		//Aurre:
		//Post:Pelikula bat sartuta pelikulan parte hartu duten aktoreen zerrenda bueltatzen du
		listaAktoreak lista=new listaAktoreak();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry e = (Map.Entry)it.next();
		    Aktorea a= (Aktorea) e.getValue();
		    if(a.pelikulanParteHartu(pPelikula)){
		    	lista.aktoreaGehitu(a);
		    }
		}
		return lista;
	}
	
	public int luzera() {
		return this.map.size();
	}
}