package game.componenets;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu() {
        initialize();
    }

    private void initialize(){
        setBounds(0,0,900,750);
        setBackground(new Color(0, 60, 67));

        setLayout(null);

        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
