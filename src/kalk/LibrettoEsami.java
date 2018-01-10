package kalk;

public class LibrettoEsami extends VettoreGenerico<Esame>{
	private String nomeStudente, cognomeStudente;
    private int matricolaStudente;
    private int targetCFU;  //rappresenta il numero totale di CFU
    private int totaleCFU;  //rappresenta i CFU acquisiti
    
    public LibrettoEsami(String nomeS,String cognomeS, int matricolaS, int targCFU) {
    	nomeStudente = nomeS;
    	cognomeStudente = cognomeS;
    	matricolaStudente = (matricolaS > 0 ? matricolaS:1);
    	targetCFU = (targCFU > 0?targCFU: 1);
    }
    
    public void aggiungiElemento(Esame e){
        if(!cerca(e) && ((targetCFU-totaleCFU) >= e.getCFU())){
            super.aggiungiElemento(e);
            totaleCFU += e.getCFU();
        }
    }
    
    public boolean rimuoviElemento(Esame e){
    	boolean trovato = false;
    	for(int i=0; i < getSize() && !trovato; ++i) {
        	if(getAt(i).uguale(e)) {
        		trovato = true;
        		super.rimuoviPoz(i);
        		totaleCFU -= e.getCFU();
        	}
        }
        return trovato;
    }
    
    //Ritorna i CFU per completare il percorso di studi
    public int getTargetCFU(){
        return targetCFU;
    }
    
    //Ritorna i CFU acquisiti tramite gli esami sostenuti
    public int getTotaleCFU(){
        return totaleCFU;
    }
    
    int getMatricola() {
        return matricolaStudente;
    }
    
    public void setMatricola(int m){
    	matricolaStudente = (m > 0 ? m:1);
    }

    public String getNomeStudente() {
        return nomeStudente;
    }

    public void setNomeStudente(String n){
        nomeStudente = n;
    }

    public String getCognomeStudente() {
        return cognomeStudente;
    }

    public String getInfoStudente(){
        String s = "Nome: ";
        s += nomeStudente;
        s += "\nCognome: ";
        s += cognomeStudente;
        s += "\nMatricola: ";
        s += matricolaStudente;
        return s;
    }
    
    public void setCognomeStudente(String c){
        cognomeStudente = c;
    }

    public double percentualeCompletamento(){
        return (totaleCFU*100)/targetCFU;
    }

    public int rimanentiCFU(){
        return targetCFU-totaleCFU;
    }

    public double previsioneVotoLaurea(){
        return (mediaPonderata()*110)/30;
    }

    public double mediaAritmetica(){
        if(getSize() > 0){
            int i=0;
            double votoTemp=0;
            for(; i < getSize(); ++i){
                votoTemp += (this.getAt(i)).getVoto();
            }
            return votoTemp/i;
        }
        return 0;
    }

    public double mediaPonderata(){
        if(getSize() > 0){
            int i=0;
            double votoTemp=0;
            for(; i < getSize(); ++i){
                votoTemp += (this.getAt(i).getVoto()) * (this.getAt(i).getCFU());
            }
            return votoTemp/getTotaleCFU();
        }
        return 0;
    }
    
    public Esame esameMigliore(){
        if(getSize() > 0){
            Esame esameTemp, esameMigliore=this.getAt(0);
            int pesoEsame=esameMigliore.getVoto()*esameMigliore.getCFU();
            for(int i=0; i < getSize(); ++i){
                esameTemp = this.getAt(i);
                if(esameTemp.getVoto()*esameTemp.getCFU() > pesoEsame){
                    esameMigliore = esameTemp;
                    pesoEsame = esameMigliore.getVoto()*esameMigliore.getCFU();
                }
            }
            return esameMigliore;
        }
        return null;
    }

