package packHirugarrenPraktika;

public class ProbaGraphHash {
	

	public static void main(String[] args) {
		listaAktoreak listaAk=new listaAktoreak();
		Aktorea a1=new Aktorea("a1");
		Aktorea a2=new Aktorea("a2");
		Aktorea a3=new Aktorea("a3");
		Aktorea a4=new Aktorea("a4");
		Pelikula p1=new Pelikula("p1");
		Pelikula p2=new Pelikula("p2");
		Pelikula p3=new Pelikula("p3");
		Pelikula p4=new Pelikula("p4");
		Pelikula p5=new Pelikula("p5");
		listaAk.aktoreaGehitu(a1);
		listaAk.aktoreaGehitu(a2);
		listaAk.aktoreaGehitu(a3);
		listaAk.aktoreaGehitu(a4);
		a1.gehituPelikula(p1);
		a1.gehituPelikula(p2);
		a2.gehituPelikula(p2);
		a2.gehituPelikula(p3);
		a3.gehituPelikula(p3);
		a3.gehituPelikula(p4);
		a4.gehituPelikula(p4);
		a4.gehituPelikula(p5);
		a3.gehituPelikula(p5);
		a4.gehituPelikula(p1);
		a2.gehituPelikula(p3);
		a1.gehituPelikula(p4);
		
	/*	listaFilmak filmak=new listaFilmak();
		filmak.pelikulaGehitu(p1);
		filmak.pelikulaGehitu(p2);

		filmak.pelikulaGehitu(p3);

		filmak.pelikulaGehitu(p4);

		filmak.pelikulaGehitu(p5);
		
		System.out.println(filmak.luzera());

		*/
		
		GraphHash g=new GraphHash();
		g.grafoaSortu(listaAk);
		g.print();

	}

}
