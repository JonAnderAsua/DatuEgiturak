package packHirugarrenPraktika;
import java.util.HashMap;

public class ListaPelikulakOsoa {
	private HashMap<String,Pelikula> map=null;
	private static ListaPelikulakOsoa nireListaPelikulakOsoa=null;
	
	private ListaPelikulakOsoa() {
		this.map=new HashMap<String,Pelikula>();
	}
	
	public static ListaPelikulakOsoa getNireListaPelikulakOsoa() {
		if(nireListaPelikulakOsoa==null) {
			nireListaPelikulakOsoa=new ListaPelikulakOsoa();
		}
		
		return nireListaPelikulakOsoa;
	}
	public Pelikula gehituPelikula(Pelikula p) {
		//Aurre:
		//Post: Pelikula ez badago HashMapean txertatzen du
		if (!this.map.containsKey(p)){ //galdetu
			this.map.put(p.getIzena(),p);
		}
		return p;
	}
	
	public Pelikula pelikulaBilatu(Pelikula pPelikula) {
		//Aurre:
		//Post: Pelikulan HashMapean bilatzen du, aurkitzen badu Pelikula hori bueltatzen du, bestela null bueltatzen du
		Pelikula emaitza=null;
		if(map.containsKey(pPelikula)) {
			emaitza=pPelikula;
		}
		return emaitza;
	}
}