package FreezeMonster.sprite;

import javax.swing.ImageIcon;

public class Ray extends Sprite {

    public Ray() {
    }

    public Ray(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        var shotImg = "src/images/shot.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
