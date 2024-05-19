package game.componenets;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LevelButton extends JButton {

    public LevelButton(LevelSelectionMenu menu , int levelNumber) {

        setForeground(new Color(119, 176, 170));
        setBackground(new Color(19, 93, 102));
        setFocusable(false);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(new Color(0, 60, 67),2));
        setText(String.valueOf(levelNumber));
        addActionListener(e -> menu.setSelectedLevel(levelNumber));
        setVisible(true);

    }


}
