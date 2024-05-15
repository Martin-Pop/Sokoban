package game.componenets;

import game.GameState;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JButton resetButton;
    private GameStateManager gameStateManager;

    public ControlPanel(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        initialize();
    }

    private void initialize(){
        setBounds(700, 200, 150, 100);
        setBackground(Color.GRAY);
        setFocusable(true);
        setLayout(null);

        resetButton = new JButton();
        resetButton.setBounds(0, 0, 150, 150);
        resetButton.setText("reset level");
        resetButton.setForeground(new Color(227, 254, 247));
        resetButton.setBackground(new Color(19, 93, 102));
        resetButton.setFocusable(false);
        resetButton.setBorder(null);
        //resetButton.setFont(new Font("Ariel", Font.PLAIN, 25));
        resetButton.addActionListener(e -> gameStateManager.setCurrentState(GameState.RESET_LEVEL));

        add(resetButton);
        setVisible(false);
    }

}
