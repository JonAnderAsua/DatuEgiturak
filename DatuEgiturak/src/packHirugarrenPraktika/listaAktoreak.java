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
	private int kont;
	
	public listaAktoreak(){
		this.lista= new ArrayList<Aktorea>();
		this.kont=this.lista.size();
	}
	
	public Iterator<Aktorea> getIteradorea(){
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
	
	public void aktoreaGehitu(Aktorea pA){ 
		//Aurre:
		//Post: Aktorea zerrendan ez badago gehitzen du
		if(!this.lista.contains(pA)) {
			this.lista.add(pA);
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
				l.aktoreaGehitu(a);
			}
		}
		return l;
	}
	
	public int luzera() {
		Aktorea a;
		Iterator<Aktorea>itr=this.getIteradorea();
		a=itr.next();
		int emaitza=0;
		while(itr.hasNext()) {
			emaitza++;
			a=itr.next();
		}
		return emaitza;
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
	
	public Aktorea posizioanBueltatu(int pPosizioa) {
		Aktorea a=null;
		Iterator<Aktorea>itr=this.getIteradorea();
		int emaitza=pPosizioa;
		if(itr.hasNext()&&pPosizioa<=this.luzera()) {
			a=itr.next();
			while(emaitza>0) {
				a=itr.next();
				emaitza--;
			}
		}
		return a;
	}
	
	public void aktoreakInprimatu() {
		Aktorea a;
		Iterator<Aktorea>itr=this.getIteradorea();
		while(itr.hasNext()) {
			a=itr.next();
			System.out.println(a);
		}
	}
}