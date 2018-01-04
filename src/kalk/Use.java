package kalk;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.ColorUIResource;

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
		System.out.println(" -1) -Chiudi");
		int scelta =0;
		try{
			scelta = in.nextInt();

		}catch (Exception e) {
			scelta = 0;
		}

		switch (scelta) {
		case 1:
			coloriRgbUse();
			break;
		case 2:
			ricettaUse();
			break;
		case 3:

			break;
		case 4:

			break;
		case -1: 
			System.out.println("Grazie per utilizzato la calcolatrice");

			break;
		default:
			System.out.println("emetti un input valido Riavvia il programma");
			break;

		}


	}
	static void coloriRgbUse(){
		ColoreRgb c1 = new ColoreRgb(0,0,255,1);
		ColoreRgb c2 = new ColoreRgb(234,154,105,1);
		ColoreRgb c3 = new ColoreRgb();
		System.out.println("Colore 1 : "+c1.schemaColore());
		System.out.println("Colore 2 : "+c2.schemaColore());
		System.out.println("Colore result : "+c3.schemaColore());;
		c3=(ColoreRgb)c1.somma(c2);
		System.out.println("Colore result Somma : "+c3.schemaColore());
		c3=(ColoreRgb)c1.differenza(c2);
		System.out.println("Colore result Differenza : "+c3.schemaColore());;
		c3=(ColoreRgb)c1.modula(c2);
		System.out.println("Colore result Modulazione : "+c3.schemaColore());;
		System.out.println("Delta E Colori : "+Double.toString(c1.deltaE(c2)));;
		System.out.println("Colore complementare Colore 1 : "+c1.complementare().schemaColore());
		System.out.println("Luminosita Colore 1 : "+c1.luminositaColore().schemaColore());
		if(((ColoreRgb)c1.luminositaColore()).getR()<130){
			 System.out.println("E' un colore SCURO.\n Per cui ti conviene mettere il testo in sovraimpressione in BIANCO");
		}else{
			 System.out.println("E' un colore Chiaro.\n Per cui ti conviene mettere il testo in sovraimpressione in NERO");
		}
		
		if(c1.coloreCaldo()){
			 System.out.println(c1.schemaColore()+" E' un colore Caldo");
		}else
			 System.out.println(c1.schemaColore()+" E' un colore Freddo");
		double lab[] = new double[3];
		lab  = c1.rgb2lab();
		System.out.println("Rappresentazione in lab: "+c1.schemaColore()+" ==> LAB("+Double.toString(lab[0])+','+Double.toString(lab[1])+','+Double.toString(lab[2])+')');
		double hsl[] = new double[3];
		hsl  = c1.rgb2hsl();
		System.out.println("Rappresentazione in HSL: "+c1.schemaColore()+" ==> HSL("+Double.toString(hsl[0])+','+Double.toString(hsl[1])+','+Double.toString(hsl[2])+')');
		System.out.println("Rappresentazione in HEX: "+c1.schemaColore()+" ==> HEX("+c1.rgb2hex()+')');
		System.out.println("Colore 1 scalato di 0.5 "+c1.scala(0.5).schemaColore());
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
}
