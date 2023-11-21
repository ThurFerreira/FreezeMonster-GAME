package FreezeMonster.sprite;

import FreezeMonster.Commons;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Monster extends Sprite {
    public Commons commons = Commons.getInstance();
    private int monsterType;

    private Gosma gosma;

    public Monster(int monsterType) {
        this.monsterType = monsterType;
        initAlien();
    }

    private void initAlien() {
        Random random = new Random();
        this.x = random.nextInt(commons.BOARD_WIDTH);
        this.y = random.nextInt(commons.BOARD_HEIGHT);

        gosma = new Gosma(x, y);

        String url = "src/images/monster" + monsterType + ".png";
        //!!!! resize image, fazer resize depois manualmente
        var ii = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(commons.MONSTER_WIDTH, commons.MONSTER_HEIGHT, Image.SCALE_SMOOTH));

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
