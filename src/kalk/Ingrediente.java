package kalk;

public class Ingrediente {
	private String nome;
	private double quantita;
	private double calorie;
	private double costo;
	public Ingrediente() {
		nome = "";
		quantita=0;
		costo=0;
		calorie=0;
	}
	public Ingrediente(String n,double q,double c,double cal){
		nome = n;
		quantita = q;
		calorie = cal;
		costo = c;
		if(quantita<0){
			quantita=0;
			nome="Quantita negativa";
		}
		if(calorie<0){
			calorie=0;
			nome="Calorie negative";
		}
		if(costo<0){
			costo=0;
			nome="Costo negativo";
		}
	}

	public String getNome(){
		return nome;
	}
	public double getQuantita(){
		return quantita;
	}
	public double getCalorie(){
		return calorie;
	}
	public double getCosto(){
		return costo;
	}
	public void setNome(String n){
		nome = n;
	}
	public void setQuantita(double q){
		if(q>0)
	        quantita= q;
	}
	public void setCalorie(double cal){
		if(cal>0)
	        calorie= cal;
	}
	public void setCosto(double c){
	    if(c>0)
	        costo = c;
	}
	public static double kilo2g(double kg){
		return kg*1000;
	}
	public String getDescrizione(){
	    return "\nNome Ingrediente: "+nome+"\n -Quantita:"+Double.toString(quantita)+" g\n -calorie: "+Double.toString(calorie)+" x 100g\n -Costo: "+Double.toString(costo)+" al g";
	}
	
	public Ingrediente somma(Ingrediente i){
		Ingrediente tmp = new Ingrediente(nome,quantita,costo,calorie);
	    if(i.getNome().equals(nome)){
	        tmp.setQuantita(tmp.getQuantita()+i.getQuantita());
	    }
	    return tmp;
	}
	public Ingrediente differenza(Ingrediente i){
    	Ingrediente tmp = new Ingrediente(nome,quantita,costo,calorie);
	    if(i.getNome().equals(nome)){
            tmp.setQuantita(tmp.getQuantita()- i.getQuantita());
        }
        return tmp;
    	
    }
    public Ingrediente moltiplica(int p){
    	Ingrediente tmp = new Ingrediente(nome,quantita,costo,calorie);
        tmp.setQuantita(tmp.getQuantita()*p);
        return tmp;
    }
    public Ingrediente diminuisciQuantita(double q){
    	Ingrediente tmp = new Ingrediente(nome,quantita,costo,calorie);
        tmp.setQuantita(tmp.getQuantita()-q);
        return tmp;
    }
    public double calorieTorali(){
        return (calorie/100)*quantita;
    }
    public boolean uguali(Ingrediente i){
    	return i.getNome()==nome && i.getCalorie()==calorie && i.getCosto() == costo;
    }
    public boolean  diversi(Ingrediente i){
    	return !(this.uguali(i));
    }
	public String toString(){
		return getDescrizione();
	}
    

}
