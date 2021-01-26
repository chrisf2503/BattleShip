package BattleShip;
import java.util.*;
public class Cpu{
  public static final String CYAN = "\u001B[36m";
  public static final String RED = "\u001B[31m";
  public static final String YELLOW = "\u001B[33m";
  public static final String WHITE = "\u001B[37m";
  public static final String BLACK = "\u001B[30m";
  public static final String BLUE = "\033[0;34m";
  public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
  public static final String PURPLE = "\033[0;35m";  // PURPLE
  private char[][] mainBoard;
  private char[][] keepTrackBoard;
  private Rules r;
  private ArrayList<Piece> ships;

  public Cpu(Rules r){
    this.r = r;
    ships = new ArrayList<Piece>();
    mainBoard = new char[10][10];
    keepTrackBoard = new char[10][10];
    for(int i = 0; i < mainBoard.length; i++){
      for(int j = 0; j < mainBoard[i].length; j++){
        mainBoard[i][j] = '0';
        keepTrackBoard[i][j] = '0';
      } 
    }
  }
  public void boardGame2(){
    String letters = "ABCDEFGHIJ";
    System.out.println("Plyer Main board");
    System.out.println("   1 2 3 4 5 6 7 8 9 10");
    System.out.println("  ---------------------");
    for(int i = 0; i < mainBoard.length; i++){
      for(int j = 0; j < mainBoard[i].length; j++){
        if(j == 0){
          System.out.print(letters.charAt(i) + "| ");
          if(mainBoard[i][j] == 'B'){
            System.out.print(BLUE+mainBoard[i][j]+WHITE +" ");
          }
          else if(mainBoard[i][j] == 'R'){
            System.out.print(RED+mainBoard[i][j]+WHITE +" ");
          }
          else if(mainBoard[i][j] == 'W' || mainBoard[i][j] == '0'){
            System.out.print(mainBoard[i][j] + " ");
          }
        }
        else if(j == 9 ){
         if(mainBoard[i][j] == 'B'){
            System.out.print(BLUE+mainBoard[i][j]+WHITE +" |");
          }
          else if(mainBoard[i][j] == 'R'){
            System.out.print(RED+mainBoard[i][j]+WHITE +" |");
          }
          else if(mainBoard[i][j] == 'W' || mainBoard[i][j] == '0'){
            System.out.print(mainBoard[i][j] + " |");
          }
        }
        else {
          if(mainBoard[i][j] == 'B'){
            System.out.print(BLUE+mainBoard[i][j]+WHITE +" ");
          }
          else if(mainBoard[i][j] == 'R'){
            System.out.print(RED+mainBoard[i][j]+WHITE +" ");
          }
          else if(mainBoard[i][j] == 'W' || mainBoard[i][j] == '0'){
            System.out.print(mainBoard[i][j] + " ");
          }
        }
      }
      System.out.println();
    }
  }
  public void boardGame1(){
    String letters = "ABCDEFGHIJ";
    System.out.println("Keep Track board");
    System.out.println("   1 2 3 4 5 6 7 8 9 10");
    System.out.println("  ---------------------");
    for(int i = 0; i < keepTrackBoard.length; i++){
      for(int j = 0; j < keepTrackBoard[i].length; j++){
        if(j == 0){
          System.out.print(letters.charAt(i) + "| ");
          if(keepTrackBoard[i][j] == 'B'){
            System.out.print(BLUE+keepTrackBoard[i][j]+WHITE +" ");
          }
          else if(keepTrackBoard[i][j] == 'R'){
            System.out.print(RED+keepTrackBoard[i][j]+WHITE +" ");
          }
          else if(keepTrackBoard[i][j] == 'W' || keepTrackBoard[i][j] == '0'){
            System.out.print(keepTrackBoard[i][j] + " ");
          }
        }
        else if(j == 9 ){
         if(keepTrackBoard[i][j] == 'B'){
            System.out.print(BLUE+keepTrackBoard[i][j]+WHITE +" |");
          }
          else if(keepTrackBoard[i][j] == 'R'){
            System.out.print(RED+keepTrackBoard[i][j]+WHITE +" |");
          }
          else if(keepTrackBoard[i][j] == 'W' || keepTrackBoard[i][j] == '0'){
            System.out.print(keepTrackBoard[i][j] + " |");
          }
        }
        else {
          if(keepTrackBoard[i][j] == 'B'){
            System.out.print(BLUE+keepTrackBoard[i][j]+WHITE +" ");
          }
          else if(keepTrackBoard[i][j] == 'R'){
            System.out.print(RED+keepTrackBoard[i][j]+WHITE +" ");
          }
          else if(keepTrackBoard[i][j] == 'W' || keepTrackBoard[i][j] == '0'){
            System.out.print(keepTrackBoard[i][j] + " ");
          }
        }
      }
      System.out.println();
    }
  }
  public void placePiece(String position, int col, int row, int numSpace){//works
    int keepTrack = 0; 
    if(position.equals("horizontal")){
      while(keepTrack != numSpace){
        mainBoard[row][col+keepTrack] = 'B';
        keepTrack++;
      }
    }
    else if(position.equals("vertical")){
      while(keepTrack != numSpace){
        mainBoard[row+keepTrack][col] = 'B';
        keepTrack++;
      }
    }
  }  
  public void input(){
    int count = 0;
    int row = 0;
    int col = 0;
    int outBoard = 0;
    int overLapShips = 0;
    while (count != 5){
      int pos = (int)(Math.random()* 2 )+ 1;
      if(pos == 1){
        col = (int)(Math.random()* 10 );
        row = (int)(Math.random()* 10 );
        outBoard = r.outOfBound("horizontal", col, row, count, mainBoard);
        while(outBoard == -1){
          col = (int)(Math.random()* 10 );
          row = (int)(Math.random()* 10 );
          outBoard = r.outOfBound("horizontal", col, row, count, mainBoard);
        }
        overLapShips = r.overLap("horizontal", col, row, count, mainBoard);
        while(overLapShips == -1){
          col = (int)(Math.random()* 10 );
          row = (int)(Math.random()* 10 );
          outBoard = r.outOfBound("horizontal", col, row, count, mainBoard);
          while(outBoard == -1){
            col = (int)(Math.random()* 10 );
            row = (int)(Math.random()* 10 );
            outBoard = r.outOfBound("horizontal", col, row, count, mainBoard);
          }
          overLapShips = r.overLap("horizontal", col, row, count, mainBoard);
        }
        placePiece("horizontal", col, row, r.numSpace(count));
        ships.add(new Piece(r.name(count), "horizontal" , col, row, r.numSpace(count)));
      }
      else{
        col = (int)(Math.random()* 10 );
        row = (int)(Math.random()* 10 );
        outBoard = r.outOfBound("vertical", col, row, count, mainBoard);
        while(outBoard == -1){
          col = (int)(Math.random()* 10 );
          row = (int)(Math.random()* 10 );
          outBoard = r.outOfBound("vertical", col, row, count, mainBoard);
        }
        overLapShips = r.overLap("vertical", col, row, count, mainBoard);
        while(overLapShips == -1){
          col = (int)(Math.random()* 10 );
          row = (int)(Math.random()* 10 );
          outBoard = r.outOfBound("vertical", col, row, count, mainBoard);
          while(outBoard == -1){
            col = (int)(Math.random()* 10 );
            row = (int)(Math.random()* 10 );
            outBoard = r.outOfBound("vertical", col, row, count, mainBoard);
          }
          overLapShips = r.overLap("vertical", col, row, count, mainBoard);
        }
        placePiece("vertical", col, row, r.numSpace(count));
        ships.add(new Piece(r.name(count), "vertical" , col, row, r.numSpace(count)));
      }
      count++;
    }
  }
  public ArrayList<Piece> ship(){
    return ships;
  }
  public char[][] mainBoard(){
    return mainBoard;
  }
  public void updateMainBoard(int row, int col, boolean hitOrMiss){
    if(hitOrMiss == true){
      mainBoard[row][col] = 'R';
    }
    else{
      mainBoard[row][col] = 'W';
    }
  }
  public void updateKeepTrackBoard(int row, int col, boolean hitOrMiss){
    if(hitOrMiss == true){
      keepTrackBoard[row][col] = 'R';
    }
    else{
      keepTrackBoard[row][col] = 'W';
    }
  }
  public int randomInput(){
    int ran = (int)(Math.random()*10);
    return ran;
  }
  public int horizontalHits(int row, int col){
    int add = 1, outOfBoard = 0, miss = 0;
    while(miss != 1 && outOfBoard != 1){
      if(col + add > 9){
        outOfBoard++;
        break;
      }
      else if(keepTrackBoard[row][col+add] == 'W'){
        miss++;
        break;
      }
      else if(keepTrackBoard[row][col+add] == '0'){
        return col+add;
      }
      add++;
    }
    add = -1;
    while((miss!=2) && ((outOfBoard != 1 && miss == 1) || (outOfBoard == 1 && miss != 1))){
      if(col + add < 0){
        outOfBoard++;
        break;
      }
      else if(keepTrackBoard[row][col+add] == 'W'){
        miss++;
        break;
      }
      else if(keepTrackBoard[row][col+add] == '0'){
        return col+add;
      }
      add--;
    }
    if(outOfBoard == 1 && miss == 1){
      return -1;
    }
    else if(miss == 2){
      return -1;
    }
    return -2;
  }
  public int verticalHits(int row, int col){
    int add = 1, outOfBoard = 0, miss = 0;
    while(miss != 1 && outOfBoard != 1){
      if(row + add > 9){
        outOfBoard++;
        break;
      }
      else if(keepTrackBoard[row+add][col] == 'W'){
        miss++;
        break;
      }
      else if(keepTrackBoard[row+add][col] == '0'){
        return row+add;
      }
      add++;
    }
    add = -1;
    while((miss!=2) && ((outOfBoard != 1 && miss == 1) || (outOfBoard == 1 && miss != 1))){
      if(row + add < 0){
        outOfBoard++;
        break;
      }
      else if(keepTrackBoard[row+add][col] == 'W'){
        miss++;
        break;
      }
      else if(keepTrackBoard[row+add][col] == '0'){
        return row+add;
      }
      add--;
    }
    if(outOfBoard == 1 && miss == 1){
      return -1;
    }
    else if(miss == 2){
      return -1;
    }
    return -2;
  }
}