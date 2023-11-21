package FreezeMonster.sprite;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Monster extends Sprite {

    private Gosma gosma;

    public Monster(int x, int y, String url) {

        initMonster(x, y, url);
    }

    private void initMonster(int x, int y, String url) {

        this.x = x;
        this.y = y;

        gosma = new Gosma(x, y);

        //!!!! resize image, fazer resize depois manualmente
        var monsterImg = url;
        var ii = new ImageIcon(new ImageIcon(monsterImg).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        setImage(ii.getImage());
    }

    public void act(int dx, int dy) {

        this.x += dx;
        this.y += dy;

    }

    public Gosma getGosma() {

        return gosma;
    }

}
