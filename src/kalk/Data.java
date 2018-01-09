package kalk;

public class Data {
	private int giorno, mese, anno;
	
	public Data() {
		giorno = 1;
		mese = 1;
		anno = 1900;
	}
	
	public Data(int g, int m, int a) {
		giorno = ((g >= 0 || g <= 31) ? g : 1);
		mese = ((m >= 1 || m <=12) ? m : 1);
		anno = ((a >= 1900) ? a : 1900);
		
		if(!dataValida()) {
			giorno = 1;
			mese = 1;
			anno = 1900;
		}
	}
	
	public int Giorno() {
		return giorno;
	}
	
	public int Mese() {
		return mese;
	}
	
	public int Anno() {
		return anno;
	}
	
	public boolean annoBisestile(int anno){
	    if(anno >= 0)
	        return ((anno % 4 == 0) && (anno % 100 != 0)) || (anno % 400 == 0);
	    else
	        return false;
	}
	
	public boolean dataValida(){
	    if(giorno==31 && (mese == 2 || mese == 4 || mese ==6 || mese==9||mese==11))
	        return false;
	    else if(giorno == 30 && mese == 2)
	        return false;
	    else if(mese==2 && giorno==29 && !annoBisestile(anno))
	        return false;

	    return true; // Data valida
	}
	
	public boolean uguale(Data d) {
		return giorno==d.giorno && mese==d.mese && anno==d.anno;
	}
	
	public boolean diverso(Data d) {
		return !(this.uguale(d));
	}
	
	public boolean minore(Data d) {
		if(anno<d.anno) return true;
	    if((anno==d.anno) && (mese<d.mese)) return true;
	    if((anno==d.anno) && (mese==d.mese) &&(giorno<d.giorno)) return true;
	    return false;
	}
	
	public boolean maggiore(Data d) {
		return !(this.minore(d));
	}
	
	public String getRappresentazioneStringa(){
	    String s;
	    s = giorno + "/" + mese + "/" + anno;
	    return s;
	}
}
