package kalk;

public class ColoreRgba extends ColoreRgb{

	private float a; //alpha compreso tra 0.0 e 1.0


	public ColoreRgba(){
		super(0,0,0);
		a=1;
	}

	public ColoreRgba(int red, int green, int blue, float alpha){
		super(red,green,blue);
		if(alpha<0 || alpha > 1.0)
			alpha=1;
		else
			a=alpha;
	}
	public ColoreRgba(int red, int green, int blue){
		super(red,green,blue);
		a=1;
	}
	public void setA(float alpha){
		if(alpha>=0 && alpha <= 1)
			a= alpha;
	}
	public float getA(){
		return a;
	}

	public ColoreRgba somma(Colore c) {

		ColoreRgba aux= new ColoreRgba();

		if (c instanceof ColoreRgba) {
			ColoreRgba tmp = (ColoreRgba)c;


			ColoreRgb baseOp;
			baseOp=super.somma(tmp);
			aux.modificaColore(baseOp.getR(),baseOp.getG(),baseOp.getB());
			aux.setA((float)(0.5*a+tmp.getA()*0.5));
		}
		return aux;

	}


	public ColoreRgba differenza(Colore c) {
		//sintesi sottrativa di un colore. cioe data una luce di un certo colore. e data la quantita di luce che assorbe la superfice di riflessione ottengo la luce che verra percepita dal occhio
		ColoreRgba aux= new ColoreRgba();

		if (c instanceof ColoreRgba) {
			ColoreRgba tmp = (ColoreRgba)c;


			ColoreRgb baseOp;
			baseOp=super.differenza(tmp);
			aux.modificaColore(baseOp.getR(),baseOp.getG(),baseOp.getB());
			aux.setA(a-tmp.getA());
		}
		return aux;
	}

	public ColoreRgba modula(Colore c) {
		ColoreRgba aux= new ColoreRgba();

		if (c instanceof ColoreRgba) {
			ColoreRgba tmp = (ColoreRgba)c;


			ColoreRgb baseOp;
			baseOp=super.modula(tmp);
			aux.modificaColore(baseOp.getR(),baseOp.getG(),baseOp.getB());
			aux.setA(a*tmp.getA());
		}
		return aux;
	}

	public ColoreRgba scala(double s) {

		ColoreRgba aux= new ColoreRgba(getR(),getG(),getB());
		ColoreRgb baseOp;
		baseOp=super.scala(s);
		aux.modificaColore(baseOp.getR(),baseOp.getG(),baseOp.getB());
		aux.setA((float)(a*s));
		return aux;
	}

	public String schemaColore() {
		String s= "rgba("+Integer.toString(getR())+","+Integer.toString(getG())+","+Integer.toString(getB())+","+Float.toString(getA())+")";
		return s;
	}
}
