import java.math.*; import java.util.Random;
public class Ice {
	public static class Data{
		int year; int daysOfIce;
		public Data(int newYear, int newDaysOfIce) {
			year = newYear; daysOfIce = newDaysOfIce;
		}
		public int getYear() {
			return year;
		}
		public int getDays() {
			return daysOfIce;
		}
		public String toString() {
			return year + " " + daysOfIce;
		}
	}
	public static void main(String[] args) {
		
		Data[] iceData = getData();
		int flag = Integer.valueOf(args[0]);
		
		
		if(flag == 100) {
			
			for(int i = 0; i<162;i++) {
				System.out.println(iceData[i]);
			}
			
		}else if (flag == 200) {
			
			double sum=0; double mean = 0;
			for(int i = 0; i<iceData.length;i++) {
				sum+=iceData[i].getDays();
			}
			
			mean = sum/162;
			double stanDeviation=0; double stanSum=0;
			for(int i = 0; i<iceData.length;i++) {
				stanSum += Math.pow(iceData[i].getDays()-mean, 2);
			}
			stanDeviation = Math.sqrt(stanSum/(iceData.length-1));
			System.out.println(iceData.length);
			System.out.println(String.format("%.2f", mean));
			System.out.println(String.format("%.2f", stanDeviation));
			
		}else if(flag == 300) {
			
			double b1 = Double.valueOf(args[1]); double  b2 = Double.valueOf(args[2]);
			double MSE = getMSE(b1, b2, iceData);
			
			System.out.println(String.format("%.2f", MSE));
			
		}else if (flag == 400) {

			double sumB0 = 0; double sumB1=0; double gradB0 = 0; double gradB1 = 0;
			double B0 = Double.valueOf(args[1]);
			double B1 = Double.valueOf(args[2]);
			double[] grads = new double[2];
			grads=getGradient(B0, B1, iceData);
			gradB0 = grads[0]; gradB1 = grads[1];
			System.out.println(String.format("%.2f", gradB0));
			System.out.println(String.format("%.2f", gradB1));
			
		}else if (flag == 500) {
			
			double n = Double.valueOf(args[1]); int T = Integer.valueOf(args[2]);
			double B0 = 0; double B1 = 0; double MSE = 0;
			double gradB0 = 0; double gradB1 = 0; double []grads = new double[2];
			for(int Tcount = 0; Tcount < T; Tcount++) {
				grads = getGradient(B0,B1, iceData);
				gradB0 = grads[0]; gradB1 = grads[1];
				B0= B0 - n*gradB0;
				B1 = B1 - n*gradB1;
				MSE = getMSE(B0, B1, iceData);	
				
				
				System.out.println(Tcount+1 + " " + String.format("%.2f", B0) + " " + String.format("%.2f", B1) + " " + String.format("%.2f", MSE) );
			}
			
			
		}else if (flag == 600 ) {
			double[] CFS = new double[2];
			CFS = getCFS(iceData);
			double B0= CFS[0]; double B1 = CFS[1];
			
			System.out.println(String.format("%.2f", B0) + " " +String.format("%.2f", B1));
			
		} else if (flag == 700) {
			int x = Integer.valueOf(args[1]);
			double[] CFS = new double[2];
			CFS = getCFS(iceData);
			double B0 = CFS[0]; double B1 = CFS[1];
			
			
			System.out.println(String.format("%.2f",B0+B1*x));
			
			
		}else if (flag == 800) {
			//TODO fix this?
			double B0 = 0; double B1=0; double meanX = 0; double stdX = 0; 
			double gradB0 = 0; double gradB1 = 0; double[] grads = new double[2]; double MSE = 0;
			double N = Double.valueOf(args[1]); double T = Integer.valueOf(args[2]);
			double sum = 0;
			for(int i = 0; i<iceData.length;i++) {
				sum += iceData[i].getYear();
			}
			meanX = sum/162;
			int sumX =0;
			for (int i = 0; i<iceData.length;i++) {
				sumX += Math.pow(((double)iceData[i].getYear()-meanX), 2);
			}
			stdX = Math.sqrt(sumX/(iceData.length-1));
			//System.out.println(stdX);
			double[] newIceData = new double[162];
			double year=0;
			for(int i = 0; i<162; i++) {
				year = (double)iceData[i].getYear();
				year= (year - meanX)/stdX;
				newIceData[i]= year;
			}
			
			for(int Tcount = 0; Tcount < T; Tcount++) {
				
				grads = getGradient(B0,B1, iceData, newIceData);
				gradB0 = grads[0]; gradB1 = grads[1];
				B0= B0 - N*gradB0;
				B1 = B1 - N*gradB1;
				MSE = getMSE(B0, B1, iceData, newIceData);	
				
				
				System.out.println(Tcount+1 + " " + String.format("%.2f", B0) + " " + String.format("%.2f", B1) + " " + String.format("%.2f", MSE) );
			}
			
			
		}else if (flag == 900) {
			//TODO finish this
			double B0 = 0; double B1 = 0;double meanX=0; double stdX=0;double sum = 0; double N=Double.valueOf(args[1]); int T = Integer.valueOf(args[2]);
			for(int i = 0; i<iceData.length;i++) {
				sum += iceData[i].getYear();
			}
			meanX = sum/162;
			int sumX =0;
			for (int i = 0; i<iceData.length;i++) {
				sumX += Math.pow(((double)iceData[i].getYear()-meanX), 2);
			}
			stdX = Math.sqrt(sumX/(iceData.length-1));
			double[] newIceData = new double[162];
			double year=0;
			for(int i = 0; i<162; i++) {
				year = (double)iceData[i].getYear();
				year= (year - meanX)/stdX;
				newIceData[i]= year;
			}
				double[] grads = new double[2];
				Random rand = new Random(); int randT=0; double gradB1=0; double gradB0=0; double MSE = 0;
			for(int i = 0; i < T; i++) {
				randT = rand.nextInt(162);
				grads = getGradients(B0, B1, iceData, newIceData, randT);
				gradB0 = grads[0]; gradB1 = grads[1];
				
				B0 = B0 - N*gradB0;
				B1 =B1 - N*gradB1;
				MSE = getMSE(B0, B1, iceData, newIceData);
				System.out.println(i+1 +" " +String.format("%.2f", B0)+ " " + String.format("%.2f", B1) + " " + String.format("%.2f", MSE));
			}
			
			
		}

	}
	public static double getMSE(double B0, double B1, Data[] iceData) {
		
		double MSE = 0; double sum = 0;
		for(int i = 0 ; i < iceData.length;i++) {
			sum+= Math.pow((B0+B1*iceData[i].getYear()-iceData[i].getDays()), 2);
		}
		MSE = sum/iceData.length;
		
		return MSE;
	}
	
