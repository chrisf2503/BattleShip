package BattleShip;
import java.util.*;
//import java.io.*;

public class Game{
  private ArrayList<Piece> p1;
  private ArrayList<Piece> cpu;
  int gamesPlayed = 0;
  int wins = 0;

  public Game(ArrayList<Piece> player, ArrayList<Piece> cpu){
    p1 = player;
    this.cpu = cpu;
    //readFile();
  }
  public boolean hitOrMiss(int row, int col, char[][] mainBoard){
    if(mainBoard[row][col] == 'B'){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean ifValid(int row, int col, char[][] mainBoard){
    if(mainBoard[row][col] == 'R' || mainBoard[row][col] == 'W'){
      return false;
    }
    else{
      return true;
    }
  }
  public String removePiece(char[][] mainBoard, int player){
    int count = 0, position = 0;
    String name = "";
    boolean shipSank = false;
    if(player == 1){
      Piece currentPiece = p1.get(count);
      while(count != p1.size()){
        currentPiece = p1.get(count);
        currentPiece.hits(mainBoard);
        shipSank = currentPiece.shipDown();
        if(shipSank == true){
          name = currentPiece.name();
          position = count;
          break;
        }
        count++;
      }
      if(name.equals(currentPiece.name())){
        p1.remove(position);
        return name;
      }
      else{
        return name;
      }
    }
    else{
      Piece currentPiece = cpu.get(count);
      while(count != cpu.size()){
        currentPiece = cpu.get(count);
        currentPiece.hits(mainBoard);
        shipSank = currentPiece.shipDown();
        if(shipSank == true){
          name = currentPiece.name();
          position = count;
          break;
        }
        count++;
      }
      if(name.equals(currentPiece.name())){
        cpu.remove(position);
        return name;
      }
      else{
        return name;
      }
    }
  }
  public int winner(){
    if(p1.size() == 0){
      return 1;
    }
    else if(cpu.size() == 0){
      return 2;
    }
    else{
      return -1;
    }
  }
  public int cpuListSize(){
    return cpu.size();
  }
  public int playerListSize(){
    return p1.size();
  }
  /*public void updateFile(boolean ifPlayerWon){
    String num;
    try{
      FileWriter his = new FileWriter("History.txt");
      his.write(gamesPlayed+1 + "\n");
      if(ifPlayerWon){
        num = (wins+1) + "";
        his.write(num);
      }
      else{
        num = wins+"";
        his.write(num);
      }
      his.close();
    }
    catch(IOException e){
      System.out.println("Does not work");
    }
  }
  public void readFile(){
    try{
      File his = new File("History.txt");
      Scanner read = new Scanner(his);
      int count = 0;
      while(read.hasNextLine()){
        String num = read.nextLine();
        count++;
        if(count == 1){
          gamesPlayed = Integer.parseInt(num);
        }
        else if(count == 2){
          wins = Integer.parseInt(num);
        }
      }
      read.close();
    }
    catch(FileNotFoundException e){
      System.out.println("File is not found");
      e.printStackTrace();
    }
  }//*/
  //Add a return method for wins and match played
}
