
import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    static class Interval{
    	int index; double left; double right; 
    	public Interval(int newIndex, double newLeft,double newRight) {
    	index = newIndex;
    	left= newLeft;
    	right = newRight;
    	}
    	public Interval() {
    	}
    	public boolean inRange(double numToCheck) {
    		if (numToCheck>left && numToCheck<=right) {
    			return true;
    		}
    		return false;
    	}
    	public int getIndex() {
    		return index;
    	}
    	public String toString() {
    		return index + " " + String.format("%.7f", left) + " " + String.format("%.7f", right) + " ";
    	}
    	
    }
    private static ArrayList<Interval> getIntervals(ArrayList<Integer> corpus,int choice){
    	int index; double left; double right;double farRight=0;int count; double probability; ArrayList<Interval> intervals = new ArrayList<Interval>();
    	if(choice==2) {
    	for(int i=0;i<4700;i++) {
    		index=i; 
    		left=farRight;
    		count = Collections.frequency(corpus, index);
    		
    		probability = count/(double)corpus.size();
    		right = left+probability;
    		if(probability!=0) {
    		Interval newInterv = new Interval(index, left,right);
    		intervals.add(newInterv);
    		farRight=right;
    		}
    	}
 
    		
    	}else if (choice==4) {
    		ArrayList<Integer> checkedIndex = new ArrayList<Integer>();
    		for(int i = 0; i<corpus.size();i++) {
    			index =corpus.get(i);
    			left = farRight;
    			count=Collections.frequency(corpus, index);
    			probability=count/(double)corpus.size();
    			right=left+probability;
    			
    			if(probability!=0 && !checkedIndex.contains(index) ) {
    				Interval newInterv = new Interval(index,left,right);
    				intervals.add(newInterv);
    				farRight=right;
    				checkedIndex.add(index);
    			}
    		}
    		
    		
    	}
    
    	return intervals;
    }
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            count = Collections.frequency(corpus, w);
            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int index = -1; double left=-1; double right=-1;
            ArrayList<Interval> intervals = getIntervals(corpus,2);
            double r=(double)n1/(double)n2;
         
            for(Interval i:intervals) {
            	System.out.println(i);
            	if(i.inRange(r)) {
            		index = i.index;
            		left=i.left;
            		right=i.right;
            		break;
            	}
            }           
            System.out.println(index);
            System.out.println(String.format("%.7f", left));
            System.out.println(String.format("%.7f",right));
        }
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            for(int i = 0; i<corpus.size();i++) {
            	if(corpus.get(i)==h) {
            		words_after_h.add(corpus.get(i+1));
            	}
            }
            count = Collections.frequency(words_after_h, w);
            

            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            double r = (double)n1/(double)n2;
            int h = Integer.valueOf(args[3]);
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            for(int i =0;i<corpus.size();i++) {
            	if(corpus.get(i)==h) {
            		words_after_h.add(corpus.get(i+1));
            	}
            }
            Collections.sort(words_after_h);
            int index = -1; double left=-1; double right=-1;
            ArrayList<Interval> intervals = getIntervals(words_after_h,4);
            for(Interval i:intervals) {
            	if(r==0) {
            		index=i.index;
            		left=i.left;
            		right=i.right;
            		break;
            	}
            	if(i.inRange(r)) {
            		index = i.index;
            		left=i.left;
            		right=i.right;
            		break;
            	}
            }
            System.out.println(index);
            System.out.println(String.format("%.7f", left));
            System.out.println(String.format("%.7f", right));
           
            
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            for(int i = 0;i<corpus.size();i++) {
            	if(corpus.get(i)==h1&&corpus.get(i+1)==h2) {
            		words_after_h1h2.add(corpus.get(i+2));
            	}
            }
            count=Collections.frequency(words_after_h1h2, w);
            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            
            double r = (double)n1/(double)n2;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            for(int i = 0; i<corpus.size();i++) {
            	if(corpus.get(i)==h1 && corpus.get(i+1)==h2) {
            		words_after_h1h2.add(corpus.get(i+2));
            	}
            }
            Collections.sort(words_after_h1h2);
            int index=-1;double left=-1; double right=-1;
            ArrayList<Interval> intervals=getIntervals(words_after_h1h2,4);
            for(Interval i:intervals) {
            	if(r==0) {
            		index=i.index;
            		left=i.left;
            		right=i.right;
            		break;
            	}
            	if(i.inRange(r)) {
            		index = i.index;
            		left=i.left;
            		right=i.right;
            		break;
            	}
            }
            if(index==-1) {
            	System.out.println("undefined");
            }else {
            System.out.println(index);
            System.out.println(String.format("%.7f", left));
            System.out.println(String.format("%.7f", right));
            }
        }
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;
            ArrayList<Interval> intervals = new ArrayList<Interval>();

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                intervals = getIntervals(corpus,2);
                for(Interval i : intervals) {
                	if(i.inRange(r)) {
                		h1=i.index;
                		break;
                	}
                }
                
                
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

                // TODO Generate second word using r
                r = rng.nextDouble();
                ArrayList<Integer> words_after_h1 = new ArrayList<Integer>();
                for(int i= 0;i<corpus.size();i++) {
                	if(corpus.get(i)==h1) {
                		words_after_h1.add(corpus.get(i+1));
                	}
                }
                Collections.sort(words_after_h1);
                intervals = getIntervals(words_after_h1,4);
                for(Interval j : intervals) {
                	if(j.inRange(r)) {
                		h2=j.index;
                		break;
                	}
                }
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                ArrayList<Integer> words_after_h1 = new ArrayList<Integer>();
                for(int i= 0;i<corpus.size();i++) {
                	if(corpus.get(i)==h1) {
                		words_after_h1.add(corpus.get(i+1));
                	}
                }
                Collections.sort(words_after_h1);
                intervals = getIntervals(words_after_h1,4);
                for(Interval j : intervals) {
                	if(j.inRange(r)) {
                		h2=j.index;
                		break;
                	}
                }
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
               // System.out.println("r is " + r);
                int w  = 0;
                // TODO Generate new word using h1,h2
                ArrayList<Integer> words_after_h1h2=new ArrayList<Integer>();
                for(int i=0;i<corpus.size();i++) {
                	if(corpus.get(i)==h1&&corpus.get(i+1)==h2) {
                		words_after_h1h2.add(corpus.get(i+2));
                	}
                }
                Collections.sort(words_after_h1h2);
                intervals = getIntervals(words_after_h1h2,4);
                for(Interval k:intervals) {
                	if(k.inRange(r)) {
                		w=k.index;
                	}
                }
                
                System.out.println(w);
                h1 = h2;
                h2 = w;

            }
        }

        return;
    }
}