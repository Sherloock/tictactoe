package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public final class PopupGUI {

    private final int WINDOW_WIDTH = 350, WINDOW_HEIGHT = 250;
    private final JFrame WINDOW = new JFrame("TicTacToe");
    private final JPanel PANEL = new JPanel();

    private final JButton START = new JButton("Start!");

    private final ButtonGroup GROUP = new ButtonGroup();
    private final JRadioButton RADIO_BUTTONS[] = new JRadioButton[3];

    public PopupGUI() {
        initWindow();
        initPanel();
        initRadioButtons();
        initNewGameButton();
        initMessage();
        WINDOW.setVisible(true);
    }

    public void initWindow() {
        WINDOW.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        WINDOW.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        WINDOW.setLocation(dim.width / 2 - WINDOW.getSize().width / 2, dim.height / 2 - WINDOW.getSize().height / 2);
        WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initPanel() {
        PANEL.setBackground(Color.WHITE);
        PANEL.setLayout(null); // absoulute positioning
        PANEL.setOpaque(true);
        WINDOW.add(PANEL);
    }

    private void initNewGameButton() {
        Game.GameMode[] mode = {Game.GameMode.PvP, Game.GameMode.PvAI, Game.GameMode.AIvP};
        int width = 200, height = 50;
        START.setBounds((WINDOW_WIDTH - width) / 2, 150, width, height);
        START.setBackground(Color.white);
        START.setFont(new Font("Courier", Font.BOLD, (int) (height * 0.8)));
        START.setFocusPainted(false);
        START.setMultiClickThreshhold(1000);
        START.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < RADIO_BUTTONS.length; i++) {
                    if (RADIO_BUTTONS[i].isSelected()) {
                        if (TicTacToe.gui != null) {
                            TicTacToe.gui.closeWindow();
                        }
                        TicTacToe.crateNewGame(mode[i]);
                        if(i == 2){
                            TicTacToe.game.getGameNode().bestMove();
                        }
                    }
                }
                WINDOW.dispose();
            }
        });
        PANEL.add(START);

    }

    private void initRadioButtons() {
        String[] text = {"Player vs Player", "Player vs AI", "AI vs Player"};

        for (int i = 0; i < RADIO_BUTTONS.length; i++) {
            RADIO_BUTTONS[i] = new JRadioButton(text[i]);
            RADIO_BUTTONS[i].setFont(new Font("Courier", Font.BOLD, 18));
            RADIO_BUTTONS[i].setBounds((WINDOW_WIDTH-RADIO_BUTTONS[i].getPreferredSize().width)/2,50+30*i, 
                    RADIO_BUTTONS[i].getPreferredSize().width,  RADIO_BUTTONS[i].getPreferredSize().height);
            RADIO_BUTTONS[i].setBackground(Color.WHITE);
            
            RADIO_BUTTONS[i].setFocusPainted(false);
            GROUP.add(RADIO_BUTTONS[i]);
            PANEL.add(RADIO_BUTTONS[i]);
        }
        RADIO_BUTTONS[0].setSelected(true);
    }

    private void initMessage() {
        final JLabel message = new JLabel("Select gamemode and start!", SwingConstants.CENTER);
        message.setBounds(0, 10, WINDOW_WIDTH, 30);
        message.setFont(new Font("Courier", Font.BOLD, 24 ));
        PANEL.add(message);
    }
}
