package tictactoe;

public class GameState {

    private Result result;
    private Sign[][] board = new Sign[3][3];

    //new game
    public GameState() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board[x][y] = Sign.EMPTY;
            }
        }
        result = Result.NOT_OVER;
    }

    public GameState(GameState gameState) {
        this.result = gameState.result;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                this.board[x][y] = gameState.board[x][y];
            }
        }
    }

    private void setResult() {
        //vertical
        for (int x = 0; x < 3; x++) {
            if (board[x][0] == board[x][1] && board[x][1] == board[x][2] && board[x][0] != Sign.EMPTY) {
                result = board[x][0] == Sign.X ? Result.X_WON : Result.O_WON;
                return;
            }
        }
        //horizontal
        for (int y = 0; y < 3; y++) {
            if (board[0][y] == board[1][y] && board[1][y] == board[2][y] && board[0][y] != Sign.EMPTY) {
                result = board[0][y] == Sign.X ? Result.X_WON : Result.O_WON;
                return;
            }
        }
        //diagonal
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != Sign.EMPTY)
                || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != Sign.EMPTY)) {
            result = board[1][1] == Sign.X ? Result.X_WON : Result.O_WON;
            return;
        }

        //if there is an empty filed than not over yet
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[x][y] == Sign.EMPTY) {
                    result = Result.NOT_OVER;
                    return;
                }
            }
        }
        //no one won and not empty field so its a draw
        result = Result.DRAW;
    }

    public Result getResult() {
        return result;
    }

    public Sign[][] getBoard() {
        return board;
    }

    public boolean equals(GameState gameState) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (this.board[x][y] != gameState.board[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setField(Sign sign, int x, int y) {
        board[x][y] = sign;
        setResult();
    }

    public String toString() {
        String board = "";
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board += this.board[x][y].toString();
            }
            board += "\n";
        }
        
//        for (int y = 0; y < 5; y++) {
//            for (int x = 0; x < 5; x++) {
//                if (x % 2 == 0) {
//                    if (y % 2 == 0) {
//                        board += this.board[x / 2][y / 2].toString();
//                    } else {
//                        board += "-";
//                    }
//                } else {
//                    if (y % 2 == 0) {
//                        board += "|";
//                    } else {
//                        board += "+";
//                    }
//                }
//            }
//            board += "\n";
//        }
        return board;
    }

}
