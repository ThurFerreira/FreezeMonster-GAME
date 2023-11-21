package FreezeMonster.sprite;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Gosma extends Sprite {

    private boolean destroyed;

    public Gosma(int x, int y) {
        initGosma(x, y);
    }

    private void initGosma(int x, int y) {

        setDestroyed(true);

        this.x = x;
        this.y = y;
        dx = 0;
        dy = 0;

        var gosmaImg = "src/images/gosma.png";
        var ii =  new ImageIcon(new ImageIcon(gosmaImg).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        setImage(ii.getImage());
    }

    public void setDirection() {
        var generator = new Random();
        do {
            dx = generator.nextInt(3) - 1; //valores: -1, 0, 1
            dy = generator.nextInt(3) - 1; //valores: -1, 0, 1
        } while (dx == 0 && dy == 0);
    }

    public void mover() {
        // Movimenta a gosma na direcao atual com a velocidade definida
        x += dx;
        y += dy;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;

        if(!destroyed){
            this.setDirection();
        }
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
