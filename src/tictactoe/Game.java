package tictactoe;

public class Game {
    public static enum GameMode {
        PvP, PvAI, AIvP
    };

    public GameNode getGameNode() {
        return gameNode;
    }

    private final GameMode GAMEMODE;
    private GameState gameState;
    private final Player[] PLAYERS = new Player[2];
    private Player nextPlayer;
    private GameNode gameNode;

    public Game(GameMode GAMEMODE) {
        this.GAMEMODE = GAMEMODE;
        gameState = new GameState();
        PLAYERS[0] = new Player(GAMEMODE == GameMode.AIvP ? Player.Type.AI : Player.Type.HUMAN, Sign.X);
        PLAYERS[1] = new Player(GAMEMODE == GameMode.PvAI ? Player.Type.AI : Player.Type.HUMAN, Sign.O);
        nextPlayer = PLAYERS[0];
        gameNode = TicTacToe.root;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    private void nextPlayerToMove() {
        nextPlayer = (nextPlayer == PLAYERS[0]) ? PLAYERS[1] : PLAYERS[0];
    }


    public void setField(int x, int y) {
        if (gameState.getBoard()[x][y] == Sign.EMPTY) {
            gameState.setField(nextPlayer.getSign(), x, y);
            TicTacToe.gui.setField(x, y, nextPlayer.getSign());
            setGameTree();
            checkResult();
        }
    }
    private void setGameTree() {
        for (int i = 0; i < gameNode.getChilds().size(); i++) {
            if(gameNode.getChilds().get(i).getGameState().equals(gameState)){
                gameNode = gameNode.getChilds().get(i);
                System.out.println(gameNode.toString());
            }
        }
    }

    private void checkResult() {
        if (gameState.getResult() == Result.NOT_OVER) {
            nextPlayerToMove();
            message(nextPlayer.getSign().toString() + " to move!");
            //disable buttons if AI moves next
            TicTacToe.gui.setBoardEnabled(nextPlayer.getType() == Player.Type.HUMAN);
            if (nextPlayer.getType() == Player.Type.AI) {
                gameNode.bestMove();
            }
        } else { // game over
            message(gameState.getResult().toString());
            TicTacToe.gui.setBoardEnabled(false);
            TicTacToe.start();
        }
    }

    private void message(String s) {
        TicTacToe.gui.message(s);
    }
}
