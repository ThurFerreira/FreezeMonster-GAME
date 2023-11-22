package FreezeMonster.sprite;

import FreezeMonster.Settings;
import framework.Sprite;

import javax.swing.ImageIcon;
import java.awt.*;

public class Ray extends Sprite {

    public Ray() {
    }

    public Ray(int x, int y, int dx, int dy) {
        this.dx = dx * 2;
        this.dy = dy * 2;

        initShot(x, y);
    }

    private void initShot(int x, int y) {
        var set = Settings.getInstance();

        var shotImg = "src/images/ray.png";
        var ii =  new ImageIcon(new ImageIcon(shotImg).getImage().getScaledInstance(set.RAY_SIZE, set.RAY_SIZE, Image.SCALE_SMOOTH));
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
