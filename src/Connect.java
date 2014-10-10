


public class Connect{
	
	public static void main(String[] args){
		
		
	// Hold the view methods.  Also whether the update should be 
    // incremental (display changes as they occur) or all at once.
    //Connect4View view = new ConnectTextView();
		Connect4View view = new Connect4ViewGraphical();
    boolean updateIncrementally = true; 
    
    Player[] players = new Player[2];
    
 // Initialize the game 

    players[0] = makePlayer(view, "first");
    players[1] = makePlayer(view, "second");
    
 // Hold current game state
    ConnectGame state = new ConnectGame(0, players, view);  

    view.display(state);

    while (!state.gameIsOver()) {
    	int move = state.getPlayerToMove().getMove(state, view);
    	if(updateIncrementally)
    	  state.makeMove(move);
    	else
    		state.makeMove(move);
    	view.display(state);
    }
    //System.out.println(state.isFull());
    System.out.println(state.haveHorWinner());
    System.out.println(state.haveVertWinner());
    System.out.println(state.haveRightSlantWinner());
    System.out.println(state.haveLeftSlantWinner());
    if(state.isFull() && !(state.haveHorWinner() || state.haveVertWinner() || state.haveRightSlantWinner()|| state.haveLeftSlantWinner())){
    	view.reportToUser("The game is a draw");
    }
    else{
    	view.reportToUser("\n"+players[1-state.getPlayerNum()].getName() + " wins");
    }
     
	}
	
	/** 
   * Constructs a Connect player.  If the name contains "Computer" it
   * constructs a computer player; else a human player
   * @param view the view to use to communicate to the world
   * @param playerMsg the player to ask for 
   * @return
   */
  public static Player makePlayer(Connect4View view, String playerMsg) {
    String playerName = view.getAnswer("Enter the name of the " + playerMsg + 
    		" player." + "\n(Include 'Computer' in the name of a computer player) ");
    if(playerName.contains("Computer")) {
    	int depth = view.getIntAnswer("How far should I look ahead? ");
      return new ComputerConnectPlayerAB(playerName, depth);
    }
    else
      return new HumanConnectPlayer(playerName);
  }
}