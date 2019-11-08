package packHirugarrenPraktika;

public class Pelikula {
	private String izenBurua;
	private int dirua;
	
	public Pelikula(String pIzenBurua) {
		this.izenBurua=pIzenBurua;
	}
	
	public void setDirua(int pDirua) {
		//Aurre:Zenbakia positiboa izatea
		//Post:Dirua atributua aldatzen da
		if(pDirua>=0) {
			this.dirua=pDirua;
		}
	}
	
	public int getDirua() {
		return this.dirua;
	}
	
	public String getIzena() {
		return this.izenBurua;
	}
}