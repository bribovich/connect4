


public class ConnectGame implements Connect4State{
	
	private char[][] board;
	private int playerToMoveNum;
	private Player[] players;
	private Connect4View view;
	private char currentChecker;
	private final int needToWin = 4;
	

  /**
   * Constructs a game in the initial state
   * @param playerNum the player whose move it is
   * @param thePlayers the player objects
   * @param aView the view in the model-view-controller model
   */
	public ConnectGame(int playerNum, Player [] thePlayers, Connect4View aView){
		char[][] initBoard = new char[ROWS][COLS];
		for(int i = 0; i < COLS; i++){
			for(int j = 0; j < ROWS; j++){
				initBoard[j][i] = EMPTY;
			}
		}
		
		initialize(playerNum, thePlayers, initBoard);
		view = aView;
		
		
	}
	
  /**
   * Constructs a game given a board
   * @param playerNum the player whose move it is
   * @param thePlayers the player objects
   * @param initBoard the board to copy into this state
   */
	public ConnectGame(int playerNum, Player [] thePlayers, char[][] initBoard){
		initialize(playerNum, thePlayers, initBoard);
	}
	
	
  /**
   * Initialization to call from both constructors
   * @param playerNum the number of the player to move
   * @param thePlayers the array of player objects
   * @param initBoard the initial board to use
   */
	public void initialize(int playerNum, Player [] thePlayers, char[][] initBoard){
		board = new char[ROWS][COLS];
		for(int i=0; i<COLS; i++){
			for(int j=0; j<ROWS; j++){
				board[j][i] = initBoard[j][i];
			}
		}
		
		playerToMoveNum = playerNum;
		players = thePlayers;
		
	}
	
  public char[][]getBoard() {
  	return board;
  }
  
  public Player [] getPlayers() {
  	return players;
  }
  
  public int getPlayerNum () {
  	return playerToMoveNum;
  }
  
  public Player getPlayerToMove() {
  	return players[playerToMoveNum];
  }
  
  public boolean isValidMove(int col){
  	for(int k =0;k<ROWS;k++){
  		if(board[k][col] == EMPTY){
  			return true;
  		}	
  	}
  	return false;
  }
	
  /**
   * Make a move, placing a checker into a column of choice
   * @param col the column to move to
   */
  public void makeMove(int col){
  	if(isValidMove(col)){
  		currentChecker = CHECKERS[playerToMoveNum];
  		for(int k = 0; k<ROWS; k++){
  			if(board[k][col] == EMPTY){
  				board[k][col] = currentChecker;
  				playerToMoveNum = 1 - playerToMoveNum;    // Switch player 
  				return;
  			}
  		}
  	}
  	view.display(this);
  	
  }
  
  
  public boolean isFull(){
  	for(int i=0; i<ROWS; i++){
			for(int j=0; j<COLS; j++){
				if(board[i][j] ==EMPTY){
					return false;
				}
			}
  	}
  	return true;
  }
  /**
   * Decides if the game is over
   * @return true iff the game is over
   */
  public boolean gameIsOver(){
  	return(isFull() || haveHorWinner() || haveVertWinner() || haveRightSlantWinner()|| haveLeftSlantWinner());
  	
  	}
  
  public boolean haveHorWinner(){
  	char checker = EMPTY;
  	for(int j = 0; j<ROWS; j++){
    	int count = 1;
  		for(int i = 0; i<COLS; i++){
  			char slot = board[j][i];
  			if(slot == checker && slot!= EMPTY){
  				count = count+1;
  				if(count == needToWin){
  					return true;
  				}
  			
  			}
  			else{
  				count = 1;
  				checker = slot;
  				
  			}
  		}
  	}
  	return false;
  	
  	
  }
  
  public boolean haveVertWinner(){
  	char checker = EMPTY;
  	for(int i = 0; i<COLS; i++){
    	int count = 1;
  		for(int j = 0; j<ROWS; j++){
  			char slot = board[j][i];
  			if(slot == checker && slot!= EMPTY){
  				count=count+1;
  				if(count == needToWin){
  					return true;
  				}
  			
  			}
  			else{
  				count = 1;
  				checker = slot;
  				
  			}
  		}
  	}
  	return false;
  	
  }
  
  public boolean haveRightSlantWinner(){
  	for(int i=COLS-needToWin; i>=0; i--){
  		if(rightSlantHelper(0,i) == true){
  			return true;
  		}
  	}
  	for(int j = ROWS - needToWin; j>0; j--){
  		if(rightSlantHelper(j,0) == true){
  			return true;
  		}
  	}
  	return false;
  	
  }
  
  public boolean rightSlantHelper(int x, int y){
  	char checker = EMPTY;
  	int count =1;
  	int xCoord = x;
  	int yCoord = y;
		while((xCoord<ROWS) && (yCoord <COLS) ){
			char slot = board[xCoord][yCoord];
			if(slot == checker && slot!= EMPTY){
				count=count+1;
				if(count == needToWin){
					return true;
				}
			
			}
			else{
				count = 1;
				checker = slot; 
			}
			xCoord=xCoord+1;
			yCoord=yCoord+1;
			
		}
		return false;
		
  }
  
