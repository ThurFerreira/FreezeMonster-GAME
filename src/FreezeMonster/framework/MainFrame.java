package FreezeMonster.framework;

import javax.swing.*;

import FreezeMonster.Board;
import FreezeMonster.Settings;

public abstract class MainFrame extends JFrame  {
    Settings set = Settings.getInstance();

    // hotspot
    protected abstract Board createBoard();
    public abstract void start();

    public MainFrame() {

        add(createBoard());

        setSize(set.BOARD_WIDTH, set.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setWindowTitle(String title){
        setTitle(title);
    }
}
