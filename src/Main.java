public class Main {
    public static void main(String[] args) {

        Helper.createPlayerName();
        Helper.createSize();

        MineSwapper mineSwapper = new MineSwapper(Helper.col,Helper.row,Helper.playerName);
        mineSwapper.gameRun();
    }
}