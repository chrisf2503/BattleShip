package BattleShip;
import java.util.*;

class battleShip {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Rules r = new Rules();
        Player1 p = new Player1(r);
        Cpu c = new Cpu(r);

        p.userInput();
        c.input();
        //c.boardGame2();
        ArrayList<Piece> playerShips = p.ship();
        ArrayList<Piece> cpuShips = c.ship();
        Game g = new Game(playerShips, cpuShips);
        char[][] cpuBoard = c.mainBoard();
        char[][] playerBoard = p.mainBoard();
        String mode = "";
        String input = "";
        String playerRow = "";
        String playerCol = "";
        int row = 0;
        int col = 0;
        int cpuRow = 0;
        int cpuCol = 0;
        int hitRow = 0;
        int hitCol = 0;
        int player = 0;
        int winner = -1;
        boolean valid = false;
        boolean cpuHitOrMiss = false;
        boolean hitOrMiss = false;
        String playerOutcome = "";
        String shipsSank1 = "";
        String shipsSank2 = "";
        System.out.println("Please select your level of harness");
        System.out.print("choice 1 for easy, choice 2 for hard: ");
        mode = s.nextLine();
        while(!mode.equals("1") && !mode.equals("2")){
            System.out.println("This level does not exist");
            System.out.println("Please select your level of harness");
            System.out.print("choice 1 for easy, choice 2 for hard: ");
            mode = s.nextLine();
        }
        while (winner == -1) {
            clearScreen();
            System.out.println("Player ships left: " + g.playerListSize());
            System.out.println("Cpu ships left: " + g.cpuListSize());
            if (!shipsSank1.equals("")) {
                System.out.println("Cpu " + shipsSank1 + " sank!");
                shipsSank1 = "";
            }
            if (!shipsSank2.equals("")) {
                System.out.println("Players " + shipsSank2 + " sank!");
                shipsSank2 = "";
            }
            System.out.println("Player outcome: " + playerOutcome);
            //c.boardGame1();
            p.boardGame1();
            p.boardGame2();
            //c.boardGame2();

            player = 2;
            System.out.print("Please pick a coordinate: ");
            input = s.nextLine();
            playerRow = input.substring(0, 1);
            playerRow = playerRow.toUpperCase();
            playerCol = input.substring(1);
            row = r.checkAndSendRow(playerRow);
            col = r.checkAndSendCol(playerCol);
            while (col == -1 || row == -1 || input.length() == 1) {
                System.out.print("Sorry please choose a proper coordinate: ");
                input = s.nextLine();
                playerRow = input.substring(0, 1);
                playerRow = playerRow.toUpperCase();
                playerCol = input.substring(1);
                row = r.checkAndSendRow(playerRow);
                col = r.checkAndSendCol(playerCol);
            }
            valid = g.ifValid(row, col, cpuBoard);
            while (valid == false) {
                System.out.println("Sorry this isn't a Valid coordinate");
                System.out.print("Please pick a coordinate: ");
                input = s.nextLine();
                playerRow = input.substring(0, 1);
                playerRow = playerRow.toUpperCase();
                playerCol = input.substring(1);
                row = r.checkAndSendRow(playerRow);
                col = r.checkAndSendCol(playerCol);
                while (col == -1 || row == -1 || input.length() == 1) {
                    System.out.print("Sorry please choose a proper coordinate: ");
                    input = s.nextLine();
                    playerRow = input.substring(0, 1);
                    playerRow = playerRow.toUpperCase();
                    playerCol = input.substring(1);
                    row = r.checkAndSendRow(playerRow);
                    col = r.checkAndSendCol(playerCol);
                }
                valid = g.ifValid(row, col, cpuBoard);
            }
            hitOrMiss = g.hitOrMiss(row, col, cpuBoard);
            p.updateKeepTrackBoard(row, col, hitOrMiss);
            c.updateMainBoard(row, col, hitOrMiss);
            if (hitOrMiss == true) {
                playerOutcome = "HIT TARGET!";
            } 
            else if (hitOrMiss == false) {
                playerOutcome = "MISS TARGET!";
            }
            shipsSank1 = g.removePiece(cpuBoard, player);
            winner = g.winner();
            if(winner == 2){
                break;
            }
            if(cpuHitOrMiss == false || mode.equals("1")){
                player = 1;
                cpuRow = c.randomInput();
                cpuCol = c.randomInput();
                valid = g.ifValid(cpuRow, cpuCol, playerBoard);
                while (valid == false) {
                    cpuRow = c.randomInput();
                    cpuCol = c.randomInput();
                    valid = g.ifValid(cpuRow, cpuCol, playerBoard);
                }
      
                cpuHitOrMiss = g.hitOrMiss(cpuRow, cpuCol, playerBoard);
                p.updateMainBoard(cpuRow, cpuCol, cpuHitOrMiss);
                c.updateKeepTrackBoard(cpuRow, cpuCol, cpuHitOrMiss);
                hitRow = cpuRow;
                hitCol = cpuCol;
            }

            else if(cpuHitOrMiss == true){
                player = 1;
                int newRow = 0;
                int newCol = c.horizontalHits(hitRow, hitCol);
                if(newCol != -1){
                    cpuHitOrMiss = g.hitOrMiss(hitRow, newCol, playerBoard);
                    p.updateMainBoard(hitRow, newCol, cpuHitOrMiss);
                    c.updateKeepTrackBoard(hitRow, newCol, cpuHitOrMiss);
                    cpuHitOrMiss = true;
                }//*/
                if(newCol == -1){
                    newRow = c.verticalHits(hitRow, hitCol);
                    if(newRow != -1){
                        cpuHitOrMiss = g.hitOrMiss(newRow, hitCol, playerBoard);
                        p.updateMainBoard(newRow, hitCol, cpuHitOrMiss);
                        c.updateKeepTrackBoard(newRow, hitCol, cpuHitOrMiss);
                        cpuHitOrMiss = true;
                    }
          
                }
                shipsSank2 = g.removePiece(playerBoard, player);
                if(!shipsSank2.equals("") || (newRow == -1 && newCol == -1)){
                    cpuHitOrMiss = false;
                }//*/
            }
            //shipsSank2 = g.removePiece(playerBoard, player);
            winner = g.winner();
        }
        clearScreen();
        p.boardGame1();
        p.boardGame2();
        if (winner == 2) {
            System.out.println("Player Wins");
            g.updateFile(true);
        } 
        else if (winner == 1) {
            System.out.println("Computer Wins");
            g.updateFile(false);
        }//*/
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}