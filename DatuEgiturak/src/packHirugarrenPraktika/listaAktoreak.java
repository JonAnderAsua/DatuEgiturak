package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class listaAktoreak {
	private ArrayList<Aktorea> lista;
	
	public listaAktoreak(){
		this.lista= new ArrayList<Aktorea>();
	}
	
	private Iterator<Aktorea> getIteradorea(){
		//Aurre:
		//Post: Zerrendaren iteradorea bueltatzen du
		return this.lista.iterator();
	}
	
	public void aktoreaKendu(Aktorea pAktorea){ 
		//Aurre:
		//Post: Aktorea zerrendan badago ezabatzen du
		if(this.lista.contains(pAktorea)) {
			this.lista.remove(pAktorea);
		}
	}
	
	public void aktoreaGehitu(String pIzena){ 
		//Aurre:
		//Post: Aktorea zerrendan ez badago gehitzen du
		Aktorea a=new Aktorea(pIzena);
		if(!this.lista.contains(a)) {
			this.lista.add(a);
		}
	}
	
	public listaFilmak aktorearenPelikulakBueltatu(Aktorea pAktorea) {
		//Aurre:
		//Post: Aktore baten pelikulak bueltatzen du
		return pAktorea.pelikulenLista();
	}
	
	public void zerrendaOrdenatu(){
		//Aurre:
		//Post: Zerrenda mergesort teknikaren bidez ordenatzen du 
		this.quickSort(this.lista);
	}
	
	private ArrayList<Aktorea> quickSort(ArrayList<Aktorea> list){
	    if (list.isEmpty()) 
	        return list;
	    ArrayList<Aktorea> sorted; 
	    ArrayList<Aktorea> smaller = new ArrayList<Aktorea>(); 
	    ArrayList<Aktorea> greater = new ArrayList<Aktorea>(); 
	    Aktorea pivot = list.get(0); 
	    int i;
	    Aktorea j; 
	    for (i=1;i<list.size();i++){
	        j=list.get(i);
	        if (j.compareTo(pivot)<0) 
	            smaller.add(j);
	        else
	            greater.add(j);
	    }
	    smaller=quickSort(smaller);  // capitalise 's'
	    greater=quickSort(greater);  // sort both halfs recursively
	    smaller.add(pivot);          // add initial pivot to the end of the (now sorted) smaller Vehicles
	    smaller.addAll(greater);     // add the (now sorted) greater Vehicles to the smaller ones (now smaller is essentially your sorted list)
	    sorted = smaller;            // assign it to sorted; one could just as well do: return smaller

	    return sorted;
	}	
	
	public listaAktoreak pelikularenAktoreakBueltatu(Pelikula pPelikula) {
		//Aurre:
		//Post: Pelikula bat sartuta pelikulan parte hartzen duten aktoreen zerrenda bueltatzen du
		listaAktoreak l = new listaAktoreak();
		Aktorea a=null;
		Iterator<Aktorea>itr=this.getIteradorea();
		while(itr.hasNext()){
			a=itr.next();
			if(a.pelikulanParteHartu(pPelikula)){
				l.aktoreaGehitu(a.getIzena());
			}
		}
		return l;
	}
	
	public void zerrendaFitxategianGorde() {
		//Aurre: Sartutako helbidea existitzea
		//Post: Zerrenda fitxategi batean gordetzen du
		String ruta = "~/DEA/listaAktoreak.txt";
	    File f = new File(ruta);
	    try {
	    	FileWriter fw = new FileWriter(f);
	    BufferedWriter escritura = new BufferedWriter(fw);
	    for(int i=0;i<lista.size();i++){
	        escritura.write(lista.get(i).getIzena());//write(lista.get(i));
	        escritura.newLine();	
	    }
	    escritura.close();
	    }
	    catch(IOException fw) {
	    	System.out.println("Fitxategia ez da existitzen");
	    }
	}
}