package tictactoe;
public enum Sign {
    EMPTY("."),
    X("X"),
    O("O");
    
    private final String text;

    Sign(final String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}