package kalk;

import java.util.ArrayList;
import java.util.Scanner;


public class Use {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 

		System.out.println("Progetto Kalk: Singh Harwinder , Singh Pardeep");
		System.out.println("Scegliere un Tipo di dato");

		System.out.flush();
		System.out.println(" 1) -Colori RGB");
		System.out.println(" 2) -Ricetta");
		System.out.println(" 3) -Libretto Scolastico");
		System.out.println(" 4) -Matrici");
		System.out.println("-1) -Chiudi");
		int scelta =0;
		try{
			scelta = in.nextInt();

		}catch (Exception e) {
			scelta = 0;
		}

		switch (scelta) {
		case 1:
			coloriUse();
			break;
		case 2:
			ricettaUse();
			break;
		case 3:
			librettoEsamiUse();
			break;
		case 4:
			matriceUse();
			break;
		case -1: 
			System.out.println("Grazie per utilizzato la calcolatrice");

			break;
		default:
			System.out.println("emetti un input valido Riavvia il programma");
			break;

		}
		in.close();


	}
	
	
	static void coloriUse() {
		Colore colRGB = new ColoreRgb(0,0,255);
		Colore colRGB2 = new ColoreRgb(0,255,255);
		Colore colRGBA = new ColoreRgba(234,154,105,0.87f);
		Colore colHSL = new ColoreHsl(50,25,50);
		System.out.println("Colore formato RGB : " + colRGB.schemaColore());
		System.out.println("Colore formato RGBA : "+ colRGBA.schemaColore());
		System.out.println("Colore formato HSL : " + colHSL.schemaColore());
		Colore colT = (ColoreRgb)(colRGB.somma(colRGBA));
		System.out.println("Colore somma di "+colRGB+" e "+colRGBA+": "+colT);
		Colore colT2 = (ColoreHsl)(colHSL.somma(new ColoreHsl()));
		System.out.println("Colore somma di "+colHSL+" e colore "+(new ColoreHsl())+": "+colT2);
		System.out.println("Colore differenza tra "+colRGB+" e "+colRGBA+": "+(ColoreRgb)(colRGB.differenza(colRGBA)));
		colT=(ColoreRgb)colRGB.modula(colRGBA);
		System.out.println("Colore result Modulazione di "+colRGB+" con "+colRGBA+" : "+colT);
		System.out.println("Delta E Colori di "+colRGB+" con "+colRGB2+": "+Double.toString(colRGB.deltaE(colRGB2)));
		System.out.println("Colore complementare di "+colRGB2+" : "+colRGB2.complementare());

		if(colRGB2.coloreCaldo()){
			 System.out.println(colRGB2+" e' un colore Caldo");
		}else
			 System.out.println(colRGB2 + " e' un colore Freddo");
		System.out.println("Luminosita Colore "+colRGB+" : "+colRGB.getLuminosita());
		
		double lab[] = new double[3];
		lab  = colRGB.ToLab();
		System.out.println("Rappresentazione in lab di "+colRGB+" ==> LAB("+Double.toString(lab[0])+','+Double.toString(lab[1])+','+Double.toString(lab[2])+')');
		
		double hsl[] = new double[3];
		hsl  = ((ColoreRgb)colRGB).ToHsl();
		System.out.println("Rappresentazione in HSL di "+colRGB+" ==> HSL("+Double.toString(hsl[0])+','+Double.toString(hsl[1])+','+Double.toString(hsl[2])+')');
		System.out.println("Rappresentazione in HEX di "+colRGB+" ==> HEX("+colRGB.ToHex()+')');
		System.out.println("Colore "+colHSL+" scalato di 0.5 "+colHSL.scala(0.5));
		System.out.println("Scala grigio per colore "+colHSL+": "+colHSL.grayScale());
		
		
		double HSL[] = new double[3];
		HSL[0]=324;
		HSL[1]=100;
		HSL[2]=50;
		int RGB[] = new int[3];
		RGB = Colore.hsl2rgb(HSL);
		System.out.println("HSL TO RGB HSL(324,100,50) ==> RGB("+Integer.toString(RGB[0])+','+Integer.toString(RGB[1])+','+Integer.toString(RGB[2])+",1)");
		
	}

	
	static void ricettaUse(){

		Ricetta r = new Ricetta();
		r.setNomeRicetta("Ricetta 1");
		Ingrediente tmp1 = new Ingrediente("Ingrediente 1",1,1,2);
		Ingrediente tmp2 = new Ingrediente("Ingrediente 2",1,1,2);
		r.aggiungiElemento(tmp1);
		r.aggiungiElemento(tmp2);
		ArrayList<Ingrediente> tmp = Ricetta.copiaProfonda(r.getVettore());
		Ricetta r1 = new Ricetta(tmp,"Ricetta 2");
		r1.getAt(0).setNome("Ingrediente 3");
		System.out.println(r.getRicetta());
		System.out.println(r1.getRicetta());
		System.out.println("SOMMA TRA RICETTA 1 e RICETTA 2");
		System.out.println(((Ricetta)r.somma(r1)).getRicetta());
		System.out.println("DIFFERENZA TRA RICETTA 1 e RICETTA 2");
		System.out.println(((Ricetta)r.differenza(r1)).getRicetta());
		System.out.println("Costo totale della ricetta "+r.getNomeRicetta()+" e' di "+Double.toString(r.costoRicetta())+" euro");
		System.out.println("Calorie totali della ricetta "+r.getNomeRicetta()+" sono "+Double.toString(r.calorieRicetta())+" cal");
		System.out.println(r.porzionePer(5).getRicetta());

	}
	
	static void librettoEsamiUse() {
		LibrettoEsami l1 = new LibrettoEsami("Nome 1", "Cognome 1",123,180);
		LibrettoEsami l2 = new LibrettoEsami("Nome 2","Cognome 2",234,180);
		Esame e1 = new Esame("Materia 1","Corso 1", "Professore 1",new Data(1,1,2012),12,29);
		Esame e2 = new Esame("Materia 2","Corso 2", "Professore 2",new Data(2,2,2012),12,34);
		l1.aggiungiElemento(e1);
		l2.aggiungiElemento(e2);
		System.out.println(" ----- libretto l1 ----- \n"+l1);
		System.out.println("\n ----- libretto l2 ----- \n"+l2);
		System.out.println("\n ----- Somma tra libretto l1 e libretto l2 (con assegnazione) ----- \n");
		l1.sommaConAssegnazione(l2);
		LibrettoEsami tempL = (LibrettoEsami)l1.sommaEsame(new Esame("Materia","Corso","Professore",7,18));
		l1.aggiungiElemento(new Esame("Materia","Corso","Professore",7,18));
		System.out.println(l1);
		System.out.println("\nCFU totali di l1: "+l1.getTotaleCFU());
		System.out.println("Target CFU di l1: "+l1.getTargetCFU());
		System.out.println("CFU rimanenti di l1: "+l1.rimanentiCFU());
		System.out.println("Media aritmetica di l1: "+l1.mediaAritmetica());
		System.out.println("Media ponderata di l1: "+l1.mediaPonderata());
		System.out.println("Percentuale completamento di l1: "+l1.percentualeCompletamento());
		System.out.println("Previsione voto di laurea: "+l1.previsioneVotoLaurea());
		System.out.println("\n -- Esame migliore di l1 -- : \n"+l1.esameMigliore());
		System.out.println("\n -- Esame peggiore di l1 -- : \n"+l1.esamePeggiore());
		System.out.println("\n -- Esame meno recente di l1 -- : \n"+l1.esameMenoRecente());
		System.out.println("\n -- Esame piu recente di l1 -- : \n"+l1.esamePiuRecente());
		System.out.println("\n ----- Togliere un esame dal libretto tempL -----");
		System.out.println(tempL.differenzaEsame(new Esame("Materia","Corso","Professore",7,18)));
		System.out.println("\n -- Somma di libretto l1 con libretto l2 -- :\n"+l2.somma(l1));
		System.out.println("\n -- Differenza di libretto l1 con libretto l2 -- :\n"+l1.differenza(l2));
	}
	
	static void matriceUse() {
		Matrice m1 = new Matrice(3,3,1);
		Matrice m2 = new Matrice(3,3,2);
		Matrice m3 = new Matrice(3,3);
		Matrice m4 = new Matrice(2,2);
		
		for(int i=0; i < m3.getNumRighe(); ++i) {
			for(int j=0; j < m3.getNumColonne(); ++j) {
				m3.setValore(i, j, (i+j+1));
			}
		}
		
		System.out.println("\nMatrice m1:\n "+m1);
		System.out.println("\nMatrice m2:\n "+m2);
		System.out.println("\nMatrice m3:\n "+m3);
		System.out.println("\nSomma tra matrice m1 e m2:\n "+m1.somma(m2));
		System.out.println("\nDifferenza tra m1 e m2:\n "+m1.differenza(m2));
		System.out.println("\nProdotto scalare di m2 per il valore 1.5:\n"+m2.prodottoScalare(1.5));
		System.out.println("\nProdotto tra m1 e m2:\n"+m1.prodotto(m2));
		System.out.println("\nProdotto tra m1 e m3:\n"+m3.prodotto(m1));
		System.out.println("\nProdotto tra m3 e m1:\n"+m1.prodotto(m3));
		System.out.println("\nDivisione scalare di matrice m3 col valore 2:\n"+m3.divisioneScalare(2));
		System.out.println("\nMatrice trasposta di m3:\n"+m3.matriceTrasposta());
		System.out.println("\nMatrice m3 elevata a potenza 2:\n"+m3.elevaAPotenza(2));
		System.out.println("\nMatrice m3 diagonale: "+m3.isDiagonale());
		System.out.println("Matrice m4 diagonale: "+m4.isDiagonale());
		System.out.println("Matrice m3 simmetrica: "+m3.isSimmetrica());
		System.out.println("Matrice m3 e' scalare per il valore 2: "+m3.isScalare(2));
		System.out.println("Matrice m4 e' scalare per il valore 0: "+m4.isScalare(0));
		System.out.println("Determinante della matrice m4: "+m4.determinante());
		
		for(int i=0; i< m4.getSize(); ++i) {
			m4.setAt(i,(double)(i+1));
		}
		if(m4.determinante() == 0) {
			System.out.println("La matrice m4 non e' invertibile");
		}else {
			System.out.println("\nMatrice inversa di m4: \n"+m4.matriceInversa());
		}
		
		System.out.println("\nMatrice ridotta di m3 su riga 2 e colonna 2:\n"+m3.matriceRidotta(1, 1));
		System.out.println("\nMatrice dei cofattori di m4: \n"+m4.matriceCofattore());
	}
}
