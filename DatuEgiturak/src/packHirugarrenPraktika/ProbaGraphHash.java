package packHirugarrenPraktika;

public class ProbaGraphHash {
	

	public static void main(String[] args) {
		listaAktoreak listaAk=new listaAktoreak();
		
		Aktorea a1=new Aktorea("a1");
		Aktorea a2=new Aktorea("a2");
		Aktorea a3=new Aktorea("a3");
		Aktorea a4=new Aktorea("a4");
		Aktorea a5=new Aktorea("a5");
		Aktorea a6=new Aktorea("a6");
		
		Pelikula p1=new Pelikula("p1");
		Pelikula p2=new Pelikula("p2");
		Pelikula p3=new Pelikula("p3");
		Pelikula p4=new Pelikula("p4");
		Pelikula p5=new Pelikula("p5");
		Pelikula p6=new Pelikula("p6");
		
		listaAk.aktoreaGehitu(a1);
		listaAk.aktoreaGehitu(a2);
		listaAk.aktoreaGehitu(a3);
		listaAk.aktoreaGehitu(a4);
		listaAk.aktoreaGehitu(a5);
		listaAk.aktoreaGehitu(a6);
		
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
		a5.gehituPelikula(p6);
		a6.gehituPelikula(p6);
		
		
		GraphHash g=new GraphHash();
		g.grafoaSortu(listaAk);
		g.print();
		
		
		System.out.println("Elementu berdinak direnez true eman beharko luke, eta " + g.konektatuta(a1.getIzena(), a1.getIzena()) + " ematen du");
		System.out.println("Hurrengoa true eman beharko luke eta " + g.konektatuta(a1.getIzena(), a2.getIzena()) + " ematen du" );
		System.out.println("Hurrengoa false eman beharko luke eta " + g.konektatuta(a1.getIzena(), a5.getIzena()) + " ematen du" );
		System.out.println("Hurrengoa true eman beharko luke eta " + g.konektatuta(a5.getIzena(), a6.getIzena()) + " ematen du" );

	}

}
