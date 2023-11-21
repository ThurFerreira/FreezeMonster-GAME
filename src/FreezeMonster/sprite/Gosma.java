package FreezeMonster.sprite;

import javax.swing.*;

public class Gosma extends Sprite {

    private boolean destroyed;

    public Gosma(int x, int y) {
        initGosma(x, y);
    }

    private void initGosma(int x, int y) {

        setDestroyed(true);

        this.x = x;
        this.y = y;

        var gosmaImg = "src/images/gosma.png";
        var ii = new ImageIcon(gosmaImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
