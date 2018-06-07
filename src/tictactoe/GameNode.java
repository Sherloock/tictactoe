package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GameNode {

    private GameState gameState;
    private List<GameNode> childs = new ArrayList<GameNode>();

    private int depth; //signs on the board
    
    //1.0 X win for sure // -1.0 O wins for sure
    private double minMaxValue;
    private double averageValue;
    private int lastMoveX;
    private int lastMoveY;

    private Sign signToMove;

    public GameNode() { // root constructor
        gameState = new GameState();
        depth = 0;
        signToMove = Sign.X;
    }

    private GameNode(GameState parent, Sign signToMove, int x, int y, int depth) {
        this.gameState = parent;
        this.gameState.setField(signToMove, x, y);
        lastMoveX = x;
        lastMoveY = y;
        this.depth = depth;
        this.signToMove = depth % 2 == 0 ? Sign.X : Sign.O;
    }

    public void populateNextLevel() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (gameState.getBoard()[x][y] == Sign.EMPTY) {
                    GameNode child = new GameNode( new GameState(gameState), signToMove, x, y, depth + 1);
                    this.childs.add(child);
                    if (child.gameState.getResult() == Result.NOT_OVER) {
                        child.populateNextLevel();
                        child.setValues();
                    } else if (child.gameState.getResult() == Result.O_WON) {
                        child.averageValue = child.minMaxValue = -1;
                    } else if (child.gameState.getResult() == Result.X_WON) {
                        child.averageValue = child.minMaxValue = 1;
                    } else {
                        child.averageValue = child.minMaxValue = 0;
                    }
                }
            }
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<GameNode> getChilds() {
        return childs;
    }

    public int getDepth() {
        return depth;
    }

    public String toString() {
        return "depth: " + depth
                + "\naverageValue: " + averageValue
                + "\nminMaxValue: " + minMaxValue
                + "\nmove: " + signToMove.toString() + "\n"
                + gameState.toString();
    }

    private void setValues() {
        averageValue = 0;
        for (int i = 0; i < childs.size(); i++) {
            averageValue += childs.get(i).minMaxValue;
        }
        averageValue /= childs.size();

        minMaxValue = getChilds().get(0).minMaxValue;
        for (int i = 1; i < childs.size(); i++) {
            if (depth % 2 == 0) { //max
                if (minMaxValue < childs.get(i).minMaxValue) {
                    minMaxValue = childs.get(i).minMaxValue;
                }
            } else {
                if (minMaxValue > childs.get(i).minMaxValue) {
                    minMaxValue = childs.get(i).minMaxValue;
                }
            }
        }
    }

    public void bestMove() {
        // X TO MOVE
        if (signToMove == Sign.X) {
            int maxIndex = 0;
            for (int i = 0; i < childs.size(); i++) {
                if (childs.get(i).minMaxValue == 1) {
                    TicTacToe.game.setField(childs.get(i).lastMoveX, childs.get(i).lastMoveY);
                    return;
                }
                double max = -1;
                
                if (childs.get(i).averageValue > max && childs.get(i).minMaxValue == 0) {
                    max = childs.get(i).averageValue;
                    maxIndex = i;
                }
            }
            TicTacToe.game.setField(childs.get(maxIndex).lastMoveX, childs.get(maxIndex).lastMoveY);
        }
        // O TO MOVE
        else{
            int minIndex = 0;
            for (int i = 0; i < childs.size(); i++) {
                if (childs.get(i).minMaxValue == -1) {
                    TicTacToe.game.setField(childs.get(i).lastMoveX, childs.get(i).lastMoveY);
                    return;
                }
                double min = 1;
                
                if (childs.get(i).averageValue < min && childs.get(i).minMaxValue == 0) {
                    min = childs.get(i).averageValue;
                    minIndex = i;
                }
               
            }
            TicTacToe.game.setField(childs.get(minIndex).lastMoveX, childs.get(minIndex).lastMoveY);
        }
    
    }

}
