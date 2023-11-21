package FreezeMonster.sprite;

import FreezeMonster.Commons;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;
    private int height;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {

        var playerImg = "src/images/woody.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);
    }

    public void act() {

        x += dx;

        y += dy;

        if (x <= 2) {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {

            x = Commons.BOARD_WIDTH - 2 * width;
        }

        if (y <= 2) {

            y = 2;
        }

        if (y >= Commons.BOARD_HEIGHT - 2 * height) {

            y = Commons.BOARD_HEIGHT - 2 * height;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
        }

        if (key == KeyEvent.VK_DOWN) {

            dy = -2;
        }

        if (key == KeyEvent.VK_UP) {

            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {

            dy = 0;
        }

        if (key == KeyEvent.VK_UP) {

            dy = 0;
        }
    }
}
