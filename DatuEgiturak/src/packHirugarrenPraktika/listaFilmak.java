package packHirugarrenPraktika;

import java.util.ArrayList;
import java.util.Iterator;


public class listaFilmak {
	private Node<Pelikula> first;
	
	
	public listaFilmak(){
		this.first=null;
	}
	
	
	public boolean badago(Pelikula pPelikula) {
		//Aurre:
		//Post: Zerrendan Pelikula dagoen ala ez adierazten du, badago true bueltatzen du, bestela false
		boolean emaitza=false;
		Node<Pelikula> lag;
		if(!isEmpty()) {
			emaitza=first.data.equals(pPelikula);
			lag=first.next;
			while(lag!=first && !emaitza) {
				emaitza=lag.data.equals(pPelikula);
				lag=lag.next;
			}
		}
		return emaitza;
	}
	
	public void pelikulaGehitu(Pelikula pPelikula) {
		//Aurre:
		//Post: Pelikula ez badago zerrendan AMAIERAN gehitzen du
		Node<Pelikula> berria = new Node(pPelikula);
		if(isEmpty()) {
			first=berria;
			first.prev=first.next=berria;
		}
		else {
			berria.prev=first.prev;
			berria.next=first;
			first.prev.next=berria;
			first.prev=berria;
		}
	}
	
	public boolean isEmpty() {
		return(first==null);
	}
}