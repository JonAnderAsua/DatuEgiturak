package packHirugarrenPraktika;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.io.File;
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
		
		long startTime = System.nanoTime();
		String unekoAktore=null;
		String[] linea = null;
	    File file = new File("/home/jonander/FilmsActors20162017.txt"); //Errorea ematen du eta ez dakit zergatik 
	    Scanner sc = new Scanner(file); 
	    while (sc.hasNextLine()) {
		    linea = sc.nextLine().replace(" &&& ", "<").replace("> ", ">").split("[<>]+"); 
		    String pelikulaIzena=linea[0].replace("-","");
		    Pelikula Pelikula1 =new Pelikula(pelikulaIzena);
	    	for(int i=1;i<linea.length;i++) { 
	    			unekoAktore=linea[i].replace(", ", " ");
	    			String izena = unekoAktore;
	    			Aktorea Aktore1 = new Aktorea(izena);//eeeeeeeeeee
	    			if(ListaAktoreakOsoa.nireListaAktoreakOsoa.badago(Aktore1)){
	    				ListaAktoreakOsoa.nireListaAktoreakOsoa.aktoreaBilatu(Aktore1).gehituPelikula(Pelikula1);
	    			}else {
		    			Aktore1.gehituPelikula(Pelikula1);
		    			ListaAktoreakOsoa.nireListaAktoreakOsoa.aktoreaGehitu(Aktore1);	    
	    			}			
	    		}
	    	ListaPelikulakOsoa.getNireListaPelikulakOsoa().gehituPelikula(Pelikula1);
	    }

//		System.out.println(timeElapsed / 1000000000 + " segundu behar ditu" );
		sc.close();
		
	}
	
	public void aktoreaGehitu(Aktorea pAktorea){
		//Aurre:
		//Post: Aktorea ez badago HashMapean txertatzen du
		if (!this.map.containsValue(pAktorea)){ 
			this.map.put(pAktorea.getIzena(),pAktorea);
		}
	}
	
	public Aktorea aktoreaBilatu(Aktorea pAktorea){
		//Aurre:
		//Post: Aktorea HashMapean bilatzen du, aurkitzen badu Aktore hori bueltatzen du, bestela null bueltatzen du
		Aktorea emaitza=null;
		if(this.map.containsValue(pAktorea)){
			emaitza=pAktorea;
		}
		return emaitza;
	}
	
	
	public Aktorea aktoreaBilatuIzenez(String pIzena) {
		return this.map.get(pIzena);
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
	public boolean badagoString(String pIzena) {
		return this.map.containsKey(pIzena);
	}
	public int luzera() {
		return this.map.size();
	}

	public int size() {
		return this.map.size();
	}

	public Object[] keySet() {
		// TODO Auto-generated method stub
		return this.map.keySet().toArray();
	}
	
	public boolean badago(Aktorea a) {
		return this.map.containsKey(a);
	}
}