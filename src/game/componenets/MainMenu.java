package game.componenets;

import game.GameState;

import javax.swing.*;
import java.awt.*;

/**
 * Main menu
 */
public class MainMenu extends JPanel {

    private final GameStateManager gameStateManager;
    public MainMenu(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        initialize();
    }

    private void initialize(){
        setBounds(0,0,900,750);
        setBackground(new Color(0, 60, 67));
        setLayout(null);

        JButton button = new JButton();
        button.setBounds(100,350,700,100);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setText("CLICK HERE TO START THE GAME");
        button.setForeground(new Color(119, 176, 170));
        button.setBackground(new Color(19, 93, 102));
        button.setFocusable(false);
        button.setBorder(null);
        button.setFont(new Font("Ariel", Font.PLAIN, 25));
        button.addActionListener(e -> gameStateManager.setCurrentState(GameState.GAME_MODE_CHOICE));

        add(button);
        setVisible(false);
    }
}
