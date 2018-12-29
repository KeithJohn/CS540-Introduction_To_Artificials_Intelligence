import java.util.*;

public class successor {
    public static class JugState {
        int[] Capacity = new int[]{0,0,0};
        int[] Content = new int[]{0,0,0};
        public JugState(JugState copyFrom)
        {
            this.Capacity[0] = copyFrom.Capacity[0];
            this.Capacity[1] = copyFrom.Capacity[1];
            this.Capacity[2] = copyFrom.Capacity[2];
            this.Content[0] = copyFrom.Content[0];
            this.Content[1] = copyFrom.Content[1];
            this.Content[2] = copyFrom.Content[2];
        }
        public JugState()
        {
        }
        public JugState(int A,int B, int C)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
        }
        public JugState(int A,int B, int C, int a, int b, int c)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
            this.Content[0] = a;
            this.Content[1] = b;
            this.Content[2] = c;
        }
 
        public void printContent()
        {
            System.out.println(this.Content[0] + " " + this.Content[1] + " " + this.Content[2]);
        }
        public JugState pourJug(JugState jugs, int A, int B) {							//check if jugs are able to be poured if yes then pour and add to successors
        	if(jugs.Content[A]>0 && jugs.Content[B]<jugs.Capacity[B]) {					//if jug A isn't empty and jug B isn't full
        		if(jugs.Content[A]<jugs.Capacity[B]-jugs.Content[B]) {					//if jug A has less than the available space in jug B
        			jugs.Content[B]+=jugs.Content[A];						
        			jugs.Content[A]=0;
        			return jugs;
        	}else {
        		jugs.Content[A]-=(jugs.Capacity[B]-jugs.Content[B]);
        		jugs.Content[B] = jugs.Capacity[B];
        		return jugs;
        	}
        	
        
        }
        	return jugs;
        }
        
        
        public void resetJugs(JugState jugs, JugState init) {							//reset jug to initial state
        	for(int i = 0 ; i < 3; i++) {
        	jugs.Content[i] = init.Content[i];
        	}
        }
 
        public ArrayList<JugState> getNextStates(){
           
        	ArrayList<JugState> successors = new ArrayList<>();
            JugState jugs = new JugState(this);
    
            
            
            if(pourJug(jugs,0,1).Content[0]<this.Content[0]) {							//if jugstate is new then add to successor
            	successors.add(new JugState(jugs));
            }
            resetJugs(jugs, this);														//then reset jugstate
            
            if(pourJug(jugs,0,2).Content[0]<this.Content[0])
            	successors.add(new JugState(jugs));
            resetJugs(jugs, this);
            
            if(pourJug(jugs,1,2).Content[1]<this.Content[1])
            	successors.add(new JugState(jugs));
            resetJugs(jugs, this);
            
            if(pourJug(jugs,1,0).Content[1]<this.Content[1])
            	successors.add(new JugState(jugs));
            resetJugs(jugs, this);
            
            if(pourJug(jugs,2,0).Content[2]<this.Content[2])
            	successors.add(new JugState(jugs));
            resetJugs(jugs, this);
            
            if(pourJug(jugs,2,1).Content[2]<this.Content[2])
            	successors.add(new JugState(jugs));
            resetJugs(jugs, this);
            
            
           
       		
          
           //fill and empty each jug
            
           for(int counter= 0; counter<3; ++counter) {
            //fill jug
            if(jugs.Capacity[counter] > jugs.Content[counter]) {	//check if jug is full
            	jugs.Content[counter]=jugs.Capacity[counter];		//if not then fill
            	successors.add(new JugState(jugs));								//add state to successors
            	resetJugs(jugs, this);										//reset jug state
            }
           // empty jug
            if(jugs.Content[counter] > 0) {							//check if jug is empty
            	jugs.Content[counter]=0;							//if not then empty	
            	successors.add(new JugState(jugs));								//add state to successors
            	resetJugs(jugs, this);										//rest jug state
            }
            }
            
            // TODO add all successors to the list

            return successors;
            
        }
        
    }

    public static void main(String[] args) {
        if( args.length != 6 )
        {
            System.out.println("Usage: java successor [A] [B] [C] [a] [b] [c]");
            return;
        }

        // parse command line arguments
        JugState a = new JugState();
        a.Capacity[0] = Integer.parseInt(args[0]);
        a.Capacity[1] = Integer.parseInt(args[1]);
        a.Capacity[2] = Integer.parseInt(args[2]);
        a.Content[0] = Integer.parseInt(args[3]);
        a.Content[1] = Integer.parseInt(args[4]);
        a.Content[2] = Integer.parseInt(args[5]);

        // Implement this function
        ArrayList<JugState> asist = a.getNextStates();

        // Print out generated successors
        for(int i=0;i< asist.size(); i++)
        {
            asist.get(i).printContent();
        }

        return;
    }
}