package packHirugarrenPraktika;

public class Aktorea {
	private String izenAbizena;
	private listaFilmak lista;
	
	
	public Aktorea(String pIzenAbizena){
		this.izenAbizena=pIzenAbizena;
		this.lista=new listaFilmak();
	}
	
	public listaFilmak pelikulenLista(){
		//Aurre:
		//Post: Aktorearen pelikulen lista bueltatzen du
		return this.lista;
	}
	
	public boolean izenBerdina(String pIzena) {
		//Aurre:
		//Post: Sartutako Stringa eta aktorearen izenaren arteko konparaketa egiten du, berdinak badira true bueltatzen du, bestela false
		return (this.izenAbizena.equals(pIzena));
	}
	
	public boolean pelikulanParteHartu(Pelikula pPelikula) {
		//Aurre:
		//Post: Aktorea sartutako pelikulan parte hartzen duen ala ez adierazten du, parte hartzen badu true bueltatzen du, bestela false
		return this.lista.badago(pPelikula);
	}
	
	
	public String getIzena() {
		//Aurre:
		//Post: Aktorearen izena bueltatzen du
		return this.izenAbizena;
	}
	
	public void gehituPelikula(Pelikula pPelikula) {
		//Aurre:
		//Post: pPelikula aktorea duen pelikulen zerrendan gehitzen du
		this.lista.pelikulaGehitu(pPelikula);
	}

	public int compareTo(Aktorea pivot) {
		Aktorea a= new Aktorea(this.izenAbizena);
		return pivot.compareTo(a);
	}
}