	public static double getMSE(double B0, double B1, Data[] iceData,double[] newIceData) {
		
		double MSE = 0; double sum = 0; 
		for(int i = 0 ; i < iceData.length;i++) {

			sum+= Math.pow((B0+B1*newIceData[i]-iceData[i].getDays()), 2);
		}
		MSE = sum/iceData.length;
		
		return MSE;
	}
	
	public static double[] getGradients( double B0, double B1, Data[] iceData, double[] newIceData, int randT) {
		double gradB0 = 0; double gradB1=0; double[] grads = new double[2];
		 gradB0 = 2*(B0+B1*newIceData[randT]-iceData[randT].getDays());
		 gradB1 = 2*((B0+B1*newIceData[randT]-iceData[randT].getDays())*newIceData[randT]);
		 grads[0] = gradB0;
		 grads[1] = gradB1;
		return grads;
	}

	public static double[] getGradient(double B0, double B1, Data[] iceData, double[] newIceData) {
		double sumB0=0; double sumB1=0; double gradB0 = 0; double gradB1 = 0; double[] grads = new double[2];

		for(int i= 0; i < iceData.length;i++) {
			sumB0 += (B0+ B1* newIceData[i] -iceData[i].getDays());
		}
		gradB0 = (sumB0*2)/iceData.length;
		
		for(int i = 0; i<iceData.length;i++) {
			sumB1 +=((B0+B1* newIceData[i] -iceData[i].getDays())* newIceData[i] );
		}
		gradB1= (2*sumB1)/iceData.length;
		
		grads[0] = gradB0;
		grads[1] = gradB1;
		return grads;
		
		
	}
	
