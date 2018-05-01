package kalk;

public class ColoreHsl extends Colore{

	private int h,s,l;//(Hue, Saturation, Lightness)


	public ColoreHsl(){//colore nero
		h=s=l=0;
		updateLuminosita();
	}

	public ColoreHsl(int hue, int sat, int light){
		if(hue<0|| hue >= 360)
			h=0;
		else
			h=hue;
		if(sat<0|| sat > 100)
			s=0;
		else
			s=sat;
		if(light<0|| light > 100)
			l=0;
		else
			l=light;
		updateLuminosita();
	}

	public void setH(int hue){
		if(hue>=0&&hue <360)
			h=hue;


	}
	public void setS(int sat){
		if(sat>=0&&sat <=100)
			s=sat;

	}
	public void setL(int light){
		if(light>=0&&light <=100){
			l=light;
			updateLuminosita();
		}
	}
	public int getH(){ return h;}
	public int getS(){return s;}
	public int getL(){return l;}


	public void modificaColore(int hue, int sat, int light){
		setH(hue);
		setS(sat);
		setL(light);
	}

	public ColoreHsl somma(Colore c){
		//media dei hue
		//se la la differenza tra gli hue è > 180 => add mezzo giro (360/2 = 180) in modulo 360.else

		//algoritmo ricavato da idea:
		//RGB colour space uses a Cartesian coordinate system while HSL and HSV use polar coordinates. "Half way" is fairly simple in Cartesian coordinates, you just average them pairwise. It gets more complex in polar coordinates. You think of "half way" in different terms, for instance, it's halfway point of an arc ? If an arc, there are two, and four "rules" for choosing one of the two so do you go the short way, the long way, clockwise from the first colour, or counterclockwise from the first colour.
		//To find the "straight line" you pretty much have to convert to a Cartesian coordinate system. The simplest arc version would be to average the heights (L) and radii (S), and bisect the smaller angle between the hues. The result will be more saturated than the straight line version.

		ColoreHsl aux= new ColoreHsl();
		if (c instanceof ColoreHsl) {
			ColoreHsl tmp = (ColoreHsl)c;

			int mediaH = (int)(0.5*h +0.5*tmp.getH());
			if(abs(h-tmp.getH())>180)
				aux.setH((mediaH+180)%360);
			else
				aux.setH(mediaH);
			aux.setS((int)(0.5*s+tmp.getS()*0.5));
			aux.setL((int)(0.5*l+tmp.getL()*0.5));
		}
		return aux;
	}
	public ColoreHsl differenza(Colore c){
		ColoreHsl aux= new ColoreHsl();

		if (c instanceof ColoreHsl) {
			ColoreHsl tmp = (ColoreHsl)c;
			int diff = h-tmp.getH();

			if(diff>0)
				aux.setH(diff);

			diff= s -tmp.getS();
			if(diff>0)
				aux.setS(diff);

			diff =l-tmp.getL();
			if(diff>0)
				aux.setL(diff);
		}
		return aux;
	}
	public ColoreHsl modula(Colore c){
		ColoreHsl result = new ColoreHsl();
		if (c instanceof ColoreHsl) {
			ColoreHsl tmp = (ColoreHsl)c;
			result.modificaColore((tmp.getH()*h)%360,(tmp.getS()*s)%100,(tmp.getL()*l)%100);
		}
		return result;
	}
	public  ColoreHsl scala(double s){
		ColoreHsl result = new ColoreHsl(h,this.s,l);
		if(h*s>=0 && h*s<361){
			result.setH((int)(h*s));
		}
		if(this.s*s>=0 && this.s*s<101){
			result.setS((int)(this.s*s));
		}
		if(l*s>=0 && l*s<101){
			result.setL((int)(l*s));
		}
		return result;
	}
	public ColoreHsl complementare(){
		ColoreHsl  result= new ColoreHsl(h,s,l);
		result.setH((h+180)%360);
		return result;
	}
	public ColoreHsl grayScale(){
		ColoreHsl result = new ColoreHsl(0,0,l);
		return result;
	}

	public boolean coloreCaldo(){
		return h<180;
	}

	public double deltaE(Colore c){


		if (c instanceof ColoreHsl) {
			ColoreHsl chsl = (ColoreHsl)c;


			double deltaE;
			double Lab1[] = new double[3], Lab2[]=new double[3];
			//converting to rgb
			int rgb1[] = new int[3];
			int rgb2[]=new int[3];
			rgb1=ToRgb();
			rgb2=chsl.ToRgb();
			//converting to lab
			Lab1=rgb2lab(rgb1);
			Lab2=rgb2lab(rgb2);
			//calculating value
			deltaE= java.lang.Math.sqrt(java.lang.Math.pow(Lab2[0]-Lab1[0],2) + java.lang.Math.pow(Lab2[1]-Lab1[1],2) +java.lang.Math.pow(Lab2[2]-Lab1[2],2));
			return deltaE;

		}else
			return -1;
	}

	public int[] ToRgb(){
		double hsl[]= new double[3];
		hsl[0]=h;
		hsl[1]=s;
		hsl[2]=l;
		return hsl2rgb(hsl);
	}
	public double[] ToLab(){
		int rgb[]= new int[3];
		rgb=ToRgb();
		return rgb2lab(rgb);
	}
	public String ToHex(){
		int rgb[] = new int[3];
		rgb=ToRgb();
		return rgb2hex(rgb);
	}
	public void updateLuminosita(){
		setLuminosita(l);
	}
	public String schemaColore(){
		String s= "hsl("+Integer.toString(getH())+","+Integer.toString(getS())+","+Integer.toString(getL())+")";
		return s;
	}



}
