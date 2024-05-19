package game.componenets;

import javax.swing.*;
import java.awt.*;

public class LevelSelectionMenu extends JPanel {

    private JLabel label;
    private JPanel levelsPanel;
    private JButton[] buttons;

    private final int levelsAmount;
    private int selectedLevel;

    public LevelSelectionMenu(int levelsAmount) {
        this.levelsAmount = levelsAmount;
        initialize();
    }

    public void initialize() {
        setBounds(0, 0, 900, 750);
        setBackground(new Color(0, 60, 67));
        setLayout(null);

        label = new JLabel();
        label.setBounds(100,100,700,50);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("SELECT LEVEL");
        label.setForeground(new Color(119, 176, 170));
        label.setBackground(new Color(19, 93, 102));
        label.setOpaque(true);
        label.setFont(new Font("Ariel", Font.PLAIN, 25));

        levelsPanel = new JPanel();
        levelsPanel.setBounds(100,200, 700,500);
        levelsPanel.setBackground(new Color(19, 93, 102));

        add(label);
        add(levelsPanel);
        setVisible(false);
    }
}
