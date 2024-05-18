package game.componenets;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private GameStateManager gameStateManager;
    private JLabel informationLabel;
    private JLabel levelLabel;

    public InformationPanel(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        initialize();
    }

    public void initialize(){
        setBounds(50, 600, 300, 50);
        setFocusable(true);
        setLayout(null);

        informationLabel = new JLabel();
        informationLabel.setBounds(0, 0, 50, 50);
        informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        informationLabel.setText("INFO:");
        //informationLabel.setFont(new Font("Ariel", Font.PLAIN, 25));
        informationLabel.setForeground(new Color(227, 254, 247));
        informationLabel.setBackground(new Color(19, 93, 102));
        informationLabel.setFocusable(false);
        informationLabel.setOpaque(true);
        informationLabel.setBorder(null);
        //informationLabel.setVisible(true);

        levelLabel = new JLabel();
        levelLabel.setBounds(50, 0, 250, 50);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setText("0");
        levelLabel.setForeground(new Color(227, 254, 247));
        levelLabel.setBackground(new Color(19, 93, 102));
        levelLabel.setFocusable(false);
        levelLabel.setOpaque(true);
        levelLabel.setBorder(null);

        add(informationLabel);
        add(levelLabel);
        setVisible(false);
    }
}