	public static double[] getGradient( double B0, double B1, Data[] iceData) {
		double sumB0=0; double sumB1=0; double gradB0 = 0; double gradB1 = 0; double[] grads = new double[2];
		
		for(int i = 0; i < iceData.length;i++) {
			sumB0 += (B0+ B1*iceData[i].getYear()-iceData[i].getDays());
		}
		gradB0 = (sumB0*2)/iceData.length;
		
		for(int i = 0; i<iceData.length;i++) {
			sumB1 +=((B0+B1*iceData[i].getYear()-iceData[i].getDays())*iceData[i].getYear());
		}
		gradB1= (2*sumB1)/iceData.length;
		
		grads[0] = gradB0;
		grads[1] = gradB1;
		return grads;
		
	}
	
	public static double[] getCFS(Data[] iceData) {
		double meanX = 0; double meanY = 0; double B1 = 0; double B0=0; double sumX=0;
		for(int i =0; i<iceData.length;i++) {
			meanX += iceData[i].getYear();
			meanY += iceData[i].getDays();
		}
		meanX = meanX/iceData.length;
		meanY = meanY/iceData.length;
		
		for( int i =0; i<iceData.length;i++) {
			sumX += Math.pow(iceData[i].getYear()-meanX, 2);
			B1+= (iceData[i].getYear()-meanX)*(iceData[i].getDays()-meanY);
		}
		B1 = B1/sumX;
		B0 = meanY-B1*meanX;
		double[] CFS = new double[2];
		CFS[0] = B0; CFS[1] = B1;
		return CFS;
	}
	
	public static Data[] getData() {
		int counter = 0; Data[] iceData = new Data[162];int year = 1855;
		Data newData = new Data(year, 118); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 151); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 121); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 96); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 110); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 117 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,132 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,104 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,125 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,118 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,125 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,123 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,110 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,127 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,131 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,99 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,126 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,144 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,136 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,126 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,91 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,130 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,62 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,112 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,99 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,161 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,78 );iceData[counter]= newData; counter++; year++;
		newData = new Data(year,124 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,119 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,124 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,128 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,131 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,113 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,88 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,75 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,111 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,97 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,112 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year,101 ); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 101); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 91); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 110); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 100); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 130); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 111); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 107); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 89); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 126); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 108); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 97); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 94); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 83); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 106); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 98); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 101); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 108); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 99); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 88); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 115); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 102); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 116); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 115); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 82); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 110); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 81); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 96); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 125); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 104); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 124); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 103); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 106); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 96); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 107); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 98); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 65); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 115); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 91); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 94); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 101); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 121); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 97); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 96); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 82); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 116); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 114); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 92); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 98); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 101); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 104); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 96); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 109); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 122); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 114); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 81); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 85); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 92); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 114); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 111); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 95); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 126); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 108); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 117); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 112); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 113); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 120); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 65); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 98); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 91); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 108); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 113); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 110); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 97); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 105); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 107); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 88); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 115); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 123); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 118); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 99); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 93); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 96); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 54); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 111); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 85); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 107); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 89); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 87); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 97); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 93); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 88); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 99); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 108); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 94); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 74); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 119); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 102); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 47); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 82); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 53); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 115); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 21); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 89); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 80); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 101); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 95); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 66); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 106); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 97); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 87); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 109); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 57); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 87); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 117); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 91); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 62); iceData[counter]= newData; counter++; year++;
		newData = new Data(year, 65); iceData[counter]= newData; counter++; year++;
		return iceData;
	}
		
	
}
