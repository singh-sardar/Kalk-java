package kalk;

public abstract class Colore {
	
	public abstract boolean uguale(Colore c);
	public abstract boolean diverso(Colore c);
	public abstract Colore somma(Colore c);
	public abstract Colore differenza(Colore c);
	public abstract Colore modula(Colore c);
	public abstract double deltaE(Colore c);
	public abstract Colore scala(double s);
	public abstract Colore complementare();
	public abstract Colore luminositaColore();
	public abstract boolean coloreCaldo();
	public abstract String schemaColore();
	
	
	
	
	private static double abs(double d){
		return d >=0 ? d : -d;
	}
	public static int[] hsl2rgb(double HSL[]){
		int RGB[] = new int[3];
	    double  RGB1[] = new double[3];
	    HSL[1]/=100.0;
	    HSL[2]/=100.0;
	    if (HSL[1] == 0.0)
	    {
	        RGB[0] = RGB[1]= RGB[2] = (int) (HSL[2] * 255);
	    }
	    else
	    {


	        double chroma = (1 - abs(2 * HSL[2] - 1)) * HSL[1];
	        double x = chroma * (1 - (abs(((HSL[0]/ 60)%2)-1) ));
	        double m = HSL[2] - chroma/2;

	        if(HSL[0]>=0.0&&HSL[0]<60.0){
	            RGB1[0]=chroma;
	            RGB1[1]=x;
	            RGB1[2]=0;
	        }
	        if(HSL[0]>=60.0&&HSL[0]<120.0){
	            RGB1[0]=x;
	            RGB1[1]=chroma;
	            RGB1[2]=0;
	        }
	        if(HSL[0]>=120.0&&HSL[0]<180.0){
	            RGB1[0]=0;
	            RGB1[1]=chroma;
	            RGB1[2]=x;
	        }
	        if(HSL[0]>=180.0&&HSL[0]<240.0){
	            RGB1[0]=0;
	            RGB1[1]=x;
	            RGB1[2]=chroma;
	        }
	        if(HSL[0]>=240.0&&HSL[0]<300.0){
	            RGB1[0]=x;
	            RGB1[1]=0;
	            RGB1[2]=chroma;
	        }
	        if(HSL[0]>=300.0&&HSL[0]<360.0){
	            RGB1[0]=chroma;
	            RGB1[1]=0;
	            RGB1[2]=x;
	        }
	        RGB[0]=(int)((RGB1[0]+m)*255);
	        RGB[1]=(int)((RGB1[1]+m)*255);
	        RGB[2]=(int)((RGB1[2]+m)*255);

	    }

		return RGB;
	}
}
