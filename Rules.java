package BattleShip;
public class Rules{
    private String letters = "ABCDEFGHIJ";
    private String numbers = "12345678910";
    private String[] piece = new String[]{"Carrior", "BattleShip", "Crusier", "Submarine", "Destroyer"};
    private int[] numSpace = {5, 4, 3, 3, 2};
  
    public Rules(){
    }
    public int correctPostion(String input){//works
      int matchHor = input.indexOf("horizontal");
      int matchVer = input.indexOf("vertical");
      if(matchHor == -1 && matchVer == -1){
        return -1;
      }
      else{
        return 1;
      }
    }
    public int checkAndSendRow(String row){
      int index = letters.indexOf(row);
      int length = row.length();
      if(index == -1 || length >1){
        return -1;
      }
      else{
        return index;
      }
    }
    public int checkAndSendCol(String col){
      int index = numbers.indexOf(col);
      int length = col.length();
      if(index == 9){
        return index;
      }
      else if(index == -1 || length >1 || index == 10){
        return -1;
      }
      else{
        return index;
      }
    }
    public int overLap(String position, int col, int row, int index, char[][] mainBoard){
      int numSpaces = numSpace[index];
      boolean foundOverLap = false;
      int keepTrack = 0;
      if(position.equals("horizontal")){
        while (foundOverLap == false && keepTrack != numSpaces){
          if(mainBoard[row][col+keepTrack] == 'B'){
            foundOverLap = true;
          }
          keepTrack++;
        }
        if(foundOverLap == true){
          return -1;
        }
      }
      else if(position.equals("vertical")){
        while (foundOverLap == false && keepTrack != numSpaces){
          if(mainBoard[row+keepTrack][col] == 'B'){
            foundOverLap = true;
          }
          keepTrack++;
        }
        if(foundOverLap == true){
          return -1;
        }
      }
      
      return 1;
      
    }
    public int outOfBound(String pos, int col, int row, int index, char[][] mainBoard){
      int numSpaces = numSpace(index);
      if(pos.equals("horizontal")){
        if(col + (numSpaces-1) >= 10){
          return -1;
        }
        else{
          return 1;
        }
      }
      else if(pos.equals("vertical")){
        if(row + (numSpaces-1) >= 10){
          return -1; 
        }
        else{
          return 1;
        }
      }
      else{
        return 1;
      }
    }
    public String name(int index){
      return piece[index];
    }
    public int numSpace(int index){
      return numSpace[index];
    }
    
  }