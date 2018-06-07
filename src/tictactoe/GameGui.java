package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class GameGui {

    private final int BUTTON_SIZE = 170;
    private final int WINDOW_WIDTH = (BUTTON_SIZE + 2) * 3;
    private final int WINDOW_HEIGHT = 30 + 3 * (BUTTON_SIZE + 1) + 20;
    private final int INFO_BOX_HEIGHT = 20;

    private final JFrame WINDOW = new JFrame("TicTacToe");
    private final JPanel PANEL = new JPanel();
    private final JButton[][] BOARD = new JButton[3][3];
    private final JLabel INFO_BOX = new JLabel("", SwingConstants.CENTER);

    public GameGui(Game.GameMode gamemode) {
        initWindow();
        initPanel();
        initGameButtons();
        if(gamemode == Game.GameMode.AIvP){
            setBoardEnabled(false);
        }
        initInfoBox();
        WINDOW.setVisible(true);
        
    }

    private void initWindow() {
        WINDOW.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        WINDOW.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW.setLocation(dim.width / 2 - WINDOW.getSize().width / 2, dim.height / 2 - WINDOW.getSize().height / 2);
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void closeWindow(){
        WINDOW.dispose();
    }

    private void initPanel() {
        PANEL.setBackground(Color.WHITE);
        PANEL.setLayout(null); // absoulute positioning
        PANEL.setOpaque(true);
        WINDOW.add(PANEL);
    }

    private void initGameButtons() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                final int a = x, b = y;
                BOARD[x][y] = new JButton();
                BOARD[x][y].setBounds(x * BUTTON_SIZE, y * BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
                BOARD[x][y].setBackground(Color.white);
                BOARD[x][y].setFont(new Font("Courier", Font.BOLD, (int) (BUTTON_SIZE * 0.9)));
                BOARD[x][y].setFocusPainted(false);
                BOARD[x][y].setMultiClickThreshhold(1000);
                BOARD[x][y].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        TicTacToe.game.setField(a, b);
                    }
                });
                PANEL.add(BOARD[x][y]);
            }
        }
    }

    public void setField(int x, int y, Sign sign) {
        BOARD[x][y].setText(sign.toString());
        BOARD[x][y].setForeground(sign == Sign.X ? Color.BLUE : Color.RED);
    }

    public void setBoardEnabled(boolean enabled) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                BOARD[x][y].setEnabled(enabled);
            }
        }
    }

    private void initInfoBox() {
        INFO_BOX.setFont(new Font("Courier", Font.BOLD, INFO_BOX_HEIGHT - 5));
        INFO_BOX.setBounds(0, 3 * (BUTTON_SIZE + 1), WINDOW_WIDTH, INFO_BOX_HEIGHT);
        INFO_BOX.setForeground(Color.black);
        INFO_BOX.setText("X start the game!");
        PANEL.add(INFO_BOX);
    }

    public void message(String s) {
        INFO_BOX.setText(s);
    }
}
