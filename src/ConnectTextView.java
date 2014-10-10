

import java.util.Scanner;
import java.util.InputMismatchException;


public class ConnectTextView implements Connect4View{
	
	private Scanner input;
	
	public ConnectTextView(){
		input = new Scanner(System.in);
	}
	
	
	@Override
	public void display(Connect4State state){
	  // Postcondition:  Displays the current board
		char [][] board = state.getBoard();
		for(int i=Connect4State.ROWS-1; i>=0; i--){
			System.out.println("\n");
			for(int j=0; j<Connect4State.COLS; j++)
				System.out.print(board[i][j]);
		}
	}
	
	@Override
	public int getUserMove(Connect4State state){
		int col; //The column under consideration
		//char[][] board = state.getBoard(); Needed?
		System.out.println();
		
		col = getIntAnswer( "Column to place move, " + state.getPlayerToMove().getName() + "? ");
		while(col<0 || col>Connect4State.COLS -1){
			System.out.println("Illegal move.  Try again.");
      col = getIntAnswer("Column to place move? ");
		}
		return col;
	}
	
	@Override
	public void reportMove (int chosenMove, String name){
		System.out.println("\n" + name + " chooses column " + chosenMove);
	}
	
	
	
	@Override
  public int getIntAnswer(String question) {
  	int answer = 0;
  	boolean valid = false;
  	
  	// Ask for a number
    System.out.print(question + " ");   
  	while(!valid) {
  		try {
        answer = input.nextInt();;
        valid = true;   // If got to here we have a valid integer
  		}
  		catch(InputMismatchException ex) {
  			reportToUser("That was not a valid integer");
  			valid = false;
  			input.nextLine();  // Throw away the rest of the line
  	    System.out.print(question + " ");   
  		}
  	}
  	input.nextLine();  // Throw away the rest of the line
  	
    return answer;
  }
	
	
  @Override
  public void reportToUser(String message) {
  // Reports something to the user
  	System.out.println(message);
  }

  @Override
  public String getAnswer(String message) {
  	System.out.print(message);
  	return input.nextLine();
  }
}