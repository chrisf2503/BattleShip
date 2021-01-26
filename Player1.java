package BattleShip;
import java.util.*;
public class Player1{
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

  public Player1(Rules r){
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

  public void placePeice(String position, int col, int row, int numSpace){//works
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
  public void userInput(){
    Scanner s = new Scanner(System.in);
    int count = 0;
    String input = "";
    String row = "";
    String col = "";
    int ifExist = -10;
    int found = -10;
    int length = 0;
    int outOfBoard = 0;
    int ifWorks = 0;
    while (count < 5){
      clearScreen();
      boardGame2();
      System.out.print("What position you want your " + r.name(count) + "(horizontal or vertical) : ");//first change(works)
      String pos = s.nextLine();
      pos = pos.toLowerCase();
      int prob = r.correctPostion(pos);//second Change works
      while(prob != 1){
        System.out.print("Sorry please input the right position: ");
        pos = s.nextLine();
        prob = r.correctPostion(pos);
      }
      System.out.print("Please select a coordinate: ");
      input = s.nextLine();
      row = input.substring(0,1);
      row = row.toUpperCase();
      col = input.substring(1);
      length = input.length();
      ifExist = r.checkAndSendRow(row);
      found = r.checkAndSendCol(col);
      while(found == -1 || ifExist == -1 || length == 1){
        System.out.print("Sorry please input a valid coordinate: ");
        input = s.nextLine();
        row = input.substring(0,1);
        row = row.toUpperCase();
        col = input.substring(1);
        length = input.length();
        ifExist = r.checkAndSendRow(row);
        found = r.checkAndSendCol(col);
      }
      outOfBoard = r.outOfBound(pos, found, ifExist, count, mainBoard);
      while(outOfBoard == -1){
        System.out.println("Sorry piece is out of board");
        System.out.print("Please select a coordinate: ");
        input = s.nextLine();
        row = input.substring(0,1);
        row = row.toUpperCase();
        col = input.substring(1);
        length = input.length();
        ifExist = r.checkAndSendRow(row);
        found = r.checkAndSendCol(col);
        while(found == -1 || ifExist == -1 || length == 1){
          System.out.print("Sorry please input a valid coordinate: ");
          input = s.nextLine();
          row = input.substring(0,1);
          row = row.toUpperCase();
          col = input.substring(1);
          length = input.length();
          ifExist = r.checkAndSendRow(row);
          found = r.checkAndSendCol(col);
        }
        outOfBoard = r.outOfBound(pos, found, ifExist, count, mainBoard);
      }
      ifWorks = r.overLap(pos, found, ifExist, count, mainBoard);
      while(ifWorks == -1){
        System.out.println("Sorry the place you want to choose is invalid due to overlaping ships");
        System.out.print("Please select a coordinate: ");
        input = s.nextLine();
        row = input.substring(0,1);
        row = row.toUpperCase();
        col = input.substring(1);
        length = input.length();
        ifExist = r.checkAndSendRow(row);
        found = r.checkAndSendCol(col);
        while(found == -1 || ifExist == -1 || length == 1){
          System.out.print("Sorry please input a valid coordinate: ");
          input = s.nextLine();
          row = input.substring(0,1);
          row = row.toUpperCase();
          col = input.substring(1);
          length = input.length();
          ifExist = r.checkAndSendRow(row);
          found = r.checkAndSendCol(col);
        }
        outOfBoard = r.outOfBound(pos, found, ifExist, count, mainBoard);
        while(outOfBoard == -1){
          System.out.println("Sorry piece is out of board");
          System.out.print("Please select a coordinate: ");
          input = s.nextLine();
          row = input.substring(0,1);
          row = row.toUpperCase();
          col = input.substring(1);
          length = input.length();
          ifExist = r.checkAndSendRow(row);
          found = r.checkAndSendCol(col);
          while(found == -1 || ifExist == -1 || length == 1){
            System.out.print("Sorry please input a valid coordinate: ");
            input = s.nextLine();
            row = input.substring(0,1);
            row = row.toUpperCase();
            col = input.substring(1);
            length = input.length();
            ifExist = r.checkAndSendRow(row);
            found = r.checkAndSendCol(col);
          }
          outOfBoard = r.outOfBound(pos, found, ifExist, count, mainBoard);
        }
        ifWorks = r.overLap(pos, found, ifExist, count, mainBoard);
      }
      placePeice(pos, found, ifExist, r.numSpace(count));
      ships.add(new Piece(r.name(count),pos , found, ifExist, r.numSpace(count)));
      count++;
    }
  }
  
  public ArrayList<Piece> ship(){
    return ships;
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
  public char[][] mainBoard(){
    return mainBoard;
  }
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}