package frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900,750));
        setResizable(false);

        setTitle("Sokoban");

        setLayout(null);
        MainPanel mainPanel = new MainPanel();
        add(mainPanel);

        pack();
        setVisible(true);
        //mainPanel.startGame();
        System.out.println("BACK");
    }

}
