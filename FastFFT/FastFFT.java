package FastFFT;
import java.util.*;

import FastFFT.FastFFT.Complex;

public class FastFFT {
	public static ArrayList<Complex> ConvertToComplex(ArrayList<Double> ar) {
		ArrayList<Complex> res=new ArrayList<Complex>();
		for (Double el:ar) {			
			res.add(new Complex(el,0.0));
		}
		return res;
	}
	public static void Fft(ArrayList<Complex> a, boolean invert){
		int n=a.size();
		if (n==1) return ;
		ArrayList<Complex> a0 =new ArrayList<>(n/2);
		ArrayList<Complex> a1 =new ArrayList<>(n/2);
		for (int i=0,j=0;i<n;i+=2,++j) {
			a0.add(j,a.get(i));
			a1.add(j,a.get(i+1));
		}
		Fft(a0, invert);
		Fft(a1, invert);
		
		double ang=2*Math.PI/n*(invert?-1:1);
		Complex w=new Complex(1,0);
		Complex wn=new Complex(Math.cos(ang), Math.sin(ang));
		for (int i=0;i<n/2;++i) {
			a.set(i, a0.get(i).sum(w.Multiplicate(a1.get(i))));
			a.set(i+n/2, a0.get(i).sub(w.Multiplicate(a1.get(i))));
			if (invert) {
				a.set(i, a.get(i).divByReal(2.0));
				a.set(i+n/2, a.get(i+n/2).divByReal(2.0));
			}
			w=w.Multiplicate(wn);
		}
	}
	public static class Complex{
		double re;
		double im;
		public Complex(double re, double im) {
			this.re=re;
			this.im=im;
		}
		public Complex Multiplicate(Complex y) {
			Complex res=new Complex(this.re*y.re-this.im*y.im, this.im*y.re+this.re*y.im);
			return res;
		}
		public Complex sum(Complex y) {
			Complex res=new Complex(this.re+y.re, this.im+y.im);
			return res;
		}
		public Complex sub(Complex y) {
			Complex res=new Complex(this.re-y.re, this.im-y.im);
			return res;
		}
		public Complex divByReal(Double y) {
			Complex res=new Complex(this.re/y, this.im/y);
			return res;
		}
		@Override
		public String toString() {
			return re+"+"+im+"i";
		}
	}
	
}
