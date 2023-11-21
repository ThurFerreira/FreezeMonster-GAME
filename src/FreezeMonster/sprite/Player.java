package FreezeMonster.sprite;

import FreezeMonster.Commons;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    public Commons commons = Commons.getInstance();

    private int width;
    private int height;
    private int xLastMove = 2;
    private int yLastMove = 2;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {
        var playerImg = "src/images/woody.png";
        var ii = new ImageIcon(new ImageIcon(playerImg).getImage().getScaledInstance(commons.PLAYER_WIDTH, commons.PLAYER_HEIGHT, Image.SCALE_SMOOTH));

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

        if (x >= commons.BOARD_WIDTH - 2 * width) {

            x = commons.BOARD_WIDTH - 2 * width;
        }

        if (y <= 2) {

            y = 2;
        }

        if (y >= commons.BOARD_HEIGHT - 2 * height) {

            y = commons.BOARD_HEIGHT - 2 * height;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -2;
            xLastMove = dx;
            yLastMove = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 2;
            xLastMove = dx;
            yLastMove = 0;
        }

        if (key == KeyEvent.VK_UP) {

            dy = -2;
            yLastMove = dy;
            xLastMove = 0;
        }

        if (key == KeyEvent.VK_DOWN) {

            dy = 2;
            yLastMove = dy;
            xLastMove = 0;
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

    public int getxLastMove(){
        return xLastMove;
    }

    public int getyLastMove(){
        return yLastMove;
    }
}
