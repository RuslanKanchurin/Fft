package FastFFT;
import java.util.ArrayList;
import FastFFT.FastFFT;
public class FftUse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Double> ar=new ArrayList<>();
		for (int i=0;i<128;i++) {
			ar.add((double)i);			
		}
		ArrayList<FastFFT.Complex> car=new ArrayList<>();
		car=FastFFT.ConvertToComplex(ar);
		System.out.println(car);
		FastFFT.Fft(car, false);
		System.out.println(car);

	}

}
