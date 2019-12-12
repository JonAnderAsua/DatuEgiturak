package packHirugarrenPraktika;

public class ProbaGraphHash {
	

	public static void main(String[] args) {
		GraphHash g = new GraphHash();
		System.out.println(g.size());
		//g.pageRank();
		//System.out.println(g.bilatzea("Pitt"));
	}

	public static void main2(String[] args) {
		GraphHash g = new GraphHash();
		g.grafoaSortuHashetik();
		g.print();
		g.denboraNeurtu(100);
	}
}
