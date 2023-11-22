package FreezeMonster;

import java.awt.EventQueue;

import FreezeMonster.framework.MainFrame;
public class FreezeMonster extends MainFrame {
    public Settings commons = Settings.getInstance();

    public FreezeMonster(String title) {
        setWindowTitle(title);
        setTitle(title);
    }

    private void initUI() {

        add(new Board());
        setSize(commons.BOARD_WIDTH, commons.BOARD_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @Override
    protected Board createBoard() {
        return new Board();
    }

    @Override
    public void start() {
        EventQueue.invokeLater(() -> {
            initUI();
            this.setVisible(true);
        });
    }
}
