package FreezeMonster;

import java.awt.EventQueue;
import javax.swing.JFrame;
public class FreezeMonster extends JFrame {
    public Commons commons = Commons.getInstance();

    public FreezeMonster() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Freeze Monster");
        setSize(commons.BOARD_WIDTH, commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new FreezeMonster();
            ex.setVisible(true);
        });
    }
}
