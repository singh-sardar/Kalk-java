package kalk;

public class ColoreRgb extends Colore{

	private int r, g, b; 

	public ColoreRgb(){
		r=g=b=0;
		updateLuminosita();

	}
	public ColoreRgb(int R, int G, int B){
		if(R<0 || R> 255)
			r=0;
		else 
			r=R;
		if(G<0 || G> 255)
			g=0;
		else
			g=G;
		if(B<0 || B> 255)
			b=0;
		else
			b=B;
		updateLuminosita();

	}


	public void setR(int red){
		if(red>=0 && red<= 255){
			r=red;
			updateLuminosita();
		}
	}
	public void setG(int green){
		if(green>=0 && green<=255){
			g= green;
			updateLuminosita();

		}
	}
	public void setB(int blue){
		if(blue>=0 && blue<= 255){
			b=blue;
			updateLuminosita();

		}
	}

	public int getR(){
		return r;
	}
	public int getG(){
		return g;
	}
	public int getB(){
		return b;
	}

	public void modificaColore(int red, int green, int blue){
		setR(red);
		setG(green);
		setB(blue);
	}


	public ColoreRgb somma(Colore c) {

		ColoreRgb aux= new ColoreRgb();

		if (c instanceof ColoreRgb) {
			ColoreRgb tmp = (ColoreRgb)c;
			aux.setR((int)(0.5*r+tmp.getR()*0.5));
			aux.setG((int)(0.5*g+tmp.getG()*0.5));
			aux.setB((int)(0.5*b+tmp.getB()*0.5));
		}
		return aux;

	}


	public ColoreRgb differenza(Colore c) {
		//sintesi sottrativa di un colore. cioe data una luce di un certo colore. e data la quantita di luce che assorbe la superfice di riflessione ottengo la luce che verra percepita dal occhio
		ColoreRgb aux= new ColoreRgb();

		if (c instanceof ColoreRgb) {
			ColoreRgb tmp = (ColoreRgb)c;

			int valoreLuce = r-tmp.getR();

			if(valoreLuce<0)
				valoreLuce=0;
			aux.setR(valoreLuce);

			valoreLuce= g -tmp.getG();
			if(valoreLuce<0)
				valoreLuce=0;
			aux.setG(valoreLuce);

			valoreLuce = b -tmp.getB();
			if(valoreLuce<0)
				valoreLuce=0;
			aux.setB(valoreLuce);
		}
		return aux;
	}
	public ColoreRgb modula(Colore c) {
		ColoreRgb aux= new ColoreRgb();

		if (c instanceof ColoreRgb) {
			ColoreRgb tmp = (ColoreRgb)c;
			aux.setR((r*tmp.getR())/255);
			aux.setG((g*tmp.getG())/255);
			aux.setB((b*tmp.getB())/255);
		}
		return aux;
	}

	public ColoreRgb scala(double s) {
		ColoreRgb aux = new ColoreRgb(r,g,b);
		if(r*s>0 && r*s<255){
			aux.setR((int)(r*s));
		}
		if(g*s>0 && g*s<255){
			aux.setG((int)(g*s));
		}
		if(b*s>0 && b*s<255){
			aux.setB((int)(b*s));
		}
		return aux;
	}

	public double deltaE(Colore c) {
		if (c instanceof ColoreRgb) {
			ColoreRgb crgb1 = (ColoreRgb)c;
			double deltaE;
			double Lab1[] = new double[3];
			double Lab2[] = new double[3];

			Lab1 = ToLab();
			Lab2 = crgb1.ToLab();


			deltaE= java.lang.Math.sqrt(java.lang.Math.pow(Lab2[0]-Lab1[0],2) + java.lang.Math.pow(Lab2[1]-Lab1[1],2) +java.lang.Math.pow(Lab2[2]-Lab1[2],2));
			return deltaE;

		}else
			return -1;
	}

	public double[] ToLab(){

		int rgb[] = new int[3];
		rgb[0]= r;
		rgb[1]= g;
		rgb[2]= b;
		return  rgb2lab(rgb);
	}




	public ColoreRgb complementare() {
		ColoreRgb aux = new ColoreRgb();
		double HSL[]= new double[3];
		int RGB[] = new int[3];
		HSL=ToHsl();
		HSL[0]+=180;
		HSL[0]=(HSL[0]%360);
		RGB = hsl2rgb(HSL);
		aux.setR(RGB[0]);
		aux.setG(RGB[1]);
		aux.setB(RGB[2]);

		return aux;
	}

	public double[] ToHsl(){
		int rgb[] = new int[3];
		rgb[0]= r;
		rgb[1]= g;
		rgb[2]= b;
		return rgb2hsl(rgb);
	}

	public boolean coloreCaldo() {
		return r>b;
	}

	public ColoreRgb grayScale() {
		int value=(int)java.lang.Math.sqrt(r * r* 0.241 + g * g * 0.691 + b * b * 0.068);
		return new ColoreRgb(value,value,value);
	}

	public String schemaColore() {
		String s= "rgb("+Integer.toString(getR())+","+Integer.toString(getG())+","+Integer.toString(getB())+")";
		return s;
	}
	public void updateLuminosita(){
		int value=(int)java.lang.Math.sqrt(r * r* 0.241 + g * g * 0.691 + b * b * 0.068);
		setLuminosita((100*value)/255);
	}
	public String ToHex(){
		int rgb[] = new int[3];
		rgb[0]= r;
		rgb[1]= g;
		rgb[2]= b;
		return  rgb2hex(rgb);
	}
	
}
