package tictactoe;
public class TicTacToe {
    public static GameGui gui;
    public static Game game;
    public static GameNode root;
    public static void main(String[] args) {
        root = new GameNode();
        root.populateNextLevel();
 //       System.out.println(GameNode.counter);
//        System.out.println("xwon=" + GameNode.xWon);
//        System.out.println("owon=" + GameNode.oWon);
//        System.out.println("draw=" + GameNode.draw);
//        for (int i = 0; i < 9; i++) {
//            System.out.printf("%d elemu %d darab\n",i+1,GameNode.counters[i] );
//        }
       start();
    }
    public static void start() {
        new PopupGUI();
    }
    public static void crateNewGame(Game.GameMode gamemode){
        game = new Game(gamemode);
        gui = new GameGui(gamemode);
    }


    
}
