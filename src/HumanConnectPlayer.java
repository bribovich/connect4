

public class HumanConnectPlayer extends Player{
	
	public HumanConnectPlayer(String name) {
		super(name);
		
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view){
		//Get a move for the user
		return view.getUserMove(state);
	}

}