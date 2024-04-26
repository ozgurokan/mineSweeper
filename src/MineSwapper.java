
import java.lang.Math;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
public class MineSwapper {

        private final Random rand = new Random();

        public static final String empty = "-";
        public static final String mine = "*";


        private boolean isAlive = true;
        private boolean isWin = false;
        private String[][] board;
        private String[][] board_open;
        private int size_of_mine;
        private final int col;
        private final int row;
        private final String username;
        private int selectedCol;
        private int selectedRow;
        private int total_empty;


        public MineSwapper(int col,int row,String userName){
            this.col = col;
            this.row = row;
            this.username = userName;
            this.board = new String[row][col];
            this.board_open = new String[row][col];
            this.size_of_mine =(int) (col * row) / 4;
            this.total_empty = (col * row) - size_of_mine;
        }




        public void boardGenerator(){
            for(int i = 0; i < row ; i++){
                for(int j = 0; j < col; j++){
                    board[i][j] = empty;
                    board_open[i][j] = empty;
                }
            }
        }



        public void randomMineGenerator(){
            int mineRow = 0;
            int mineCol = 0;

            while(size_of_mine > 0){
                mineRow = rand.nextInt(row);
                mineCol = rand.nextInt(col);
                if (!board_open[mineRow][mineCol].equals(mine)) {
                    board_open[mineRow][mineCol] = mine;
                    size_of_mine--;
                }else{
                    System.out.println(mineRow + " " + mineCol);
                }
            }

        }


        public void printBoard(){
            for(int i = 0; i < board.length ; i++){
                System.out.println();
                for(int j = 0; j < board[i].length ; j++){
                    System.out.print(board[i][j] + " ");
                }
            }
        }

        public void printBoardOpen(){
            for(int i = 0; i < board_open.length ; i++){
                System.out.println();
                for(int j = 0; j < board_open[i].length ; j++){
                    System.out.print(board_open[i][j] + " ");
                }
            }
        }

        public void checkSelection(int x,int y){
            if(board_open[x][y].equals(mine)){
                isAlive = false;
                gameOver();
            }else{
                if(board[x][y].equals(empty)){
                    int minesAround = checkAround(x,y);
                    board[x][y] = String.valueOf(minesAround);
                    total_empty--;
                    isWin();
                }else{
                    System.out.println(Helper.already_choose);
                }

            }
        }

        public int checkAround(int x,int y){
            // x -> row
            // y -> col
            int mines_count_around = 0;
            ArrayList<String> around = new ArrayList<>();
            // üst sıra
            //row sıfır olursa üst sırası yoktur yani kontrol etmeye gerek yok
            if(x > 0){
                // col 0 olursa soluna bakmaya gerek yok ve index hatası alır
                if(y > 0){
                    around.add(board_open[x-1][y-1]);
                }
                // y = col olduğunda sondadır sağına bakmaya gerek yok
                if(y < col -1){
                    around.add(board_open[x-1][y+1]);
                }
                around.add(board_open[x-1][y]);
            }
            // kendi sırası -- kendisini kontrol etmeye gerek yok
            if(y > 0){
                around.add(board_open[x][y-1]);
            }
            if(y < col -1){
                around.add(board_open[x][y+1]);
            }

            // alt sıra
            // x rowa eşit olduğunda en alt sıradadır alt sırası yoktur
            if(x < row -1){
                if(y > 0){
                    around.add(board_open[x+1][y-1]);
                }
                if(y < col -1){
                    around.add(board_open[x+1][y+1]);
                }
                around.add(board_open[x+1][y]);
            }

            for (String str : around){
                if(str.equals(mine)){
                    mines_count_around++;
                }
            }
            return mines_count_around;
        }



        public void gameOver(){
            System.out.println(Helper.lose_massage);
            mergeBoards();
            System.out.println("Gerçek Harita buydu!");
            printBoard();
        }

        public void isWin(){
            if(this.total_empty == 0){
                this.isWin = true;
                mergeBoards();
                printBoard();
                System.out.println(Helper.win_message);
            }

        }

        public void mergeBoards(){
            for(int i = 0 ; i < board_open.length; i++){
                for(int y = 0; y < board[i].length;y++){
                    if(board_open[i][y].equals(mine)){
                        board[i][y] = mine;
                    }
                }
            }
        }

        public void gameRun(){
            boardGenerator();
            randomMineGenerator();
            printBoardOpen();
            System.out.println("\n////////////////////////////////////////////////");
            while(isAlive && !isWin){
                printBoard();
                System.out.print("\nSatır:");
                selectedRow = Helper.input.nextInt();
                System.out.print("Sütun:");
                selectedCol = Helper.input.nextInt();
                if((selectedCol > col -1 || selectedRow > row -1) || (selectedRow < 0 || selectedCol < 0)){
                    System.out.println(Helper.invalid_selection_message);
                    continue;
                }
                checkSelection(selectedRow,selectedCol);
            }
        }


}
