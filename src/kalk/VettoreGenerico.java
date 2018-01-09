package kalk;

import java.util.ArrayList;

public abstract class VettoreGenerico<T> {
	private ArrayList<T> vettore;
	VettoreGenerico(){
		vettore = new ArrayList<T>();
	}
	VettoreGenerico(ArrayList<T> v){
		vettore = new ArrayList<T>();
		vettore= v;
	}
	
	public void aggiungiElemento(T ele){
		vettore.add(ele);
	}
	
	public T popBackElemento(){
		return vettore.remove(vettore.size()-1);
	}
	
	public boolean rimuoviElemento(T ele){
		
		boolean trovato = false;
		for(int i = 0 ; i < vettore.size() && !trovato;i++){
			if(vettore.get(i).toString().equals(ele.toString())){
				trovato=true;
				vettore.remove(i);
			}
		}
		return trovato;

	}
	
	public void rimuoviPoz(int i){
		vettore.remove(i);
	}
	public boolean cerca(T ele){
		boolean trovato = false;
		for(int i = 0 ; i < vettore.size() && !trovato;i++){
			if(vettore.get(i).toString().equals(ele.toString()))
				trovato=true;
		}
		return trovato;
	}
	public T getAt(int i){
		return vettore.get(i);
	}
	
	public void setAt(int i, T t) {
		vettore.set(i, t);
	}
	
	public int getSize(){
		return vettore.size();
	}
	
	public String toString(){
		String s="";
		for(int i = 0 ; i < vettore.size();i++){
			s+= vettore.get(i).toString()+"\n";
			
		}
		return s;
	}
	
	public abstract VettoreGenerico<T> somma(VettoreGenerico<T> v);
	public abstract VettoreGenerico<T> differenza(VettoreGenerico<T> v);
	public boolean uguali(VettoreGenerico<T> v){
		if(vettore.size() != v.getSize()){
	        return false;
	    }
	    boolean uguali = true;
	    for( int i=0; i < vettore.size()&& uguali; ++i){
	        if( !(vettore.get(i).toString().equals(v.getAt(i).toString())))
	        	uguali = false;
	    }
	    return uguali;
	}
	public boolean diversi(VettoreGenerico<T> v){
		return !(uguali(v));
	}
	public ArrayList<T> getVettore(){
		return vettore;
	}

}
