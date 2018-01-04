package kalk;

import java.util.ArrayList;

public class Ricetta extends VettoreGenerico<Ingrediente>{

	private String nomeRicetta;


	public Ricetta(){
		nomeRicetta = "Non definito";
	}

	public Ricetta(ArrayList<Ingrediente> v,String nome){
		super(v);
		nomeRicetta = nome;
	}


	public void setNomeRicetta(String s){
		nomeRicetta = s;
	}
	public String getNomeRicetta(){
		return nomeRicetta;
	}
	public String getRicetta(){
		return nomeRicetta+"\n"+super.toString();
	}

	public static ArrayList<Ingrediente> copiaProfonda(ArrayList<Ingrediente> a){
		ArrayList<Ingrediente> result = new ArrayList<Ingrediente>();

		Ingrediente ing ;
		for(int i = 0; i < a.size();i++){
			ing = new Ingrediente(a.get(i).getNome(),a.get(i).getQuantita(),a.get(i).getCosto(),a.get(i).getCalorie());
			result.add(ing);
		}


		return result;

	}



	@Override
	public VettoreGenerico<Ingrediente> somma(VettoreGenerico<Ingrediente> v) {
		Ricetta aux= new Ricetta(copiaProfonda(getVettore()),"Somma");



		if (v instanceof Ricetta) {
			Ricetta r = (Ricetta)v;
			boolean presente = false;

			for(int i = 0 ; i < r.getSize(); i++){
				for(int  j = 0; j < aux.getSize();j++){
					if(aux.getAt(j).uguali(r.getAt(i))){
						presente = true;
						Ingrediente temp = aux.getAt(j);
						temp.setQuantita(aux.getAt(j).somma(r.getAt(i)).getQuantita());
					}
				}
				if(!presente){
					aux.aggiungiElemento(r.getAt(i));

				}
				presente = false;
			}

		}


		return aux;
	}

	@Override
	public VettoreGenerico<Ingrediente> differenza(VettoreGenerico<Ingrediente> v) {
		Ricetta aux= new Ricetta(copiaProfonda(getVettore()),"Differenza");



		if (v instanceof Ricetta) {
			Ricetta r = (Ricetta)v;


			for( int i = 0 ; i < r.getSize(); i++){
				for( int  j = 0; j < aux.getSize();j++){
					if(aux.getAt(j).uguali(r.getAt(i))){
						if(aux.getAt(j).getQuantita()- r.getAt(i).getQuantita() >0.0){
							Ingrediente temp = aux.getAt(j);
							temp.setQuantita(aux.getAt(j).differenza(r.getAt(i)).getQuantita());
							//aux->operator [](j) = aux->operator [](j)-r->operator [](i);
						}
						else{

							aux.rimuoviPoz(j);

						}
					}
				}
			}

		}


		return aux;
	}


	public double calorieRicetta(){
		double cal=0;

		for( int i = 0 ; i < getSize();i++){
			cal+= getAt(i).calorieTorali();
		}

		return cal;
	}

	public double costoRicetta(){
		double euro=0.0;

		for( int i = 0 ; i < getSize();i++){
			euro+= getAt(i).getCosto()*getAt(i).getQuantita() ;
		}

		return euro;
	}
	public Ricetta porzionePer(int persone){
		Ricetta r = new Ricetta();
		r.setNomeRicetta(nomeRicetta+" per "+Integer.toString(persone)+" persone");
		for( int i = 0 ; i < getSize();i++){
			r.aggiungiElemento(new Ingrediente(getAt(i).getNome(),getAt(i).getQuantita()* persone,getAt(i).getCalorie(),getAt(i).getCosto()));

		}
		return r;
	}


}
