package kalk;

public class ColoreRgb extends Colore{
	/*
    private int r, g, b; 
    private float a; 


    public ColoreRgb(){
    	r=g=b=0;
    	a =1;
    }
    public ColoreRgb(int R, int G, int B, float A){

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
        if(A>0 || A > 1)
            a= 1 ;
        else
        	a=A;

    }

     private static double pivotXYZ(double q){//funzione di utilita per rgb2lab
    	 double value;
    	    if ( q > 0.008856 ) {
    	        value = java.lang.Math.pow( q, 0.333333 );
    	        return value;
    	    }
    	    else {
    	        value = (903.3*q+16)/116;
    	        return value;
    	    }
    }


    private static double PivotRgb(double n){
    	return (n > 0.04045 ? java.lang.Math.pow((n + 0.055) / 1.055, 2.4) : n / 12.92) * 100.0;
    }
    private static double Min(double a,double b){
    	 return a<b ? a:b;
    }
    private static double Max(double a,double b){
    	  return a>b ? a:b;
    }


    public void setR(int red){
    	if(red>=0 && red<= 255)
            r=red;
    }
    public void setG(int green){
    	if(green>=0 && green<=255)
            g= green;
    }
    public void setB(int blue){
    	 if(blue>=0 && blue<= 255)
    	        b=blue;
    }
    public void setA(float alpha){
    	  if(alpha>=0 && alpha <= 1)
    	        a= alpha;
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
    public float getA(){
    	return a;
    }
    public void modificaColore(int red, int green, int blue, float alpha){
    	setR(red);
        setG(green);
        setB(blue);
        setA(alpha);
    }




	@Override
	public boolean uguale(Colore c) {
		  return (schemaColore().equals(c.schemaColore()));
	}

	@Override
	public boolean diverso(Colore c) {
		return !(this.uguale(c));
	}

	@Override
	public Colore somma(Colore c) {

		ColoreRgb aux= new ColoreRgb();

		if (c instanceof ColoreRgb) {
			ColoreRgb tmp = (ColoreRgb)c;
	        aux.setR((int)(0.5*r+tmp.getR()*0.5));
	        aux.setG((int)(0.5*g+tmp.getG()*0.5));
	        aux.setB((int)(0.5*b+tmp.getB()*0.5));
	    }
	    return aux;

	}

	@Override
	public Colore differenza(Colore c) {
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

	@Override
	public Colore modula(Colore c) {
		 ColoreRgb aux= new ColoreRgb();

		 if (c instanceof ColoreRgb) {
				ColoreRgb tmp = (ColoreRgb)c;
		        aux.setR((r*tmp.getR())/255);
		        aux.setG((g*tmp.getG())/255);
		        aux.setB((b*tmp.getB())/255);
		    }
		    return aux;
	}

	@Override
	public double deltaE(Colore c) {
		if (c instanceof ColoreRgb) {
			ColoreRgb crgb1 = (ColoreRgb)c;
	        double deltaE;
	        double Lab1[] = new double[3];
	        double Lab2[] = new double[3];

	        Lab1 = rgb2lab();
	        Lab2 = crgb1.rgb2lab();

	        deltaE= java.lang.Math.sqrt(java.lang.Math.pow(Lab2[0]-Lab1[0],2) + java.lang.Math.pow(Lab2[1]-Lab1[1],2) +java.lang.Math.pow(Lab2[2]-Lab1[2],2));
	        return deltaE;

	    }else
	        return -1;
	}


    public double[] rgb2lab(){
    	double Lab[] = new double[3];

    	double RGB[] = new double[3];
        double XYZ[] = new double[3];
        double adapt[] = new double[3];

        //riferimenti del bianco
        adapt[0] = 95.0467;
        adapt[1] = 100.0000;
        adapt[2] = 108.8969;

        //riporto il colore da scala 0 - 255 a 0 - 1.0
        RGB[0] = PivotRgb(r /255.0);
        RGB[1] = PivotRgb(g /255.0);
        RGB[2] = PivotRgb(b /255.0);

        //RGB TO XYZ
        XYZ[0] = 0.412424 * RGB[0] + 0.357579 * RGB[1] + 0.180464 * RGB[2];
        XYZ[1] = 0.212656 * RGB[0] + 0.715158 * RGB[1] + 0.0721856 * RGB[2];
        XYZ[2] = 0.0193324 * RGB[0] + 0.119193 * RGB[1] + 0.950444 * RGB[2];

        //LAB
        Lab[0] = 116 * pivotXYZ( XYZ[1] / adapt[1] ) - 16;
        if(Lab[0]<0)
            Lab[0]=0;
        Lab[1] = 500 * ( pivotXYZ( XYZ[0] / adapt[0] ) - pivotXYZ( XYZ[1] / adapt[1] ) );
        Lab[2] = 200 * ( pivotXYZ( XYZ[1] / adapt[1] ) - pivotXYZ( XYZ[2] / adapt[2] ) );

        return Lab;
    }


    public double[] rgb2hsl(){
    	double HSL[]= new double[3];
    	double RGB[] = new double[3];
        //Portare RGB in ranga tra 0 -1.0
        RGB[0] = r /255.0;
        RGB[1] = g /255.0;
        RGB[2] = b /255.0;

        //calcolo del min e max e del delta
        double min = Min(Min(RGB[0], RGB[1]), RGB[2]);
        double max = Max(Max(RGB[0], RGB[1]), RGB[2]);
        double delta = max - min;

        //calcolo del L
        HSL[2] = (max + min) / 2.0;

        if (delta == 0.0)
        {   //in caso di delta = 0 => H = S = 0
            HSL[0] = 0.0;
            HSL[1] = 0.0;
        }
        else
        {
          //Formula di calcolo DEL S se delta !=0
            HSL[1] = ((2*HSL[2]) >=1.0) ? (delta / (1.0 -(2.0*HSL[2]-1.0))) : (delta / (1.0 +(2.0*HSL[2]-1.0)));

            double hue;
            //Calcolo del H
            if (RGB[0] == max)
            {
                hue = ((RGB[1] - RGB[2]) / 6.0) / delta;
            }
            else if (RGB[1] == max)
            {
                hue = (1.0 / 3.0) + ((RGB[2] - RGB[0]) / 6.0) / delta;
            }
            else
            {
                hue = (2.0 / 3.0) + ((RGB[0] - RGB[1]) / 6.0) / delta;
            }

            if (hue < 0.0)
                hue += 1;
            if (hue > 1.0)
                hue -= 1;

            HSL[0] =(hue  *360);
        }
        //trasformazione in precentuale di SL
        HSL[1]= HSL[1]*100;
        HSL[2]= HSL[2]*100;
        return HSL;
    }

	@Override
	public Colore scala(double s) {
		 ColoreRgb aux = new ColoreRgb(r,g,b,a);
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

	@Override
	public Colore complementare() {
		ColoreRgb aux = new ColoreRgb();
	    double HSL[]= new double[3];
	    int RGB[] = new int[3];
	    HSL=rgb2hsl();
	    HSL[0]+=180;
	    HSL[0]=(HSL[0]%400);
	    RGB = hsl2rgb(HSL);
	    aux.setR(RGB[0]);
	    aux.setG(RGB[1]);
	    aux.setB(RGB[2]);

	    return aux;
	}

	@Override
	public Colore luminositaColore() {
		int value=(int)java.lang.Math.sqrt(r * r* 0.241 + g * g * 0.691 + b * b * 0.068);
	    return new ColoreRgb(value,value,value,1);
	}

	@Override
	public boolean coloreCaldo() {
		return r>b;
	}

	@Override
	public String schemaColore() {
		String s= "rgba("+Integer.toString(getR())+","+Integer.toString(getG())+","+Integer.toString(getB())+","+Float.toString(getA())+")";
	    return s;
	}

    public String rgb2hex(){
    	String s = "#";
    	String tmp="";
    	tmp=Integer.toString(r, 16);
    	if(tmp.length()==1)
    		s+="0"+tmp;
    	else
    		s+=tmp;

    	tmp="";
    	tmp=Integer.toString(g, 16);

    	if(tmp.length()==1)
    		s+="0"+tmp;
    	else
    		s+=tmp;


    	tmp="";
    	tmp=Integer.toString(b, 16);



    	if(tmp.length()==1)
    		s+="0"+tmp;
    	else
    		s+=tmp;


    	return s;
    }
	 */


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
