package FreezeMonster;

import java.awt.EventQueue;

import framework.MainFrame;
public class FreezeMonster extends MainFrame {
    public Settings commons = Settings.getInstance();

    public FreezeMonster(String title) {
        setWindowTitle(title);
        setTitle(title);
    }

    @Override
    protected Board createBoard() {
        return new Board();
    }

    @Override
    public void start() {
        EventQueue.invokeLater(() -> {
            this.setVisible(true);
        });
    }
}
