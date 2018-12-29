import java.util.*;

class State {
	int[] board;
	State parentPt;
	int depth;

	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}
	/** Function that adds all possible moves for torus 8 puzzle and adds states to an array
	 * 
	 * @param initBoard = initial board state which is manipulated to find successors
	 * @param successors = array to be filled with all possible tile shifts 
	 * @param tile0 = tile which is empty
	 * 
	 * Tile A-D = tiles which can be shifted into empty tile
	 * @param tileA
	 * @param tileB
	 * @param tileC
	 * @param tileD
	 */
	
	public void addSuccessors(int[] initBoard, State[] successors, int tile0, int tileA, int tileB,int  tileC, int tileD) {
		
		swapTiles(initBoard,tile0, tileA);
		successors[0] = new State(initBoard);
		successors[0].parentPt =this;
		successors[0].depth=this.depth+1;
		swapTiles(initBoard, tile0, tileA);
		
		swapTiles(initBoard,tile0, tileB);
		successors[1] = new State(initBoard);
		successors[1].parentPt =this;
		successors[1].depth=this.depth+1;
		swapTiles(initBoard, tile0, tileB);
		
		swapTiles(initBoard,tile0, tileC);
		successors[2] = new State(initBoard);
		successors[2].parentPt =this;
		successors[2].depth=this.depth+1;
		swapTiles(initBoard, tile0, tileC);
		
		swapTiles(initBoard,tile0, tileD);
		successors[3] = new State(initBoard);
		successors[3].parentPt =this;
		successors[3].depth=this.depth+1;
		swapTiles(initBoard, tile0, tileD);
		
		
	
		sortSuccessors(successors);
		
	}
	
	public void sortSuccessors(State[] successors) {
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3;j++) {
				if(successors[j+1].intBoard()<successors[j].intBoard()) {
					State tmp = successors[j];
					successors[j] = successors[j+1];
					successors[j+1]= tmp;
				}
			}
		}
		
	}
	/**
	 * Function that swaps the number of two given tiles
	 * 
	 * @param board = array of integers that represent 8 puzzle board
	 * @param A = tile to shift
	 * @param B = tile to shift
	 */
	public void swapTiles(int[] board, int A, int B) {
		int tmp;
		tmp = board[A];
		board[A]= board[B];
		board[B] = tmp;
	}

	public State[] getSuccessors() {
		// TO DO: get all four successors and return them in sorted order
		
		State[] successors = new State[4]; 
		int[] initState = new int[9];								// declare array to store initial board state
		
		for(int i = 0; i < 9; i++) {						//fill initial board state with current board state
			initState[i] = this.board[i];
		}
		
		if(initState[0]==0) {
			addSuccessors(initState, successors, 0, 1, 3, 2, 6);
		}else if (initState[1] == 0) {
			
			addSuccessors(initState, successors, 1, 0, 2, 4, 7);
			
		}else if (initState[2] == 0) {
			
			addSuccessors(initState, successors, 2, 1, 5, 8, 0);
			
		}else if (initState[3] == 0) {
			
			addSuccessors(initState, successors, 3, 0, 4, 6, 5);
			
		}else if (initState[4] == 0) {
			
			addSuccessors(initState, successors, 4, 1, 3, 5, 7);
			
		}else if (initState[5] == 0) {
			
			addSuccessors(initState, successors, 5, 2, 4, 8, 3);
			
		}else if (initState[6] == 0) {
			
			addSuccessors(initState, successors, 6, 3, 7, 0, 8);
			
		}else if (initState[7] == 0) {
			
			addSuccessors(initState, successors, 7, 4, 6, 8, 1);
			
		}else if (initState[8] == 0) {
			
			addSuccessors(initState, successors, 8, 5, 7, 2, 6);
			
		}
	
		return successors;
	}
	
	

	public void printState(int option) {
		
		if(option==1||option==2) {
			System.out.println(this.getBoard());
		}else if(option == 3){
			if(this.parentPt == null) {
				System.out.println(this.getBoard() + " parent 0 0 0 0 0 0 0 0 0"); // if initial state 
			}else {
			System.out.println(this.getBoard() + " parent " + this.parentPt.getBoard());
			} 
			}else if(option==5) {
				State tmp = this;
				Stack<State> printStack = new Stack<>();							//fill stack to print parent order correctly
				while(tmp.parentPt!=null) {
					printStack.add(tmp);
					tmp = tmp.parentPt;
				}
				printStack.add(tmp);
				while(!printStack.isEmpty()) {
					System.out.println(printStack.pop().getBoard());
				}
			}
		
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(Object o) {
		State src = (State) o;
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
	
	
	public int intBoard() {																//convert int[] of board into single int
		String[] boardArr = this.getBoard().split(" ");
		int boardInt=0;
		for(int i = 0; i< boardArr.length;i++) {
			boardInt= boardInt*10 + Integer.parseInt(boardArr[i]);
		}
		return boardInt;
}
	
}

public class Torus {

	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			List<State> prefixToPrint = new ArrayList<>();
			int goalChecked = 0;
			int maxStackSize = Integer.MIN_VALUE;
			
			if(option !=5) {
				stack.push(init);
				prefix.add(init);
				prefixToPrint.add(init);
				
				while(!stack.isEmpty()) {
					State stateToCheck = stack.pop();
					stateToCheck.printState(option);
					if(stateToCheck.isGoalState()) {
						break;
					}
					if(stateToCheck.depth<cutoff) {
						State[] successors = stateToCheck.getSuccessors();
						for(int i = 0; i < 4;i++) {
							if(!containsBoard(prefix,successors[i])) {
								stack.push(successors[i]);
							}
						}
						
						}
					
					int listCounter=0;
					
					while(listCounter<prefix.size()) {
						if(prefix.get(listCounter) == stateToCheck.parentPt) {
							if(listCounter<prefix.size()-1) {
							prefix.subList(prefix.indexOf(stateToCheck.parentPt)+1, prefix.size()).clear();
							prefix.add(stateToCheck);
							listCounter = prefix.size();
							}else {
							prefix.add(stateToCheck);
							if(prefixToPrint.size()<stateToCheck.depth+1) {
							prefixToPrint.add(stateToCheck);
							}
							listCounter=prefix.size();
							
						}
						}
					
						listCounter++;
					}
					
				}
				if(option == 4) {
					for(State tmp : prefixToPrint) {
						System.out.println(tmp.getBoard());
					}
			}
			}else {
				
				
				int maxStack=0;
				int cutoffDepth = 0;
				boolean goalfound = false;
				while(goalfound==false) {
					int goalCheckNum= 0;
					stack.push(init);
					prefix.add(init);
				while(!stack.isEmpty()) {
					State stateToCheck = stack.pop();
					
					goalCheckNum++;										//before checking goal increase goal number check
					if(stateToCheck.isGoalState()) {
						stateToCheck.printState(5);
						System.out.println("Goal-check " + goalCheckNum);
						System.out.println("Max-stack-size " + maxStack );
						goalfound=true;
						break;
					}
					if(stateToCheck.depth<cutoffDepth) {
						State[] successors = stateToCheck.getSuccessors();
						for(int i = 0; i < 4;i++) {
							if(!containsBoard(prefix,successors[i])) {
								stack.push(successors[i]);
							}
						}
					}
						if(stack.size()>maxStack) {
							maxStack = stack.size()-1;
						}
					
				if(prefix.contains(stateToCheck.parentPt)) {										//if parent found in prefix list	
					prefix.subList(prefix.indexOf(stateToCheck.parentPt)+1, prefix.size()).clear();	//delete every element after parent
					prefix.add(stateToCheck);														//add to prefix list
				}
			}
				
				cutoffDepth++;																		//increase cutoff depth after each iteration
		}
		}
		}
		
	}

	public static boolean containsBoard(List<State> states, State stateToCheck ) {				//check if the board state is found within the prefix list
		for(State tmp:states) {
			if(tmp.intBoard()==stateToCheck.intBoard()) {
				return true;
			}
		}
		return false;
	}
}
