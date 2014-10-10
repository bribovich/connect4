


public class ComputerConnectPlayerAB extends Player{
	
	private int depth;    // Look-ahead depth
  
  /**
   * Constructs a computer player that uses alpha-beta pruning
   * @param name
   * @param maxDepth
   */
	
	public ComputerConnectPlayerAB(String name, int maxDepth){
		super(name);
		depth = maxDepth;
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view){
	// Returns the computer play's choice using alpha-beta  search
		int move = pickMove(state, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE).move;
		view.reportMove(move,state.getPlayerToMove().getName());
		
		return move;
		
		
	}
	/**
	 * Uses game tree search with alpha-beta pruning to pick player's move
	 * low and high define the current range for the best move.
	 * The current player has another move choice which will get him at least low,
	 * and his opponent has another choice that will hold his losses to high.
	 * 
	 * @param state current state of the game
	 * @param depth number of moves to look ahead in game tree search
	 * @param low a value that the player can achieve by some other move
	 * @param high a value that the opponent can force by a different line of play
	 * @return the move chosen
	 */
	private ConnectMove pickMove (Connect4State state, int depth, int low, int high) {
		ConnectMove currentMove;
		ConnectMove bestMove;
		
		int playerToMove = state.getPlayerNum();
		char[][] board = state.getBoard();
		
		// A dummy move that will be replaced when a real move is evaluated, 
		// so the pit number is irrelevant.
		bestMove = new ConnectMove(Integer.MIN_VALUE, 0);
		
	  // Run through possible moves 
		for(int i=0; i<Connect4State.COLS; i++){
			if(state.isValidMove(i)){
			// Make a scratch copy of state
				ConnectGame copy = new ConnectGame(playerToMove, state.getPlayers(), board);   
				copy.makeMove(i);             // Make the move

				// Find the value of this board by evaluating if game over or looking ahead if not

				if (copy.gameIsOver())
					// Evaluate the true score, and multiply it by a huge constant so that the 
					// program will choose a sure win over a potentially larger speculative win 
					// and a possible loss over a sure loss.  
					currentMove = new ConnectMove(2000000, i);
				
				else if (playerToMove == copy.getPlayerNum()){    // Did current player change?
				
					currentMove = pickMove(copy, depth, low, high);  // No, so no depth change
					currentMove.move = i;              // Remember move made
				}
			
				else if (depth > 0){               // Player changed, so reduce search depth 
				
					currentMove = pickMove(copy, depth - 1, -high, -low);
					currentMove.value = -currentMove.value;   // Good for opponent is bad for me 
					currentMove.move = i;                   // Remember move made
				}
				else    // Depth exhausted, so estimate who is winning by comparing kalahs 
					currentMove = new ConnectMove(copy.unblocked(), i);

				if (currentMove.value > bestMove.value)  {  // Found a new best move?
					bestMove = currentMove;
					low = Math.max(low, bestMove.value);   // Update the low value, also
				}
			}
			}
		
		return bestMove;
		
		
		
	}


	
}