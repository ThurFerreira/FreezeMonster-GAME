package FreezeMonster.sprite;

import framework.Sprite;

import javax.swing.ImageIcon;
import java.awt.*;

public class Ray extends Sprite {

    public Ray() {
    }

    public Ray(int x, int y, int dx, int dy) {
        this.dx = dx;
        this.dy = dy;

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        var shotImg = "src/images/ray.png";
        var ii =  new ImageIcon(new ImageIcon(shotImg).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        setImage(ii.getImage());

        int SPACE = 6;
        setX(x + SPACE);
        setY(y - SPACE);
    }

    public void move(){
        x += dx;
        y += dy;
    }
}
