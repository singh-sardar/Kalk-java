package kalk;

import java.util.ArrayList;

public class Matrice extends VettoreGenerico<Double>{
	private int numRighe;
	private int numColonne;
	private int indiceColonnaFinale;
	
	public Matrice(int numR, int numC) {
		this(numR,numC,0);
	}
	
	public Matrice(int numR, int numC, double defVal) {
		super(new ArrayList<Double>(numR*numC));
		numRighe = (numR > 0 ? numR : 1);
		numColonne = (numC > 0 ? numC : 1);
		for(int i=0; i < numR*numC; ++i) {
			super.aggiungiElemento(defVal);
		}
	}
	
	public int getNumRighe(){
	    return numRighe;
	}

	public int getNumColonne(){
	    return numColonne;
	}

	public double getValore(int riga, int colonna) {
	    return getAt(riga*numColonne+colonna);
	}

	public void setValore(int riga, int colonna, double valore){
	    setAt(riga*numColonne+colonna,valore);
	}
	
	private static double sommaVettori(ArrayList<Double> v1, ArrayList<Double> v2){
	    double temp=0;
	    if(v1.size() == v2.size()){
	        for(int i=0; i < v1.size(); ++i){
	            temp += v1.get(i)*v2.get(i);
	        }
	    }
	    return temp;
	}

	public void aggiungiElemento(double d){
	    if((indiceColonnaFinale == numColonne) || indiceColonnaFinale==0){
	        numRighe++;
	        indiceColonnaFinale=0;
	        super.aggiungiElemento(d);
	        indiceColonnaFinale++;
	        for(int i=numColonne, j=indiceColonnaFinale; i-j>0; ++j){
	            super.aggiungiElemento(0.0);
	        }
	    }else{
	        setValore(numRighe-1,indiceColonnaFinale,d);
	        indiceColonnaFinale++;
	    }
	}
	
	public boolean rimuoviElemento(double d){
	    boolean trovato=false;
	    for(int i=0; i < getNumRighe() && !trovato; ++i){
	        for(int j=0; j < getNumColonne() && !trovato; ++j){
	            if(getValore(i,j)==d){
	                trovato=true;
	                setValore(i,j,0);
	            }
	        }
	    }
	    return trovato;
	}

	public void rimuoviPoz(int indice){
	    setAt(indice,0.0);
	}
	
	public Matrice ottieniCopia() {
		Matrice temp = new Matrice(this.getNumRighe(),this.getNumColonne());
		for(int i=0; i < getSize(); ++i) {
			temp.setAt(i, this.getAt(i));
		}
		return temp;
	}
	
	public Matrice somma(VettoreGenerico<Double> m) {
		if(m instanceof Matrice) {
			Matrice tempM = (Matrice)m;
		    if(tempM != null && (tempM.getNumRighe() == getNumRighe()) && (tempM.getNumColonne() == getNumColonne())){
		        Matrice aux = this.ottieniCopia();
		        for(int i = 0; i < getNumRighe(); ++i){
		            for(int j=0; j < getNumColonne(); ++j){
		                aux.setValore(i,j,(aux.getValore(i,j) + tempM.getValore(i,j)));
		            }
		        }
		        return aux;
		    }
		    return null;
		}
	    return null;
	}
	
	public Matrice differenza(VettoreGenerico<Double> m) {
		if(m instanceof Matrice) {
			Matrice tempM = (Matrice)m;
		    if(tempM != null && (tempM.getNumRighe() == getNumRighe()) && (tempM.getNumColonne() == getNumColonne())){
		        Matrice aux = this.ottieniCopia();
		        for(int i = 0; i < getNumRighe(); ++i){
		            for(int j=0; j < getNumColonne(); ++j){
		                aux.setValore(i,j,(aux.getValore(i,j) - tempM.getValore(i,j)));
		            }
		        }
		        return aux;
		    }
		    return null;
		}
	    return null;
	}
	
	public Matrice prodottoScalare(double n) {
		Matrice aux = this.ottieniCopia();
		for(int i = 0; i < getNumRighe(); ++i){
	        for(int j=0; j < getNumColonne(); ++j){
	            aux.setValore(i,j,(aux.getValore(i,j) * n));
	        }
	    }
	    return aux;
	}
	
	private ArrayList<Double> getRiga(int riga){
	    ArrayList<Double> tempV = new ArrayList<Double>(numColonne);
	    if(riga <= numRighe){
	        for(int i=0; i < numColonne; ++i){
	        	tempV.add(getValore(riga,i));
	        }
	    }
	    return tempV;
	}
	
	public Matrice prodotto(VettoreGenerico<Double> m) {
		if(m instanceof Matrice) {
			Matrice tempM = (Matrice)m;
		    //il prodotto e' possibile solo se il numero di colonne del primo operando e' uguale al numero di righe del secondo operando
		    if(tempM != null && (getNumColonne() == tempM.getNumRighe())){
		        Matrice aux = new Matrice(getNumRighe(), tempM.getNumColonne());
		        for(int i = 0; i < aux.getNumRighe(); ++i){
		            for(int j=0; j < aux.getNumColonne(); ++j){
		                ArrayList<Double> tempList = new ArrayList<Double>();
		                for(int k=0; k < tempM.getNumRighe(); ++k){
		                	tempList.add(tempM.getValore(k,j));
		                }
		                aux.setValore(i,j,sommaVettori(getRiga(i),tempList));
		            }
		        }
		        return aux;
		    }else{
		        return null;
		    }
		}
		return null;
	}
	
