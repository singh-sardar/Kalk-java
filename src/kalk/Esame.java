package kalk;

public class Esame {
	private int CFU;
	private int voto;
	boolean lode;
	private String nomeMateria;
	private String nomeCorso;
	private String nomeProfessore;
	private Data data;
	
	public Esame(String nomeM, String nomeC, String nomeP,int c, int v) {
		this(nomeM,nomeC,nomeP,new Data(),c,v);
	}
	
	public Esame(String nomeM, String nomeC, String nomeP, Data d, int c, int v) {
		if(c >= 1) {
			CFU = c;
		}else {
			CFU = 1;
		}
		
		if(v >= 18) {
			voto = v;
		}else {
			voto = 18;
		}
		
		if(nomeM.isEmpty())
			nomeMateria = "Nome materia";
		if(nomeC.isEmpty())
			nomeCorso = "Nome corso";
		if(nomeP.isEmpty())
			nomeProfessore = "Nome professore";
		
		lode = (voto > 30) ? true : false;
	    if(lode)
	        voto = 30;
	}
	
	public Esame ottieniCopiaEsame() {
		Esame temp = new Esame(this.getNomeMateria(),this.getNomeCorso(),this.getNomeProfessore(),this.getData(),this.getCFU(),this.getVoto());
		return temp;
	}
	
	public String getNomeCorso(){
	    return nomeCorso;
	}

	public String getNomeMateria(){
	    return nomeMateria;
	}

	public String getNomeProfessore(){
	    return nomeProfessore;
	}
	
	public int getCFU(){
	    return CFU;
	}

	public int getVoto(){
	    return voto;
	}

	public boolean getLode(){
	    return lode;
	}

	public Data getData(){
	    return data;
	}

	public void setNomeCorso(String n){
		if(n.isEmpty())
			nomeCorso = n;
	}

	public void setNomeMateria(String n){
		if(n.isEmpty())
			nomeMateria = n;
	}

	public void setNomeProfessore(String n){
		if(n.isEmpty())
			nomeProfessore = n;
	}
	
	public void setCFU(int c) {
		if(c >= 1)
			CFU = c;
	}
	
	public void setVoto(int v) {
		if(v>=18)
			voto = v;
		if(voto > 30) {
			voto = 30;
			lode = true;
		}else {
			lode = false;
		}
	}
	
	public void setData(Data d) {
		data = d;
	}
	
	public boolean uguale(Esame e) {
		return nomeCorso == e.nomeCorso && nomeMateria == e.nomeMateria;
	}
	
	public boolean diverso(Esame e) {
		return !(this.uguale(e));
	}
	
	public String getRappresentazioneStringa(){
	    String t = new String();
	    t += "\nCFU: " + getCFU() + "; ";
	    t += "\nVoto: " + getVoto() + "; ";
	    t += ("\nLode: " + (getLode() ? "Si" : "No") + "; ");
	    t += "\nNome Materia: " + getNomeMateria() + "; ";
	    t += "\nNome Professore: " + getNomeProfessore() + "; ";
	    t += "\nNome Corso: " + getNomeCorso() + "; ";
	    t += "\nData: " + data.getRappresentazioneStringa() + "; ";
	    return t;
	}
	
	public String toString(){
		return getRappresentazioneStringa();
	}
	
	public Esame somma(Esame e){
	    Esame tmp = this.ottieniCopiaEsame();
	    if(nomeMateria == e.getNomeMateria() && nomeCorso == e.getNomeCorso() && nomeProfessore == e.getNomeProfessore()){
	        tmp.voto = (voto+e.getVoto())/2;
	    }
	    return tmp;
	}
}
