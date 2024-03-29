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
	
	public Iterator<Pelikula> getIteradorea(){
		return this.lista.iterator();
	}
	
	public void pelikulaGehitu(Pelikula pPelikula) {
		//Aurre:
		//Post: Pelikula ez badago zerrendan gehitzen du
		if(!this.badago(pPelikula)) {
			this.lista.add(pPelikula);
		}
	}
	
	public int luzera() {
		Iterator<Pelikula>itr=this.getIteradorea();
		int emaitza=0;
		if(itr.hasNext()) {
			Pelikula p=itr.next();
			emaitza++;
			while(itr.hasNext()) {
				p=itr.next();
				emaitza++;
			}
		}
		return emaitza;
	}
	
	public Pelikula posizioanLortu(int pPos) {
		Pelikula p=null;
		Iterator<Pelikula>itr=this.getIteradorea();
		int txibatoa=pPos;
		while(itr.hasNext()&&txibatoa>=0) {
			p=itr.next();
			txibatoa--;
		}
		return p;
	}
}