	public Matrice divisioneScalare(double n) {
		if(n == 0){ return null;}
	    return this.prodottoScalare(1/n);
	}
	
	public Matrice matriceTrasposta(){
	    Matrice aux = new Matrice(getNumColonne(), getNumRighe());
	    for(int i=0; i < getNumRighe(); ++i){
	        for(int j=0; j < getNumColonne(); ++j){
	            aux.setValore(j,i,this.getValore(i,j));
	        }
	    }
	    return aux;
	}
	
	public Matrice matriceRidotta(int i, int j){
	    Matrice min = new Matrice(numRighe-1,numColonne-1);

	    for(int row=0; row < min.getNumRighe(); ++row){
	        for(int col=0; col < min.getNumColonne(); ++col){
	            if(col >= j && row >= i)
	                min.setValore(row,col,getValore(row+1,col+1));
	            if (col >= j && row<i)
	                min.setValore(row,col, getValore(row, col+1));
	            if(col < j && row>=i)
	                min.setValore(row,col, getValore(row+1,col));
	            if(row < i && col < j)
	                min.setValore(row,col, getValore(row, col));
	        }
	    }
	    return min;
	}
	
	public double determinante(){
	    double t=0;
	    if((numRighe == numColonne) && numRighe > 2){
	        for(int i=0; i < numColonne;++i){
	            Matrice min = matriceRidotta(0,i);
	            if(i%2==0){
	                t+= (getValore(0,i)*min.determinante());
	            }else{
	                t-= (getValore(0,i)*min.determinante());
	            }
	        }
	    }else if((numRighe==numColonne) && numRighe==2){//matrice 2*2
	        t+= ((getValore(0,0)*getValore(1,1))-(getValore(1,0)*getValore(0,1)));
	    }else{
	        t = getValore(0,0);
	    }
	    return t;
	}
	
	public Matrice matriceCofattore(){
	    if((numRighe == numColonne) && numRighe >= 2){
	        Matrice mat1 = new Matrice(numRighe, numColonne);
	        for(int i=0; i < mat1.getNumRighe(); ++i){
	            for(int j=0; j < mat1.getNumColonne(); ++j){
	                Matrice mat2 = matriceRidotta(i,j);

	                if((i%2==0 && j%2==0) || i==j){
	                    mat1.setValore(i,j,mat2.determinante());
	                }else{
	                    mat1.setValore(i,j,-(mat2.determinante()));
	                }
	            }
	        }
	        return mat1;
	    }
	    return null;
	}
	
	public Matrice matriceInversa(){
	    if(determinante() != 0.0 && (numColonne==numRighe)){
	        if(numColonne > 2){
	            Matrice cofat = matriceCofattore();
	            Matrice trasp = cofat.matriceTrasposta();
	            return trasp.divisioneScalare(determinante());
	        }else{
	            Matrice mat = new Matrice(numRighe, numColonne);
	            mat.setValore(0,0,getValore(1,1)/determinante());
	            mat.setValore(0,1,-getValore(0,1)/determinante());
	            mat.setValore(1,0,-getValore(1,0)/determinante());
	            mat.setValore(1,1,getValore(0,0)/determinante());
	            return mat;
	        }
	    }
	    return null;
	}
	
	public boolean isDiagonale() {
	    if(getNumRighe() == getNumColonne()){
	        boolean diversoDaZero = true;
	        for(int i = 0; i < getNumRighe() && diversoDaZero; ++i){
	            for(int j=0; j < getNumColonne(); ++j){
	                if(getValore(i,j) != 0.0 && i!=j){
	                    diversoDaZero = false;
	                }
	            }
	        }
	        return diversoDaZero;
	    }
	    return false;
	}

	public String rappresentazioneStringa() {
	    String t = "Numero righe: " + getNumRighe() + " Numero colonne: " + getNumColonne();
	    for(int i=0; i < getNumRighe(); ++i){
	        t += "\n";
	        for(int j=0; j < getNumColonne(); ++j){
	            t += getValore(i,j) + " ";
	        }
	    }
	    return t;
	}
	
	public String toString(){
		return rappresentazioneStringa();
	}
	
	public boolean isScalare(double n) {
	    if(isDiagonale()){
	        boolean flag = true;
	        for(int i=0; i < getNumRighe() && flag; ++i){
	            for(int j=0; j < getNumColonne(); ++j){
	                if(i==j && (getValore(i,j) != n)){
	                    flag = false;
	                }
	            }
	        }
	        return flag;
	    }
	    return false;
	}

	public boolean isSimmetrica() {
		return this.uguali(this.matriceTrasposta());
	}
	
	public Matrice elevaAPotenza(int n){
	    Matrice aux = this.ottieniCopia();
	    for(int i=0; i < getNumRighe(); ++i){
	        for(int j=0; j < getNumColonne(); ++j){
	            aux.setValore(i,j,Math.pow(getValore(i,j),n));
	        }
	    }
	    return aux;
	}
}
