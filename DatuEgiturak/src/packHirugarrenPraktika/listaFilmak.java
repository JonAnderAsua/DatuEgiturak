package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.Iterator;


public class listaFilmak {
	private ArrayList<Pelikula> lista;
	
	
	public listaFilmak(){
		this.lista=new ArrayList<Pelikula>();
	}
	
	
	public boolean badago(Pelikula pPelikula) {
		//Aurre:
		//Post: Zerrendan Pelikula dagoen ala ez adierazten du, badago true bueltatzen du, bestela false
		return this.lista.contains(pPelikula);
	}
	
	public void pelikulaGehitu(Pelikula pPelikula) {
		//Aurre:
		//Post: Pelikula ez badago zerrendan gehitzen du
		if(!this.lista.contains(pPelikula)) {
			lista.add(pPelikula);
		}
	}
}