    public Esame esamePeggiore(){
        if(getSize() > 0){
            Esame esameTemp, esamePeggiore=this.getAt(0);
            int pesoEsame=esamePeggiore.getVoto()*esamePeggiore.getCFU();
            for(int i=0; i < getSize(); ++i){
                esameTemp = this.getAt(i);
                if(esameTemp.getVoto()*esameTemp.getCFU() < pesoEsame){
                    esamePeggiore = esameTemp;
                    pesoEsame = esamePeggiore.getVoto()*esamePeggiore.getCFU();
                }
            }
            return esamePeggiore;
        }
        return null;

    }

    public Esame esameMenoRecente(){
        if(getSize() > 0){
            Esame esameTemp, esameMenoRecente=this.getAt(0);

            for(int i=0; i < getSize(); ++i){
                esameTemp = this.getAt(i);
                if(esameTemp.getData().minore(esameMenoRecente.getData()))
                    esameMenoRecente = esameTemp;
            }
            return esameMenoRecente;
        }
        return null;

    }

    public Esame esamePiuRecente() {
        if(getSize() > 0){
            Esame esameTemp, esamePiuRecente= this.getAt(0);

            for(int i=0; i < getSize(); ++i){
                esameTemp = this.getAt(i);
                if(esameTemp.getData().maggiore(esamePiuRecente.getData()))
                    esamePiuRecente = esameTemp;
            }
            return esamePiuRecente;
        }
        return null;
    }
    
    public LibrettoEsami ottieniCopiaLibretto() {
    	LibrettoEsami temp = new LibrettoEsami(this.getNomeStudente(),this.getCognomeStudente(),this.getMatricola(),this.getTargetCFU());
    	
    	for(int i=0; i < getSize(); ++i) {
    		temp.aggiungiElemento((this.getAt(i).ottieniCopiaEsame()));
    	}
    	
    	return temp;
    }
    
    @Override
	public VettoreGenerico<Esame> somma(VettoreGenerico<Esame> v) {

    	LibrettoEsami aux = this.ottieniCopiaLibretto();
    	aux.sommaConAssegnazione(v);
    	return aux;
    }
    
    public void sommaConAssegnazione(VettoreGenerico<Esame> v){
    	if(v instanceof LibrettoEsami) {
    		LibrettoEsami l = (LibrettoEsami)v;
    		
	    	for(int i=0; i < l.getSize(); ++i) {
    			aggiungiElemento(l.getAt(i));
	    	}
    	}
    }
    
    public boolean cerca(Esame e){
    	boolean trovato = false;
		for(int i = 0 ; i < getSize() && !trovato;i++){
			if(getAt(i).uguale(e))
				trovato=true;
		}
		return trovato;
    }
    
    public VettoreGenerico<Esame> differenza(VettoreGenerico<Esame> v){
    	LibrettoEsami aux = this.ottieniCopiaLibretto();
    	aux.differenzaConAssegnazione(v);
    	return aux;
    }
    
    public void differenzaConAssegnazione(VettoreGenerico<Esame> v) {
    	if(v instanceof LibrettoEsami) {
    		LibrettoEsami l = (LibrettoEsami)v;
    		
    		for(int i=0; i < l.getSize(); ++i) {
    			rimuoviElemento(l.getAt(i));
	    	}
    	}
    }
    
    public VettoreGenerico<Esame> sommaEsame(Esame e){
    	LibrettoEsami aux = this.ottieniCopiaLibretto();
    	aux.aggiungiElemento(e);
    	return aux;
    }
    
    public VettoreGenerico<Esame> differenzaEsame(Esame e){
    	LibrettoEsami aux = this.ottieniCopiaLibretto();
    	aux.rimuoviElemento(e);
    	return aux;
    }
    
    public String getRappresentazioneStringa(){
        String t = new String();
        t += "Nome: "+getNomeStudente() + "; Cognome: "+getCognomeStudente()+"; Matricola: "+getMatricola();
        for(int i=0; i < getSize(); ++i){
            t += "\n\nEsame " + (i+1) + ":";
            t += (getAt(i)).getRappresentazioneStringa();
        }
        return t;
    }
    
	public String toString(){
		return getRappresentazioneStringa();
	}
}
