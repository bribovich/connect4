/**
 * ConnectMove.java
 * 
 * @author Scot Drysdale on 5/28/00

 * A simple class that allows a move and its value to be returned from a
 *   call to a method.
 * A case where public instance variables make sense.
 */

public class ConnectMove
{
  public int value;       // Game value of this move
  public int move;        // Number of pit to be emptied
  
  public ConnectMove(int value, int move)
  {
    this.value = value;
    this.move = move;
  }
}