  public boolean haveLeftSlantWinner(){
  	for(int i=needToWin-1; i< COLS; i++){
  		if(leftSlantHelper(0,i) == true){
  			return true;
  		}
  	}
  	for(int j = ROWS - needToWin; j>0; j--){
  		if(leftSlantHelper(j,COLS-1) == true){
  			return true;
  		}
  	}
  	return false;
  	
  }
  
  public boolean leftSlantHelper(int x, int y){
  	char checker = EMPTY;
  	int count =1;
  	int xCoord = x;
  	int yCoord = y;
		while(xCoord <ROWS && yCoord >=0 ){
			char slot = board[xCoord][yCoord];
			if(slot == checker && slot!= EMPTY){
				count=count+1;
				if(count == needToWin){
					return true;
				}
			
			}
			else{
				count = 1;
				checker = slot; 
			}
			xCoord = xCoord +1;
			yCoord= yCoord -1;
			
		}
		return false;
		
  }
  
  public int unblocked(){
  	return(unblockedHor()+unblockedVert()+unblockedRightSlant()+unblockedLeftSlant());
  }
	
  public int unblockedHor(){
  	currentChecker = CHECKERS[playerToMoveNum];
  	int unblocked = 0;
  	int same=0;
  	int weighted=0;
  	for(int r = 0; r<ROWS;r++){
  		for(int i=0;i<COLS - needToWin; i++){
  			same=0;
  			weighted=0;
  			unblocked=0;
    		for(int j=0; j<=needToWin-1; j++){
    			char slot = board[r][i+j];
    			if(slot!=currentChecker && slot!=EMPTY){
    				break;
    			}
    			else if(slot==EMPTY){
    				unblocked = unblocked +1;
    			}
    			else{
    				unblocked=unblocked+1;
    				same=same+1;
    			}
    		}
    		if(unblocked==needToWin){
    			weighted+=(same+1)^2;
    		}
  		}
  	}
  	return weighted;
  }
    			
  public int unblockedVert(){
  	currentChecker = CHECKERS[playerToMoveNum];
  	int unblocked = 0;
  	int same=0;
  	int weighted=0;
  	for(int c = 0; c<COLS;c++){
  		for(int i=0;i<ROWS - needToWin; i++){
  			same=0;
  			weighted=0;
  			unblocked=0;
    		for(int j=0; j<=needToWin-1; j++){
    			char slot = board[i+j][c];
    			if(slot!=currentChecker&& slot!=EMPTY){
    				break;
    			}
    			else if(slot==EMPTY){
    				unblocked = unblocked +1;
    			}
    			else{
    				unblocked = unblocked+1;
    				same = same+1;
    			}
    		}
    		if(unblocked==needToWin){
    			weighted+=(same+1)^2;
    		}
  		}
  	}
  	return weighted;
  }
  public int unblockedRightSlant(){
  	int unblocked =0;
  	for(int i=COLS-needToWin; i>=0; i--){
  		unblocked+=rightUnblockedHelper(0,i);
  	}
  	for(int j = ROWS - needToWin; j>0; j--){
  		unblocked+=rightUnblockedHelper(j,0);
  	}
  	return unblocked;
  }

  public int rightUnblockedHelper(int x, int y){
  	int weighted = 0;
  	int unblocked =0;
  	int same =0;
  	int xCoord = x;
  	int yCoord = y;
  	while(xCoord<= ROWS-needToWin && yCoord <=COLS -needToWin){
  		unblocked =0;
  		weighted =0;
  		same =0;
  		for(int j=0; j<=needToWin-1; j++){
  			char slot = board[xCoord+j][yCoord+j];
  			if(slot!=currentChecker&& slot!=EMPTY){
  				unblocked=0;
  				break;
  			}
  			
  			else if(slot==EMPTY){
  				unblocked = unblocked+1;
  			}
  			//slot is current checker
  			else{
  				unblocked = unblocked +1;
  				same=same+1;
  			}
  		}
  		if(unblocked==needToWin){
  			weighted+=(same+1)^2;
  		}
  		xCoord = xCoord+1;
  		yCoord = yCoord+1;
  	}
  		
  	return weighted;
  	
  }
  public int unblockedLeftSlant(){
  	int unblocked =0;
  	for(int i=needToWin; i<COLS; i++){
  		unblocked+=rightUnblockedHelper(0,i);
  	}
  	for(int j = ROWS - needToWin; j>0; j--){
  		unblocked+=rightUnblockedHelper(j, COLS-1);
  	}
  	return unblocked;
  }

  public int leftUnblockedHelper(int x, int y){
  	int weighted = 0;
  	int unblocked =0;
  	int same =0;
  	int xCoord = x;
  	int yCoord = y;
  	while(xCoord > 0 && yCoord <COLS-needToWin ){
  		weighted = 0;
    	unblocked =0;
    	same =0;
  		for(int j=0; j<=needToWin-1; j++){
  			char slot = board[xCoord-j][yCoord+j];
  			if(slot!=currentChecker&& slot!=EMPTY){
  				unblocked=0;
  				break;
  			}
  			
  			else if(slot==EMPTY){
  				unblocked = unblocked+1;
  			}
  			//slot is current checker
  			else{
  				unblocked = unblocked +1;
  				same=same+1;
  			}
  		}
  		if(unblocked==needToWin){
  			weighted+=(same+1)^2;
  		}
  		xCoord = xCoord-1;
  		yCoord = yCoord+1;
  	}
  		
  	return weighted;
  	
  }
  	
  		
  
  
}