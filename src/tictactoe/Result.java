package tictactoe;
public enum Result {
    NOT_OVER(""),
    X_WON("X won the game!"),
    O_WON("O won the game!"),
    DRAW("It's a draw!")
    ;
    
    private final String text;

    Result(final String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
