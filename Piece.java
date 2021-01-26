package BattleShip;
public class Piece{
    private String name;
    private String position;
    private int row;
    private int col;
    private int numSpaces;
    private int hits;
  
    public Piece(String name, String position, int col, int row, int numSpaces){
      this.name = name;
      this.position = position;
      this.row = row;
      this.col = col;
      this.numSpaces = numSpaces;
      hits = 0;
    }
    public String name(){
      return name;
    }
    public void hits(char[][] board){
      hits = 0;
      int count = 0;
      while(count != numSpaces){
        if(position.equals("horizontal")){
          if(board[row][col+count] == 'R'){
            hits++;
          }
        }
        else{
          if(board[row+count][col] == 'R'){
            hits++;
          }
        }
        count++;
      }
    }
    public boolean shipDown(){
      if(hits == numSpaces){
        return true;
      }
      else{
        return false;
      }
    }
  }
