package tictactoe;
public class Player {    
    public static enum Type { HUMAN, AI; };
    private final Type TYPE;
    private final Sign SIGN;

    public Player(Type TYPE, Sign SIGN) {
        this.TYPE = TYPE;
        this.SIGN = SIGN;
    }

    public Type getType() {
        return TYPE;
    }

    public Sign getSign() {
        return SIGN;
    }

}
