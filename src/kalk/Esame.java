package kalk;

public class Esame {
	private int CFU;
	private int voto;
	private String nomeMateria;
	private String nomeCorso;
	private String nomeProfessore;
	private Data data;
	
	public Esame(String nomeM, String nomeC, String nomeP,int c, int v) {
		this(nomeM,nomeC,nomeP,new Data(),c,v);
	}
	
	public Esame(String nomeM, String nomeC, String nomeP, Data d, int c, int v) {
		
		CFU = (c >= 1 ? c : 1);
		voto = (v >= 18 ? v : 18);
		voto = (voto <= 30 ? voto: 30);
		data = d;
		
		nomeMateria = (nomeM.isEmpty() ? "Nome materia" : nomeM);
		nomeCorso = (nomeC.isEmpty() ? "Nome corso" : nomeC);
		nomeProfessore = (nomeP.isEmpty() ? "Nome professore": nomeP);
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
	
	public void setVoto(int v) {
		voto = (v >= 18 ? v : 18);
	    voto = (voto <= 30 ? voto : 30);
	}
	
	public void setData(Data d) {
		data = d;
	}
	
	public boolean uguale(Esame e) {
		return nomeCorso.equals(e.getNomeCorso()) && nomeMateria.equals(e.getNomeMateria()); 
	}
	
	public boolean diverso(Esame e) {
		return !(this.uguale(e));
	}
	
	public String getRappresentazioneStringa(){
	    String t = new String();
	    t += "\nCFU: " + getCFU() + ";";
	    t += "\nVoto: " + getVoto() + ";";
	    t += "\nNome Materia: " + getNomeMateria() + ";";
	    t += "\nNome Professore: " + getNomeProfessore() + ";";
	    t += "\nNome Corso: " + getNomeCorso() + ";";
	    t += "\nData: " + data.getRappresentazioneStringa() + ";";
	    return t;
	}
	
	public String toString(){
		return getRappresentazioneStringa();
	}
}
