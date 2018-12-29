import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Neural {
	

	public static void main(String[] args) throws FileNotFoundException {
		int FLAG = Integer.valueOf(args[0]);
		
		if (FLAG == 100) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			double x1 = Double.valueOf(args[10]); double x2 = Double.valueOf(args[11]);
			double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
			double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
			A = getUVValues('A', weights, x1, x2,0,0);
			uA= A[0]; vA = A[1]; 
			B=getUVValues('B', weights, x1, x2, 0,0);
			uB=B[0]; vB=B[1];
			C = getUVValues('C',weights,x1,x2,vA, vB);
			uC=C[0]; vC=C[1];
			System.out.println(String.format("%.5f", uA)+ " " + String.format("%.5f", vA) + " " + String.format("%.5f", uB) + " " + String.format("%.5f", vB)+ " " + String.format("%.5f", uC) + " " +String.format("%.5f", vC));
			
		}else if(FLAG == 200) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			double x1 = Double.valueOf(args[10]); double x2 = Double.valueOf(args[11]);
			double y = Double.valueOf(args[12]);
			double E = 0; double EvC=0; double EuC = 0;
			double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
			double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
			A = getUVValues('A', weights, x1, x2,0,0);
			uA= A[0]; vA = A[1]; 
			B=getUVValues('B', weights, x1, x2, 0,0);
			uB=B[0]; vB=B[1];
			C = getUVValues('C',weights,x1,x2,vA, vB);
			uC=C[0]; vC=C[1];
			
			E= 0.5*Math.pow(vC-y, 2);
			EvC = vC-y;
			EuC = EvC*(vC*(1-vC));
			
			System.out.println(String.format("%.5f", E)+ " " + String.format("%.5f", EvC) + " " + String.format("%.5f", EuC));
			
		}else if(FLAG == 300) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			double x1 = Double.valueOf(args[10]); double x2 = Double.valueOf(args[11]);
			double y = Double.valueOf(args[12]);
			double E = 0; double EvC=0; double EuC = 0;double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
			double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
			double EuA = 0; double EvA = 0; double EuB=0; double EvB = 0;
			A = getUVValues('A', weights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', weights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',weights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			E= 0.5*Math.pow(vC-y, 2); EvC = vC-y; EuC = EvC*(vC*(1-vC));
			
			EvA=weights[7]*EuC;
			EvB=weights[8]*EuC;
			if(uA<0) {
				EuA=0;
			}else {
				EuA = EvA;
			}
			if(uB<0) {
				EuB=0;
			}else {
				EuB =EvB;
			}
			System.out.println(String.format("%.5f", EvA)+ " " + String.format("%.5f", EuA) + " " + String.format("%.5f", EvB) + " " + String.format("%.5f", EuB));
			
		}else if(FLAG==400) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			double x1 = Double.valueOf(args[10]); double x2 = Double.valueOf(args[11]);
			double y = Double.valueOf(args[12]);
			double E = 0; double EvC=0; double EuC = 0;double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
			double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
			double EuA = 0; double EvA = 0; double EuB=0; double EvB = 0;
			A = getUVValues('A', weights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', weights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',weights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			E= 0.5*Math.pow(vC-y, 2); EvC = vC-y; EuC = EvC*(vC*(1-vC));
			EvA=weights[7]*EuC;EvB=weights[8]*EuC;
			if(uA<0) {EuA=0;}else {EuA = EvA;}
			if(uB<0) {EuB=0;}else {EuB =EvB;}
			
			double Ew1 = 0; double Ew2 =0; double Ew3=0; double Ew4 = 0; double Ew5 = 0; double Ew6=0; double Ew7=0; double Ew8=0; double Ew9=0;
			Ew1=EuA;
			Ew2=x1*EuA;
			Ew3=x2*EuA;
			Ew4=EuB;
			Ew5=x1*EuB;
			Ew6=x2*EuB;
			Ew7=EuC;
			Ew8=vA*EuC;
			Ew9=vB*EuC;
			System.out.println(String.format("%.5f", Ew1) +" "+String.format("%.5f", Ew2) +" "+String.format("%.5f", Ew3) +" "
					+String.format("%.5f", Ew4) +" "+String.format("%.5f", Ew5) +" "+String.format("%.5f", Ew6) +" "
					+String.format("%.5f", Ew7) +" "+String.format("%.5f", Ew8) +" "+String.format("%.5f", Ew9) +" ");
			
		}else if(FLAG==500) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			double x1 = Double.valueOf(args[10]); double x2 = Double.valueOf(args[11]);
			double y = Double.valueOf(args[12]);
			double n = Double.valueOf(args[13]);
			double E = 0; double EvC=0; double EuC = 0;double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
			double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
			double EuA = 0; double EvA = 0; double EuB=0; double EvB = 0;
			A = getUVValues('A', weights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', weights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',weights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			E= 0.5*Math.pow(vC-y, 2); EvC = vC-y; EuC = EvC*(vC*(1-vC));
			EvA=weights[7]*EuC;EvB=weights[8]*EuC;
			if(uA<0) {EuA=0;}else {EuA = EvA;}
			if(uB<0) {EuB=0;}else {EuB =EvB;}
			
			double Ew1 = 0; double Ew2 =0; double Ew3=0; double Ew4 = 0; double Ew5 = 0; double Ew6=0; double Ew7=0; double Ew8=0; double Ew9=0;
			Ew1=EuA;
			Ew2=x1*EuA;
			Ew3=x2*EuA;
			Ew4=EuB;
			Ew5=x1*EuB;
			Ew6=x2*EuB;
			Ew7=EuC;
			Ew8=vA*EuC;
			Ew9=vB*EuC;
			double[] newWeights = new double[9];
			newWeights[0]= weights[0]-n*Ew1;
			newWeights[1]= weights[1]-n*Ew2;
			newWeights[2]= weights[2]-n*Ew3;
			newWeights[3]= weights[3]-n*Ew4;
			newWeights[4]= weights[4]-n*Ew5;
			newWeights[5]= weights[5]-n*Ew6;
			newWeights[6]= weights[6]-n*Ew7;
			newWeights[7]= weights[7]-n*Ew8;
			newWeights[8]= weights[8]-n*Ew9;
			
			
			A = getUVValues('A', newWeights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', newWeights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',newWeights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			double newE= 0.5*Math.pow(vC-y, 2);
			
			System.out.println(String.format("%.5f",weights[0]) + " " +String.format("%.5f",weights[1]) + " " +String.format("%.5f",weights[2]) + " " +String.format("%.5f",weights[3]) + " " +String.format("%.5f",weights[4]) + " " +
					String.format("%.5f",weights[5]) + " " +String.format("%.5f",weights[6]) + " " +String.format("%.5f",weights[7]) + " " +String.format("%.5f",weights[8]));
			System.out.println(String.format("%.5f",E));
			System.out.println(String.format("%.5f",newWeights[0]) + " " +String.format("%.5f",newWeights[1]) + " " +String.format("%.5f",newWeights[2]) + " " +String.format("%.5f",newWeights[3]) + " " +String.format("%.5f", newWeights[4]) + " " +
					String.format("%.5f",newWeights[5]) + " " +String.format("%.5f",newWeights[6]) + " " +String.format("%.5f",newWeights[7]) + " " +String.format("%.5f",newWeights[8]));
			System.out.println(String.format("%.5f",newE));
			
		}else if(FLAG==600) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			double n = Double.valueOf(args[10]);
			 weights = epoch(weights,n,1);	
		}else if(FLAG==700) {
			double[] weights = new double[9];
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			int t = Integer.valueOf(args[11]);
			for(int i =0; i<t; i++) {
				double n = Double.valueOf(args[10]);
				 weights = epoch(weights,n,0);
				 System.out.println(String.format("%.5f",weights[0]) + " " +String.format("%.5f",weights[1]) + " " +String.format("%.5f",weights[2]) + " " +String.format("%.5f",weights[3]) + " " +String.format("%.5f", weights[4]) + " " +
							String.format("%.5f",weights[5]) + " " +String.format("%.5f",weights[6]) + " " +String.format("%.5f",weights[7]) + " " +String.format("%.5f",weights[8]));
				 File eval = new File("hw2_midterm_A_eval.txt");
				 Scanner sEval = new Scanner(eval);
				 double evalSetError = 0;double newE=0;
				 double x1 = 0; double x2 = 0; double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];  double uA = 0; double vA = 0; double uB =0; 
					double vB=0; double uC=0; double vC=0; double y=0;
					while(sEval.hasNext()) {
						x1=sEval.nextDouble();x2=sEval.nextDouble();y=sEval.nextDouble();
						A = getUVValues('A', weights, x1, x2,0,0); uA= A[0]; vA = A[1];
						B=getUVValues('B', weights, x1, x2, 0,0); uB=B[0]; vB=B[1];
						C = getUVValues('C',weights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
						newE= 0.5*Math.pow(vC-y, 2);
						evalSetError+=newE;
					}
					System.out.println(String.format("%.5f", evalSetError));
					
			}
			
		}else if(FLAG==800) {
			double[] weights = new double[9];double evalSetError = 0;
			for(int i = 0; i<9; i++) {
				weights[i] = Double.valueOf(args[i+1]);
			}
			int t = Integer.valueOf(args[11]);
			int epochCounter=0;
			double oldError = 100;
			for(int i =0; i<t; i++) {
				double n = Double.valueOf(args[10]);
				 weights = epoch(weights,n,0);
				 File eval = new File("hw2_midterm_A_eval.txt");
				 Scanner sEval = new Scanner(eval);
				 double newE=0;
				 double x1 = 0; double x2 = 0; double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];  double uA = 0; double vA = 0; double uB =0; 
					double vB=0; double uC=0; double vC=0; double y=0;
					evalSetError = 0;
					while(sEval.hasNext()) {
						x1=sEval.nextDouble();x2=sEval.nextDouble();y=sEval.nextDouble();
						A = getUVValues('A', weights, x1, x2,0,0); uA= A[0]; vA = A[1];
						B=getUVValues('B', weights, x1, x2, 0,0); uB=B[0]; vB=B[1];
						C = getUVValues('C',weights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
						newE= 0.5*Math.pow(vC-y, 2);
						evalSetError+=newE;
					}
					if(evalSetError>oldError) {
						epochCounter=i+1;
						i=t;
					}
					oldError=evalSetError;
					
			}
			 System.out.println(epochCounter);
			 System.out.println(String.format("%.5f",weights[0]) + " " +String.format("%.5f",weights[1]) + " " +String.format("%.5f",weights[2]) + " " +String.format("%.5f",weights[3]) + " " +String.format("%.5f", weights[4]) + " " +
						String.format("%.5f",weights[5]) + " " +String.format("%.5f",weights[6]) + " " +String.format("%.5f",weights[7]) + " " +String.format("%.5f",weights[8]));
			 System.out.println(String.format("%.5f", evalSetError));
			
			 double testAccuracy = testWeights(weights); 
			 System.out.println(String.format("%.5f", testAccuracy));
			
		}

	}
	public static double testWeights(double[] weights) throws FileNotFoundException {
		File test = new File("hw2_midterm_A_test.txt");
		double correctCounter = 0;
		Scanner sTest = new Scanner(test);
		while(sTest.hasNext()) {
		double x1 = sTest.nextDouble(); double x2 = sTest.nextDouble(); double y= sTest.nextDouble();
		double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
		double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
		A = getUVValues('A', weights, x1, x2,0,0);
		uA= A[0]; vA = A[1]; 
		B=getUVValues('B', weights, x1, x2, 0,0);
		uB=B[0]; vB=B[1];
		C = getUVValues('C',weights,x1,x2,vA, vB);
		uC=C[0]; vC=C[1];
		if((vC < 0.5 && y == 0) || (vC>0.5 && y==1)) {
			correctCounter++;
		}
		}
		return correctCounter/25;
	}
	public static double[] getUVValues(char node, double[] weights, double x1, double x2, double vA1, double vB1) {
		if(node == 'A') {
			
			double uA = weights[0]+ weights[1]*x1 + weights[2]*x2;
			double vA = Math.max(uA, 0);
			double[] A = new double[2]; 
			A[0]=uA;A[1]=vA;
			return A;
			
		}else if(node == 'B') {
			double uB = weights[4]*x1+weights[5]*x2+weights[3];
			double vB = Math.max(uB, 0);
			double[] B = new double[2]; 
			B[0] = uB; B[1] = vB;
			return B;
			
		}else if(node == 'C') {
			double uC = weights[6]+ weights[7]*vA1+weights[8]*vB1;
			double vC = 1/(1+Math.pow(Math.E, -(uC)));
			double[] C = new double[2];
			C[0]=uC; C[1] = vC;
			return C;
	}
		return null;
	
	}
	public static double[] epoch(double[] weights, double n, int option) throws FileNotFoundException {
		File eval = new File("hw2_midterm_A_eval.txt");
		//File test = new File("hw2_midterm_A_test.txt");
		File train = new File("hw2_midterm_A_train.txt");
		Scanner sEval = new Scanner(eval);
		//Scanner sTest = new Scanner(test);
		Scanner sTrain = new Scanner(train);
		while(sTrain.hasNext()) {
			sEval = new Scanner(eval);
			double x1 = sTrain.nextDouble(); double x2=sTrain.nextDouble(); double y=sTrain.nextDouble();
			double E = 0; double EvC=0; double EuC = 0;double uA = 0; double vA = 0; double uB = 0; double vB = 0; double uC = 0; double vC = 0;
			double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2];
			double EuA = 0; double EvA = 0; double EuB=0; double EvB = 0;
			A = getUVValues('A', weights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', weights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',weights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			E= 0.5*Math.pow(vC-y, 2); EvC = vC-y; EuC = EvC*(vC*(1-vC));
			
			//System.out.println();
			//System.out.println(uA + " " + vA);
			
			EvA=weights[7]*EuC;EvB=weights[8]*EuC;
			if(uA<0) {EuA=0;}else {EuA = EvA;}
			if(uB<0) {EuB=0;}else {EuB =EvB;}
			
			double Ew1 = 0; double Ew2 =0; double Ew3=0; double Ew4 = 0; double Ew5 = 0; double Ew6=0; double Ew7=0; double Ew8=0; double Ew9=0;
			Ew1=EuA;
			Ew2=x1*EuA;
			Ew3=x2*EuA;
			Ew4=EuB;
			Ew5=x1*EuB;
			Ew6=x2*EuB;
			Ew7=EuC;
			Ew8=vA*EuC;
			Ew9=vB*EuC;
			double[] newWeights = new double[9];
			newWeights[0]= weights[0]-n*Ew1;
			newWeights[1]= weights[1]-n*Ew2;
			newWeights[2]= weights[2]-n*Ew3;
			newWeights[3]= weights[3]-n*Ew4;
			newWeights[4]= weights[4]-n*Ew5;
			newWeights[5]= weights[5]-n*Ew6;
			newWeights[6]= weights[6]-n*Ew7;
			newWeights[7]= weights[7]-n*Ew8;
			newWeights[8]= weights[8]-n*Ew9;
			
			
			A = getUVValues('A', newWeights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', newWeights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',newWeights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			weights=newWeights;
			if(option==1) {
			System.out.println(String.format("%.5f", x1) + " " + String.format("%.5f", x2)+ " " + String.format("%.5f",y));
			System.out.println(String.format("%.5f",newWeights[0]) + " " +String.format("%.5f",newWeights[1]) + " " +String.format("%.5f",newWeights[2]) + " " +String.format("%.5f",newWeights[3]) + " " +String.format("%.5f", newWeights[4]) + " " +
					String.format("%.5f",newWeights[5]) + " " +String.format("%.5f",newWeights[6]) + " " +String.format("%.5f",newWeights[7]) + " " +String.format("%.5f",newWeights[8]));
			double evalSetError = 0;double newE=0;
			while(sEval.hasNext()) {
				x1=sEval.nextDouble();x2=sEval.nextDouble();y=sEval.nextDouble();
				A = getUVValues('A', newWeights, x1, x2,0,0); uA= A[0]; vA = A[1];
				B=getUVValues('B', newWeights, x1, x2, 0,0); uB=B[0]; vB=B[1];
				C = getUVValues('C',newWeights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
				newE= 0.5*Math.pow(vC-y, 2);
				evalSetError+=newE;
			}
			System.out.println(String.format("%.5f", evalSetError));
		}
		}
		return weights;
	}
	public static double getError(double[] newWeights) throws FileNotFoundException {
		File eval = new File("hw2_midterm_A_eval.txt");
		Scanner sEval = new Scanner(eval);
		double x1 = 0; double x2 = 0; double[] A = new double[2]; double[] B = new double[2]; double[] C = new double[2]; double evalSetError = 0; double newE = 0; double uA = 0; double vA = 0; double uB =0; 
		double vB=0; double uC=0; double vC=0; double y=0;
		while(sEval.hasNext()) {
			x1=sEval.nextDouble();x2=sEval.nextDouble();y=sEval.nextDouble();
			A = getUVValues('A', newWeights, x1, x2,0,0); uA= A[0]; vA = A[1];
			B=getUVValues('B', newWeights, x1, x2, 0,0); uB=B[0]; vB=B[1];
			C = getUVValues('C',newWeights,x1,x2,vA, vB); uC=C[0]; vC=C[1];
			newE= 0.5*Math.pow(vC-y, 2);
			evalSetError+=newE;
		}
		return evalSetError;
	}

}
